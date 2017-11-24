package cn.dlj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaRd;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

/**
 * 职能部门(监管单位)
 * 
 */
@MyBatisDao
public interface DepUnitDao {

	/**
	 * 获取巡查记录List
	 * 
	 */
	List<Xuncha> pagingXuncha(PagingMySql paging);

	/**
	 * 获取巡查RD明细
	 * 
	 */
	XunchaRd getXunchaRd(@Param("id") String id);

	/**
	 * 获取巡查图片
	 * 
	 */
	String getImg(@Param("id") String id);

	void updateXC(XunchaRd rd);

	void updateFlag(XunchaRd rd);

	void updateRd(XunchaRd rd);

	List<Xuncha> getMyPaging(PagingMySql paging);
}