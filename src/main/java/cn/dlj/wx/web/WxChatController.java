package cn.dlj.wx.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlj.entity.Unit;
import cn.dlj.service.UnitService;
import cn.dlj.utils.ParamUtils;
import cn.dlj.utils.StringUtils;
import cn.dlj.wx.entity.WxChat;
import cn.dlj.wx.service.WxChatService;

/**
 * 微信-巡查(被监管单位)
 */
@Controller
@RequestMapping("/wx/")
public class WxChatController {

	@Autowired
	private WxChatService wxChatService;
	@Autowired
	private UnitService unitService;

	@RequestMapping("addChatByUnit")
	@ResponseBody
	public String addChatByUnit(HttpServletRequest request) {
		String msg = ParamUtils.getStr(request, "msg");
		Integer unitId = ParamUtils.getInt(request, "unitId");
		Integer userId = ParamUtils.getInt(request, "userId");
		Unit unit = unitService.getById(unitId);
		String unitName = "";
		if (unit != null) {
			unitName = unit.getName();
		}
		WxChat wxChat = new WxChat();
		wxChat.setMsg(msg);
		wxChat.setUnitId(unitId);
		wxChat.setUnitName(unitName);
		wxChat.setUserId(userId);
		wxChat.setType("0");
		wxChat.setCt(new Date());
		wxChatService.add(wxChat);
		return "1";
	}

	/** 标记已读 0:企业 1:巡查 **/
	@RequestMapping("readChat")
	@ResponseBody
	public String readChat(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		String type = ParamUtils.getStr(request, "type");
		wxChatService.updateByUnitId(unitId, type);
		return "1";
	}

	//------------------巡查------------------
	//TODO
	@RequestMapping("addChatByXuncha")
	@ResponseBody
	public String addChatByXuncha(HttpServletRequest request) {
		String msg = ParamUtils.getStr(request, "msg");
		Integer unitId = ParamUtils.getInt(request, "unitId");
		Integer userId = ParamUtils.getInt(request, "userId");
		Unit unit = unitService.getById(unitId);
		String unitName = "";
		if (unit != null) {
			unitName = unit.getName();
		}
		WxChat wxChat = new WxChat();
		wxChat.setMsg(msg);
		wxChat.setUnitId(unitId);
		wxChat.setUnitName(unitName);
		wxChat.setUserId(userId);
		wxChat.setType("1");
		wxChat.setCt(new Date());
		wxChatService.add(wxChat);
		return "1";
	}

	@RequestMapping("getListByUnitId")
	@ResponseBody
	public String getListByUnitId(HttpServletRequest request) {
		Integer unitId = ParamUtils.getInt(request, "unitId");
		wxChatService.updateByUnitId(unitId, "0");
		List<WxChat> wxChats = wxChatService.getListByUnitId(unitId);
		String json = StringUtils.json(wxChats);
		return json;
	}

}
