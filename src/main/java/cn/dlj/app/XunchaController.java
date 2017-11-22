package cn.dlj.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaRd;
import cn.dlj.service.DepUnitService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

@RequestMapping("dep/xc")
@Controller
public class XunchaController {

	@Autowired
	private DepUnitService depUnitService;

	/** 巡查列表 */
	@RequestMapping("list")
	@ResponseBody
	public String unitList(HttpServletRequest request) {
		Integer currentPage = ParamUtils.getInt(request, "currentPage");
		String townId = ParamUtils.getStr(request, "townId");
		String departmentId = ParamUtils.getStr(request, "departmentId");
		String unitName = ParamUtils.getStr(request, "unitName");
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("townId", townId);
		paging.add("departmentId", departmentId);
		if (unitName != null && !"".equals(unitName)) {
			paging.add("unitName", "%" + unitName + "%");
		}
		List<Xuncha> pagingXuncha = depUnitService.pagingXuncha(paging);
		String json = unitJson(pagingXuncha);
		return json;
	}

	/** 组装请假信息数据 **/
	private String unitJson(List<Xuncha> list) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Xuncha x : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", x.getId());
			map.put("unitId", x.getUnitId());
			map.put("unitName", x.getUnitName());
			map.put("xcTime", x.getXcTime());
			li.add(map);
		}
		return StringUtils.json(li);
	}

	/** 获取巡查RD明细 */
	@RequestMapping("get_xuncha_rd")
	@ResponseBody
	public String getXunchaRd(HttpServletRequest request) {
		String id = ParamUtils.getStr(request, "id");
		XunchaRd rd = depUnitService.getXunchaRd(id);
		String imgIds = rd.getImgIds();
		if (null != imgIds && !"".equals(imgIds)) {
			String[] split = imgIds.split(",");
			if (split.length > 0) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < split.length; i++) {
					String img = depUnitService.getImg(split[i]);
					sb.append(img).append(",");
				}
				String imgs = sb.toString();
				imgs.substring(0, imgs.length() - 1);
				rd.setImgUrls(imgs);
			}
		}
		String json = StringUtils.json(rd);
		return json;
	}

	/** 获取巡查RD明细 */
	@RequestMapping("save_rd")
	@ResponseBody
	public void saveRd(HttpServletRequest request) {
		//TODO 监管表要补上
		String starLevel = ParamUtils.getStr(request, "starLevel");//安全状态等级
		String rdType = ParamUtils.getStr(request, "rdType");//0:暂存 1:提交

		XunchaRd rd = new XunchaRd();
		if ("1".equals(rdType)) {
			rd.setFlag(ParamUtils.getStr(request, "flag"));
		}
		//TODO 巡查时间要补上
		rd.setXcTime(ParamUtils.getStr(request, "xcTime"));
		rd.setEtPerson(ParamUtils.getStr(request, "etPerson"));
		rd.setMeno(ParamUtils.getStr(request, "meno"));
		rd.setLiveThree(ParamUtils.getStr(request, "liveThree"));
		rd.setId(ParamUtils.getStr(request, "rdId"));
		rd.setXunchaId(ParamUtils.getStr(request, "xunchaId"));
		rd.setXcItem1(ParamUtils.getStr(request, "xcItem1"));
		rd.setXcItem2(ParamUtils.getStr(request, "xcItem2"));
		rd.setXcItem3(ParamUtils.getStr(request, "xcItem3"));
		rd.setXcItem4(ParamUtils.getStr(request, "xcItem4"));
		rd.setXcItem5(ParamUtils.getStr(request, "xcItem5"));
		rd.setXcItem6(ParamUtils.getStr(request, "xcItem6"));
		rd.setXcItem7(ParamUtils.getStr(request, "xcItem7"));
		rd.setXcItem8(ParamUtils.getStr(request, "xcItem8"));
		rd.setXcItem9(ParamUtils.getStr(request, "xcItem9"));
		rd.setXcItem10(ParamUtils.getStr(request, "xcItem10"));
		rd.setXcItem11(ParamUtils.getStr(request, "xcItem11"));
		depUnitService.updateXuncha(rd);
		return;
	}

}
