package cn.dlj.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Unit;
import cn.dlj.app.service.UnitService;
import cn.dlj.utils.ChineseToSpell;
import cn.dlj.utils.FileUtils;
import cn.dlj.utils.IdUtils;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;

@RequestMapping("app/unit")
@Controller
public class UnitController {

	@Autowired
	private UnitService unitService;

	@RequestMapping("myList")
	@ResponseBody
	public String myList(HttpServletRequest request, int currentPage, String unitName, int userId) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("userId", userId);
		if (unitName != null && !"".equals(unitName)) {
			paging.add("unitName", "%" + unitName + "%");
			paging.add("address", "%" + unitName + "%");
		}
		List<Unit> pagingBuilding = unitService.getMyPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;

	}

	@RequestMapping("list")
	@ResponseBody
	public String list(HttpServletRequest request, int currentPage, String unitName) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		if (unitName != null && !"".equals(unitName)) {
			paging.add("unitName", "%" + unitName + "%");
			paging.add("address", "%" + unitName + "%");
		}
		List<Unit> pagingBuilding = unitService.getPaging(paging);
		String json = StringUtils.json(pagingBuilding);
		return json;

	}

	@RequestMapping("get")
	@ResponseBody
	public String get(HttpServletRequest request, int id) {
		Unit unit = unitService.getById(id);
		unit = unitService.getBuilds(id, unit);
		String json = StringUtils.json(unit);
		return json;
	}

	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request, Unit unit, String img64Str, String buildIds) throws UnsupportedEncodingException {
		String newBimg = dealUploadBimg(null, img64Str, WxConfig.BUILD_IMG_SERVER_PATH, WxConfig.BUILD_IMG_UPLOAD_PATH);
		Unit un = unitService.get(unit.getName());
		if (un == null) {
			unit.setAddTime(new Date());
			unit.setModTime(new Date());
			unit.setBimg(newBimg);
			unit = code(unit);
			if (buildIds != null && !"".equals(buildIds)) {
				Set<Integer> buildingSet = new HashSet<>();
				String[] split = buildIds.split(",");
				for (String builId : split) {
					buildingSet.add(Integer.valueOf(builId));
				}
				unit.setBuildingSet(buildingSet);
			}
			unitService.add(unit);
		}
		return "1";
	}

	@RequestMapping("edit")
	@ResponseBody
	public String edit(HttpServletRequest request, Unit unit, String img64Str, String buildIds) {
		String newBimg = dealUploadBimg(unit.getBimg(), img64Str, Unit.UNIT_IMG_SERVER_PATH, WxConfig.BUILD_IMG_UPLOAD_PATH);
		Unit un = unitService.getById(unit.getId());
		if (un != null) {
			un.setLicense(unit.getLicense());
			un.setAddress(unit.getAddress());
			un.setType(unit.getType());
			un.setSafedLinkman(unit.getSafedLinkman());
			un.setSafedTelphone(unit.getSafedTelphone());
			un.setManageLinkman(unit.getManageLinkman());
			un.setManageTelphone(unit.getManageTelphone());
			un.setMeno(unit.getMeno());
			un.setArea(unit.getArea());
			un.setBuildingsLayer(unit.getBuildingsLayer());
			un.setKeyUnit(unit.getKeyUnit());
			un.setIscg(unit.getIscg());
			un.setModTime(new Date());
			un.setBimg(newBimg);
			if (buildIds != null && !"".equals(buildIds)) {
				Set<Integer> buildingSet = new HashSet<>();
				String[] split = buildIds.split(",");
				for (String builId : split) {
					buildingSet.add(Integer.valueOf(builId));
				}
				un.setBuildingSet(buildingSet);
			}
			unitService.update2(un);
		}
		return "1";
	}

	/** 生成单位编码code **/
	private Unit code(Unit appUnit) {
		String townName = appUnit.getTownName();
		townName = ChineseToSpell.getPinYinHeadChar(townName);
		if (townName != null) {
			townName = townName.substring(0, 2);
		} else {
			townName = "er";
		}
		String departmentName = appUnit.getDepartmentName();
		departmentName = ChineseToSpell.getPinYinHeadChar(departmentName);
		if (departmentName != null) {
			departmentName = departmentName.substring(0, 2);
		} else {
			departmentName = "er";
		}
		Integer maxId = unitService.getMaxId();
		String ids = "";
		if (maxId == null) {
			ids = "000001";
		} else if (maxId < 10) {
			ids = "00000" + (maxId + 1);
		} else if (maxId < 100) {
			ids = "0000" + (maxId + 1);
		} else if (maxId < 1000) {
			ids = "000" + (maxId + 1);
		} else if (maxId < 10000) {
			ids = "00" + (maxId + 1);
		} else if (maxId < 100000) {
			ids = "0" + (maxId + 1);
		} else {
			maxId = maxId + 1;
			ids = maxId.toString();
		}
		String code = townName + departmentName + ids;
		appUnit.setCode(code);
		return appUnit;
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
		//		String historyBimgs = appImgs.getBimg();
		//		if (bimg == null || "".equals(bimg)) {
		//			return newBimg;
		//		}
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
