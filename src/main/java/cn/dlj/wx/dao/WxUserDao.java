package cn.dlj.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.utils.MyBatisDao;
import cn.dlj.wx.entity.WxUser;

/**
 * 微信-巡查 用户记录
 * 
 */
@MyBatisDao
public interface WxUserDao {

	WxUser getByOpenId(@Param("openId") String openId);

	/**
	 * 新增
	 * 
	 */
	int add(WxUser wxUser);

	WxUser getByNameAndPwd(@Param("userName") String userName, @Param("userPwd") String userPwd);

}