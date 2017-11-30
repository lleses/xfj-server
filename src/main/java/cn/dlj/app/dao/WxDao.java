package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.entity.WxXunchaImg;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

/**
 * 巡查登记
 * 
 */
@MyBatisDao
public interface WxDao {

	/**
	 * 新增
	 * 
	 */
	int add(WxXunchaImg wxXunchaImg);

	/**
	 * 修改
	 * 
	 */
	void update(@Param("id") Integer id, @Param("picName") String picName);

	/**
	 * 
	 */
	List<WxXunchaImg> getList(@Param("xunchaId") Integer xunchaId);

	/**
	 * 
	 */
	WxXunchaImg getNum(@Param("xunchaId") Integer xunchaId, @Param("num") Integer num);

	void updateFalg(@Param("id") Integer id, @Param("flag") Integer flag, @Param("remark") String remark);

	List<WxXuncha> getWxWaitList(PagingMySql paging);

	List<WxXuncha> getWxAlreadyList(PagingMySql paging);

	List<WxXuncha> getWxGlyWaitList(PagingMySql paging);

	List<WxXuncha> getWxGlyAlreadyList(PagingMySql paging);

}