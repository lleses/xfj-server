package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.User;
import cn.dlj.utils.MyBatisDao;

/**
 * 用户
 * 
 */
@MyBatisDao
public interface UserDao {

	/**
	 * 根据账号名称获取账号信息
	 * 
	 * @param username
	 * 			  账号名称
	 */
	List<User> get(@Param("username") String username);

	/**
	 * 根据账号名称获取账号信息
	 * 
	 */
	List<User> getByNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 获取消防站ID
	 * 
	 * @param townId
	 * 			所属镇区
	 * @param departmentId
	 * 			所属社区
	 * @return
	 */
	Integer getStationId(@Param("townId") Integer townId, @Param("departmentId") Integer departmentId);

}