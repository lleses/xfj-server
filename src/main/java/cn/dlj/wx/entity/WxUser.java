package cn.dlj.wx.entity;

/**
 * 微信-巡查 用户记录
 * 
 */
public class WxUser {

	/** ID */
	private Integer id;
	/** openId */
	private String openId;
	/** 监管单位 */
	private Integer unitId;
	/** 账号Id **/
	private Integer userId;
	/** 0:被监管单位 1:平台巡查员 2:平台管理员  */
	private Integer type;
	/** 账号名称(对应平台管理员，用于绑定) **/
	private String userName;
	/** 账号密码(对应平台管理员，用于绑定),md5加密 **/
	private String userPwd;

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
	public String getOpenId() {
		return openId;
	}

	/**
	 * 
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
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
	 * 0:被监管单位 1:平台巡查员 2:平台管理员
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 0:被监管单位 1:平台巡查员 2:平台管理员
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * 
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
