package cn.dlj.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Xuncha;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

@MyBatisDao
public interface XunchaDao {

	int add(Xuncha xuncha);

	void addFlag(Map<String, Object> map);

	void addRd(Map<String, Object> map);

	void delByTownId(Integer townId);

	void updateMeno(@Param("meno") String meno, @Param("id") Integer id);

	void updateRdImgs(Map<String, Object> map);

	void updateXc(Xuncha xuncha);

	Xuncha getById(@Param("id") Integer id);

	Xuncha getByUnitId(@Param("unitId") Integer unitId);

	void updateXcFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	void updateXcFlagAndMeno(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag,
			@Param("meno") String meno);

	void updateXcRdFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	void updateXcFlagFlag(@Param("xunchaId") Integer xunchaId, @Param("flag") String flag);

	void updateXunchaXcItem(@Param("xcItem") String xcItem, @Param("XcItemNum") Integer XcItemNum,
			@Param("xunchaId") Integer xunchaId);

	List<Xuncha> find(@Param("unitId") Integer unitId);

	List<Xuncha> findByXcTime(@Param("xcTime") String xcTime);

	List<Xuncha> getDepPaging(PagingMySql paging);
}