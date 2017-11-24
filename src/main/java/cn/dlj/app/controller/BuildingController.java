package cn.dlj.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Building;
import cn.dlj.app.service.BuildingService;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;

@RequestMapping("app/building")
@Controller
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request, int currentPage, String buildName, int townId) {
		Integer userId = ParamUtils.getInt(request, "userId");
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("townId", townId);
		if (userId != null) {
			paging.add("userId", userId);
		}
		if (buildName != null && !"".equals(buildName)) {
			paging.add("buildName", "%" + buildName + "%");
			paging.add("obligation", "%" + buildName + "%");
		}
		List<Building> pagingBuilding = buildingService.getPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;

	}

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Building building = buildingService.getById(id);
		String json = StringUtils.json(building);
		return json;

	}

	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request, Building building, String img64Str) {
		String newBimg = dealUploadBimg(null, img64Str, WxConfig.BUILD_IMG_SERVER_PATH, WxConfig.BUILD_IMG_UPLOAD_PATH);
		Building build = buildingService.get(building.getName());
		if (build == null) {
			building.setModTime(new Date());
			building.setAddTime(new Date());
			building.setWorkTime(new Date());
			building.setBirthdate(new Date());
			building.setBimg(newBimg);
			buildingService.add(building);
		}
		return "1";
	}

	@RequestMapping("edit")
	@ResponseBody
	public String edit(HttpServletRequest request, Building building, String img64Str) {
		String newBimg = dealUploadBimg(building.getBimg(), img64Str, WxConfig.BUILD_IMG_SERVER_PATH, WxConfig.BUILD_IMG_UPLOAD_PATH);
		Building build = buildingService.getById(building.getId());
		if (build != null) {
			build.setObligation(building.getObligation());
			build.setLxname(building.getLxname());
			build.setTelphone(building.getTelphone());
			build.setIsControl(building.getIsControl());
			build.setClassification(building.getClassification());
			build.setAreaa(building.getAreaa());
			build.setAcreage(building.getAcreage());
			build.setJzheight(building.getJzheight());
			build.setGround(building.getGround());
			build.setUnderground(building.getUnderground());
			build.setEvacuationA(building.getEvacuationA());
			build.setEvacuationB(building.getEvacuationB());
			build.setEvacuationC(building.getEvacuationC());
			build.setStructure(building.getStructure());
			build.setRefractory(building.getRefractory());
			build.setFacilityA(building.getFacilityA());
			build.setMessage(building.getMessage());
			build.setModTime(new Date());
			build.setWorkTime(new Date());
			build.setBimg(newBimg);
			buildingService.update(build);
		}
		return "1";
	}

	/**
	 * 处理客户端上传的图片
	 * 
	 * @param appImgs
	 *            app图片(支持多个，中间用“,”隔开)
	 * @param serverImgPathType
	 *            图片映射地址路径
	 * @param imgLocalSavePath
	 *            图片本地保存路径
	 * 
	 */
	private String dealUploadBimg(String oldBimg, String img64Str, String serverImgPathType, String imgLocalSavePath) {
		String newBimg = "";
		// 获取旧的图片名称
		String newHistoryBimgs = "";
		if (oldBimg != null) {
			for (String hb : oldBimg.split(",", -1)) {
				if (hb.startsWith(serverImgPathType)) {
					newHistoryBimgs += "," + hb.replaceAll(serverImgPathType, "");
				}
			}
		}
		// 转化成图片并返回图片名称
		String bimg = "";
		if (img64Str != null && !"".equals(img64Str) && !"undefined".equals(img64Str)) {
			List<String> img64 = img64(img64Str, null, imgLocalSavePath);
			if (img64 != null && !img64.isEmpty()) {
				for (String img : img64) {
					bimg += "," + img;
				}
			}
		}
		newBimg = newHistoryBimgs + bimg;
		if (newBimg.length() > 0) {
			newBimg = newBimg.substring(1);
		}
		return newBimg;
	}

	/**
	 * basc64转成图片文件
	 * 
	 * @param imgStr
	 *            basc64
	 * @param more
	 *            是否多张图片
	 * @param srcName
	 *            图片原名
	 * @param basePath
	 *            图片保存路径
	 * @return 图片名称
	 */
	private List<String> img64(String imgStr, String srcName, String basePath) {
		List<String> list = new ArrayList<String>();
		imgStr = imgStr.replaceAll("data:image/jpg;base64,", "");
		imgStr = imgStr.replaceAll("data:image/jpeg;base64,", "");
		String[] split = imgStr.split("#", -1);
		for (int i = 0; i < split.length; i++) {
			if (null == split[i] || "".equals(split[i])) {
				continue;
			}
			String fileName = IdUtils.id32() + ".jpg";
			String path = basePath + fileName;
			if (FileUtils.write(split[i], path)) {
				list.add(fileName);
			}
		}
		return list;
	}

}
