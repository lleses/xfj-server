package cn.dlj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.dao.LogDao;
import cn.dlj.entity.LogDTO;

/**
 * 日志服务
 * 
 * @author LiuWenzhao
 *
 */
@Service
@Transactional(readOnly = true)
public class LogService {
	
	@Autowired
	private LogDao dao;
	
	/**
	 * 保存同步日志
	 */
	@Transactional
	public void saveLog(LogDTO log){
		dao.saveLog(log);
	}
}
