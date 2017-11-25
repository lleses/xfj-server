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
<script type="text/javascript" src="/res/js/jquery.js"></script>
<script type="text/javascript" src="/res/js/comm.js"></script>
<script type="text/javascript" src="/res/js/util.js"></script>
</head>
<style>
.changeColor{background-color: #9a9da2;font-weight: bold;color: #fff;}
.xxtx_clo1{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #6299b5;font-size: 13px;}
.xxtx_clo2{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #888;font-size: 13px;}
.xxtx_clo3{border: 1px solid rgba(244, 67, 54, 0.72);font-size: 11px;padding: 5px;font-size: 13px;}
.wtjd_clo1{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #6299b5;font-size: 13px;}
.wtjd_clo2{padding: 5px 10px;border-bottom: 1px solid #e9e9e9;text-align: left;color: #888;font-size: 13px;}
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
			<div onclick="swichTab('2')" id="xxtxTab" class="changeColor" style="padding: 10px 0px;float: left;width: calc(50% - 1px);border-right: 1px solid #e9e9e9;">待审核</div>
			<div onclick="swichTab('1')" id="wtjdTab" style="padding: 10px 0px;float: left;width: 50%;">已审核</div>
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
				<a href="/wx/gly/list?statusType=0" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary">审核更多</a>
			</div>
		</div>
		<!-- 问题解答 -->
		<div id="wtjd_div" style="height:420px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;display: none;">
			<div id="wtjd" style="overflow-y: auto;height:350px;float: left;width: 100%;">
				<c:forEach	items="${alreadyList }" var="alreadyList" varStatus="status">
					<div class="xxtx_clo2" onclick="toDetail('${alreadyList.xunchaId }')">
						${status.index+1 }、${alreadyList.unitName }<span style="color: red;font-size: 11px;padding: 5px;">${alreadyList.statusStr }</span>
					</div>
				</c:forEach>
			</div>
			<div class="btn" style="padding:10px 5%; width: 90%; float: left;background-color: rgba(158, 158, 158, 0.12);">
				<a href="/wx/gly/list?statusType=1" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary">查看更多</a>
			</div>
		</div>
	</div>
</body>
<script>
var g_xctype = "1";//1人员密集场所巡查2三少场所巡查3工业企业巡查
var _type = '${unit.type}';
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
	$("#wtjd").css({
		"height" : g_hei-50
	});
})

function swichTab(num){
	if(num=="1"){
		$("#xxtx_div").hide();
		$("#xxtxTab").removeClass();
		$("#wtjd_div").show();
		$("#wtjdTab").addClass("changeColor");
	}else{
		$("#xxtx_div").show();
		$("#xxtxTab").addClass("changeColor");
		$("#wtjd_div").hide();
		$("#wtjdTab").removeClass();
	}
}

function toDetail(xunchaId) {
	window.location.href="/wx/gly/form?xunchaId="+xunchaId+"&userId="+'${wxUser.userId}';
}

</script>
</html>
