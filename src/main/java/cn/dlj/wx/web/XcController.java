package cn.dlj.wx.web;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.Unit;
import cn.dlj.entity.User;
import cn.dlj.entity.WxXuncha;
import cn.dlj.entity.WxXunchaImg;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
import cn.dlj.service.UnitService;
import cn.dlj.service.UserService;
import cn.dlj.service.WxService;
import cn.dlj.service.XunchaService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.WxConfig;
import cn.dlj.wx.entity.WxUser;
import cn.dlj.wx.service.WxUserService;
import cn.dlj.wx.service.WxXunchaService;

/**
 * 微信-巡查(被监管单位)
 */
@Controller
@RequestMapping("/wx/xc/")
public class XcController {

	private static final Logger log = LoggerFactory.getLogger(XcController.class);
	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private UserService userService;
	@Autowired
	private WxService wxService;
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxXunchaService wxXunchaService;

//	@RequestMapping("index")
//	public String index(HttpServletRequest request) {
//		String toLogin = "wx/bind";
//		String openId = null;
//		try {
//			TextMessage textMessage = CoreService.textMessage;
//			if (textMessage == null) {
//				log.error("textMessage 为null");
//				return toLogin;
//			}
//			// 发送方帐号 OpenID
//			openId = textMessage.getFromUserName();
//			request.setAttribute("openId", openId);
//			WxUser wxUser = wxUserService.getByOpenId(openId);
//			if (wxUser == null) {
//				log.error("wxUser 为null");
//				return toLogin;
//			}
//			Integer type = wxUser.getType();//0:被监管单位 1:平台巡查员 2:平台管理员
//			CoreService.textMessage = null;
//			if (type == 0) {
//				Integer unitId = wxUser.getUnitId();
//				Xuncha xuncha = xunchaService.getByUnitId(unitId);
//				Unit unit = unitService.findById(unitId);
//				request.setAttribute("xuncha", xuncha);
//				request.setAttribute("unit", unit);
//				return "wx/index";
//			} else if (type == 1) {
//				PagingMySql paging = new PagingMySql();
//				paging.setCurrentPage(1);
//				paging.setPageSize(20);
//				//巡查员(待审核)
//				List<WxXuncha> waitList = wxService.getWxWaitList(paging);
//				//巡查员(已审核)
//				List<WxXuncha> alreadyList = wxService.getWxAlreadyList(paging);
//				request.setAttribute("waitList", waitList);
//				request.setAttribute("size", waitList.size());
//				request.setAttribute("alreadyList", alreadyList);
//				return "wx/admin/index";
//			} else {
//				PagingMySql paging = new PagingMySql();
//				paging.setCurrentPage(1);
//				paging.setPageSize(20);
//				//巡查员(待审核)
//				List<WxXuncha> waitList = wxService.getWxGlyWaitList(paging);
//				//巡查员(已审核)
//				List<WxXuncha> alreadyList = wxService.getWxGlyAlreadyList(paging);
//				request.setAttribute("waitList", waitList);
//				request.setAttribute("size", waitList.size());
//				request.setAttribute("alreadyList", alreadyList);
//				return "wx/gly/index";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		request.setAttribute("openId", openId);
//		return toLogin;
//	}

	@RequestMapping("to_index")
	public String toIndex(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		Xuncha xuncha = xunchaService.getByUnitId(unitId);
		Unit unit = unitService.findById(unitId);
		request.setAttribute("xuncha", xuncha);
		request.setAttribute("unit", unit);
		return "wx/index";
	}

	@RequestMapping("form")
	public String form(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		Xuncha xuncha = xunchaService.getByUnitId(unitId);
		Unit unit = unitService.findById(unitId);
		List<XunchaImg> imgs = xunchaService.getImgs(xuncha.getId());
		List<WxXunchaImg> wxImgs = wxService.getList(xuncha.getId());
		request.setAttribute("xuncha", xuncha);
		request.setAttribute("unit", unit);
		request.setAttribute("wxImgs", wxImgs);
		request.setAttribute("imgs", imgs);
		request.setAttribute("upload", WxConfig.XUNCHA_IMG);
		return "wx/form";
	}

