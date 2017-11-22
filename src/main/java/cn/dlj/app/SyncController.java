package cn.dlj.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.dlj.entity.AppSysnData;
import cn.dlj.entity.AppSysnImgs;
import cn.dlj.entity.LogDTO;
import cn.dlj.service.LogService;
import cn.dlj.service.SyncService;
import cn.dlj.utils.Config;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

/**
 * 更新数据
 */
@Controller
@RequestMapping("/sync/")
public class SyncController {

	private static final Logger log = LoggerFactory.getLogger(SyncController.class);
	/** app端是否初始化数据(1:yes 0:no) */
	private static final String APP_INIT = Config.get("app.init");

	/** 日志前缀 */
	public static final String LOG_APP_SUF = "【app操作日志】";
	/** 上传日志成功标识 */
	public static final String LOG_APP_RES_SUCC = "0";
	/** 上传日志失败标识 */
	public static final String LOG_APP_RES_FAIL = "-1";
	/** app操作日志类型 */
	public static final String LOG_APP_TYPE = "数据同步";

	@Autowired
	private SyncService syncService;
	@Autowired
	private LogService logService;

	// ****************************************处理提交的数据begin****************************************

	/** 处理提交的数据 */
	@RequestMapping("hand_data")
	@ResponseBody
	public String handData(HttpServletRequest request) {
		log.error("*******************************同步【数据】开始*******************************");
		// 获取app传过来的json
		String appJson = ParamUtils.getStr(request, "appSysnData");
		if (appJson == null) {
			String response = rsJson("-1", null, "同步失败，提交的数据过长，请联系管理员进行处理", null, "0");
			toLog(appJson, response, null, null, LOG_APP_RES_FAIL, "同步数据失败，appJson:null");
			return response;
		}
		// json转对象
		appJson = appJson.replace("&quot;", "\"");
		appJson = appJson.replaceAll("(\r\n|\r|\n|\n\r)", "");
		log.error("appJson:" + appJson);
		ObjectMapper mapper = StringUtils.MAPPER;
		AppSysnData appSysnData = null;
		String userId = ""; // 操作人ID
		String userName = ""; // 操作人姓名
		try {
			appSysnData = mapper.readValue(appJson, AppSysnData.class);
			userId = null == appSysnData.getUserId() ? "" : appSysnData.getUserId().toString();
			userName = null == appSysnData.getUserName() ? "" : appSysnData.getUserName().toString();
		} catch (IOException e) {
			String response = rsJson("-1", null, "同步失败，提交的数据存在不正确的类型，请联系管理员进行处理", null, "0");
			toLog(appJson, response, null, null, LOG_APP_RES_FAIL, "同步失败，原因:" + e.getMessage());
			return response;
		}
		// 处理同步数据,并返回处理成功列表 和 异常列表
		Map<String, Object> succList = new HashMap<String, Object>();
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();//异常列表
		try {
			syncService.handSysnData(appSysnData, succList, errList);
			if (errList.isEmpty()) {
				String response = rsJson("1", succList, "同步成功", null, "0");
				toLog(appJson, response, userId, userName, LOG_APP_RES_SUCC, "同步成功");
				return response;
			} else {
				String response = rsJson("2", succList, "同步成功，但部分数据同步异常，请在异常列表查看，并在后台核对数据，核对完后在后台补填数据，app不支持再上传已经异常的数据", errList, "0");
				toLog(appJson, response, userId, userName, LOG_APP_RES_SUCC, "同步成功，但部分数据同步异常，请在异常列表查看，并在后台核对数据，核对完后在后台补填数据，app不支持再上传已经异常的数据");
				return response;
			}
		} catch (Exception e) {
			String response = rsJson("-1", null, "处理同步数据失败，请联系管理员进行处理", errList, "0");
			toLog(appJson, response, userId, userName, LOG_APP_RES_FAIL, "处理同步数据失败" + e.getMessage());
			return response;
		}
	}

	/**
	 * to保存日志
	 * 
	 * @param request
	 *            请求参数
	 * @param response
	 *            响应参数
	 * @param uid
	 *            同步人ID
	 * @param uname
	 *            同步人
	 * @param result
	 *            同步结果
	 */
	private void toLog(String request, String response, String uid, String uname, String result, String err) {
		try {
			if (null != err) {
				log.error(err);
			}
			LogDTO logDTO = new LogDTO();
			logDTO.setId(IdUtils.id32());
			logDTO.setType(LOG_APP_TYPE);
			logDTO.setRequest(request);
			logDTO.setResponse(response);
			logDTO.setUid(uid);
			logDTO.setUname(uname);
			logDTO.setResult(result);
			logService.saveLog(logDTO);
		} catch (Exception e) {
			log.error(LOG_APP_SUF + LOG_APP_TYPE + "：保存日志出错" + e.getMessage());
		}

	}

	// ****************************************处理提交的数据end****************************************
	// ****************************************处理提交的图片begin****************************************

	/** 处理提交的图片 */
	@RequestMapping("hand_imgs")
	@ResponseBody
	public String handImgs(HttpServletRequest request) {
		log.error("*******************************同步【图片】开始*******************************");
		// 获取app传过来的json
		String appJson = ParamUtils.getStr(request, "appSysnImgs");
		if (appJson == null) {
			log.error("图片同步失败，appJson:null");
			return rsJson("-1", null, null, null, "0");

		}
		// json转对象
		appJson = appJson.replace("&quot;", "\"");
		ObjectMapper mapper = StringUtils.MAPPER;
		AppSysnImgs appSysnImgs = null;
		try {
			appSysnImgs = mapper.readValue(appJson, AppSysnImgs.class);
		} catch (IOException e) {
			log.error("图片同步失败，原因:" + e.getMessage());
			return rsJson("-1", null, "同步失败，提交的数据存在不正确的类型，请联系管理员进行处理", null, "0");
		}
		// 处理同步的图片
		syncService.handSysnImgs(appSysnImgs);
		return rsJson("1", null, null, null, "0");

	}

	// ****************************************处理提交的图片end****************************************

	// ****************************************返回服务器数据begin****************************************

	/** 返回服务器数据 */
	@RequestMapping("rs_data")
	@ResponseBody
	public String rsData(HttpServletRequest request) {
		log.error("*******************************返回服务器数据*******************************");
		Integer townId = ParamUtils.getInt(request, "townId");
		Integer departmentId = ParamUtils.getInt(request, "departmentId");
		Map<String, Object> rsData = syncService.rsData(townId, departmentId);
		return rsJson("1", rsData, null, null, APP_INIT);
	}
	// ****************************************返回服务器数据end****************************************

	/**
	 * 返回app端的json数据
	 * 
	 * @param succ
	 *            状态
	 * @param data
	 *            返回数据
	 * @param msg
	 *            返回信息
	 * @param errList
	 *            返回异常列表
	 * @param init
	 *            app端是否初始化数据(1:yes 0:no)
	 * @return
	 */
	private String rsJson(String succ, Map<String, Object> data, String msg, List<Map<String, Object>> errList, String init) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succ", succ);
		map.put("data", data);
		map.put("msg", msg);
		map.put("err", errList);
		map.put("init", init);
		String json = StringUtils.json(map);
		return json;
	}
}
