package cn.dlj.app.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.XunchaArchive;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface XunchaArchiveDao {

	int add(@Param("archiveNum") Integer archiveNum, @Param("archiveTime") Date archiveTime);

	List<XunchaArchive> getByUnitId(@Param("unitId") Integer unitId);

	XunchaArchive getById(@Param("id") Integer id);

	Integer getMaxNum(@Param("townId") Integer townId);

}