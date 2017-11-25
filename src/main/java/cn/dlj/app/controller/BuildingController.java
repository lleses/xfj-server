package cn.dlj.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Building;
import cn.dlj.app.service.BuildingService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("app/building")
@Controller
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request, int currentPage, String name, Integer townId) {
		Integer userId = ParamUtils.getInt(request, "userId");
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("townId", townId);
		if (userId != null) {
			paging.add("userId", userId);
		}
		if (name != null && !"".equals(name)) {
			paging.add("buildName", "%" + name + "%");
			paging.add("obligation", "%" + name + "%");
		}
		List<Building> pagingBuilding = buildingService.getPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;

	}

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Building building = buildingService.getById(id);
		String json = StringUtils.json(building);
		return json;

	}

	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request, Building building, String img64Str) {
		Building build = buildingService.get(building.getName());
		if (build == null) {
			building.setModTime(new Date());
			building.setAddTime(new Date());
			building.setWorkTime(new Date());
			building.setBirthdate(new Date());
			building.setBimg(img64Str);
			buildingService.add(building);
		}
		return "1";
	}

	@RequestMapping("edit")
	@ResponseBody
	public String edit(HttpServletRequest request, Building building, String img64Str) {
		Building build = buildingService.getById(building.getId());
		if (build != null) {
			build.setObligation(building.getObligation());
			build.setLxname(building.getLxname());
			build.setTelphone(building.getTelphone());
			build.setIsControl(building.getIsControl());
			build.setClassification(building.getClassification());
			build.setAreaa(building.getAreaa());
			build.setAcreage(building.getAcreage());
			build.setJzheight(building.getJzheight());
			build.setGround(building.getGround());
			build.setUnderground(building.getUnderground());
			build.setEvacuationA(building.getEvacuationA());
			build.setEvacuationB(building.getEvacuationB());
			build.setEvacuationC(building.getEvacuationC());
			build.setStructure(building.getStructure());
			build.setRefractory(building.getRefractory());
			build.setFacilityA(building.getFacilityA());
			build.setMessage(building.getMessage());
			build.setModTime(new Date());
			build.setWorkTime(new Date());
			build.setBimg(img64Str);
			buildingService.update(build);
		}
		return "1";
	}

}
