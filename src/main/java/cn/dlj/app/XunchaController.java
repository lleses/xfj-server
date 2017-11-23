package cn.dlj.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.Unit;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
import cn.dlj.entity.XunchaRd;
import cn.dlj.service.DepUnitService;
import cn.dlj.service.UnitService;
import cn.dlj.service.XunchaService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("dep/xc")
@Controller
public class XunchaController {

	@Autowired
	private DepUnitService depUnitService;
	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private UnitService unitService;

	/** 巡查列表 */
	@RequestMapping("list")
	@ResponseBody
	public String unitList(HttpServletRequest request, int currentPage, String unitName) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		if (unitName != null && !"".equals(unitName)) {
			paging.add("unitName", "%" + unitName + "%");
		}
		List<Xuncha> pagingBuilding = depUnitService.getMyPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;
	}

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Xuncha xuncha = xunchaService.getById(id);
		if (xuncha != null) {
			Unit unit = unitService.getById(xuncha.getUnitId());
			List<XunchaImg> list = xunchaService.getImgs(xuncha.getId());
			String imgs = null;
			for (XunchaImg xunchaImg : list) {
				imgs += "," + xunchaImg.getPicName();
			}
			if (imgs != null) {
				imgs = imgs.substring(1);
				xuncha.setImg64(imgs);
			}
			map.put("unit", unit);
			map.put("xuncha", xuncha);
		}
		String json = StringUtils.json(map);
		return json;
	}

	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request, Xuncha xuncha) {
		xuncha.setAddTime(new Date());
		xuncha.setOxcTime(new Date());
		xuncha.setXcTime(new Date());
		xuncha.setModTime(new Date());
		Integer id = ParamUtils.getInt(request, "id");
		Integer starLevel = ParamUtils.getInt(request, "starLevel");
		String img64Str = ParamUtils.getStr(request, "img64Str");

		Xuncha xc = xunchaService.getById(id);
		xc.setXcTime(new Date());
		xc.setModTime(new Date());
		xc.setXctype(xuncha.getXctype());
		xc.setUnitId(xuncha.getUnitId());
		xc.setUnitName(xuncha.getUnitName());
		xc.setDepartId(xuncha.getDepartId());
		xc.setDepartName(xuncha.getDepartName());
		xc.setTownId(xuncha.getTownId());
		xc.setTownName(xuncha.getTownName());
		xc.setXcPerson(xuncha.getXcPerson());
		xc.setEtPerson(xuncha.getEtPerson());
		xc.setXcItem1(xuncha.getXcItem1());
		xc.setXcItem2(xuncha.getXcItem2());
		xc.setXcItem3(xuncha.getXcItem3());
		xc.setXcItem4(xuncha.getXcItem4());
		xc.setXcItem5(xuncha.getXcItem5());
		xc.setXcItem6(xuncha.getXcItem6());
		xc.setXcItem7(xuncha.getXcItem7());
		xc.setXcItem8(xuncha.getXcItem8());
		xc.setXcItem9(xuncha.getXcItem9());
		xc.setXcItem10(xuncha.getXcItem10());
		xc.setXcItem11(xuncha.getXcItem11());
		xc.setMeno(xuncha.getMeno());
		xc.setFlag(xuncha.getFlag());
		xc.setDoorNum(xuncha.getDoorNum());
		xc.setDoorTime1(xuncha.getDoorTime1());
		xc.setDoorTime2(xuncha.getDoorTime2());
		xc.setPxquantity(xuncha.getPxquantity());
		xc.setTrainingA(xuncha.getTrainingA());
		xc.setLiveThree(xuncha.getLiveThree());
		xunchaService.updateXc(xc);
		xuncha = xc;
		if ("30".equals(xuncha.getFlag())) {
			xuncha.setFlag("13");
			// 历史记录
			addFlag(xuncha, xuncha.getXcPerson());
			// 巡查流转情况记录表
			addRd(xuncha, xuncha.getXcPerson(), null);
			unitService.updateIsxc(starLevel, xuncha.getUnitId());
		} else if ("1".equals(xuncha.getFlag())) {
			unitService.updateGm(xuncha.getUnitId(), "2");
		} else {
			unitService.updateGm(xuncha.getUnitId(), "1");
		}
		String imgIds = "";
		if (xuncha.getId() == null || img64Str == null || "".equals(img64Str)) {
			return "1";
		}
		String[] imgNames = img64Str.split(",");
		xunchaService.delXunchaImg(xuncha.getId());
		for (String imgName : imgNames) {
			XunchaImg xunchaImg = new XunchaImg();
			xunchaImg.setXunchaId(xuncha.getId());// 巡查ID
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
		xunchaService.updateRdImgs(xuncha.getId(), imgIds);
		return "1";
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
		map.put("flag", "1".equals(appXuncha.getFlag()) ? "1" : "13");
		map.put("liveThree", appXuncha.getLiveThree());
		xunchaService.addFlag(map);
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
		map.put("rectDate", new Date());
		map.put("meno", appXuncha.getMeno());
		map.put("flag", "1".equals(appXuncha.getFlag()) ? "1" : "13");
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
	}
	//-------------------------------------------------

	/** 组装请假信息数据 **/
	private String unitJson(List<Xuncha> list) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Xuncha x : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", x.getId());
			map.put("unitId", x.getUnitId());
			map.put("unitName", x.getUnitName());
			map.put("xcTime", x.getXcTime());
			li.add(map);
		}
		return StringUtils.json(li);
	}

	/** 获取巡查RD明细 */
	@RequestMapping("get_xuncha_rd")
	@ResponseBody
	public String getXunchaRd(HttpServletRequest request) {
		String id = ParamUtils.getStr(request, "id");
		XunchaRd rd = depUnitService.getXunchaRd(id);
		String imgIds = rd.getImgIds();
		if (null != imgIds && !"".equals(imgIds)) {
			String[] split = imgIds.split(",");
			if (split.length > 0) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < split.length; i++) {
					String img = depUnitService.getImg(split[i]);
					sb.append(img).append(",");
				}
				String imgs = sb.toString();
				imgs.substring(0, imgs.length() - 1);
				rd.setImgUrls(imgs);
			}
		}
		String json = StringUtils.json(rd);
		return json;
	}

	/** 获取巡查RD明细 */
	@RequestMapping("save_rd")
	@ResponseBody
	public void saveRd(HttpServletRequest request) {
		//TODO 监管表要补上
		String starLevel = ParamUtils.getStr(request, "starLevel");//安全状态等级
		String rdType = ParamUtils.getStr(request, "rdType");//0:暂存 1:提交

		XunchaRd rd = new XunchaRd();
		if ("1".equals(rdType)) {
			rd.setFlag(ParamUtils.getStr(request, "flag"));
		}
		//TODO 巡查时间要补上
		rd.setXcTime(ParamUtils.getStr(request, "xcTime"));
		rd.setEtPerson(ParamUtils.getStr(request, "etPerson"));
		rd.setMeno(ParamUtils.getStr(request, "meno"));
		rd.setLiveThree(ParamUtils.getStr(request, "liveThree"));
		rd.setId(ParamUtils.getStr(request, "rdId"));
		rd.setXunchaId(ParamUtils.getStr(request, "xunchaId"));
		rd.setXcItem1(ParamUtils.getStr(request, "xcItem1"));
		rd.setXcItem2(ParamUtils.getStr(request, "xcItem2"));
		rd.setXcItem3(ParamUtils.getStr(request, "xcItem3"));
		rd.setXcItem4(ParamUtils.getStr(request, "xcItem4"));
		rd.setXcItem5(ParamUtils.getStr(request, "xcItem5"));
		rd.setXcItem6(ParamUtils.getStr(request, "xcItem6"));
		rd.setXcItem7(ParamUtils.getStr(request, "xcItem7"));
		rd.setXcItem8(ParamUtils.getStr(request, "xcItem8"));
		rd.setXcItem9(ParamUtils.getStr(request, "xcItem9"));
		rd.setXcItem10(ParamUtils.getStr(request, "xcItem10"));
		rd.setXcItem11(ParamUtils.getStr(request, "xcItem11"));
		depUnitService.updateXuncha(rd);
		return;
	}

}
