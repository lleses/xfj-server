package cn.dlj.app.entity;

import java.util.List;

/**
 * app同步提交的数据
 * 
 */
public class AppSysnData {

	/** 所属镇区ID **/
	private Integer townId;
	/** 所属社区ID **/
	private Integer departmentId;
	/** 消防站ID **/
	private Integer stationId;
	/** 账号ID **/
	private Integer userId;
	/** 账号名称 **/
	private String userName;
	/** 建筑物信息 **/
	private List<Building> building;
	/** 监管单位(巡查单位) **/
	private List<Unit> unit;
	/** 巡查登记 **/
	private List<Xuncha> Xuncha;
	/** 只提交数据不下载数据(1:是，0否) **/
	private String onlySubmit;

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
	 * 所属社区ID
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * 所属社区ID
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	 * 建筑物信息
	 */
	public List<Building> getBuilding() {
		return building;
	}

	/**
	 * 建筑物信息
	 */
	public void setBuilding(List<Building> building) {
		this.building = building;
	}

	/**
	 * 监管单位(巡查单位)
	 */
	public List<Unit> getUnit() {
		return unit;
	}

	/**
	 * 监管单位(巡查单位)
	 */
	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

	/**
	 * 巡查登记
	 */
	public List<Xuncha> getXuncha() {
		return Xuncha;
	}

	/**
	 * 巡查登记
	 */
	public void setXuncha(List<Xuncha> xuncha) {
		Xuncha = xuncha;
	}

	/**
	 * 账号ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 账号ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 账号名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 账号名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 只提交数据不下载数据(1:是，0否)
	 */
	public String getOnlySubmit() {
		return onlySubmit;
	}

	/**
	 * 只提交数据不下载数据(1:是，0否)
	 */
	public void setOnlySubmit(String onlySubmit) {
		this.onlySubmit = onlySubmit;
	}

}
