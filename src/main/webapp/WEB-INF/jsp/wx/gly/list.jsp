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
</style>
</head>
<body>
	<div id="m_main">
		<!-- 头部 -->
		<div class="list_top">
                <input id="qry_unit_name" type="text" placeholder="请输入搜索关键字">
                <span onclick="loadUnitDb(true);" style="width: 40px;padding: 0px 10px;">搜索</span>
         </div>
		<!-- 标题 -->
		<div class="ltitle">
			<span style="width: 100%;">监管单位</span>
		</div>
		<!-- 数据 -->
		<div id="data_list" class="ldatas" style="overflow: auto; height: 512px;"></div>
		<!--加载更多数据-->
        <div id="load_more" class="load_more" onclick="loadUnitDb(false);" >
                <a   class="weui_btn weui_btn_primary" >加载更多数据</a>
        </div>
         <!--已无更多数据-->
        <div id="no_more" class="no_more">
                已无更多数据
        </div>
        <input id="inp_userId" type="hidden" value="${userId }">
	</div>
</body>
<script>
var g_currentPage = 0;
var g_statusType = '${statusType}';
$(function(){
	var hei = ($(window).height() - 78 - 44 - 41) + "px";
	$("#data_list").css({
		"height" : hei
	});
	loadUnitDb(true);
})

function loadUnitDb(isQry) {
	var _unitName = $.trim($("#qry_unit_name").val());
	if (isQry) {
		g_currentPage = 0;
		$("#data_list").empty();
	}
	
	$.showLoading();
	g_currentPage++;
	
	console.log("post:"+g_currentPage+",name:"+_unitName);
	var param={
       	"currentPage": g_currentPage,
       	"unitName": _unitName,
       	"statusType": g_statusType,
       	"userId":$("#inp_userId").val(),
         "t": new Date().getTime()
	}
	
	$.post("/wx/gly/list_data",param,function(rs){
		$.hideLoading();
		var data = eval("("+rs+")");
		load_db_callback(data);
	})
}
	
//查询后回调
function load_db_callback(datas) {
	var _data = "";
	if (datas.length == 0) {
		$("#load_more").hide();
		$("#no_more").show();
	} else {
		for (var i = 0; i < datas.length; i++) {
			_data += "<div class='ldata' onclick='toDetail(\"" + datas[i].xunchaId + "\")'>";
			_data += "<span style='width:100%;text-align:left;'>" + datas[i].unitName + "</span>";
			_data += "</div>";
		}
		$("#data_list").append(_data);
		$("#load_more").show();
		$("#no_more").hide();
	}
}

function toDetail(xunchaId) {
	window.location.href="/wx/gly/form?xunchaId="+xunchaId;
}


</script>
</html>
