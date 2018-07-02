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

import cn.dlj.app.entity.XunchaArchive;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.XunchaArchiveService;
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
	private UnitService unitService;

	//	@RequestMapping("test")
	//	@ResponseBody
	//	public String test(HttpServletRequest request) {
	//		Integer maxNum = xunchaArchiveService.getMaxNum(16);
	//		if (maxNum == null) {
	//			maxNum = 0;
	//		}
	//		maxNum++;
	//		xunchaArchiveService.add(maxNum, new Date());
	//
	//		//xunchaService.delByTownId(16);
	//		//unitService.archiveInit(16);
	//		return "成功";
	//	}

	/** 归档 */
	@RequestMapping("handle")
	@ResponseBody
	public String handle(HttpServletRequest request) {
		Integer townId = ParamUtils.getInt(request, "townId");
		Integer maxNum = xunchaArchiveService.getMaxNum(townId);
		if (maxNum == null) {
			maxNum = 0;
		}
		maxNum++;
		xunchaArchiveService.add(maxNum, new Date());
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xunchaArchive", xunchaArchive);
		String json = StringUtils.json(map);
		return json;
	}

}
