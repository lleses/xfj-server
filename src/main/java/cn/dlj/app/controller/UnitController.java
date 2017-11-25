package cn.dlj.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Unit;
import cn.dlj.app.service.UnitService;
import cn.dlj.utils.ChineseToSpell;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/unit")
@Controller
public class UnitController {

	@Autowired
	private UnitService unitService;

	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request, int currentPage, String name, Integer townId) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("townId", townId);
		Integer userId = ParamUtils.getInt(request, "userId");
		if (userId != null) {
			paging.add("userId", userId);
		}
		if (name != null && !"".equals(name)) {
			paging.add("unitName", "%" + name + "%");
			paging.add("address", "%" + name + "%");
		}
		////默认显示草稿(0:不显示草稿 1:显示草稿)
		String showCg = ParamUtils.getStr(request, "showCg");
		if (!"1".equals(showCg)) {
			paging.add("showCg", showCg);
		}
		List<Unit> pagingBuilding = unitService.getPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;

	}

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Unit unit = unitService.getById(id);
		unit = unitService.getBuilds(id, unit);
		String json = StringUtils.json(unit);
		return json;
	}

	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request, Unit unit, String img64Str, String buildIds) throws UnsupportedEncodingException {
		Unit un = unitService.get(unit.getName());
		if (un == null) {
			unit.setAddTime(new Date());
			unit.setModTime(new Date());
			unit.setBimg(img64Str);
			unit = code(unit);
			if (buildIds != null && !"".equals(buildIds)) {
				Set<Integer> buildingSet = new HashSet<>();
				String[] split = buildIds.split(",");
				for (String builId : split) {
					buildingSet.add(Integer.valueOf(builId));
				}
				unit.setBuildingSet(buildingSet);
			}
			unitService.add(unit);
		}
		return "1";
	}

	@RequestMapping("edit")
	@ResponseBody
	public String edit(HttpServletRequest request, Unit unit, String img64Str, String buildIds) {
		Unit un = unitService.getById(unit.getId());
		if (un != null) {
			un.setLicense(unit.getLicense());
			un.setAddress(unit.getAddress());
			un.setType(unit.getType());
			un.setSafedLinkman(unit.getSafedLinkman());
			un.setSafedTelphone(unit.getSafedTelphone());
			un.setManageLinkman(unit.getManageLinkman());
			un.setManageTelphone(unit.getManageTelphone());
			un.setMeno(unit.getMeno());
			un.setArea(unit.getArea());
			un.setBuildingsLayer(unit.getBuildingsLayer());
			un.setKeyUnit(unit.getKeyUnit());
			un.setIscg(unit.getIscg());
			un.setModTime(new Date());
			un.setBimg(img64Str);
			if (buildIds != null && !"".equals(buildIds)) {
				Set<Integer> buildingSet = new HashSet<>();
				String[] split = buildIds.split(",");
				for (String builId : split) {
					buildingSet.add(Integer.valueOf(builId));
				}
				un.setBuildingSet(buildingSet);
			}
			unitService.update(un);
		}
		return "1";
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

}
