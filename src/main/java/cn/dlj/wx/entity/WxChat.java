package cn.dlj.wx.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 微信-客服
 * 
 */
public class WxChat {

	/** ID */
	private Integer id;
	private String msg;
	private Integer unitId;
	private String unitName;
	private Integer userId;
	/** 0:企业 1:巡查员 **/
	private String type;
	/** 0:未读 2:已读 **/
	private String readMsg;
	private Date ct;

	public String getCtStr() {
		String ctStr = "";
		if (ct != null) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
			ctStr = simp.format(ct);
		}
		return ctStr;
	}

	/**
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 */
	public Integer getUnitId() {
		return unitId;
	}

	/**
	 * 
	 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * 
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public String getReadMsg() {
		return readMsg;
	}

	/**
	 * 
	 */
	public void setReadMsg(String readMsg) {
		this.readMsg = readMsg;
	}

	/**
	 * 
	 */
	public Date getCt() {
		return ct;
	}

	/**
	 * 
	 */
	public void setCt(Date ct) {
		this.ct = ct;
	}

}
