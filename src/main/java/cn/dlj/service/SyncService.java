package cn.dlj.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.entity.AppImgs;
import cn.dlj.entity.AppSysnData;
import cn.dlj.entity.AppSysnImgs;
import cn.dlj.entity.Building;
import cn.dlj.entity.Unit;
import cn.dlj.entity.UnitStr;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
import cn.dlj.utils.ChineseToSpell;
import cn.dlj.utils.Config;
import cn.dlj.utils.IdUtils;

/**
 * 同步
 * 
 */
@Service
@Transactional(readOnly = true)
public class SyncService {

	@Autowired
	private BuildingService buildingService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private XunchaService xunchaService;

	/** 建筑物图片保存地址 */
	private static final String BUILD_IMG_UPLOAD_PATH = Config.get("build.img.upload.path");
	/** 巡查图片保存地址 */
	private static final String XUNCHA_IMG_UPLOAD_PATH = Config.get("xuncha.img.upload.path");

	private static final Logger log = LoggerFactory.getLogger(SyncService.class);

	/**
	 * 处理同步数据
	 * 
	 * @param appUpdate
	 *            app更新信息
	 * @param succList
	 *            返回处理成功的列表
	 * @param errList
	 *            返回异常列表
	 * @return 异常列表
	 */
	@Transactional
	public List<Map<String, Object>> handSysnData(AppSysnData appUpdate, Map<String, Object> succList, List<Map<String, Object>> errList) {
		List<Building> bList = appUpdate.getBuilding();// 建筑物
		List<Unit> uList = appUpdate.getUnit();// 监管单位(巡查单位)
		// 处理建筑物
		handBuild(bList, succList, errList);
		// 处理监管
		handUnit(uList, bList, succList, errList);
		// 处理巡查登记
		handXuncha(appUpdate.getXuncha(), uList, succList, errList, appUpdate);
		return errList;
	}

	/**
	 * 处理建筑物
	 * 
	 * @param buildingList
	 *            手机上传的建筑物
	 * @param succList
	 *            返回处理成功的列表
	 * @param errList
	 *            返回异常列表
	 */
	private void handBuild(List<Building> bList, Map<String, Object> succList, List<Map<String, Object>> errList) {
		List<Map<String, Object>> temData = new ArrayList<Map<String, Object>>();
		if (bList.isEmpty()) {
			succList.put("building", temData);
			return;
		}
		for (Building appBuild : bList) {
			if (appBuild.getDr() != null && "1".equals(appBuild.getDr()) && appBuild.getId() != null) {// 删除
				buildingService.del(appBuild.getId());
			} else {
				// 本地新增还是历史记录
				Building building = buildingService.get(appBuild.getName());
				if (appBuild.getId() == null || "".equals(appBuild.getId())) {// 手机新增
					if (building == null) {// 新增
						appBuild.setAddTime(new Date());
						appBuild.setModTime(new Date());
						Integer id = buildingService.add(appBuild);
						log.error("建筑物新增成功,id:" + appBuild.getId() + ",name:" + appBuild.getName() + ",账号ID:" + appBuild.getUserId());
						appBuild.setId(id);
						temData = handSucc(temData, id, appBuild.getAppBuildingId());
					} else {// 异常
						//判断是否重复提交
						if (appBuild.getAppBuildingId().equals(building.getAppBuildingId())) {
							temData = handSucc(temData, building.getId(), appBuild.getAppBuildingId());
						} else {
							appBuild.setErr("-1");
							errList = handErr(errList, "【" + appBuild.getName() + "】app新增的建筑物名称和服务器的重复", appBuild.getAppBuildingId(), "t_building", "建筑");
							log.error("【" + appBuild.getName() + "】app新增的建筑物名称和服务器的重复");
						}
					}
				} else {// 历史
					if (building == null) {// 异常
						appBuild.setErr("-1");
						errList = handErr(errList, "【" + appBuild.getName() + "】服务器建筑名称已被删除", appBuild.getAppBuildingId(), "t_building", "建筑");
						log.error("【" + appBuild.getName() + "】服务器建筑名称已被删除");
					} else {
						appBuild.setId(building.getId());
						appBuild.setModTime(new Date());
						buildingService.update(appBuild);
						temData = handSucc(temData, building.getId(), appBuild.getAppBuildingId());
						log.error("建筑物更新成功,id:" + appBuild.getId() + ",name:" + appBuild.getName() + ",账号ID:" + appBuild.getUserId());
					}
				}
			}
		}
		succList.put("building", temData);
	}

