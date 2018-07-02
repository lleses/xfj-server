package cn.dlj.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.XunchaArchiveDao;
import cn.dlj.app.entity.XunchaArchive;

@Service
@Transactional(readOnly = true)
public class XunchaArchiveService {

	@Autowired
	private XunchaArchiveDao dao;

	@Transactional
	public void add(Integer archiveNum, Date archiveTime, Integer archiveUserId) {
		dao.add(archiveNum, archiveTime, archiveUserId);
	}

	public List<XunchaArchive> getByUnitId(Integer unitId) {
		return dao.getByUnitId(unitId);
	}

	public XunchaArchive getById(Integer id) {
		return dao.getById(id);
	}

	public Integer getMaxNum(Integer townId) {
		return dao.getMaxNum(townId);
	}
}