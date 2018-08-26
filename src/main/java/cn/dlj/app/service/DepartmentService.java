package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.DepartmentDao;
import cn.dlj.app.entity.Department;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

	@Autowired
	private DepartmentDao dao;

	public List<Department> getByTownId(Integer townId) {
		List<Department> list = new ArrayList<Department>();
		if (townId != null) {
			list = dao.getByTownId(townId);
		}
		return list;
	}

}