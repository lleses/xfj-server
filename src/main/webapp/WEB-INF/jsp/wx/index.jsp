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
.zgsj{text-align: center;padding: 10px;border: 3px solid #009688;color: #888;width: calc(90% - 28px);float: left;margin: 0px 5%;}
</style>
</head>
<body>
	<div id="m_main" class="bgf6">
		<div style="height: 20px;border-bottom: 1px solid #d9d9d9;background-color: #009688;"></div>
		
		<div style="float: left;color: rgba(244, 67, 54, 0.72);font-size: 13px;width: 90%;padding: 10px 5% 0px 5%;">
			<div style="float: left">
				温馨提示
			</div>
		</div>
		<!-- 上次更新时间 -->
		<div id="zgsj" class="zgsj">整改剩余时间还剩下<span id="zgsjDay" style="color:red;">5</span>天</div>
		
		
		
		
		<div style="border: 1px solid #e9e9e9;float: left;width: 90%;margin: 10px 5% 0px 5%;text-align: center;">
			<div onclick="swichTab('2')" id="xxtxTab" class="changeColor" style="padding: 10px 0px;float: left;width: calc(50% - 1px);border-right: 1px solid #e9e9e9;">消息提醒</div>
			<div onclick="swichTab('1')" id="wtjdTab" style="padding: 10px 0px;float: left;width: 50%;">问题解答</div>
		</div>
		<!-- 消息提醒 -->
		<div id="xxtx" style="overflow-y: auto;height:300px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;">
			<div class="xxtx_clo1">
				贵单位还有<span id="xxtx_num" style="color:red;">0</span>项需要整改
			</div>
		</div>
		<!-- 问题解答 -->
		<div id="wtjd" style="overflow-y: auto;height:300px;border-bottom: 1px solid #e9e9e9;border-left: 1px solid #e9e9e9;border-right: 1px solid #e9e9e9;float: left;width: 90%;margin: 0px 5%;text-align: center;display: none;">
			<div class="wtjd_clo1">
				1、问: 出租屋是否与生产、储存、经营其他物品场所设置在同一建筑物内且不符合消防安全要求的。这个需要怎么样才可以达到标准?
			</div>
			<div class="wtjd_clo2">
				答: **********
			</div>
			<div class="wtjd_clo1">
				2、 疏散通道、疏散楼梯、安全出口是否未保持畅通。这个需要怎么样才可以达到标准?
			</div>
			<div class="wtjd_clo2">
				答: **********
			</div>
			<div class="wtjd_clo1">
				3、 房间是否采用可燃材料（如夹板、纸板）间隔。这个需要怎么样才可以达到标准?
			</div>
			<div class="wtjd_clo2">
				答: **********
			</div>
		</div>
		<div id="btn" class="btn" style="padding:10px 5%; width: 90%; float: left;">
			<a href="/wx/xc/form?unitId=${unit.id }" style="font-size: 15px;line-height: 30px;height: 30px;" class="weui_btn weui_btn_primary" onclick="checkSubmit('1')">整改问题</a>
		</div>
	</div>
</body>
<script>
var g_xctype = "1";//1人员密集场所巡查2三少场所巡查3工业企业巡查
var _type = '${unit.type}';
$(function(){
	var g_hei = $(window).height()-(21+30+50+46+50+50);
	$("#xxtx").css({
		"height" : g_hei
	});
	$("#wtjd").css({
		"height" : g_hei
	});
	
	if(!!_type && _type != "undefined" && _type != "null"){
		if(_type == "11" || _type == "12" || _type == "13" || _type == "14" || _type == "15" || _type == "16"){//人员密集场所
			g_xctype = "1";
		}else if(_type == "21" || _type == "22" || _type == "23"){//三小场所
			g_xctype = "2";
		}else if(_type == "31" || _type == "32" || _type == "33" || _type == "34" || _type =="35"){
			g_xctype = "3";
		}else if(_type == "41"){
			g_xctype = "4";
		}else{//工业企业
			g_xctype = "1";
		}
	}
	addHtml();
})

function swichTab(num){
	if(num=="1"){
		$("#xxtx").hide();
		$("#xxtxTab").removeClass();
		$("#wtjd").show();
		$("#wtjdTab").addClass("changeColor");
	}else{
		$("#xxtx").show();
		$("#xxtxTab").addClass("changeColor");
		$("#wtjd").hide();
		$("#wtjdTab").removeClass();
	}
}

function addHtml(){
	var _i = 1;
	var _html = "";
	if (g_xctype == "1") {//出租屋 (11)
		if('${xuncha.xcItem1}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 出租屋是否与生产、储存、经营其他物品场所设置在同一建筑物内且不符合消防安全要求的：";
			//_html += "<span class='xxtx_clo3'>待处理</span>";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem2}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 疏散楼梯设置是否未符合要求：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem3}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 疏散通道、疏散楼梯、安全出口是否未保持畅通：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem4}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 设有电动车集中充电区是否未设置狭小空间快速灭火系统：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem5}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 房间是否采用可燃材料（如夹板、纸板）间隔：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem6}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 设置防盗网的楼层是否未开设紧急逃生口：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem7}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 应急照明灯、疏散指示标志配置是否未符合要求：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem8}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消火栓（卷盘）、灭火器配置是否未符合要求：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem9}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 独立式烟感报警器配置是否未符合要求：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem10}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 门禁系统是否未设置手动紧急开关：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem11}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 其他：";
			_html += "</div>";
			_i++;
		}
	} else if (g_xctype == "2") {//“三小”场所 (21)
		if('${xuncha.xcItem1}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 场所内是否存在违规住人：";
			//_html += "<span class='xxtx_clo3'>待处理</span>";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem2}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 设有人员住宿且采取分隔的场所是否未设置紧急逃生口";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem3}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口设置是否未符合要求：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem4}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口、疏散通道是否未保持畅通：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem5}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消防应急照明灯、疏散指示标志是否未按照要求配置：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem6}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 设置值班室、住宿场所以及建筑面积超20㎡的“三小”场所，是否未设置独立式感烟火灾报警器：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem7}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 灭火器是否未按照标准要求配置：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem8}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消防设施和灭火器材是否未保持完好有效：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem9}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 其他：";
			_html += "</div>";
			_i++;
		}
	} else if (g_xctype == "3") {//公共场所、工厂企业(31)  
		if('${xuncha.xcItem1}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 在公共场所、工厂企业内是否存在设置员工集体宿舍且不符合分隔要求的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem2}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 是否存在违章搭建占用防火间距的或影响消防车通道的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem3}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口设置和楼梯间形式是否不符合要求的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem4}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口、疏散通道是否未保持畅通：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem5}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 人员密集场所外墙门窗上是否设置影响逃生和灭火救援的障碍物的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem6}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 自动消防设施是否未保持完好有效：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem7}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消防控制室是否未持证上岗或脱离岗位的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem8}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消火栓系统、灭火器、应急灯、疏散指示标志是否未保持完好有效的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem9}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 其他：";
			_html += "</div>";
			_i++;
		}
	} else if (g_xctype == "4") {
		if('${xuncha.xcItem1}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 在公共场所、工厂企业内是否存在设置员工集体宿舍且不符合分隔要求的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem2}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 是否存在违章搭建占用防火间距的或影响消防车通道的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem3}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口设置和楼梯间形式是否不符合要求的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem4}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 安全出口、疏散通道是否未保持畅通：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem5}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 人员密集场所外墙门窗上是否设置影响逃生和灭火救援的障碍物的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem6}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 自动消防设施是否未保持完好有效：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem7}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消防控制室是否未持证上岗或脱离岗位的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem8}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 消火栓系统、灭火器、应急灯、疏散指示标志是否未保持完好有效的：";
			_html += "</div>";
			_i++;
		}
		if('${xuncha.xcItem9}'=="2"){
			_html += "<div class='xxtx_clo2'>";
			_html += _i + "、 其他：";
			_html += "</div>";
			_i++;
		}
	}
	var _zg_num = _i-1;
	if(_zg_num==0){
		$("#btn").hide();
		$("#zgsj").text("已完成整改");
	}else{
		var lastDayN = '${xuncha.lastDayN}';
		if(lastDayN=="-1"){
			$("#zgsj").text("不在整改期间内");
		}else if(lastDayN==""){
			$("#zgsj").text("不在整改期间内");
		}else{
			$("#zgsjDay").text(lastDayN);
		}
	}
	$("#xxtx_num").text(_zg_num);
	$("#xxtx").append(_html);
}

</script>
</html>
