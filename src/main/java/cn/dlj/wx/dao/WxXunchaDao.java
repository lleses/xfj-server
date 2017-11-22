package cn.dlj.wx.dao;

import org.apache.ibatis.annotations.Param;

import cn.dlj.entity.WxXuncha;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface WxXunchaDao {

	void add(WxXuncha wxXuncha);

	void update(WxXuncha wxXuncha);

	WxXuncha getById(@Param("xunchaId") int xunchaId);

}