	/**
	 * 处理监管单位
	 * 
	 * @param uList
	 *            app上传的监管单位
	 * @param bList
	 *            app上传的建筑物
	 * @param errList
	 *            错误信息集合
	 * @return 错误信息集合
	 */
	private void handUnit(List<Unit> uList, List<Building> bList, Map<String, Object> succList, List<Map<String, Object>> errList) {
		List<Map<String, Object>> temData = new ArrayList<Map<String, Object>>();
		if (uList.isEmpty()) {
			succList.put("unit", temData);
			return;
		}
		for (Unit appUnit : uList) {
			// ----------------------------建筑物相关--------------------------------
			String buildingNames = appUnit.getBuildingName();
			Set<Integer> buildingIds = new HashSet<Integer>();// 监管单位归属建筑物ID集合
			if (buildingNames != null && !"".equals(buildingNames) && !"null".equalsIgnoreCase(buildingNames)) {
				String[] build_arr = buildingNames.split(",", -1);
				for (String buildingName : build_arr) {
					Building building = buildingService.get(buildingName);
					if (building != null) {
						buildingIds.add(building.getId());
					}
				}
			}
			appUnit.setBuildingSet(buildingIds);
			// ------------------------------监管单位相关------------------------------
			Unit unit = unitService.get(appUnit.getName());
			if (appUnit.getId() == null) {// app新增
				if (unit == null) {// 新增
					appUnit = code(appUnit);
					appUnit.setAddTime(new Date());
					appUnit.setModTime(new Date());
					Integer id = unitService.add(appUnit);
					log.error("监管单位新增成功,id:" + appUnit.getId() + ",name:" + appUnit.getName() + ",账号ID:" + appUnit.getUserId());
					appUnit.setId(id);
					temData = handSucc(temData, id, appUnit.getAppUnitId());
				} else {// 异常
					//是否重复提交
					if (appUnit.getAppUnitId().equals(unit.getAppUnitId())) {
						appUnit.setId(unit.getId());
						appUnit = code(appUnit);
						appUnit.setModTime(new Date());
						unitService.update(appUnit);
						temData = handSucc(temData, appUnit.getId(), appUnit.getAppUnitId());
					} else {
						appUnit.setErr("-1");
						errList = handErr(errList, "【" + appUnit.getName() + "】app新增的监管单位名称和服务器的重复", appUnit.getAppUnitId(), "t_unit", "监管");
						log.error("【" + appUnit.getName() + "】app新增的监管单位名称和服务器的重复");
					}
				}
			} else {// 历史数据
				if (unit != null) {// 更新
					appUnit.setId(unit.getId());
					appUnit = code(appUnit);
					appUnit.setModTime(new Date());
					unitService.update(appUnit);
					temData = handSucc(temData, unit.getId(), appUnit.getAppUnitId());
					log.error("监管单位更新成功,id:" + appUnit.getId() + ",name:" + appUnit.getName() + ",账号ID:" + appUnit.getUserId());
				} else {// 异常
					appUnit.setErr("-1");
					errList = handErr(errList, "【" + appUnit.getName() + "】服务器监管单位已被删除", appUnit.getAppUnitId(), "t_unit", "监管");
					log.error("【" + appUnit.getName() + "】服务器监管单位已被删除");
				}
			}
		}
		succList.put("unit", temData);
	}

