package cn.dlj.wx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.dlj.app.entity.Unit;
import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.WxService;
import cn.dlj.utils.HttpUtils;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;
import cn.dlj.wx.entity.WeixinOauth2Token;
import cn.dlj.wx.entity.WxChat;
import cn.dlj.wx.entity.WxUser;
import cn.dlj.wx.service.WxChatService;
import cn.dlj.wx.service.WxUserService;

/**
 * 微信-巡查
 */
@Controller
@RequestMapping("/wx/")
public class WxController {

	private static final Logger log = LoggerFactory.getLogger(WxController.class);
	@Autowired
	private UnitService unitService;
	@Autowired
	private WxService wxService;
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxChatService wxChatService;

	/** 入口 **/
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check(HttpServletRequest request) {
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		log.error("code:" + code);
		// 用户同意授权
		if ("authdeny".equals(code)) {
			return "wx/bind";
		}
		// 获取网页授权access_token
		WeixinOauth2Token weixinOauth2Token = getOauth2AccessToken(WxConfig.APP_ID, WxConfig.APP_SECRET, code);
		log.error("weixinOauth2Token:" + weixinOauth2Token);
		if (weixinOauth2Token == null) {
			return "wx/bind";
		}
		try {
			// 发送方帐号 OpenID
			String openId = weixinOauth2Token.getOpenid();
			request.setAttribute("openId", openId);
			WxUser wxUser = wxUserService.getByOpenId(openId);
			if (wxUser == null) {
				log.error("wxUser 为null");
				return "wx/bind";
			}
			Integer type = wxUser.getType();//0:被监管单位 1:平台巡查员 2:平台管理员
			Integer userId = wxUser.getUserId();
			if (type == 0) {
				String unitIds = wxUser.getUnitId();
				List<Unit> units = unitService.findByIds(unitIds);
				request.setAttribute("units", units);
				return "wx/selUnit";
			} else if (type == 1) {
				PagingMySql paging = new PagingMySql();
				paging.setCurrentPage(1);
				paging.setPageSize(20);
				paging.add("userId", wxUser.getUserId());
				//巡查员(待审核)
				List<WxXuncha> waitList = wxService.getWxWaitList(paging);
				//巡查员(已审核)
				List<WxXuncha> alreadyList = wxService.getWxAlreadyList(paging);
				request.setAttribute("waitList", waitList);
				request.setAttribute("size", waitList.size());
				request.setAttribute("alreadyList", alreadyList);

				List<WxChat> wxChats = wxChatService.getListByUserId(userId, "0");
				request.setAttribute("wxChats", wxChats);
				List<WxChat> wxChats2 = wxChatService.getListByUserId(userId, "1");
				request.setAttribute("wxChats2", wxChats2);
				request.setAttribute("wxUser", wxUser);
				return "wx/admin/index";
			} else {
				PagingMySql paging = new PagingMySql();
				paging.setCurrentPage(1);
				paging.setPageSize(20);
				paging.add("userId", wxUser.getUserId());
				//巡查员(待审核)
				List<WxXuncha> waitList = wxService.getWxGlyWaitList(paging);
				//巡查员(已审核)
				List<WxXuncha> alreadyList = wxService.getWxGlyAlreadyList(paging);
				request.setAttribute("waitList", waitList);
				request.setAttribute("size", waitList.size());
				request.setAttribute("alreadyList", alreadyList);

				List<WxChat> wxChats = wxChatService.getListByUserId(userId, "0");
				request.setAttribute("wxChats", wxChats);
				request.setAttribute("wxUser", wxUser);
				return "wx/gly/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wx/bind";

	}

	@RequestMapping("selUnit")
	public String selUnit(HttpServletRequest request) {
		String openId = ParamUtils.getStr(request, "openId");
		String unitIds = ParamUtils.getStr(request, "unitIds");
		List<Unit> units = unitService.findByIds(unitIds);
		request.setAttribute("units", units);
		request.setAttribute("openId", openId);
		return "wx/selUnit";

	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	private WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		String json = HttpUtils.get(requestUrl);
		if (null != json) {
			try {
				ObjectMapper mapper = StringUtils.MAPPER;
				wat = mapper.readValue(json, WeixinOauth2Token.class);
			} catch (Exception e) {
			}
		}
		return wat;
	}

}
