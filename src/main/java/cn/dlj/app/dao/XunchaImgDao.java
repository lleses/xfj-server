package cn.dlj.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.utils.MyBatisDao;
import cn.dlj.utils.PagingMySql;

@MyBatisDao
public interface XunchaImgDao {

	void add(XunchaImg xunchaImg);

	List<XunchaImg> getImgs(@Param("xunchaId") Integer xunchaId);
	
	void delXunchaImg(@Param("xunchaId") Integer xunchaId);
}