	/**
	 * 处理巡查
	 * 
	 * @param buildingList
	 *            手机上传的巡查
	 */
	private void handXuncha(List<Xuncha> xList, List<Unit> uList, Map<String, Object> succList, List<Map<String, Object>> errList, AppSysnData appSysnData) {
		List<Map<String, Object>> temData = new ArrayList<Map<String, Object>>();
		if (xList.isEmpty()) {
			succList.put("xuncha", temData);
			return;
		}
		for (Xuncha appXuncha : xList) {
			//判断是否重复提交
			Xuncha xuncha = xunchaService.get(appXuncha.getAppXunchaId(), appXuncha.getUnitName());
			if (xuncha != null && appXuncha.getAppXunchaId().equals(xuncha.getAppXunchaId())) {
				temData = handSucc(temData, appXuncha.getId(), appXuncha.getAppXunchaId());
				continue;
			}
			String unitName = appXuncha.getUnitName();
			boolean uExist = false;// 手机上传的相关监管单位是否存在
			boolean xunErr = true; // 处理巡查错误标识
			// 从app上传的记录里找
			for (Unit appUnit : uList) {
				if (appUnit.getName().equals(unitName)) {
					uExist = true;
					if (!"-1".equals(appUnit.getErr())) {
						appXuncha.setUnitId(appUnit.getId());
					} else {
						errList = handErr(errList, "【" + appXuncha.getUnitName() + "】app上传的监管单位记录有异常,巡查登记不和服务器同步", appXuncha.getAppXunchaId(), "t_xuncha", "巡查");
						log.error("巡查登记新增异常,【" + appXuncha.getUnitName() + "】app上传的监管单位记录有异常,巡查登记不和服务器同步");
						xunErr = false;
					}
				}
			}
			// 如果app上传的相关监管单位不存在，则从服务器里找
			if (xunErr && !uExist) {
				Unit unit = unitService.get(appXuncha.getUnitName());
				if (unit != null) {
					appXuncha.setUnitId(unit.getId());
				}
				if (unit == null) {// 判断监管记录是否被删除
					errList = handErr(errList, "【" + appXuncha.getUnitName() + "】服务器该监管单位记录已经不存在,巡查登记不和服务器同步", appXuncha.getAppXunchaId(), "t_xuncha", "巡查");
					log.error("巡查登记新增异常,【" + appXuncha.getUnitName() + "】服务器该监管单位记录已经不存在,巡查登记不和服务器同步");
					xunErr = false;
				} else if ("1".equals(unit.getIsxc())) {// 判断服务器监管是否在跳转中
					errList = handErr(errList, "【" + appXuncha.getUnitName() + "】服务器该监管单位记录在跳转中,巡查登记不和服务器同步", appXuncha.getAppXunchaId(), "t_xuncha", "巡查");
					log.error("巡查登记新增异常,【" + appXuncha.getUnitName() + "】服务器该监管单位记录在跳转中,巡查登记不和服务器同步");
					xunErr = false;
				}
			}
			// ----------------------------巡查登记相关--------------------------------
			if (xunErr) {
				xunchaService.add(appXuncha);
				temData = handSucc(temData, appXuncha.getId(), appXuncha.getAppXunchaId());
				log.error("巡查登记新增成功,id:" + appXuncha.getId() + ",name:" + appXuncha.getUnitId() + ",账号ID:" + appXuncha.getUserId());
				// 历史记录
				addFlag(appXuncha, appSysnData.getUserName());
				// 巡查流转情况记录表
				addRd(appXuncha, appSysnData.getUserName(), null);
			}
		}
		succList.put("xuncha", temData);
	}

	/** 生成单位编码code **/
	private Unit code(Unit appUnit) {
		String townName = appUnit.getTownName();
		townName = ChineseToSpell.getPinYinHeadChar(townName);
		if (townName != null) {
			townName = townName.substring(0, 2);
		} else {
			townName = "er";
		}
		String departmentName = appUnit.getDepartmentName();
		departmentName = ChineseToSpell.getPinYinHeadChar(departmentName);
		if (departmentName != null) {
			departmentName = departmentName.substring(0, 2);
		} else {
			departmentName = "er";
		}
		Integer maxId = unitService.getMaxId();
		String ids = "";
		if (maxId == null) {
			ids = "000001";
		} else if (maxId < 10) {
			ids = "00000" + (maxId + 1);
		} else if (maxId < 100) {
			ids = "0000" + (maxId + 1);
		} else if (maxId < 1000) {
			ids = "000" + (maxId + 1);
		} else if (maxId < 10000) {
			ids = "00" + (maxId + 1);
		} else if (maxId < 100000) {
			ids = "0" + (maxId + 1);
		} else {
			maxId = maxId + 1;
			ids = maxId.toString();
		}
		String code = townName + departmentName + ids;
		appUnit.setCode(code);
		return appUnit;
	}

