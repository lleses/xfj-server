package cn.dlj.wx.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.Unit;
import cn.dlj.entity.WxXuncha;
import cn.dlj.entity.WxXunchaImg;
import cn.dlj.entity.Xuncha;
import cn.dlj.entity.XunchaImg;
import cn.dlj.service.UnitService;
import cn.dlj.service.WxService;
import cn.dlj.service.XunchaService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;
import cn.dlj.wx.service.WxXunchaService;

/**
 * 微信-管理员员
 */
@Controller
@RequestMapping("/wx/gly/")
public class GlyController {

	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private WxService wxService;
	@Autowired
	private WxXunchaService wxXunchaService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(1);
		paging.setPageSize(20);
		//巡查员(待审核)
		List<WxXuncha> waitList = wxService.getWxGlyWaitList(paging);
		//巡查员(已审核)
		List<WxXuncha> alreadyList = wxService.getWxGlyAlreadyList(paging);
		request.setAttribute("waitList", waitList);
		request.setAttribute("size", waitList.size());
		request.setAttribute("alreadyList", alreadyList);
		return "wx/gly/index";
	}

	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		String statusType = ParamUtils.getStr(request, "statusType");//0:待审核 1:已审核
		request.setAttribute("statusType", statusType);
		return "wx/gly/list";
	}

	@RequestMapping("list_data")
	@ResponseBody
	public String listData(HttpServletRequest request) {
		String statusType = ParamUtils.getStr(request, "statusType");//0:待审核 1:已审核
		String unitName = ParamUtils.getStr(request, "unitName");
		Integer currentPage = ParamUtils.getInt(request, "currentPage");
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		if (unitName != null) {
			paging.add("unitName", "%" + unitName + "%");
		}
		List<WxXuncha> list;
		if ("0".equals(statusType)) {
			//巡查员(待审核)
			list = wxService.getWxGlyWaitList(paging);
		} else {
			//巡查员(已审核)
			list = wxService.getWxGlyAlreadyList(paging);
		}
		String json = StringUtils.json(list);
		return json;
	}

	@RequestMapping("form")
	public String form(HttpServletRequest request) {
		Integer xunchaId = ParamUtils.getInt(request, "xunchaId");
		Xuncha xuncha = xunchaService.getById(xunchaId);
		Unit unit = unitService.findById(xuncha.getUnitId());
		List<XunchaImg> imgs = xunchaService.getImgs(xuncha.getId());
		List<WxXunchaImg> wxImgs = wxService.getList(xuncha.getId());
		request.setAttribute("xuncha", xuncha);
		request.setAttribute("unit", unit);
		request.setAttribute("wxImgs", wxImgs);
		request.setAttribute("imgs", imgs);
		request.setAttribute("upload", WxConfig.XUNCHA_IMG);
		return "wx/gly/form";
	}

	@RequestMapping("updateFalg")
	@ResponseBody
	public String updateFalg(HttpServletRequest request) {
		String imgIds = ParamUtils.getStr(request, "imgIds");
		String xcItems = ParamUtils.getStr(request, "xcItems");
		String xcItemVals = ParamUtils.getStr(request, "xcItemVals");
		Integer xunchaId = ParamUtils.getInt(request, "xunchaId");

		String[] imgIdsArr = imgIds.split(",");
		String[] xcItemsArr = xcItems.split(",");
		String[] xcItemValsArr = xcItemVals.split(",");
		if (imgIdsArr != null && !"".equals(imgIdsArr)) {
			boolean isSucc = true;//总的审核是否通过
			for (int i = 0; i < imgIdsArr.length; i++) {
				Integer value = Integer.valueOf(xcItemValsArr[i]);
				if (value != null && value == 2) {
					isSucc = false;
				}
				wxService.updateFalg(Integer.valueOf(imgIdsArr[i]), value);
				//消防安全状况(1没问题2有问题) '1' 审核通过' , '2' 审核不通过
				xunchaService.updateXunchaXcItem(xcItemsArr[i], value, xunchaId);
			}

			WxXuncha wxXuncha = wxXunchaService.getById(xunchaId);
			wxXuncha.setEt(new Date());
			wxXuncha.setRole(2);
			if (isSucc) {
				wxXuncha.setStatus(1);
				xunchaService.updateWxXuncha(xunchaId, "6");//更新flag状态6(不及格)
			} else {
				wxXuncha.setStatus(2);
			}
			wxXunchaService.update(wxXuncha);
		}
		return "1";
	}

}
