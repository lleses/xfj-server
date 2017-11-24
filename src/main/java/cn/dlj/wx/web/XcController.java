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

import cn.dlj.app.entity.Unit;
import cn.dlj.app.entity.User;
import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.entity.WxXunchaImg;
import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.UserService;
import cn.dlj.app.service.WxService;
import cn.dlj.app.service.XunchaService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.WxConfig;
import cn.dlj.wx.entity.WxChat;
import cn.dlj.wx.entity.WxUser;
import cn.dlj.wx.service.WxChatService;
import cn.dlj.wx.service.WxUserService;
import cn.dlj.wx.service.WxXunchaService;

/**
 * 微信-被监管单位
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
	@Autowired
	private WxChatService wxChatService;

	@RequestMapping("to_index")
	public String toIndex(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		Xuncha xuncha = xunchaService.getByUnitId(unitId);
		Unit unit = unitService.findById(unitId);
		request.setAttribute("xuncha", xuncha);
		request.setAttribute("unit", unit);

		List<WxChat> wxChats = wxChatService.getListByUnitId(unitId);
		int count = wxChatService.getAllCountByUnitId(unitId);
		request.setAttribute("wxChats", wxChats);
		request.setAttribute("count", count);
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
		String type = ParamUtils.getStr(request, "type");
		request.setAttribute("openId", openId);
		request.setAttribute("type", type);
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
		//0:社会单位	1:平台巡查员	2:平台管理员
		String wxUserType = ParamUtils.getStr(request, "wxUserType");

		if ("0".equals(wxUserType)) {//社会单位
			List<Unit> units = unitService.getByPhone(yzm);
			if (units.isEmpty()) {
				log.error("账号绑定失败,验证码不正确");
				return "-5";
			}
			String unitIds = "";
			for (Unit un : units) {
				unitIds += "," + un.getId();
			}
			if (!"".equals(unitIds)) {
				unitIds = unitIds.substring(1);
			}
			WxUser wxUser = wxUserService.getByOpenId(openId);
			if (wxUser == null) {//add
				wxUser = new WxUser();
				wxUser.setOpenId(openId);
				wxUser.setType(0);
				wxUser.setUnitId(unitIds);
				wxUser.setUserId(-1);
				wxUser.setUserName("");
				wxUser.setUserPwd("");
				wxUserService.add(wxUser);
			} else {//update
				wxUser.setType(0);
				wxUser.setUnitId(unitIds);
				wxUser.setUserId(-1);
				wxUser.setUserName("");
				wxUser.setUserPwd("");
				wxUserService.update(wxUser);
			}
			return unitIds;
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

			WxUser wxUser = wxUserService.getByOpenId(openId);
			if (wxUser == null) {//add
				wxUser = new WxUser();
				wxUser.setOpenId(openId);
				wxUser.setUnitId("-1");
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
					return "2";
				} else {
					log.error("账号绑定bug，请开发者检查代码");
				}
			} else {//update
				wxUser.setUnitId("-1");
				wxUser.setUserId(user.getId());
				wxUser.setUserName(user.getUsername());
				wxUser.setUserPwd(user.getPwd());
				if ("1".equals(wxUserType)) {
					wxUser.setType(1);
					wxUserService.update(wxUser);
					return "1";
				} else if ("2".equals(wxUserType)) {
					wxUser.setType(2);
					wxUserService.update(wxUser);
					return "2";
				} else {
					log.error("账号绑定bug，请开发者检查代码");
				}
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
