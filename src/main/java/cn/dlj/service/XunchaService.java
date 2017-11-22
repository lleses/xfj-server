package cn.dlj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.dao.XunchaDao;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
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

	/**
	 * 新增
	 * 
	 * @param building
	 * 			巡查登记
	 */
	@Transactional
	public Integer add(Xuncha xuncha) {
		dao.add(xuncha);
		return xuncha.getId();
	}

	/**
	 * 新增
	 * 
	 * @param building
	 * 			巡查登记
	 */
	@Transactional
	public Integer add2(Xuncha xuncha) {
		dao.add2(xuncha);
		return xuncha.getId();
	}

	/**
	 * 新增Img
	 * 
	 */
	@Transactional
	public Integer addImg(XunchaImg xunchaImg) {
		dao.addImg(xunchaImg);
		if (xunchaImg != null && xunchaImg.getId() != null) {
			return xunchaImg.getId();
		}
		return null;
	}

	/**
	 * 新增历史记录
	 * 
	 */
	@Transactional
	public void addFlag(Map<String, Object> map) {
		dao.addFlag(map);
	}

	/**
	 * 新增历史记录
	 * 
	 */
	@Transactional
	public void addRd(Map<String, Object> map) {
		dao.addRd(map);
	}

	/**
	 * 更新rd的图片
	 * 
	 */
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

	/**
	 * 根据appId获取id
	 * 
	 * @param appXunchaId
	 * @return
	 */
	public Integer getByAppId(String appXunchaId) {
		if (appXunchaId != null && !"".equals(appXunchaId)) {
			return dao.getByAppId(appXunchaId);
		}
		return null;
	}

	public Xuncha getById(Integer id) {
		if (id != null) {
			return dao.getById(id);
		}
		return null;
	}

	/**
	 * 根据名称和appid获取巡查记录
	 * 
	 * @param appXunchaId
	 * 				appID
	 * @param unitName
	 * 				监管名称
	 */
	public Xuncha get(String appXunchaId, String unitName) {
		if (appXunchaId != null && unitName != null) {
			return dao.get(appXunchaId, unitName);
		}
		return null;
	}

	//------------------------------wx------------------------------

	public Xuncha getByUnitId(Integer unitId) {
		if (unitId != null) {
			return dao.getByUnitId(unitId);
		}
		return null;
	}

	@Transactional
	public void updateWxXuncha(Integer xunchaId, String status) {
		if (xunchaId != null) {
			dao.updateWxXuncha(xunchaId, status);
		}
	}

	public List<XunchaImg> getImgs(Integer xunchaId) {
		List<XunchaImg> list = new ArrayList<XunchaImg>();
		if (xunchaId != null) {
			list = dao.getImgs(xunchaId);
		}
		return list;
	}

	public List<Xuncha> getWxList(PagingMySql paging) {
		List<Xuncha> wxList = dao.getWxList(paging);
		return wxList;
	}

	public void updateXunchaXcItem(String xcItem, Integer XcItemNum, Integer xunchaId) {
		dao.updateXunchaXcItem(xcItem, XcItemNum, xunchaId);
	}

	public Xuncha find(Integer unitId) {
		List<Xuncha> list = dao.find(unitId);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public void delXunchaImg(Integer xunchaId) {
		dao.delXunchaImg(xunchaId);
	}

}