package cn.dlj.app.entity;

/**
 * 用户
 * 
 */
public class User {

	/** ID */
	private Integer id;
	/** 用户名 */
	private String name;
	/** 账号名称 **/
	private String username;
	/** 密码 */
	private String pwd;

	/** 所属部门ID */
	private Integer depId;
	/** 所属部门名称 */
	private String depName;

	/** 所属镇区ID **/
	private Integer townId;
	/** 所属镇区 */
	private String townName;

	/** 消防站ID **/
	private Integer stationId;
	/** 用户角色ID **/
	private Integer roleId;

	/**
	 * ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 账号名称
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 账号名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 密码
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 所属部门ID
	 */
	public Integer getDepId() {
		return depId;
	}

	/**
	 * 所属部门ID
	 */
	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	/**
	 * 所属部门名称
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * 所属部门名称
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * 所属镇区ID
	 */
	public Integer getTownId() {
		return townId;
	}

	/**
	 * 所属镇区ID
	 */
	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	/**
	 * 所属镇区
	 */
	public String getTownName() {
		return townName;
	}

	/**
	 * 所属镇区
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

	/**
	 * 消防站ID
	 */
	public Integer getStationId() {
		return stationId;
	}

	/**
	 * 消防站ID
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	/**
	 * 用户角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 用户角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
