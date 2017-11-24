package cn.dlj.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.utils.FileUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;

@RequestMapping("uploadFile")
@Controller
public class UploadController {

	/** 巡查上传图片 **/
	@RequestMapping("img")
	@ResponseBody
	public String img(HttpServletRequest request, String imgbasc64, String xuncha) {
		String path = WxConfig.BUILD_IMG_UPLOAD_PATH;
		if (xuncha != null && !"null".equals(xuncha)) {
			path = WxConfig.XUNCHA_IMG_UPLOAD_PATH;
		}
		String imgSrc = FileUtils.imgbasc64(imgbasc64, path);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgSrc", imgSrc);
		String json = StringUtils.json(map);
		return json;
	}

}
