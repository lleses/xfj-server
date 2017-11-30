package cn.dlj.wx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.entity.WxXuncha;
import cn.dlj.wx.dao.WxXunchaDao;

@Service
@Transactional(readOnly = true)
public class WxXunchaService {

	@Autowired
	private WxXunchaDao WxXunchaDao;

	@Transactional
	public void add(WxXuncha wxXuncha) {
		WxXunchaDao.add(wxXuncha);
	}

	@Transactional
	public void update(WxXuncha wxXuncha) {
		WxXunchaDao.update(wxXuncha);
	}

	@Transactional
	public void updateLastTime(WxXuncha wxXuncha) {
		WxXunchaDao.updateLastTime(wxXuncha);
	}

	public WxXuncha getById(Integer xunchaId) {
		if (xunchaId != null) {
			return WxXunchaDao.getById(xunchaId);
		}
		return null;
	}

	public List<WxXuncha> getByLastTime() {
		return WxXunchaDao.getByLastTime();
	}

}