	@ResponseBody
	@RequestMapping("save")
	public String save(HttpServletRequest request) {
		Integer xunchaId = ParamUtils.getInt(request, "xunchaId");
		String unitName = ParamUtils.getStr(request, "unitName");
		xunchaService.updateWxXuncha(xunchaId, "31");//更新flag状态31

		WxXuncha wxXuncha = wxXunchaService.getById(xunchaId);
		if (wxXuncha == null) {
			wxXuncha = new WxXuncha();
			wxXuncha.setXunchaId(xunchaId);
			wxXuncha.setCt(new Date());
			wxXuncha.setEt(new Date());
			wxXuncha.setStatus(0);
			wxXuncha.setRole(1);
			wxXuncha.setUnitName(unitName);
			wxXunchaService.add(wxXuncha);
		} else {
			wxXuncha.setEt(new Date());
			wxXuncha.setStatus(0);
			wxXuncha.setRole(1);
			wxXunchaService.update(wxXuncha);
		}
		return "1";
	}

	/** 上传图片 **/
	@ResponseBody
	@RequestMapping("uploadImg")
	public void uploadImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String imgStr = ParamUtils.getStr(request, "imgStr");
		Integer num = ParamUtils.getInt(request, "num");
		Integer xunchaId = ParamUtils.getInt(request, "xunchaId");
		wxService.save(imgStr, num, xunchaId);
	}

	/** to账号绑定 **/
	@RequestMapping("toBind")
	public String toBind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String openId = ParamUtils.getStr(request, "openId");
		request.setAttribute("openId", openId);
		return "wx/bind";
	}

	/** 账号绑定 **/
	@ResponseBody
	@RequestMapping("userBind")
	public String userBind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = ParamUtils.getStr(request, "username");
		String pwd = ParamUtils.getStr(request, "pwd");
		String openId = ParamUtils.getStr(request, "openId");
		String yzm = ParamUtils.getStr(request, "yzm");
		String wxUserType = ParamUtils.getStr(request, "wxUserType");

		if ("0".equals(wxUserType)) {
			Unit unit = unitService.getByYzm(yzm);
			if (unit == null) {
				log.error("账号绑定失败,验证码不正确");
				return "-5";
			}
			WxUser wxUser = new WxUser();
			wxUser.setOpenId(openId);
			wxUser.setType(0);
			wxUser.setUnitId(unit.getId());
			wxUser.setUserId(-1);
			wxUser.setUserName("");
			wxUser.setUserPwd("");
			wxUserService.add(wxUser);
			return unit.getId().toString();
		} else {
			if (username == null || "".equals(username.trim()) || pwd == null || "".equals(pwd.trim())) {
				log.error("账号绑定失败,账号密码不能为空");
				return "-1";
			} else if (openId == null || "".equals(openId)) {
				log.error("账号绑定失败,openId为空");
				return "-2";
			}
			pwd = md5(pwd);
			User user = userService.getByNameAndPwd(username, pwd);
			if (user == null) {
				log.error("账号绑定失败,账号密码不对");
				return "-3";
			}
			WxUser wxUser = new WxUser();
			wxUser.setOpenId(openId);
			wxUser.setUnitId(-1);
			wxUser.setUserId(user.getId());
			wxUser.setUserName(user.getUsername());
			wxUser.setUserPwd(user.getPwd());
			if ("1".equals(wxUserType)) {
				wxUser.setType(1);
				wxUserService.add(wxUser);
				return "1";
			} else if ("2".equals(wxUserType)) {
				wxUser.setType(2);
				wxUserService.add(wxUser);
				return "1";
			} else {
				log.error("账号绑定bug，请开发者检查代码");
			}
			log.error("账号绑定bug(2)，请开发者检查代码");
			return "-4";
		}
	}

	/** md5加密 **/
	private String md5(String pwd) {
		if (pwd != null && !"".equals(pwd.trim())) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update((pwd).getBytes("UTF-8"));
				byte b[] = md5.digest();

				int i;
				StringBuffer buf = new StringBuffer("");

				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0) {
						i += 256;
					}
					if (i < 16) {
						buf.append("0");
					}
					buf.append(Integer.toHexString(i));
				}
				return buf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
