package cn.dlj.app;

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

import cn.dlj.entity.UnitStr;
import cn.dlj.service.DepUnitService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;

/**
 * 职能部门
 */
@Controller
@RequestMapping("/dep/")
public class DepartmentController {

	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepUnitService depUnitService;

	/** 监管单位列表 */
	@RequestMapping("unit_list")
	@ResponseBody
	public String unitList(HttpServletRequest request) {
		Integer currentPage = ParamUtils.getInt(request, "currentPage");
		String townId = ParamUtils.getStr(request, "townId");
		String departmentId = ParamUtils.getStr(request, "departmentId");
		String unitName = ParamUtils.getStr(request, "unitName");
		log.info("currentPage:" + currentPage + ",townId:" + townId + ",departmentId:" + departmentId + ",unitName:" + unitName);
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		paging.add("townId", townId);
		paging.add("departmentId", departmentId);
		if (unitName != null && !"".equals(unitName)) {
			paging.add("unitName", "%" + unitName + "%");
		}
		List<UnitStr> pagingUnit = depUnitService.pagingUnit(paging);
		String json = unitJson(pagingUnit);
		log.info("dep_unit:" + json);
		return json;
	}

	/** 组装请假信息数据 **/
	private String unitJson(List<UnitStr> list) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (UnitStr u : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", u.getId());
			map.put("name", u.getName());
			map.put("isxc", u.getIsxc());
			map.put("delflag", u.getDelflag());
			li.add(map);
		}
		return StringUtils.json(li);
	}

	/** 监管单位列表 */
	@RequestMapping("get_unit")
	@ResponseBody
	public String getUnit(HttpServletRequest request) {
		String id = ParamUtils.getStr(request, "id");
		UnitStr unitStr = depUnitService.get(id);
		String json = StringUtils.json(unitStr);
		return json;
	}

}
