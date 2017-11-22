package cn.dlj.dao;

import cn.dlj.entity.LogDTO;
import cn.dlj.utils.MyBatisDao;

/**
 * 日志DAO
 * @author LiuWenzhao
 *
 */
@MyBatisDao
public interface LogDao {

	/**
	 * 保存日志
	 */
	void saveLog(LogDTO log);
	
}
