package cn.dlj.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.wx.dao.WxUserDao;
import cn.dlj.wx.entity.WxUser;

/**
 * 微信-巡查 用户记录
 * 
 */
@Service
@Transactional(readOnly = true)
public class WxUserService {

	@Autowired
	private WxUserDao wxUserDao;

	@Transactional
	public void add(WxUser wxUser) {
		if (wxUser.getOpenId() != null && wxUserDao.getByOpenId(wxUser.getOpenId()) == null) {
			wxUserDao.add(wxUser);
		}
	}

	public WxUser getByOpenId(String openId) {
		if (openId != null) {
			return wxUserDao.getByOpenId(openId);
		}
		return null;
	}

	public WxUser getByNameAndPwd(String userName, String userPwd) {
		return wxUserDao.getByNameAndPwd(userName, userPwd);
	}

}