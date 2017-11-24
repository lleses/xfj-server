package cn.dlj.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Unit;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

@MyBatisDao
public interface UnitDao {

	int add(Unit unit);

	void update(Unit unit);

	void updateGm(@Param("id") Integer id, @Param("isgm") String isgm);

	void updateIsxc(@Param("starLevel") Integer starLevel, @Param("id") Integer id);

	Unit get(@Param("name") String name);

	Unit getById(@Param("id") Integer id);

	Integer getMaxId();

	void delUnitBuilding(Integer uid);

	void addUnitBuilding(@Param("unit") Unit unit);

	Unit findById(@Param("id") Integer id);

	List<Unit> getPaging(PagingMySql paging);

	List<Map<String, Object>> getBuilds(@Param("uid") Integer uid);

	List<Unit> getByPhone(@Param("phone") String phone);

	List<Unit> findByIds(@Param("ids") String ids);
}