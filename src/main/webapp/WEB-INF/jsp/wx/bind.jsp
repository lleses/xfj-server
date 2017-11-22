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
.weui-picker-modal .picker-items{font-size:17px}
.toolbar .title{font-size:15px}
.toolbar .picker-button{font-size:15px}
.weui_cell{padding:10px 0px;margin:0px 6%}
input{border:0px}
a:link{color:#fff;text-decoration:none}
a:visited{color:#fff;text-decoration:none}
a:hover{color:#999999;text-decoration:none}
</style>
</head>
<body>
	<div id="m_main">
		<div class="linfo" id="formContent">
			<div class="weui_cells weui_cells_form">
				<div class="weui_cells_title">账号绑定</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">账号类型</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
						<c:choose>
							<c:when test="${type=='1'}">
								<select id="wxUserType" onchange="selectOnchange(this)">
									<option value="0">社会单位</option>
								</select>
							</c:when>
							<c:otherwise>
								<select id="wxUserType" onchange="selectOnchange(this)">
									<option value="0">社会单位</option>
									<option value="1">平台巡查员</option>
									<option value="2">平台管理员</option>
								</select>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="weui_cell" id="username_div" style="display: none;">
					<div class="weui_cell_hd">
						<label class="weui_label">账号</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
						<input class="weui_input" type="text" placeholder="请输入账号" id="username">
					</div>
				</div>
				<div class="weui_cell" id="pwd_div" style="display: none;">
					<div class="weui_cell_hd">
						<label class="weui_label">密码</label>
					</div>
					<div id="sssq" class="weui_cell_bd weui_cell_primary">
						<input class="weui_input" type="password" placeholder="请输入密码" id="pwd">
					</div>
				</div>
				<div class="weui_cell" id="yzm_div">
					<div class="weui_cell_hd">
						<label class="weui_label">验证码</label>
					</div>
					<div id="sssq" class="weui_cell_bd weui_cell_primary">
						<input class="weui_input" type="text" placeholder="请输入验证码" id="yzm">
					</div>
				</div>
				<div class="btn" style="padding: 5%; width: 90%; float: left;">
					<a class="weui_btn weui_btn_primary" onclick="toForm()">绑定</a>
				</div>
				<input id="openId" type="hidden" value="${openId }" />
			</div>
		</div>
	</div>
</body>
<script>
	function selectOnchange(p_this){
		if(p_this.value=="0"){
			$("#yzm_div").show();
			$("#username_div").hide();
			$("#pwd_div").hide();
		}else{
			$("#yzm_div").hide();
			$("#username_div").show();
			$("#pwd_div").show();
		}
	}
	
	function toForm() {
		var _username = $.trim($("#username").val());
		var _pwd = $.trim($("#pwd").val());
		var _yzm = $.trim($("#yzm").val());
		var _wxUserType = $("#wxUserType").val();
		var _errMsg = null;
		if(_wxUserType == "0"){
			if(!_yzm){
				_errMsg = "验证码不能为空";
			}
		}else{
			if (!_username) {
				_errMsg = "账号不能为空";
			} else if (!_pwd) {
				_errMsg = "密码不能为空";
			}
		}
		if (_errMsg != null) {
			$.alert(_errMsg);
			return;
		}

		$.confirm("确认是否绑定?", function() {
			$.showLoading();
			var _param = {
					username : _username,
					pwd : _pwd,
					yzm : _yzm,
					openId : $("#openId").val(),
					wxUserType : _wxUserType
				}
			$.post("/wx/xc/userBind", _param, function(rs) {
				$.hideLoading();
				if(rs=="-1") {
					$.alert("账号绑定失败,账号密码不能为空");
				} else if(rs=="-2") {
					$.alert("账号绑定失败,openId为空");
				} else if(rs=="-3") {
					$.alert("账号绑定失败,账号密码不对");
				} else if(rs=="-4") {
					$.alert("绑定失败");
				} else if(rs=="-5") {
					$.alert("账号绑定失败,验证码不正确");
				} else {
					$.alert("绑定成功",function(){
						if(_wxUserType=="0"){
							//window.location.href="/wx/xc/to_index?unitId="+rs;
							window.location.href="/wx/selUnit?unitIds="+rs+"&openId="+$("#openId").val();
						}else if(_wxUserType == "1"){
							window.location.href="/wx/admin/xc/index";
						}else{
							window.location.href="/wx/gly/index";
						}
					});
				}
			});
		},function(){});
	}
</script>
</html>
