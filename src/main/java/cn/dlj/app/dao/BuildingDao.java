package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Building;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

@MyBatisDao
public interface BuildingDao {

	void add(Building building);

	void update(Building building);

	Building get(@Param("name") String name);

	Building getById(@Param("id") int id);

	List<Building> getPaging(PagingMySql paging);

}