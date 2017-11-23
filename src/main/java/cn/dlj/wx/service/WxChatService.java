package cn.dlj.wx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.wx.dao.WxChatDao;
import cn.dlj.wx.entity.WxChat;

@Service
@Transactional(readOnly = true)
public class WxChatService {

	@Autowired
	private WxChatDao wxChatDao;

	@Transactional
	public void add(WxChat wxChat) {
		wxChatDao.add(wxChat);
	}

	@Transactional
	public void updateByUnitId(Integer unitId, String type) {
		if (unitId != null && type != null) {
			wxChatDao.updateByUnitId(unitId, type);
		}
	}

	public List<WxChat> getListByUnitId(Integer unitId) {
		List<WxChat> list = new ArrayList<>();
		if (unitId != null) {
			list = wxChatDao.getListByUnitId(unitId);
		}
		return list;
	}

	public int getAllCountByUnitId(Integer unitId) {
		if (unitId != null) {
			return wxChatDao.getAllCountByUnitId(unitId);
		}
		return 0;
	}

	public List<WxChat> getListByUserId(Integer userId) {
		List<WxChat> list = new ArrayList<>();
		if (userId != null) {
			list = wxChatDao.getListByUserId(userId);
		}
		return list;
	}

}