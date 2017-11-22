package cn.dlj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.dao.UnitDao;
import cn.dlj.entity.Unit;
import cn.dlj.entity.UnitStr;
import cn.dlj.utils.PagingMySql;

/**
 * 建筑物
 * 
 */
@Service
@Transactional(readOnly = true)
public class UnitService {

	@Autowired
	private UnitDao dao;

	/**
	 * 新增
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public Integer add(Unit unit) {
		dao.add(unit);
		Set<Integer> buildIds = unit.getBuildingSet();
		if (unit.getId() != null) {
			if (null != buildIds && !buildIds.isEmpty()) {
				dao.addUnitBuilding(unit);// 更新监管单位归属建筑物信息
			}
			return unit.getId();
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
	public void updateYzm(String yzm, Integer id) {
		dao.updateYzm(yzm, id);
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void update(Unit unit) {
		if (unit.getTownId() != null && unit.getDepartmentId() != null && unit.getName() != null) {
			dao.delUnitBuilding(unit.getId());// 更新监管单位归属建筑物信息
			Set<Integer> buildIds = unit.getBuildingSet();
			if (null != buildIds && !buildIds.isEmpty()) {
				dao.addUnitBuilding(unit);
			}
			dao.update(unit);
		}
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void update2(Unit unit) {
		if (unit.getTownId() != null && unit.getDepartmentId() != null && unit.getName() != null) {
			dao.delUnitBuilding(unit.getId());// 更新监管单位归属建筑物信息
			Set<Integer> buildIds = unit.getBuildingSet();
			if (null != buildIds && !buildIds.isEmpty()) {
				dao.addUnitBuilding(unit);
			}
			dao.update2(unit);
		}
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void updateGm(Integer id, String isgm) {
		dao.updateGm(id, isgm);
	}

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	@Transactional
	public void updateIsxc(Integer starLevel, Integer id) {
		dao.updateIsxc(starLevel, id);
	}

	/**
	 * 修改图片
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
	 * 检测监管单位是否存在
	 * 
	 * @param name
	 *            监管单位名称
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	public Unit get(String name) {
		if (name != null) {
			return dao.get(name);
		}
		return null;
	}

	/**
	 * 根据id获取监管单位(巡查单位)
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public Unit getByYzm(String yzm) {
		if (yzm != null) {
			List<Unit> list = dao.getByYzm(yzm);
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * 根据id获取监管单位(巡查单位)
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public Unit getById(Integer id) {
		if (id != null) {
			return dao.getById(id);
		}
		return null;
	}

	/**
	 * 获取监管单位(巡查单位)离线数据
	 * 
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	public List<UnitStr> getList(Integer townId, Integer departmentId) {
		List<UnitStr> list = new ArrayList<UnitStr>();
		if (townId != null && departmentId != null) {
			list = dao.getList(townId, departmentId);
		}
		return list;
	}

	/**
	 * 获取监管单位(巡查单位)最大ID值
	 * 
	 * @return
	 */
	public Integer getMaxId() {
		return dao.getMaxId();
	}

	//------------------------------------------------wx------------------------------------------------

	/**
	 * 获取监管单位(巡查单位)离线数据
	 * 
	 * @param name
	 *            名称
	 * @return
	 */
	public Unit findById(Integer id) {
		if (id != null) {
			Unit unit = dao.findById(id);
			return unit;
		}
		return null;
	}

	public List<Unit> getPaging(PagingMySql paging) {
		return dao.getPaging(paging);
	}

	public List<Unit> getMyPaging(PagingMySql paging) {
		return dao.getMyPaging(paging);
	}

	public Unit getBuilds(Integer uid, Unit unit) {
		String buildNames = "";
		String buildIds = "";
		List<Map<String, Object>> builds = dao.getBuilds(uid);
		for (Map<String, Object> buil : builds) {
			buildIds += "," + buil.get("id");
			buildNames += "," + buil.get("name");
		}
		if (!"".equals(buildIds)) {
			buildIds = buildIds.substring(1);
			buildNames = buildNames.substring(1);
		}
		unit.setAppBuildingId(buildIds);
		unit.setBuildingName(buildNames);
		return unit;
	}

}