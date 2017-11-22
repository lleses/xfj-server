//package cn.dlj.wx.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import cn.dlj.utils.HttpUtils;
//import cn.dlj.utils.StringUtils;
//
//public class AdvancedUtil {
//
//	/**
//	 * 获取网页授权凭证
//	 * 
//	 * @param appId 公众账号的唯一标识
//	 * @param appSecret 公众账号的密钥
//	 * @param code
//	 * @return WeixinAouth2Token
//	 */
//	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
//		WeixinOauth2Token wat = null;
//		// 拼接请求地址
//		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//		requestUrl = requestUrl.replace("APPID", appId);
//		requestUrl = requestUrl.replace("SECRET", appSecret);
//		requestUrl = requestUrl.replace("CODE", code);
//		// 获取网页授权凭证
//		String json = HttpUtils.get(requestUrl);
//		if (null != json) {
//			try {
//				ObjectMapper mapper = StringUtils.MAPPER;
//				wat = mapper.readValue(json, WeixinOauth2Token.class);
//			} catch (Exception e) {
//			}
//		}
//		return wat;
//	}
//}
