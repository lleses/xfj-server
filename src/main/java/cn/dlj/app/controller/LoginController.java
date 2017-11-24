package cn.dlj.app.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.User;
import cn.dlj.app.service.UserService;
import cn.dlj.utils.Config;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

/**
 * 登陆
 */
@Controller
@RequestMapping("/login/")
public class LoginController {

	@Autowired
	private UserService service;

	/** 验证登陆 */
	@RequestMapping("verify")
	@ResponseBody
	public String verify(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = ParamUtils.getStr(request, "username");
		String pwd = ParamUtils.getStr(request, "pwd");
		if (pwd == null || "".equals(pwd.trim()) || username == null || "".equals(username.trim())) {
			map.put("succ", "-1");
			map.put("msg", "账号密码不能为空！");
			return StringUtils.json(map);
		}
		User user = service.get(username);
		if (user == null) {
			map.put("succ", "-1");
			map.put("msg", "该账号不存在，请重新输入！");
			return StringUtils.json(map);
		}
		if (user.getRoleId() != null && user.getRoleId() != 6 && user.getRoleId() != 28) {
			map.put("succ", "-1");
			map.put("msg", "只允许巡查员或职能部门人员登陆，请更换巡查员账号！");
			return StringUtils.json(map);
		}
		if (!md5(pwd).equals(user.getPwd())) {
			map.put("succ", "-1");
			map.put("msg", "密码不正确，请重新输入！");
			return StringUtils.json(map);
		}
		user.setStationId(service.getStationId(user.getTownId(), user.getDepId()));

		if (user.getRoleId() == 28) {
			map.put("succ", "2");
			map.put("data", user);
			return StringUtils.json(map);
		}
		map.put("succ", "1");
		map.put("data", user);
		return StringUtils.json(map);
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

	private static final String INIT_PWD = "app.init.password";

	/** 验证初始化密码 */
	@RequestMapping("verify_init")
	@ResponseBody
	public String verifyInit(HttpServletRequest request) {
		String pwd = request.getParameter("initPwd");
		String initPwd = Config.get(INIT_PWD);
		if (null != pwd && pwd.equals(initPwd)) {
			return "1";
		}
		return "0";
	}

}