	/** 巡查流转情况记录表 **/
	private void addRd(Xuncha appXuncha, String userName, String imgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xunchaId", appXuncha.getId());
		map.put("xctype", appXuncha.getXctype());
		map.put("unitId", appXuncha.getUnitId());
		map.put("unitName", appXuncha.getUnitName());
		map.put("departId", appXuncha.getDepartId());
		map.put("departName", appXuncha.getDepartName());
		map.put("townId", appXuncha.getTownId());
		map.put("townName", appXuncha.getTownName());
		map.put("xcTime", appXuncha.getXcTime());
		map.put("oxcTime", appXuncha.getOxcTime());
		map.put("xcPerson", appXuncha.getXcPerson());
		map.put("etPerson", appXuncha.getEtPerson());
		map.put("xcItem1", appXuncha.getXcItem1());
		map.put("xcItem2", appXuncha.getXcItem2());
		map.put("xcItem3", appXuncha.getXcItem3());
		map.put("xcItem4", appXuncha.getXcItem4());
		map.put("xcItem5", appXuncha.getXcItem5());
		map.put("xcItem6", appXuncha.getXcItem6());
		map.put("xcItem7", appXuncha.getXcItem7());
		map.put("xcItem8", appXuncha.getXcItem8());
		map.put("xcItem9", appXuncha.getXcItem9());
		map.put("xcItem10", appXuncha.getXcItem10());
		map.put("xcItem11", appXuncha.getXcItem11());
		map.put("xcItem12", appXuncha.getXcItem12());
		map.put("rectDate", appXuncha.getRectDate());
		map.put("meno", appXuncha.getMeno());
		map.put("flag", "1".equals(appXuncha.getFlag()) ? "1" : "6");
		map.put("imgIds", imgIds);
		map.put("addTime", new Date());
		map.put("userId", appXuncha.getUserId());
		map.put("userName", userName);
		map.put("liveThree", appXuncha.getLiveThree());

		String pxquantity = appXuncha.getPxquantity();
		if (pxquantity != null && !"".equals(pxquantity) && !"null".equals(pxquantity)) {
			map.put("crewSize", appXuncha.getPxquantity());// 培训人数
		} else {
			map.put("crewSize", null);// 培训人数
		}
		Unit unit = unitService.getById(appXuncha.getUnitId());
		if (unit != null) {
			map.put("code", unit.getCode());
			map.put("license", unit.getLicense());
			map.put("address", unit.getAddress());
			map.put("type", unit.getType());
			map.put("safedLinkman", unit.getSafedLinkman());
			map.put("safedTelphone", unit.getSafedTelphone());
			map.put("manageLinkman", unit.getManageLinkman());
			map.put("manageTelphone", unit.getManageTelphone());
			map.put("area", unit.getArea());
			map.put("buildingsLayer", unit.getBuildingsLayer());
			map.put("unitMeno", unit.getMeno());
		}
		xunchaService.addRd(map);
		log.error("巡查流转新增成功,xunchaId:" + appXuncha.getId() + ",name:" + appXuncha.getUnitId() + ",账号ID:" + appXuncha.getUserId());
	}

	/** 保存巡查历史记录 **/
	private void addFlag(Xuncha appXuncha, String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xunchaId", appXuncha.getId());
		map.put("townId", appXuncha.getTownId());
		map.put("townName", appXuncha.getTownName());
		map.put("departId", appXuncha.getDepartId());
		map.put("departName", appXuncha.getDepartName());
		map.put("userId", appXuncha.getUserId());
		map.put("userName", userName);
		map.put("addTime", new Date());
		map.put("addIp", null);
		map.put("flag", "1".equals(appXuncha.getFlag()) ? "1" : "6");
		map.put("liveThree", appXuncha.getLiveThree());
		xunchaService.addFlag(map);
		log.error("巡查历史新增成功,xunchaId:" + appXuncha.getId() + ",name:" + appXuncha.getUnitId() + ",账号ID:" + appXuncha.getUserId());
	}

