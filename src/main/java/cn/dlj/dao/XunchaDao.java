package cn.dlj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

/**
 * 巡查登记
 * 
 */
@MyBatisDao
public interface XunchaDao {

	/**
	 * 新增
	 * 
	 * @param xuncha
	 * 			巡查登记
	 */
	int add(Xuncha xuncha);

	/**
	 * 新增
	 * 
	 * @param xuncha
	 * 			巡查登记
	 */
	int add2(Xuncha xuncha);

	/**
	 * 新增img
	 */
	void addImg(XunchaImg xunchaImg);

	/**
	 * 新增历史记录
	 */
	void addFlag(Map<String, Object> map);

	/**
	 * 新增Rd
	 */
	void addRd(Map<String, Object> map);

	/**
	 * 更新rd的图片
	 */
	void updateRdImgs(Map<String, Object> map);

	void updateXc(Xuncha xuncha);

	/**
	 * 根据appId获取id
	 * 
	 * @param appXunchaId
	 */
	Integer getByAppId(@Param("appXunchaId") String appXunchaId);

	/**
	 * 根据名称和appid获取巡查记录
	 * 
	 * @param appXunchaId
	 * 				appID
	 * @param unitName
	 * 				监管名称
	 */
	Xuncha get(@Param("appXunchaId") String appXunchaId, @Param("unitName") String unitName);

	//------------------------------wx------------------------------
	Xuncha getById(@Param("id") Integer id);

	Xuncha getByUnitId(@Param("unitId") Integer unitId);

	void updateWxXuncha(@Param("xunchaId") Integer xunchaId, @Param("status") String status);

	List<XunchaImg> getImgs(@Param("xunchaId") Integer xunchaId);

	List<Xuncha> getWxList(PagingMySql paging);

	void updateXunchaXcItem(@Param("xcItem") String xcItem, @Param("XcItemNum") Integer XcItemNum, @Param("xunchaId") Integer xunchaId);

	List<Xuncha> find(@Param("unitId") Integer unitId);

	void delXunchaImg(@Param("xunchaId") Integer xunchaId);
}