package cn.dlj.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

@MyBatisDao
public interface XunchaDao {

	int add(Xuncha xuncha);

	void addImg(XunchaImg xunchaImg);

	void addFlag(Map<String, Object> map);

	void addRd(Map<String, Object> map);

	void updateRdImgs(Map<String, Object> map);

	void updateXc(Xuncha xuncha);

	Xuncha getById(@Param("id") Integer id);

	Xuncha getByUnitId(@Param("unitId") Integer unitId);

	void updateXcFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	void updateXcRdFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	void updateXcFlagFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	List<XunchaImg> getImgs(@Param("xunchaId") Integer xunchaId);

	void updateXunchaXcItem(@Param("xcItem") String xcItem, @Param("XcItemNum") Integer XcItemNum, @Param("xunchaId") Integer xunchaId);

	List<Xuncha> find(@Param("unitId") Integer unitId);

	List<Xuncha> findByXcTime(@Param("xcTime") String xcTime);

	void delXunchaImg(@Param("xunchaId") Integer xunchaId);

	List<Xuncha> getDepPaging(PagingMySql paging);
}