package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Department;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface DepartmentDao {

	List<Department> getByTownId(@Param("townId") Integer townId);
}