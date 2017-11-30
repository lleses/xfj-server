package cn.dlj.app.entity;

import java.util.Date;

/**
 * 平台巡查审核记录
 * 
 */
public class WxXuncha {

	/** 巡查ID **/
	private int xunchaId;
	/** 审核状态: '0' 待审核 ,'1' 审核通过' , '2' 审核不通过 **/
	private int status;
	private Date ct;
	private Date et;
	/** 审核角色(1:平台巡查员 2:平台管理员) **/
	private int role;
	/** 剩余时间 **/
	private Integer lastTime;

	/** 监管单位名称 **/
	transient private String unitName;

	public String getStatusStr() {
		if (status == 1) {
			return "审核通过";
		} else if (status == 2) {
			return "审核不通过";
		} else {
			return "";
		}
	}

	/**
	 * 
	 */
	public String getLastTimeStr() {
		return "剩余" + lastTime + "天";
	}

	/**
	 * 
	 */
	public int getXunchaId() {
		return xunchaId;
	}

	/**
	 * 
	 */
	public void setXunchaId(int xunchaId) {
		this.xunchaId = xunchaId;
	}

	/**
	 * 
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 
	 */
	public void setStatus(int status) {
		this.status = status;
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

	/**
	 * 
	 */
	public Date getEt() {
		return et;
	}

	/**
	 * 
	 */
	public void setEt(Date et) {
		this.et = et;
	}

	/**
	 * 
	 */
	public int getRole() {
		return role;
	}

	/**
	 * 
	 */
	public void setRole(int role) {
		this.role = role;
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
	public Integer getLastTime() {
		return lastTime;
	}

	/**
	 * 
	 */
	public void setLastTime(Integer lastTime) {
		this.lastTime = lastTime;
	}

}
