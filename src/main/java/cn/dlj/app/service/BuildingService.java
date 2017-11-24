package cn.dlj.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.BuildingDao;
import cn.dlj.app.entity.Building;
import cn.dlj.utils.PagingMySql;

/**
 * 建筑物
 * 
 */
@Service
@Transactional(readOnly = true)
public class BuildingService {

	@Autowired
	private BuildingDao dao;

	@Transactional
	public Integer add(Building building) {
		dao.add(building);
		if (building != null && building.getId() != null) {
			return building.getId();
		}
		return null;
	}

	@Transactional
	public void update(Building building) {
		if (building.getTownId() != null && building.getDepartmentId() != null && building.getName() != null) {
			dao.update(building);
		}
	}

	public Building get(String name) {
		if (name != null) {
			Building building = dao.get(name);
			return building;
		}
		return null;
	}

	public Building getById(int id) {
		Building building = dao.getById(id);
		return building;
	}

	public List<Building> getPaging(PagingMySql paging) {
		return dao.getPaging(paging);
	}

}