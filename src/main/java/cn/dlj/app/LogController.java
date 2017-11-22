package cn.dlj.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.LogDTO;
import cn.dlj.service.LogService;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.Log;

/**
 * app日志接口
 */
@Controller
@RequestMapping("/log/")
public class LogController {

	@Autowired
	private LogService service;

	/** 日志前缀*/
	public static final String LOG_APP_SUF = "【app操作日志】";
	/** 上传日志成功标识 */
	public static final String LOG_APP_RES_SUCC = "0";

	/** 提交操作日志接口 */
	@RequestMapping("save_log")
	@ResponseBody
	public String saveLog(HttpServletRequest request) {
		String uid = request.getParameter("uid");// 操作人id
		String uname = request.getParameter("uname");// 操作人姓名
		String type = request.getParameter("type");// 节点类型
		String json = request.getParameter("json");// 操作单据对象JSON
		if (null != json) {
			json = json.replace("&quot;", "\"");
		}
		try {
			LogDTO log = new LogDTO();
			log.setId(IdUtils.id32());
			log.setType(type);
			log.setRequest(json);
			log.setUid(uid);
			log.setUname(uname);
			log.setResult(LOG_APP_RES_SUCC);
			service.saveLog(log);

		} catch (Exception e) {
			Log.error(LOG_APP_SUF + type + "：保存日志出错" + e.getMessage());
			return "1";
		}
		Log.info(LOG_APP_SUF + type + "：保存日志成功");
		return "1";
	}
}
