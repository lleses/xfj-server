package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.UnitDao;
import cn.dlj.app.entity.Unit;
import cn.dlj.utils.PagingMySql;

@Service
@Transactional(readOnly = true)
public class UnitService {

	@Autowired
	private UnitDao dao;

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

	public Unit get(String name) {
		if (name != null) {
			return dao.get(name);
		}
		return null;
	}

	public Unit getById(Integer id) {
		if (id != null) {
			return dao.getById(id);
		}
		return null;
	}

	public Integer getMaxId() {
		return dao.getMaxId();
	}

	public List<Unit> getPaging(PagingMySql paging) {
		return dao.getPaging(paging);
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

	@Transactional
	public void updateStatus(Integer id, String isxc, Integer starLevel, String iscg, String isgm) {
		dao.updateStatus(id, isxc, starLevel, iscg, isgm);
	}

	public Unit findById(Integer id) {
		if (id != null) {
			Unit unit = dao.findById(id);
			return unit;
		}
		return null;
	}

	public List<Unit> findByIds(String ids) {
		List<Unit> list = new ArrayList<>();
		if (ids != null && !"".equals(ids)) {
			list = dao.findByIds(ids);
		}
		return list;
	}

	public List<Unit> getByPhone(String yzm) {
		List<Unit> list = new ArrayList<>();
		if (yzm != null && !"".equals(yzm)) {
			list = dao.getByPhone(yzm);
		}
		return list;
	}

}