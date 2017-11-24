package cn.dlj.utils;

public class WxConfig {

	//------------微信相关配置---------
	/** 域名 **/
	public final static String DOMAIN_NAME = Config.get("domain.name");
	public final static String APP_ID = Config.get("appid");
	public final static String APP_SECRET = Config.get("appsecret");
	public final static String TOKEN = Config.get("token");
	/** 临时二维码 **/
	public final static String QR_SCENE = "QR_SCENE";
	/** 永久二维码 **/
	public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	/** 永久二维码(字符串) **/
	public final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
	/** 创建二维码 **/
	public final static String CREATE_TICKET_PATH = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
	/** 创建菜单 **/
	public final static String CREATE_PATH = "https://api.weixin.qq.com/cgi-bin/menu/create";

	//------------业务相关配置---------
	public final static String XUNCHA_IMG = Config.get("xuncha.img.server.path");
	/** 巡查图片保存地址 */
	public static final String XUNCHA_IMG_UPLOAD_PATH = Config.get("xuncha.img.upload.path");
	/** 建筑物图片查看地址 */
	public static final String BUILD_IMG = Config.get("build.img.server.path");
	/** 建筑物图片保存地址 */
	public static final String BUILD_IMG_UPLOAD_PATH = Config.get("build.img.upload.path");
}
