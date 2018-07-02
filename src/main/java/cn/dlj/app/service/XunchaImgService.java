package cn.dlj.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.XunchaImgDao;
import cn.dlj.app.entity.XunchaImg;

@Service
@Transactional(readOnly = true)
public class XunchaImgService {

	@Autowired
	private XunchaImgDao dao;

	@Transactional
	public Integer add(XunchaImg xunchaImg) {
		dao.add(xunchaImg);
		if (xunchaImg != null && xunchaImg.getId() != null) {
			return xunchaImg.getId();
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

	public void delXunchaImg(Integer xunchaId) {
		dao.delXunchaImg(xunchaId);
	}
}