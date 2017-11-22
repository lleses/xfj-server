package cn.dlj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.entity.Building;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

/**
 * 建筑物
 * 
 */
@MyBatisDao
public interface BuildingDao {

	/**
	 * 新增
	 * 
	 * @param building
	 *            建筑物信息
	 */
	void add(Building building);

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	void update(Building building);

	/**
	 * 修改
	 * 
	 * @param building
	 *            建筑物信息
	 */
	void update2(Building building);

	/**
	 * 修改图片
	 * 
	 * @param map
	 */
	void updateImg(Map<String, Object> map);

	/**
	 * 删除
	 * 
	 * @param id
	 *            ID
	 */
	void del(Integer id);

	/**
	 * 通过名称获取建筑物
	 * 
	 * @param name
	 *            建筑物名称
	 * @return
	 */
	Building get(@Param("name") String name);

	/**
	 * 通过名称获取建筑物
	 * 
	 * @param name
	 *            建筑物名称
	 * @return
	 */
	Building getById(@Param("id") int id);

	/**
	 * 获取建筑物离线数据
	 * 
	 * @param townId
	 *            所属镇区ID
	 * @param departmentId
	 *            所属社区ID
	 * @return
	 */
	List<Building> getList(@Param("townId") Integer townId, @Param("departmentId") Integer departmentId);

	/**
	 * 获取监管单位List
	 * 
	 */
	List<Building> getPaging(PagingMySql paging);

	/**
	 * 获取监管单位List
	 * 
	 */
	List<Building> getMyPaging(PagingMySql paging);
}