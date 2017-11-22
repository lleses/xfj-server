package cn.dlj.wx.entity;

public class WechatQRCode {
	private String ticket;
	private int expire_seconds;
	private int url;

	/**
	 * 
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * 
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * 
	 */
	public int getExpire_seconds() {
		return expire_seconds;
	}

	/**
	 * 
	 */
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	/**
	 * 
	 */
	public int getUrl() {
		return url;
	}

	/**
	 * 
	 */
	public void setUrl(int url) {
		this.url = url;
	}

}
