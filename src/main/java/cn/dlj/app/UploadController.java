package cn.dlj.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.utils.Config;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("uploadFile")
@Controller
public class UploadController {
	/** 巡查图片保存地址 */
	private static final String XUNCHA_IMG_UPLOAD_PATH = Config.get("xuncha.img.upload.path");

	/** 巡查上传图片 **/
	@RequestMapping("img")
	@ResponseBody
	public String img(HttpServletRequest request, String imgbasc64) {
		String imgSrc = FileUtils.imgbasc64(imgbasc64, XUNCHA_IMG_UPLOAD_PATH);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imgSrc", imgSrc);
		String json = StringUtils.json(map);
		return json;
	}

}
