package cn.dlj.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Unit;
import cn.dlj.app.entity.XunchaArchive;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.XunchaArchiveService;
import cn.dlj.app.service.XunchaImgService;
import cn.dlj.app.service.XunchaService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/xunchaArchive")
@Controller
public class XunchaArchiveController {

	@Autowired
	private XunchaArchiveService xunchaArchiveService;
	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private XunchaImgService xunchaImgService;
	@Autowired
	private UnitService unitService;

	/** 归档 */
	@RequestMapping("handle")
	@ResponseBody
	public String handle(HttpServletRequest request) {
		Integer townId = ParamUtils.getInt(request, "townId");
		Integer userId = ParamUtils.getInt(request, "userId");
		Integer maxNum = xunchaArchiveService.getMaxNum(townId);
		if (maxNum == null) {
			maxNum = 0;
		}
		maxNum++;
		xunchaArchiveService.add(maxNum, new Date(), userId);
		xunchaService.delByTownId(townId);
		unitService.archiveInit(townId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", "1");
		String json = StringUtils.json(map);
		return json;
	}

	@RequestMapping("getByUnitId")
	@ResponseBody
	public String getByUnitId(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		List<XunchaArchive> list = xunchaArchiveService.getByUnitId(unitId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", list);
		String json = StringUtils.json(map);
		return json;
	}

	@RequestMapping("getById")
	@ResponseBody
	public String getById(HttpServletRequest request) {
		Integer id = ParamUtils.getInt(request, "id");
		XunchaArchive xunchaArchive = xunchaArchiveService.getById(id);
		if (xunchaArchive != null) {
			List<XunchaImg> list = xunchaImgService.getImgs(id);
			String imgs = "";
			for (XunchaImg xunchaImg : list) {
				imgs += "," + xunchaImg.getPicName();
			}
			if (!"".equals(imgs)) {
				imgs = imgs.substring(1);
				xunchaArchive.setImg64(imgs);
			}
		}

		Unit unit = unitService.getById(xunchaArchive.getUnitId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xunchaArchive", xunchaArchive);
		map.put("unit", unit);
		String json = StringUtils.json(map);
		return json;
	}

}
