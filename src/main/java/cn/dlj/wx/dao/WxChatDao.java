package cn.dlj.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.utils.MyBatisDao;
import cn.dlj.wx.entity.WxChat;

@MyBatisDao
public interface WxChatDao {

	void add(WxChat wxChat);

	void updateByUnitId(@Param("unitId") int unitId, @Param("type") String type);

	List<WxChat> getListByUnitId(@Param("unitId") int unitId);

	int getAllCountByUnitId(@Param("unitId") int unitId);

	List<WxChat> getListByUserId(@Param("userId") int userId);

}