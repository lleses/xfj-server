package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.XunchaDao;
import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.utils.PagingMySql;

/**
 * 巡查登记
 * 
 */
@Service
@Transactional(readOnly = true)
public class XunchaService {

	@Autowired
	private XunchaDao dao;

	@Transactional
	public Integer add(Xuncha xuncha) {
		dao.add(xuncha);
		return xuncha.getId();
	}

	@Transactional
	public Integer addImg(XunchaImg xunchaImg) {
		dao.addImg(xunchaImg);
		if (xunchaImg != null && xunchaImg.getId() != null) {
			return xunchaImg.getId();
		}
		return null;
	}

	@Transactional
	public void addFlag(Map<String, Object> map) {
		dao.addFlag(map);
	}

	@Transactional
	public void addRd(Map<String, Object> map) {
		dao.addRd(map);
	}

	@Transactional
	public void updateRdImgs(Integer id, String imgIds) {
		if (id != null && !"".equals(imgIds) && imgIds != null && !"".equals(imgIds)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("imgIds", imgIds);
			dao.updateRdImgs(map);
		}
	}

	@Transactional
	public void updateXc(Xuncha xuncha) {
		dao.updateXc(xuncha);
	}

	public Xuncha getById(Integer id) {
		if (id != null) {
			return dao.getById(id);
		}
		return null;
	}

	public List<XunchaImg> getImgs(Integer xunchaId) {
		List<XunchaImg> list = new ArrayList<XunchaImg>();
		if (xunchaId != null) {
			list = dao.getImgs(xunchaId);
		}
		return list;
	}

	public Xuncha find(Integer unitId) {
		List<Xuncha> list = dao.find(unitId);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<Xuncha> findByXcTime(String xcTime) {
		return dao.findByXcTime(xcTime);
	}

	public void delXunchaImg(Integer xunchaId) {
		dao.delXunchaImg(xunchaId);
	}

	public void updateXunchaXcItem(String xcItem, Integer XcItemNum, Integer xunchaId) {
		dao.updateXunchaXcItem(xcItem, XcItemNum, xunchaId);
	}

	@Transactional
	public void updateXcFlag(Integer xunchaId, String flag) {
		if (xunchaId != null) {
			dao.updateXcFlag(xunchaId, flag);
		}
	}

	@Transactional
	public void updateXcRdFlag(Integer xunchaId, String flag) {
		if (xunchaId != null) {
			dao.updateXcRdFlag(xunchaId, flag);
		}
	}

	@Transactional
	public void updateXcFlagFlag(Integer xunchaId, String flag) {
		if (xunchaId != null) {
			dao.updateXcFlagFlag(xunchaId, flag);
		}
	}

	public Xuncha getByUnitId(Integer unitId) {
		if (unitId != null) {
			return dao.getByUnitId(unitId);
		}
		return null;
	}

	public List<Xuncha> getDepPaging(PagingMySql paging) {
		return dao.getDepPaging(paging);
	}

}