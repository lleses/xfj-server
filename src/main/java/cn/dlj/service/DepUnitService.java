package cn.dlj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.dao.DepUnitDao;
import cn.dlj.entity.UnitStr;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaRd;
import cn.dlj.utils.PagingMySql;

/**
 * 职能部门(监管单位)
 * 
 */
@Service
@Transactional(readOnly = true)
public class DepUnitService {

	@Autowired
	private DepUnitDao dao;

	/**
	 * 获取监管单位(巡查单位)离线数据
	 */
	public List<UnitStr> pagingUnit(PagingMySql paging) {
		return dao.pagingUnit(paging);
	}

	/**
	 * 获取监管单位
	 */
	public UnitStr get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取巡查记录离线数据
	 */
	public List<Xuncha> pagingXuncha(PagingMySql paging) {
		return dao.pagingXuncha(paging);
	}

	/**
	 * 获取巡查RD明细
	 */
	public XunchaRd getXunchaRd(String id) {
		return dao.getXunchaRd(id);
	}

	/**
	 * 获取巡查图片
	 */
	public String getImg(String id) {
		return dao.getImg(id);
	}

	/**
	 * 获取巡查相关
	 */
	@Transactional
	public void updateXuncha(XunchaRd rd) {
		dao.updateXC(rd);
		dao.updateFlag(rd);
		dao.updateRd(rd);
	}

}