	/**
	 * 处理错误信息
	 * 
	 * @param errList
	 *            错误信息集合
	 * @param msg
	 *            错误信息
	 * @param appId
	 *            app对应的ID
	 * @param appTable
	 *            app对应的表
	 * @param name
	 *            app对应的表的中文名称
	 * @return 错误信息集合
	 */
	private List<Map<String, Object>> handErr(List<Map<String, Object>> errList, String msg, String appId, String appTable, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put("appId", appId);
		map.put("appTable", appTable);
		map.put("name", name);
		errList.add(map);
		return errList;
	}

	/**
	 * 返回处理成功的数据
	 * 
	 * @param errList
	 *            返回处理成功的数据
	 * @param id
	 *            服务器ID
	 * @param appId
	 *            app对应的ID
	 *            app对应的表的中文名称
	 * @return 返回处理成功的数据
	 */
	private List<Map<String, Object>> handSucc(List<Map<String, Object>> succList, Integer id, String appId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("appId", appId);
		succList.add(map);
		return succList;
	}

	/**
	 * 返回同步后的服务器数据
	 * 
	 * @param towmId
	 *            镇区ID
	 * @param departmentId
	 *            公司ID
	 * @return
	 */
	public Map<String, Object> rsData(Integer towmId, Integer departmentId) {
		List<Building> buildings = buildingService.getList(towmId, departmentId);
		List<UnitStr> units = unitService.getList(towmId, departmentId);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("building", buildings);
		m.put("unit", units);
		return m;
	}

	// ************************************************处理提交的图片begin************************************************

	/**
	 * 处理同步的图片
	 * 
	 * @param appUpdate
	 *            app更新信息
	 * @return 异常列表
	 */
	@Transactional
	public void handSysnImgs(AppSysnImgs appSysnImgs) {
		List<AppImgs> list = appSysnImgs.getAppImgs();
		if (list == null || list.isEmpty()) {
			return;
		}
		for (AppImgs appImgs : list) {
			if ("1".equals(appImgs.getType())) {
				// 处理建筑物图片
				handBuildingImg(appImgs);
			} else if ("2".equals(appImgs.getType())) {
				// 处理监管单位图片
				handUnitImg(appImgs);
			} else if ("3".equals(appImgs.getType())) {
				// 处理巡查登记图片
				handXunchaImg(appImgs);
			}
		}
	}

	/**
	 * 处理建筑物图片
	 * 
	 * @param appImgs
	 *            app同步提交的图片
	 */
	private void handBuildingImg(AppImgs appImgs) {
		String newBimg = dealUploadBimg(appImgs, Building.BUILD_IMG_SERVER_PATH, BUILD_IMG_UPLOAD_PATH);
		Building building = buildingService.get(appImgs.getName());
		if (building != null && building.getId() != null) {
			buildingService.updateImg(building.getId(), newBimg);
		}
	}

	/**
	 * 处理监管单位图片
	 * 
	 * @param appImgs
	 *            app同步提交的图片
	 */
	private void handUnitImg(AppImgs appImgs) {
		String newBimg = dealUploadBimg(appImgs, Unit.UNIT_IMG_SERVER_PATH, BUILD_IMG_UPLOAD_PATH);
		Unit unit = unitService.get(appImgs.getName());
		if (unit != null && unit.getId() != null) {
			unitService.updateImg(unit.getId(), newBimg);
		}
	}

