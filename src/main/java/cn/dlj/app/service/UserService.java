package cn.dlj.app.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.UserDao;
import cn.dlj.app.entity.User;

/**
 * 用户
 * 
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao dao;

	/**
	 * 根据账号名称获取账号信息
	 * 
	 * @param username
	 *            username
	 */
	public User get(String username) {
		User user = null;
		if (username != null) {
			List<User> list = dao.get(username);
			if (list != null && !list.isEmpty()) {
				user = list.get(0);
			}
		}
		return user;
	}

	/**
	 * 根据账号名称获取账号信息
	 * 
	 */
	public User getByNameAndPwd(String username, String password) {
		User user = null;
		if (username != null && password != null) {
			List<User> list = dao.getByNameAndPwd(username, password);
			if (list != null && !list.isEmpty()) {
				user = list.get(0);
			}
		}
		return user;
	}

	/**
	 * 获取消防站ID
	 * 
	 * @param townId
	 * 			所属镇区
	 * @param departmentId
	 * 			所属社区
	 * @return
	 */
	public Integer getStationId(@Param("townId") Integer townId, @Param("departmentId") Integer departmentId) {
		if (townId != null && departmentId != null) {
			return dao.getStationId(townId, departmentId);
		}
		return null;
	}

}