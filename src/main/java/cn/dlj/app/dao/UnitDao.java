package cn.dlj.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Unit;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

/**
 * 监管单位(巡查单位)
 * 
 */
@MyBatisDao
public interface UnitDao {

	/**
	 * 新增
	 * 
	 * @param unit
	 *            监管单位(巡查单位)
	 */
	int add(Unit unit);

	/**
	 * 修改
	 * 
	 * @param unit
	 *            监管单位(巡查单位)
	 */
	void update(Unit unit);

	/**
	 * 修改
	 * 
	 * @param unit
	 *            监管单位(巡查单位)
	 */
	void updateYzm(@Param("yzm") String yzm, @Param("id") Integer id);

	/**
	 * 修改
	 * 
	 * @param unit
	 *            监管单位(巡查单位)
	 */
	void update2(Unit unit);

	void updateGm(@Param("id") Integer id, @Param("isgm") String isgm);

	/**
	 * 修改
	 * 
	 * @param unit
	 *            监管单位(巡查单位)
	 */
	void updateIsxc(@Param("starLevel") Integer starLevel, @Param("id") Integer id);

	/**
	 * 修改图片
	 * 
	 * @param map
	 */
	void updateImg(Map<String, Object> map);

	/**
	 * 检测监管单位(巡查单位)是否存在
	 * 
	 * @param name
	 *            单位名称
	 * @return
	 */
	Unit get(@Param("name") String name);

	/**
	 * 根据id获取监管单位(巡查单位)
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	Unit getById(@Param("id") Integer id);

//	/**
//	 * 获取监管单位(巡查单位)离线数据
//	 * 
//	 * @param townId
//	 *            所属镇区ID
//	 * @param departmentId
//	 *            所属社区ID
//	 * @return
//	 */
//	List<UnitStr> getList(@Param("townId") Integer townId, @Param("departmentId") Integer departmentId);

	/**
	 * 获取监管单位(巡查单位)最大ID值
	 * 
	 * @return
	 */
	Integer getMaxId();

	/**
	 * 删除监管单位归属建筑物信息
	 * 
	 * @return
	 */
	void delUnitBuilding(Integer uid);

	/**
	 * 新增监管单位归属建筑物信息
	 * 
	 * @return
	 */
	void addUnitBuilding(@Param("unit") Unit unit);

	Unit findById(@Param("id") Integer id);

	/**
	 * 获取监管单位List
	 * 
	 */
	List<Unit> getPaging(PagingMySql paging);

	/**
	 * 获取监管单位List
	 * 
	 */
	List<Unit> getMyPaging(PagingMySql paging);

	List<Map<String, Object>> getBuilds(@Param("uid") Integer uid);

	//------------------------------------------------new------------------------------------------------
	List<Unit> getByPhone(@Param("phone") String phone);

	List<Unit> findByIds(@Param("ids") String ids);
}