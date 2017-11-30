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
.zgsj{text-align: center;padding: 10px;border: 3px solid #009688;color: #888;width: calc(90% - 28px);float: left;margin: 0px 5% 30px 5%;}
</style>
</head>
<body>
	<div id="m_main" class="bgf6" style="background-color: #ddd;">
		<div style="float:left;width:100%;background:white;">
			<div style="height: 20px;border-bottom: 1px solid #d9d9d9;background-color: #009688;"></div>
			<div style="float: left;color: rgba(244, 67, 54, 0.72);font-size: 13px;width: 90%;padding: 10px 5% 0px 5%;margin-bottom: 20px;">
				<img width="50" src="/res/img/xf_icon.png" style="float: left;" />
				<div style="float: left;height: 40px;line-height: 40px;">
					温馨提示:请选择单位
				</div>
			</div>
			<c:forEach var="unit" items="${units }">
				<img width="50" src="/res/img/001.png" style="float: left;margin-left: 5%;" />
				<div onclick="toUrl('${unit.id}')" class="zgsj" style="border: 3px solid #9E9E9E;background: #9a9da2;color: white;border-radius: 15px;width: calc(70% - 28px);">${unit.name }${unit.id}</div>
			</c:forEach>
			<div id="btn" class="btn" style="padding:10px 5%; width: 90%; float: left;margin-bottom: 30px;">
				<a href="/wx/xc/toBind?openId=${openId }&type=1" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary">添加绑定</a>
			</div>
		</div>
	</div>
</body>
<script>
function toUrl(p_unitId){
	location.href="/wx/xc/to_index?unitId="+p_unitId;
}
</script>
</html>
