package cn.dlj.wx.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.dlj.utils.WxConfig;
import cn.dlj.wx.entity.AccessToken;

public class TokenThread implements Runnable {

	public static AccessToken accessToken = null;

	public void run() {
		while (true) {
			try {
				accessToken = this.getAccessToken();
				if (null != accessToken) {
					System.out.println(accessToken.getAccessToken());
					Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒

				} else {
					Thread.sleep(1000 * 3); //获取的access_token为空 休眠3秒
				}
			} catch (Exception e) {
				System.out.println("发生异常：" + e.getMessage());
				e.printStackTrace();
				try {
					Thread.sleep(1000 * 10); //发生异常休眠1秒
				} catch (Exception e1) {

				}
			}
		}
	}

	/**
	 * 获取access_token
	 */
	private AccessToken getAccessToken() {
		String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", WxConfig.APP_ID, WxConfig.APP_SECRET);
		String result = cn.dlj.utils.HttpUtils.get(Url);
		System.out.println(result);
		JSONObject json = JSON.parseObject(result);
		AccessToken token = new AccessToken();
		token.setAccessToken(json.getString("access_token"));
		token.setExpiresin(json.getInteger("expires_in"));
		return token;
	}
}
