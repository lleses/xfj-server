package cn.dlj.app.entity;

import java.util.Date;

/**
 * 巡查登记
 * 
 */
public class XunchaImg {

	/** ID **/
	private Integer id;
	/** 巡查ID **/
	private Integer xunchaId;
	/** 图片名称 **/
	private String picName;
	/** 说明 **/
	private String intro;
	/** 经度 **/
	private String longitude;
	/** 纬度 **/
	private String latitude;
	/** 1电脑上传2手机上传 **/
	private String flag;
	/** 添加时间 **/
	private Date addTime;

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
	 * 巡查ID
	 */
	public Integer getXunchaId() {
		return xunchaId;
	}

	/**
	 * 巡查ID
	 */
	public void setXunchaId(Integer xunchaId) {
		this.xunchaId = xunchaId;
	}

	/**
	 * 图片名称
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * 图片名称
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * 说明
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * 说明
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * 经度
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 纬度
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 1电脑上传2手机上传
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 1电脑上传2手机上传
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
