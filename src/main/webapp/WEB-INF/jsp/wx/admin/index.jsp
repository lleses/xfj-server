<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<title>消防微信公众号</title>
<link type="text/css" rel="stylesheet" href="/res/css/css.css">
<link type="text/css" rel="stylesheet" href="/res/css/weui.min.css">
<link type="text/css" rel="stylesheet" href="/res/css/jquery-weui.min.css">
<script type="text/javascript" src="/res/js/jquery.js"></script>
<script type="text/javascript" src="/res/js/comm.js"></script>
<script type="text/javascript" src="/res/js/util.js"></script>
<script type="text/javascript" src="/res/js/jquery-weui.min.js"></script>
</head>
<style>
.changeColor{background-color: #9a9da2;font-weight: bold;color: #fff;}
.xxtx_clo1{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #6299b5;font-size: 13px;}
.xxtx_clo2{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #888;font-size: 13px;}
.xxtx_clo3{border: 1px solid rgba(244, 67, 54, 0.72);font-size: 11px;padding: 5px;font-size: 13px;}
.wtjd_clo1{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #6299b5;font-size: 13px;}
.wtjd_clo2{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #888;font-size: 13px;}

.wd{float:left;padding: 0px 10px;width: calc(100% - 20px);}
.wd_time{text-align:center;width:100%;float:left;color:#ddd;padding-top:10px;}
.wd_sub{float:left;width:100%;}
.wd_sub_img1{float:left;width:50px;height:40px;}
.wd_sub_s1{height:40px;float:left;text-align: left;width: calc(100% - 50px);}
.wd_sub_s2{height:40px;float:left;line-height:40px;padding:0px 5px;color: #ddd;}
.wd_sub_d1{float:left;width:100%;padding:5px 0px;text-align:left;color:#888888;}
.wd_sub_img2{float:right;width:50px;height:40px;}
.wd_sub2_s1{height:40px;float:right;line-height:40px;}
.wd_sub2_s2{height:40px;float:right;line-height:40px;padding:0px 5px;color: #ddd;}
.wd_sub_d2{float:left;width:100%;padding:5px 0px;text-align:right;color:#888888;}
.newMsg{display:none;background:#E91E63;color:white;border-radius:50px;border:1px solid #E91E63;margin-left:10px;margin-top:2px;width:25px;float:left;height:25px;}
</style>
</head>
<body>
	<div id="m_main" class="bgf6">
		<div style="height: 20px;border-bottom: 1px solid #d9d9d9;background-color: #009688;"></div>
		
		<div style="float: left;color: rgba(244, 67, 54, 0.72);font-size: 13px;width: 90%;padding: 10px 5% 0px 5%;">
			<div style="float: left">
				温馨提示:只显示最近20条
			</div>
		</div>
		<!-- 上次更新时间 -->
		<div style="text-align: center;padding: 10px;border: 3px solid #009688;color: #888;width: calc(90% - 28px);float: left;margin: 0px 5%;">
			您还有<span id="xxtx_num" style="color:red;">${size }</span>条记录需要审核
		</div>
		
		<div style="border: 1px solid #e9e9e9;float: left;width: 90%;margin: 10px 5% 0px 5%;text-align: center;">
			<div onclick="swichTab('2')" id="xxtxTab" class="changeColor" style="padding: 10px 0px;float: left;width: calc(34% - 2px);">待审核</div>
			<div onclick="swichTab('1')" id="wtjdTab" style="padding: 10px 0px;float: left;width: 33%;border-left: 1px solid #e9e9e9;">已审核</div>
			<div onclick="swichTab('3')" id="wdTab" style="padding: 10px 0px;float: left;width: 33%;border-left: 1px solid #e9e9e9;">客服</div>
		</div>
		<!-- 消息提醒 -->
		<div id="xxtx_div"  style="height:420px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;">
			<div id="xxtx" style="overflow-y: auto;height:350px;float: left;width: 100%;">
				<c:forEach	items="${waitList }" var="waitList" varStatus="status">
					<div class="xxtx_clo2" onclick="toDetail('${waitList.xunchaId }')">
						${status.index+1 }、${waitList.unitName }
					</div>
				</c:forEach>
			</div>
			<div class="btn" style="padding:10px 5%; width: 90%; float: left;background-color: rgba(158, 158, 158, 0.12);">
				<a href="/wx/admin/xc/list?statusType=0" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary">审核更多</a>
			</div>
		</div>
		<div id="wtjd_div" style="height:420px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;display: none;">
			<div id="wtjd" style="overflow-y: auto;height:350px;float: left;width: 100%;">
				<c:forEach	items="${alreadyList }" var="alreadyList" varStatus="status">
					<div class="xxtx_clo2" onclick="toDetail('${alreadyList.xunchaId }')">
						${status.index+1 }、${alreadyList.unitName }<span style="color: red;font-size: 11px;padding: 5px;">${alreadyList.statusStr }</span>
					</div>
				</c:forEach>
			</div>
			<div class="btn" style="padding:10px 5%; width: 90%; float: left;background-color: rgba(158, 158, 158, 0.12);">
				<a href="/wx/admin/xc/list?statusType=1" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary">查看更多</a>
			</div>
		</div>
		
		<!-- 问题解答 -->
		<div id="wd_div" style="height:420px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;display: none;">
			<div id="wd" style="overflow-y: auto;height:350px;float: left;width: 100%;">
				<c:forEach	items="${wxChats }" var="wxChat" varStatus="status">
					<div class="xxtx_clo2" onclick="openPopup('${wxChat.unitId }')" style="padding:10px;">
						${status.index+1 }、${wxChat.unitName }<span style="border:1px solid red;height:30px;width:30px;color:red;padding:5px;margin-left:10px;">新</span>
					</div>
				</c:forEach>
				<c:forEach	items="${wxChats2 }" var="wxChat2" varStatus="status">
					<div class="xxtx_clo2" onclick="openPopup('${wxChat2.unitId }')" style="padding:10px;">
						${status.index+1 }、${wxChat2.unitName }
					</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- 学生弹幕 -->
		<div id="about" class="weui-popup-container">
			<div class="weui-popup-overlay"></div>
			<div class="weui-popup-modal" style="background-color: white;">
				<div id="mx" style="overflow:auto;border:1px solid rgb(221,221,221);margin:20px 20px 0px 20px;">
				</div>
				<div style="width:90%;margin:0px 5%;height:112px;">
					<textarea id="txtMsg" style="outline:none;height:50px;margin:10px 0 0 0;border:1px solid #ddd;width:calc(100% - 20px);float:left;overflow:scroll;resize:none;font-size:15px;padding:10px;"></textarea>
				</div>
				<div id="sendMsg" class="close_button" onclick="sendMsg()" style="float: left;width: 50%;background-color:#ddd;color:#607D8B;">
					<a>发送</a>
				</div>
				<div class="close_button"  id="confirmStu" onclick="$.closePopup();" style="width: 50%;float:left;background-color:#607D8B;color:white;">
					<a>关闭${wxUser.userId}</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
var g_xctype = "1";//1人员密集场所巡查2三少场所巡查3工业企业巡查
var _type = '${unit.type}';
var g_userId = '${wxUser.userId}';
$(function(){
	var g_hei = $(window).height()-(21+30+50+46+30);
	$("#xxtx_div").css({
		"height" : g_hei
	});
	$("#xxtx").css({
		"height" : g_hei-50
	});
	$("#wtjd_div").css({
		"height" : g_hei
	});
	$("#wd_div").css({
		"height" : g_hei
	});
	$("#wtjd").css({
		"height" : g_hei-50
	});
	$("#mx").css({
		"height" : $(window).height()-20-112-46
	});
})

function sendMsg(p_unitId){
	var _txtMsg = $("#txtMsg")[0].value;
	if($.trim(_txtMsg)==""){
		$.alert("请填写内容后在发送");
		return;
	}
	var _html = "";
	_html+="<div class='wd_time'>${wxChat.ctStr}</div>";
	_html+="<div class='wd'>";
		_html+="<div class='wd_sub'>";
			_html+="<img width='50' src='/res/img/weChat_2.png' class='wd_sub_img2' />";
			_html+="<span class='wd_sub2_s1' >客服</span>";
		_html+="</div>";
		_html+="<div class='wd_sub_d2'>"+_txtMsg+"</div>";
	_html+="</div>";
	$("#mx").append(_html);
	$("#txtMsg").val("");
	
	var param={
			"msg":_txtMsg,
			"unitId": p_unitId,
			"userId":g_userId
		}
	$.post("/wx/addChatByXuncha",param,function(rs){});
}

function openPopup(p_unitId){
	$("#sendMsg").attr("onclick","sendMsg('"+p_unitId+"')");
	var param={
			"unitId": p_unitId,
		}
	$.post("/wx/getListByUnitId",param,function(rs){
		var _datas = eval("("+rs+")");
		var _html = "";
		for (var i = 0; i < _datas.length; i++) {
			var _data = _datas[i];
			if(_data.type==0 || _data.type=='0'){
				_html+="<div class='wd_time'>"+_data.ctStr+"</div>";
				_html+="<div class='wd'>";
					_html+="<div class='wd_sub'>";
						_html+="<img width='50' src='/res/img/weChat_1.png' class='wd_sub_img1' />";
						_html+="<span class='wd_sub_s1' >"+_data.unitName+"</span>";
					_html+="</div>";
					_html+="<div class='wd_sub_d1'>"+_data.msg+"</div>";
				_html+="</div>";
			}else{
				_html+="<div class='wd_time'>"+_data.ctStr+"</div>";
				_html+="<div class='wd'>";
					_html+="<div class='wd_sub'>";
						_html+="<img width='50' src='/res/img/weChat_2.png' class='wd_sub_img2' />";
						_html+="<span class='wd_sub2_s1'>客服</span>";
					_html+="</div>";
					_html+="<div class='wd_sub_d2'>"+_data.msg+"</div>";
				_html+="</div>";
			}
		}
		$("#mx").html(_html);
	});
	$("#about").popup();
}
function swichTab(num){
	if(num=="1"){
		$("#xxtx_div").hide();
		$("#xxtxTab").removeClass();
		$("#wtjd_div").show();
		$("#wtjdTab").addClass("changeColor");
		$("#wd_div").hide();
		$("#wdTab").removeClass();
	}else if(num=="2"){
		$("#xxtx_div").show();
		$("#xxtxTab").addClass("changeColor");
		$("#wtjd_div").hide();
		$("#wtjdTab").removeClass();
		$("#wd_div").hide();
		$("#wdTab").removeClass();
	}else{
		$("#xxtx_div").hide();
		$("#xxtxTab").removeClass();
		$("#wtjd_div").hide();
		$("#wtjdTab").removeClass();
		$("#wd_div").show();
		$("#wdTab").addClass("changeColor");
	}
}

function toDetail(xunchaId) {
	window.location.href="/wx/admin/xc/form?xunchaId="+xunchaId;
}

</script>
</html>
