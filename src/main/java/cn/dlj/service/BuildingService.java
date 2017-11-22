package cn.dlj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.dao.BuildingDao;
import cn.dlj.entity.Building;
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

	/**
	 * 新增
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public Integer add(Building building) {
		dao.add(building);
		if (building != null && building.getId() != null) {
			return building.getId();
		}
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void update(Building building) {
		if (building.getTownId() != null && building.getDepartmentId() != null && building.getName() != null) {
			dao.update(building);
		}
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void update2(Building building) {
		if (building.getTownId() != null && building.getDepartmentId() != null && building.getName() != null) {
			dao.update2(building);
		}
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void updateImg(Integer id, String bimg) {
		if (id != null && !"".equals(id) && bimg != null && !"".equals(bimg)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("bimg", bimg);
			dao.updateImg(map);
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            ID
	 */
	@Transactional
	public void del(Integer id) {
		if (id != null) {
			dao.del(id);
		}
	}

	/**
	 * 通过名称获取建筑物
	 * 
	 * @param name
	 *            建筑物名称
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	public Building get(String name) {
		if (name != null) {
			Building building = dao.get(name);
			return building;
		}
		return null;
	}

	/**
	 * 通过名称获取建筑物
	 * 
	 * @param name
	 *            建筑物名称
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	public Building getById(int id) {
		Building building = dao.getById(id);
		return building;
	}

	/**
	 * 获取建筑物离线数据
	 * 
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	public List<Building> getList(Integer townId, Integer departmentId) {
		List<Building> list = new ArrayList<Building>();
		if (townId != null && departmentId != null) {
			list = dao.getList(townId, departmentId);
		}
		return list;
	}

	public List<Building> getPaging(PagingMySql paging) {
		return dao.getPaging(paging);
	}

	public List<Building> getMyPaging(PagingMySql paging) {
		return dao.getMyPaging(paging);
	}

}