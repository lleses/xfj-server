package cn.dlj.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Department;
import cn.dlj.app.entity.Unit;
import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.app.service.DepartmentService;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.XunchaImgService;
import cn.dlj.app.service.XunchaService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/xuncha")
@Controller
public class XunchaController {

	private static final Logger log = LoggerFactory.getLogger(XunchaController.class);
	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private XunchaImgService xunchaImgService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Integer townId = ParamUtils.getInt(request, "townId");
		Unit unit = unitService.getById(id);
		Xuncha xuncha = xunchaService.find(id);
		List<Department> departments = departmentService.getByTownId(townId);
		if (xuncha != null) {
			List<XunchaImg> list = xunchaImgService.getImgs(xuncha.getId());
			String imgs = "";
			for (XunchaImg xunchaImg : list) {
				imgs += "," + xunchaImg.getPicName();
			}
			if (!"".equals(imgs)) {
				imgs = imgs.substring(1);
				xuncha.setImg64(imgs);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unit", unit);
		map.put("xuncha", xuncha);
		map.put("departments", departments);
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

		if (id == null) {
			xunchaService.add(xuncha);
		} else {
			Xuncha xc = xunchaService.getById(id);
			xc.setAddTime(new Date());
			xc.setOxcTime(new Date());
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
		}
		if ("30".equals(xuncha.getFlag())) {
			// 历史记录
			addFlag(xuncha, xuncha.getXcPerson());
			// 巡查流转情况记录表
			addRd(xuncha, xuncha.getXcPerson(), null);
			unitService.updateStatus(xuncha.getUnitId(), "1", starLevel, null, null);
		} else if ("1".equals(xuncha.getFlag())) {
			unitService.updateStatus(xuncha.getUnitId(), "0", 1, "0", "0");
		} else {// 关门
			unitService.updateStatus(xuncha.getUnitId(), null, null, null, "1");
		}
		String imgIds = "";
		if (xuncha.getId() == null || img64Str == null || "".equals(img64Str)) {
			return "1";
		}
		String[] imgNames = img64Str.split(",");
		xunchaImgService.delXunchaImg(xuncha.getId());
		for (String imgName : imgNames) {
			XunchaImg xunchaImg = new XunchaImg();
			xunchaImg.setXunchaId(xuncha.getId());// 巡查ID
			xunchaImg.setPicName(imgName);// 图片名称
			xunchaImg.setIntro(null);// 说明
			xunchaImg.setLongitude(null);// 经度
			xunchaImg.setLatitude(null);// 纬度
			xunchaImg.setFlag("2");// 1电脑上传2手机上传
			xunchaImg.setAddTime(new Date());// 添加时间
			Integer xunchaImgId = xunchaImgService.add(xunchaImg);
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

	@RequestMapping("updateMeno")
	@ResponseBody
	public String updateMeno(HttpServletRequest request) {
		String meno = ParamUtils.getStr(request, "meno");
		Integer id = ParamUtils.getInt(request, "id");
		Xuncha xuncha = xunchaService.getById(id);
		String meno2 = xuncha.getMeno();
		meno = meno2 + meno;
		xunchaService.updateMeno(meno, id);
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
		map.put("flag", "1".equals(appXuncha.getFlag()) ? "1" : "6");
		map.put("liveThree", appXuncha.getLiveThree());
		xunchaService.addFlag(map);
		log.error("在线巡查历史新增成功,xunchaId:" + appXuncha.getId() + ",name:" + appXuncha.getUnitId() + ",账号ID:"
				+ appXuncha.getUserId());
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
		log.error("在线巡查流转新增成功,xunchaId:" + appXuncha.getId() + ",name:" + appXuncha.getUnitId() + ",账号ID:"
				+ appXuncha.getUserId());
	}

}