	/**
	 * 处理客户端上传的图片
	 * 
	 * @param appImgs
	 *            app图片(支持多个，中间用“,”隔开)
	 * @param serverImgPathType
	 *            图片映射地址路径
	 * @param imgLocalSavePath
	 *            图片本地保存路径
	 * 
	 */
	private String dealUploadBimg(AppImgs appImgs, String serverImgPathType, String imgLocalSavePath) {
		String newBimg = "";
		// 获取旧的图片名称
		String historyBimgs = appImgs.getBimg();
		if (historyBimgs == null || "".equals(historyBimgs)) {
			return newBimg;
		}
		String newHistoryBimgs = "";
		for (String hb : historyBimgs.split(",", -1)) {
			if (hb.startsWith(serverImgPathType)) {
				newHistoryBimgs += "," + hb.replaceAll(serverImgPathType, "");
			}
		}
		// 转化成图片并返回图片名称
		String bimg = "";
		if (appImgs.getImg64() != null && !"".equals(appImgs.getImg64())) {
			List<String> img64 = img64(appImgs.getImg64(), true, null, imgLocalSavePath);
			if (img64 != null && !img64.isEmpty()) {
				for (String img : img64) {
					bimg += "," + img;
				}
			}
		}
		newBimg = newHistoryBimgs + bimg;
		if (newBimg.length() > 0) {
			newBimg = newBimg.substring(1);
		}
		return newBimg;
	}

	/**
	 * 处理巡查图片
	 * 
	 * @param id
	 *            巡查id
	 * @param appXuncha
	 *            app巡查对象
	 */
	private void handXunchaImg(AppImgs appImgs) {
		Integer xunchaId = xunchaService.getByAppId(appImgs.getAppId());
		String imgIds = "";
		if (xunchaId == null || appImgs.getImg64() == null || "".equals(appImgs.getImg64())) {
			return;
		}
		List<String> imgNames = img64(appImgs.getImg64(), true, null, XUNCHA_IMG_UPLOAD_PATH);
		if (imgNames != null && !imgNames.isEmpty()) {
			for (String imgName : imgNames) {
				XunchaImg xunchaImg = new XunchaImg();
				xunchaImg.setXunchaId(xunchaId);// 巡查ID
				xunchaImg.setPicName(imgName);// 图片名称
				xunchaImg.setIntro(null);// 说明
				xunchaImg.setLongitude(null);// 经度
				xunchaImg.setLatitude(null);// 纬度
				xunchaImg.setFlag("2");// 1电脑上传2手机上传
				xunchaImg.setAddTime(new Date());// 添加时间
				Integer xunchaImgId = xunchaService.addImg(xunchaImg);
				if (xunchaImgId != null) {
					imgIds += "," + xunchaImgId;
				}
			}
			if (!"".equals(imgIds)) {
				imgIds = imgIds.substring(1);
			}
		}
		xunchaService.updateRdImgs(xunchaId, imgIds);
	}

	/**
	 * basc64转成图片文件
	 * 
	 * @param imgStr
	 *            basc64
	 * @param more
	 *            是否多张图片
	 * @param srcName
	 *            图片原名
	 * @param basePath
	 *            图片保存路径
	 * @return 图片名称
	 */
	private List<String> img64(String imgStr, boolean more, String srcName, String basePath) {
		List<String> list = new ArrayList<String>();
		imgStr = imgStr.replaceAll("data:image/jpg;base64,", "");
		imgStr = imgStr.replaceAll("data:image/jpeg;base64,", "");
		if (more) {// 多张
			String[] split = imgStr.split("#", -1);
			for (int i = 0; i < split.length; i++) {
				if (null == split[i] || "".equals(split[i])) {
					continue;
				}
				String fileName = IdUtils.id32() + ".jpg";
				String path = basePath + fileName;
				if (write(split[i], path)) {
					list.add(fileName);
				}
			}
		} else {// 一张
			String fileName = IdUtils.id32() + ".jpg";
			if (null != srcName && !"".equals(srcName)) {
				fileName = srcName;
			}
			String path = basePath + fileName;
			if (write(imgStr, path)) {
				list.add(fileName);
			}

		}
		return list;
	}

	/** 写文件 **/
	private boolean write(String img64, String outPath) {
		FileOutputStream out = null;
		boolean succ = false;
		try {
			out = new FileOutputStream(outPath);
			Base64 B64 = new Base64();
			out.write(B64.decode(img64));
			out.flush();
			succ = true;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return succ;
	}
	// ************************************************处理提交的图片end************************************************

}