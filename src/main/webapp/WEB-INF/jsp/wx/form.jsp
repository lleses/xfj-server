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

<link rel="stylesheet" type="text/css" href="/res/upload/css/cropper.min.css">
<link rel="stylesheet" type="text/css" href="/res/upload/css/mui.min.css">
<link rel="stylesheet" type="text/css" href="/res/upload/css/sweet-alert.css">
<script type="text/javascript" src="/res/upload/js/sweet-alert.min.js"></script>
<script type="text/javascript" src="/res/upload/js/lrz.mobile.min.js"></script>
<script type="text/javascript" src="/res/upload/dist/lrz.all.bundle.js"></script>
<script type="text/javascript" src="/res/upload/js/cropper.min.js"></script>
</head>
<style>
.weui-picker-modal .picker-items {font-size: 17px;}
.toolbar .title {font-size: 15px;}
.toolbar .picker-button{font-size: 15px;}
.weui_cell {padding: 10px 0px;margin: 0px 6%;}
input{border: 0px;}
a:link {color: #fff;text-decoration: none;}   
a:visited {color: #fff;text-decoration: none;}   
a:hover {color: #999999;text-decoration: none;} 
</style>
</head>
<body>
	<div id="m_main">
				<!-- 上传图片、预览 -->
				<div style="width: 80%;margin: 0 auto;margin-top: 10px;display:none">
	            	<div id='elapsedTime' style="margin-bottom: 10px"></div>
	            		<p id='zipResult'></p>
	           		<div id='ua'></div>
	        	</div>
	       		<div id="loading" style="display:none;background:url(/res/img/spin.gif) no-repeat center center;position:absolute;width:50px;height:50px;z-index:11;left: calc(50% - 25px);top: 150px;"></div>
	       		<div id="showEdit" style="display: none;width:100%;height: 100%;position: fixed;top:0;left: 0;z-index: 9;">
			        <div style="width:100%;position: absolute;top:10px;left:0px;">
			            <button class="mui-btn" data-mui-style="fab" id='cancleBtn' style="margin-left: 10px;">取消</button>
			            <button class="mui-btn" data-mui-style="fab" data-mui-color="primary" id='confirmBtn' style="float:right;margin-right: 10px;">确定</button>
			        </div>
			        <div id="report">
			             <img style="width: 300px;height:300px"> 
			        </div>
			    </div>
	       		<!-- 上传图片、预览 -->
	       		
		<div class="linfo" id="formContent">
			<div class="weui_cells weui_cells_form">
				<div class="weui_cells_title">单位基本情况</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">单位名称</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary" id="unitName">${xuncha.unitName}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">所属社区</label>
					</div>
					<div id="sssq" class="weui_cell_bd weui_cell_primary">${unit.townName}--${unit.departmentName}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">单位类型</label>
					</div>
					<div id="typeName" class="weui_cell_bd weui_cell_primary"></div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">单位地址</label>
					</div>
					<div id="address" class="weui_cell_bd weui_cell_primary">${unit.address}</div>
				</div>

				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">消防安全责任人</label>
					</div>
					<div id="safedLinkman" class="weui_cell_bd weui_cell_primary">${unit.safedLinkman}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">联系电话</label>
					</div>
					<div id="safedTelphone" class="weui_cell_bd weui_cell_primary">${unit.safedTelphone}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">营业执照</label>
					</div>
					<div id="license" class="weui_cell_bd weui_cell_primary">${unit.license}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">消防安全管理人</label>
					</div>
					<div id="manageLinkman" class="weui_cell_bd weui_cell_primary">${unit.manageLinkman}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">联系电话</label>
					</div>
					<div id="manageTelphone" class="weui_cell_bd weui_cell_primary">${unit.manageTelphone}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">单位面积</label>
					</div>
					<div id="area" class="weui_cell_bd weui_cell_primary">${unit.area}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">所在层数</label>
					</div>
					<div id="floor" class="weui_cell_bd weui_cell_primary">${unit.buildingsLayer }</div>
				</div>
				<div class="weui_cell" style="border-bottom: 0px;">
					<div class="weui_cell_hd">
						<label class="weui_label">单位编码</label>
					</div>
					<div id="code" class="weui_cell_bd weui_cell_primary">${unit.code }</div>
				</div>
				<div class="weui_cells_title">巡查情况</div>
				<div class="weui_cell" style="border-bottom:0px;">
					<div class="weui_cell_hd">
						<label class="weui_label">巡查时间&nbsp;<b>*</b></label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
					<f:formatDate value="${xuncha.xcTime }" pattern="yyyy-MM-dd" />
					</div>
				</div>
				<div class="weui_cells_title">消防安全状况</div>
				<div id="unitType"></div>

				<div class="weui_cells_title">其他信息</div>
				<div id="liveThreeDiv" class="weui_cell weui_cell_switch" style="border-bottom: 1px solid #d9d9d9;">
					<div class="weui_cell_hd weui_cell_primary" style="font-size: 16px;">住人三人以上</div>
					<div class="weui_cell_bd weui_cell_primary">
						<div style="float: left;">
							<input id="liveThree0" value="0" type="radio" name="liveThree" disabled="disabled" <c:if test="${xuncha.liveThree=='0' }">checked="checked"</c:if> ><label for="isControl1">否</label>
						</div>
						<div style="float: left; margin-left: 30px;">
							<input id="liveThree1" value="1" type="radio" name="liveThree" disabled="disabled" <c:if test="${xuncha.liveThree=='0' }">checked="checked"</c:if> ><label for="isControl0">是</label>
						</div>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">培训人数</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">${xuncha.pxquantity }</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="weui_label">培训内容</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary" style="border: 1px solid #c9c9c9; padding: 5px 5px 5px 10px; border-radius: 5px;">
						<div style="padding: 2px 0px;">
							<input name="trainingA" type="checkbox" value="trainingA1" id="trainingA1" disabled="disabled"><label for="trainingA1">灭火器/消火栓使用方法</label>
						</div>
						<div style="padding: 2px 0px;">
							<input name="trainingA" type="checkbox" value="trainingA2" id="trainingA2" disabled="disabled"><label for="trainingA2">自动消防设施操作方法</label>
						</div>
						<div style="padding: 2px 0px;">
							<input name="trainingA" type="checkbox" value="trainingA3" id="trainingA3" disabled="disabled"><label for="trainingA3">火灾后如何报警</label>
						</div>
						<div style="padding: 2px 0px;">
							<input name="trainingA" type="checkbox" value="trainingA4" id="trainingA4" disabled="disabled"><label for="trainingA4">组织逃生方法</label>
						</div>
						<div style="padding: 2px 0px;">
							<input name="trainingA" type="checkbox" value="trainingA5" id="trainingA5" disabled="disabled"><label for="trainingA5">其他</label>
						</div>
					</div>
				</div>
				<div class="weui_cell" style="border:0px;">
                    <div class="weui_cell_hd" style="width: 80px;">
                        <label class="weui_label">巡查图片</label>
                    </div>
                    <div id="showPhotos" class="weui_cell_bd weui_cell_primary">
                    	<c:forEach var="xcimg" items="${imgs }">
	                    	<div class='photo_main'>
								<img  name="photo" onclick="biggerPic(this)" src="${upload }${xcimg.picName }" border='0' width='70' height='70' />
							</div>
						</c:forEach>
                    </div>
                </div>
				<div id="doorTime_div" class="weui_cell" style="border-top: 1px solid #d9d9d9; border-bottom: 1px solid #d9d9d9;">
					<div class="weui_cell_hd">
						<label class="weui_label">巡查员备注</label>
					</div>
					<div class="weui_cell_bd weui_cell_primary">${xuncha.meno }</div>
				</div>
				<div class="btn" style="padding: 5%; width: 90%; float: left;margin:0px 5%;">
					<a class="weui_btn weui_btn_primary" onclick="toForm()">提交</a>
				</div>
				
				<input id="unitId" type="hidden" /> 
				<input id="uid" type="hidden" /> 
				<input id="departId" type="hidden" /> 
				<input id="departName" type="hidden" /> 
				<input id="townId" type="hidden" /> 
				<input id="townName" type="hidden" /> 
				<input id="userId" type="hidden" />
				
			</div>
		</div>
	</div>
</body>
<script>
var g_hei = $(window).height();
var g_wid = $(window).width();
var g_xctype = "1";//1人员密集场所巡查2三少场所巡查3工业企业巡查
var g_obXcItemNum = 11;
var _trainingA = '${xuncha.trainingA}';
var _type = '${unit.type}';
var _typeName = "";//单位类型名称
var g_xunchaId = '${xuncha.id}';
$(function(){
	if(!!_trainingA){
		var _arr = _trainingA.split(",");
		if(_arr.length>0){
			for (var i = 0; i < _arr.length; i++) {
				var _ob = $("#"+_arr[i]);
				if(!!_ob){
					_ob.attr("checked","checked");
				}
			}
		}
	}
	if(!!_type && _type != "undefined" && _type != "null"){
		if(_type == "11" || _type == "12" || _type == "13" || _type == "14" || _type == "15" || _type == "16"){//人员密集场所
			g_xctype = "1";
			g_obXcItemNum = 11;
			_typeName = "出租屋";
			$("#liveThreeDiv").hide();//住人三人以上,当为出租屋时这个选项隐藏
		}else if(_type == "21" || _type == "22" || _type == "23"){//三小场所
			g_xctype = "2";
			g_obXcItemNum = 9;
			_typeName = "'三小'场所";
		}else if(_type == "31" || _type == "32" || _type == "33" || _type == "34" || _type =="35"){
			g_xctype = "3";
			g_obXcItemNum = 9;
			_typeName = "公共场所";
		}else if(_type == "41"){
			g_xctype = "4";
			g_obXcItemNum = 9;
			_typeName = "工厂企业";
		}else{//工业企业
			g_xctype = "1";
			g_obXcItemNum = 11;
			_typeName = "出租屋";
			$("#liveThreeDiv").hide();//住人三人以上,当为出租屋时这个选项隐藏
		}
		$("#typeName").text(_typeName);
		//消防安全状况
		addHtml();
		
		//选择消防安全状况
		selXcItem(1, '${xuncha.xcItem1}');
		selXcItem(2, '${xuncha.xcItem2}');
		selXcItem(3, '${xuncha.xcItem3}');
		selXcItem(4, '${xuncha.xcItem4}');
		selXcItem(5, '${xuncha.xcItem5}');
		selXcItem(6, '${xuncha.xcItem6}');
		selXcItem(7, '${xuncha.xcItem7}');
		selXcItem(8, '${xuncha.xcItem8}');
		selXcItem(9, '${xuncha.xcItem9}');
		if (g_xctype == "1") {
			selXcItem(10, '${xuncha.xcItem10}');
			selXcItem(11, '${xuncha.xcItem11}');
		}
		
		
		<c:forEach items="${wxImgs }" var="wximg">
			$("#_upimg"+'${wximg.num}').attr("src", '${upload}'+'${wximg.picName}');
			$("#_div_upimg"+'${wximg.num}').show();
			
			
			if('${wximg.flag}'=='1'){
				$("#_img_flag"+'${wximg.num}').text("审核通过");
				$("#_img_xcItem"+'${wximg.num}'+"1").hide();
			}else if('${wximg.flag}'=='2'){
				$("#_img_flag"+'${wximg.num}').text("审核不通过");
			}else{
				$("#_img_flag"+'${wximg.num}').text("待审核");
			}
			$("#_img_flag"+'${wximg.num}').show();
			
			$("#_remarkdiv"+'${wximg.num}').show();
			$("#_remark"+'${wximg.num}').text('${wximg.remark}');
		</c:forEach>
	}
})


/**
 * 人员密集场所
 * 
 * read_only:true/false;
 */
function addHtml() {
	var obj = new Array();
	if (g_xctype == "1") {//出租屋 (11)
		obj.push("1、 出租屋是否与生产、储存、经营其他物品场所设置在同一建筑物内且不符合消防安全要求的：");
		obj.push("2、 疏散楼梯设置是否未符合要求：");
		obj.push("3、 疏散通道、疏散楼梯、安全出口是否未保持畅通：");
		obj.push("4、 设有电动车集中充电区是否未设置狭小空间快速灭火系统：");
		obj.push("5、 房间是否采用可燃材料（如夹板、纸板）间隔：");
		obj.push("6、 设置防盗网的楼层是否未开设紧急逃生口：");
		obj.push("7、 应急照明灯、疏散指示标志配置是否未符合要求：");
		obj.push("8、 消火栓（卷盘）、灭火器配置是否未符合要求：");
		obj.push("9、 独立式烟感报警器配置是否未符合要求：");
		obj.push("10、 门禁系统是否未设置手动紧急开关：");
		obj.push("11、 其他：");
	} else if (g_xctype == "2") {//“三小”场所 (21)
		obj.push("1、 场所内是否存在违规住人：");
		obj.push("2、 设有人员住宿且采取分隔的场所是否未设置紧急逃生口");
		obj.push("3、 安全出口设置是否未符合要求：");
		obj.push("4、 安全出口、疏散通道是否未保持畅通：");
		obj.push("5、 消防应急照明灯、疏散指示标志是否未按照要求配置：");
		obj.push("6、 设置值班室、住宿场所以及建筑面积超20㎡的“三小”场所，是否未设置独立式感烟火灾报警器：");
		obj.push("7、 灭火器是否未按照标准要求配置：");
		obj.push("8、 消防设施和灭火器材是否未保持完好有效：");
		obj.push("9、 其他：");
	} else if (g_xctype == "3") {//公共场所、工厂企业(31)  
		obj.push("1、 在公共场所、工厂企业内是否存在设置员工集体宿舍且不符合分隔要求的：");
		obj.push("2、 是否存在违章搭建占用防火间距的或影响消防车通道的：");
		obj.push("3、 安全出口设置和楼梯间形式是否不符合要求的：");
		obj.push("4、 安全出口、疏散通道是否未保持畅通：");
		obj.push("5、 人员密集场所外墙门窗上是否设置影响逃生和灭火救援的障碍物的：");
		obj.push("6、 自动消防设施是否未保持完好有效：");
		obj.push("7、 消防控制室是否未持证上岗或脱离岗位的：");
		obj.push("8、 消火栓系统、灭火器、应急灯、疏散指示标志是否未保持完好有效的：");
		obj.push("9、 其他：");
	} else if (g_xctype == "4") {
		obj.push("1、 在公共场所、工厂企业内是否存在设置员工集体宿舍且不符合分隔要求的：");
		obj.push("2、 是否存在违章搭建占用防火间距的或影响消防车通道的：");
		obj.push("3、 安全出口设置和楼梯间形式是否不符合要求的：");
		obj.push("4、 安全出口、疏散通道是否未保持畅通：");
		obj.push("5、 人员密集场所外墙门窗上是否设置影响逃生和灭火救援的障碍物的：");
		obj.push("6、 自动消防设施是否未保持完好有效：");
		obj.push("7、 消防控制室是否未持证上岗或脱离岗位的：");
		obj.push("8、 消火栓系统、灭火器、应急灯、疏散指示标志是否未保持完好有效的：");
		obj.push("9、 其他：");
	}

	var htm = "";
	for (var i = 0; i < g_obXcItemNum; i++) {
		var _show = "show";
		if(g_xctype == "1" && obj[i] == "8、 消火栓（卷盘）、灭火器配置是否未符合要求："){
			_show = "hide";
		}else if(g_xctype == "1" && obj[i] == "9、 独立式烟感报警器配置是否未符合要求："){
			_show = "hide";
		}else if(g_xctype == "2" && obj[i] == "6、 设置值班室、住宿场所以及建筑面积超20㎡的“三小”场所，是否未设置独立式感烟火灾报警器："){
			_show = "hide";
		}else if(g_xctype == "2" && obj[i] == "7、 灭火器是否未按照标准要求配置："){
			_show = "hide";
		}else if(g_xctype == "2" && obj[i] == "8、 消防设施和灭火器材是否未保持完好有效："){
			_show = "hide";
		}
		
		if (i == 0) {
			htm += "<div class='weui_cell' style='border-bottom:0px;'>";
		} else {
			htm += "<div class='weui_cell' style='border-top:1px solid #e6e6e6;border-bottom:0px;'>";
		}
		htm += "<label class='weui_label' style='width: 100%;color: #6299b5;'>" + obj[i] + "</label>";
		htm += "</div>";
		
		htm += "<div class='weui_cell' style='border-bottom:0px;'>";
			htm += "<div style='padding: 5px 0px 5px 10px;font-size: 15px;'>";
				htm += "<input value='1' type='radio' name='xcItem" + (i + 1) + "' id='xcItem" + (i + 1) + "1' checked='checked' onchange=\"xcItemImg('xcItem" + (i + 1) + "1','hide')\"  ><label for='xcItem" + (i + 1) + "1'>否</label>";
			htm += "</div>";
			htm += "<div style='float: left;margin-left: 15px;'>";
				htm += "<input value='2' type='radio' name='xcItem" + (i + 1) + "' id='xcItem" + (i + 1) + "2' onchange=\"xcItemImg('xcItem" + (i + 1) + "1','"+_show+"')\" ><label for='xcItem" + (i + 1) + "2'>是</label>";
			htm += "</div>";
			htm += "<div id='_img_xcItem" + (i + 1) + "1' class='btn' style='padding: 10px 0px 10px 10px;display: none;'>";
				htm += "<a class='weui_btn weui_btn_primary' style='height: 25px;font-size: 13px;line-height: 25px;'>上传图片</a>";
				htm += "<input id='_uploadimg_xcItem" + (i + 1) + "1' type='file' accept='image/*' style='height: 25px;font-size: 13px;line-height: 25px;opacity: 0;margin-top: -25px;width: 100px;'/>";
			htm += "</div>";
			htm += "<div id='_img_flag" + (i + 1) + "' style='margin-left:10px;font-size: 11px;border: 1px solid red;padding: 5px;display:none;'></div>";
		htm += "</div>";
		
		htm += "<div id='_remarkdiv" + (i + 1) + "' class='weui_cell' style='border-top: 1px solid #e6e6e6;display:none;'>";
			htm += "<div class='weui_cell_hd'>";
				htm += "<label class='weui_label'>审核意见</label>";
			htm += "</div>";
			htm += "<div id='_remark" + (i + 1) + "' class='weui_cell_bd weui_cell_primary'></div>";
		htm += "</div>";
	
		htm += "<div id='_div_upimg" + (i + 1) + "' class='weui_cell' style='border-bottom:0px;display:none;'>";
			htm += "<img id='_upimg" + (i + 1) + "' onclick=\"biggerPic(this)\" width='120' height='120' src='' />";
		htm += "</div>";
	}
	$("#unitType").append(htm);
}
 
//消费安全状况触发上传图片功能
function xcItemImg(p_id, p_show) {
	if (p_show == "show") {
		$("#_img_" + p_id).show();
	} else {
		$("#_img_" + p_id).hide();
	}
}

//选中
function selXcItem(p_i, p_v) {
	p_v = (p_v == "1" ? "1" : "2");
	$("#xcItem" + p_i + "1").attr("disabled","disabled");
	$("#xcItem" + p_i + "2").attr("disabled","disabled");
	var _obj = $("#xcItem" + p_i + p_v);
	if (_obj != null && _obj.length > 0) {
		_obj[0].checked = true;
	}
	if(p_v == "2"){
		$("#_img_xcItem" + p_i + "1").show();
	}
}

function toForm(){
	var param={
		xunchaId:'${xuncha.id}',
		unitName:'${unit.name}'
	}
	$.post("/wx/xc/save",param,function(rs){
		$.alert("提交成功", function() {
			window.location.href="/wx/xc/to_index?unitId=${unit.id}";
		});
	})
}



$(function() {
	var imgId=null;
    function toFixed2(num) {
        return parseFloat(+num.toFixed(2));
    }

    $('#ua').html('UA: ' + navigator.userAgent);

    $('#cancleBtn').on('click', function() {
        $("#showEdit").fadeOut();
        $('#showResult').fadeIn();
    });

    $('#confirmBtn').on('click', function() {
        $("#showEdit").fadeOut();

        var $image = $('#report > img');
        var dataURL = $image.cropper("getCroppedCanvas");
        var imgurl = dataURL.toDataURL("image/jpeg", 0.5);
        $("#_upimg"+imgId).attr("src", imgurl);
        $("#_div_upimg"+imgId).show();
        
        $('#showResult').fadeIn();

        var imgStr = imgurl.substring(23);
       	$.post("/wx/xc/uploadImg",{"imgStr":imgStr,"num":imgId,"xunchaId":g_xunchaId},function(rs){
        	if(rs=="1"){
        		swal("图片上传成功", "", "success");
        	}
        });
        
    });

    function cutImg() {
        $('#showResult').fadeOut();
        $("#showEdit").fadeIn();
        $("#loading").hide();
        var $image = $('#report > img');
        $image.cropper({
            aspectRatio: 1 / 1,
            autoCropArea: 0.7,
            strict: true,
            guides: false,
            center: true,
            highlight: false,
            dragCrop: false,
            cropBoxMovable: false,
            cropBoxResizable: false,
            zoom: -0.2,
            checkImageOrigin: true,
            background: false,
            minContainerHeight: g_hei,
            minContainerWidth: g_wid
        });
    }

    function doFinish(startTimestamp, sSize, rst) {
        var finishTimestamp = (new Date()).valueOf();
        var elapsedTime = (finishTimestamp - startTimestamp);
        $('#elapsedTime').text('压缩耗时： ' + elapsedTime + 'ms');

        var sourceSize = toFixed2(sSize / 1024),
            resultSize = toFixed2(rst.base64Len / 1024),
            scale = parseInt(100 - (resultSize / sourceSize * 100));

        var p = document.getElementById('zipResult');
        p.style.fontSize = 13 + 'px';
        p.innerHTML = '源文件：<span style="color: #ff4136;">' +
            sourceSize + 'KB' +
            '</span> <br />' +
            '压缩后传输Base64：<span style="color: #28b62c;">' +
            resultSize + 'KB (省' + scale + '%)' +
            '</span> ';


        $("#report").html('<img src="' + rst.base64 + '" style="width: 100%;height:100%">');
        cutImg();
    }

    $("#_uploadimg_xcItem11").on('change', function() {
		imgId = "1";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem21").on('change', function() {
		imgId = "2";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem31").on('change', function() {
		imgId = "3";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem41").on('change', function() {
		imgId = "4";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem51").on('change', function() {
		imgId = "5";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem61").on('change', function() {
		imgId = "6";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem71").on('change', function() {
		imgId = "7";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem81").on('change', function() {
		imgId = "8";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem91").on('change', function() {
		imgId = "9";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem101").on('change', function() {
		imgId = "10";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
    $("#_uploadimg_xcItem111").on('change', function() {
		imgId = "11";
    	$("#loading").show();
        var startTimestamp = (new Date()).valueOf();
        var that = this;

        lrz(this.files[0], {
                width: 800,
                height: 800,
                quality: 0.9
            })
            .then(function(rst) {
                doFinish(startTimestamp, that.files[0].size, rst);
                return rst;
            })
            .then(function(rst) {
                return rst;
            })
            .then(function(rst) {
            })
            .always(function() {
            });
    });
});

</script>
</html>
