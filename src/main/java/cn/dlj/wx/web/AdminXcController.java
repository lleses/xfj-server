package cn.dlj.wx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.app.entity.Unit;
import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.entity.WxXunchaImg;
import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.entity.XunchaImg;
import cn.dlj.app.service.UnitService;
import cn.dlj.app.service.WxService;
import cn.dlj.app.service.XunchaService;
import cn.dlj.utils.PagingMySql;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.utils.WxConfig;
import cn.dlj.wx.entity.WxChat;
import cn.dlj.wx.entity.WxUser;
import cn.dlj.wx.service.WxChatService;
import cn.dlj.wx.service.WxUserService;
import cn.dlj.wx.service.WxXunchaService;

/**
 * 微信-巡查员
 */
@Controller
@RequestMapping("/wx/admin/xc/")
public class AdminXcController {

	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private WxService wxService;
	@Autowired
	private WxXunchaService wxXunchaService;
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxChatService wxChatService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(1);
		paging.setPageSize(20);

		String openId = ParamUtils.getStr(request, "openId");
		WxUser wxUser = wxUserService.getByOpenId(openId);
		if (wxUser != null) {
			paging.add("userId", wxUser.getUserId());
			//巡查员(待审核)
			List<WxXuncha> waitList = wxService.getWxWaitList(paging);
			//巡查员(已审核)
			List<WxXuncha> alreadyList = wxService.getWxAlreadyList(paging);
			request.setAttribute("waitList", waitList);
			request.setAttribute("size", waitList.size());
			request.setAttribute("alreadyList", alreadyList);

			List<WxChat> wxChats = wxChatService.getListByUserId(wxUser.getUserId(), "0");
			request.setAttribute("wxChats", wxChats);
			List<WxChat> wxChats2 = wxChatService.getListByUserId(wxUser.getUserId(), "1");
			request.setAttribute("wxChats2", wxChats2);
			request.setAttribute("wxUser", wxUser);
		}
		return "wx/admin/index";
	}

	@RequestMapping("list")
	public String list(HttpServletRequest request, Integer userId) {
		String statusType = ParamUtils.getStr(request, "statusType");//0:待审核 1:已审核
		request.setAttribute("statusType", statusType);
		request.setAttribute("userId", userId);
		return "wx/admin/list";
	}

	@RequestMapping("list_data")
	@ResponseBody
	public String listData(HttpServletRequest request, Integer userId) {
		String statusType = ParamUtils.getStr(request, "statusType");//0:待审核 1:已审核
		String unitName = ParamUtils.getStr(request, "unitName");
		Integer currentPage = ParamUtils.getInt(request, "currentPage");
		PagingMySql paging = new PagingMySql();
		paging.setCurrentPage(currentPage);
		if (unitName != null) {
			paging.add("unitName", "%" + unitName + "%");
		}
		paging.add("userId", userId);
		List<WxXuncha> list;
		if ("0".equals(statusType)) {
			//巡查员(待审核)
			list = wxService.getWxWaitList(paging);
		} else {
			//巡查员(已审核)
			list = wxService.getWxAlreadyList(paging);
		}
		String json = StringUtils.json(list);
		return json;
	}

	@RequestMapping("form")
	public String form(HttpServletRequest request) {
		Integer xunchaId = ParamUtils.getInt(request, "xunchaId");
		Xuncha xuncha = xunchaService.getById(xunchaId);
		Unit unit = null;
		List<XunchaImg> imgs = new ArrayList<>();
		List<WxXunchaImg> wxImgs = new ArrayList<>();
		if (xuncha != null) {
			unit = unitService.findById(xuncha.getUnitId());
			imgs = xunchaService.getImgs(xuncha.getId());
			wxImgs = wxService.getList(xuncha.getId());
		}
		request.setAttribute("xuncha", xuncha);
		request.setAttribute("unit", unit);
		request.setAttribute("wxImgs", wxImgs);
		request.setAttribute("imgs", imgs);
		request.setAttribute("upload", WxConfig.XUNCHA_IMG);
		return "wx/admin/form";
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
			wxXuncha.setRole(1);
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
