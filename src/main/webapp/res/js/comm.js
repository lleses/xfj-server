var server_url = "http://47.92.68.227:8080";
// var server_url = "http://192.168.1.102";
var server_img_url = "http://xiaofang.64091.com/Public/Uploads/";

/** 
 * 放大图片 
 * 
 * @param p
 *     img标签对象
 */
function biggerPic(p_this){
	var bigPhoto = "<div onclick=\"closeBigPic()\" class=\"overlay\"></div>"
	bigPhoto += "<div onclick=\"closeBigPic()\" id=\"pic_div\" style=\"display:none;position:fixed;top:10%;left:10%;z-index:9999;\"><img id=\"big_pic\" border=\"0\" src=\""+p_this.src+"\"/></div>";
	$("body").append(bigPhoto);
	$(".overlay").show();
	$("#pic_div").show().fadeIn(500);
	
	var ww = g_wid;
	var wh = g_hei;
	$("#pic_div").width(ww);
	$("#big_pic").width(ww*0.95);
	var pic_left = ($("#pic_div").width() - $("#big_pic").width())/2;
	var pic_top = "10%";
	if($("#pic_div").height() != 0){
		pic_top = (wh - $("#pic_div").height())/2;
	}
	$("#pic_div").css({"left":pic_left,"top":pic_top})
	
}

/** 关闭放大图片 */
function closeBigPic(){
	$("#pic_div").fadeOut(500).remove();
	$(".overlay").hide().remove();
}