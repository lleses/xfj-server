package cn.dlj.app.entity;

import java.util.Date;

/**
 * 巡查登记归档
 * 
 */
public class XunchaArchive {

	/** ID **/
	private Integer id;
	/** 1人员密集场所巡查2三少场所巡查3工业企业巡查 **/
	private String xctype;
	/** 对应t_xcunit的ID，查看巡查时的单位信息 **/
	private Integer unitId;
	/** 单位名称 **/
	private String unitName;
	/** 社区/村ID，对应t_department **/
	private String departId;
	/** 部门名称 **/
	private String departName;
	/** 镇区ID，对应t_town **/
	private Integer townId;
	/** 镇区名称 **/
	private String townName;
	/** 巡查时间 **/
	private Date xcTime;
	/** 起初的巡查时间 **/
	private Date oxcTime;
	/** 巡查员姓名 **/
	private String xcPerson;
	/** 陪同人员 **/
	private String etPerson;
	/** 消防安全状况(1是2否) **/
	private String xcItem1;
	/** 消防安全状况(1是2否) **/
	private String xcItem2;
	/** 消防安全状况(1是2否) **/
	private String xcItem3;
	/** 消防安全状况(1是2否) **/
	private String xcItem4;
	/** 消防安全状况(1是2否) **/
	private String xcItem5;
	/** 消防安全状况(1是2否) **/
	private String xcItem6;
	/** 消防安全状况(1是2否) **/
	private String xcItem7;
	/** 消防安全状况(1是2否) **/
	private String xcItem8;
	/** 消防安全状况(1是2否) **/
	private String xcItem9;
	/** 消防安全状况(1是2否) **/
	private String xcItem10;
	/** 消防安全状况(1是2否) **/
	private String xcItem11;
	/** 消防安全状况(1是2否) **/
	private String xcItem12;
	/** 整改日期 **/
	private String rectDate;
	/** 培训人数 **/
	private String pxquantity;
	/** 培训内容(trainingA1,trainingA2,trainingA3,trainingA4,trainingA5) **/
	private String trainingA;
	/** 备注 **/
	private String meno;
	/** 分派的镇级部门ID(没有使用) */
	private Integer agnDepartId;
	/** 分派的镇级部门名称(没有使用) */
	private String agnDepartName;
	/** 
	 * -1:关门<br>
	 * 1:及格 <br>
	 * 3:不及格 <br>
	 * <br>
	 * ----微信----(巡查员7天内未审核，自动流转到管理员)<br>
	 * --巡查员审核时间7天<br>
	 * 30:被整改单位 <br>
	 * 31:巡查员 <br>
	 * 32:被整改单位  <br>
	**/
	private String flag = "1";
	/** 行动ID(没有使用) **/
	private Integer actionId;
	/** 抽查 部门ID(没有使用) **/
	private Integer ccdepId;
	/** 添加时间 **/
	private Date addTime;
	/** 修改时间 **/
	private Date modTime;
	/** 添加者ID **/
	private Integer userId;
	/** IP地址 **/
	private String addIp;
	/** 推荐分配部门(没有使用) **/
	private String fp_bumen;
	/** 住人三人以上(0:否,1:是) **/
	private String live_three;
	/** 关门次数 **/
	private String doorNum;
	/** 关门时间 **/
	private String doorTime1;
	/** 关门时间 **/
	private String doorTime2;
	/** app巡查ID(没有使用) **/
	private String app_xuncha_id;
	/** 第几批归档记录(从1开始)(用于t_xuncha_archive表) */
	private Integer archiveNum;
	/** 归档时间(用于t_xuncha_archive表) */
	private Date archiveTime;

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
	public String getXctype() {
		return xctype;
	}

	/**
	 * 
	 */
	public void setXctype(String xctype) {
		this.xctype = xctype;
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
	public String getDepartId() {
		return departId;
	}

	/**
	 * 
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
	}

	/**
	 * 
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * 
	 */
	public Integer getTownId() {
		return townId;
	}

	/**
	 * 
	 */
	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	/**
	 * 
	 */
	public String getTownName() {
		return townName;
	}

	/**
	 * 
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

	/**
	 * 
	 */
	public Date getXcTime() {
		return xcTime;
	}

	/**
	 * 
	 */
	public void setXcTime(Date xcTime) {
		this.xcTime = xcTime;
	}

	/**
	 * 
	 */
	public Date getOxcTime() {
		return oxcTime;
	}

	/**
	 * 
	 */
	public void setOxcTime(Date oxcTime) {
		this.oxcTime = oxcTime;
	}

	/**
	 * 
	 */
	public String getXcPerson() {
		return xcPerson;
	}

	/**
	 * 
	 */
	public void setXcPerson(String xcPerson) {
		this.xcPerson = xcPerson;
	}

	/**
	 * 
	 */
	public String getEtPerson() {
		return etPerson;
	}

	/**
	 * 
	 */
	public void setEtPerson(String etPerson) {
		this.etPerson = etPerson;
	}

	/**
	 * 
	 */
	public String getXcItem1() {
		return xcItem1;
	}

	/**
	 * 
	 */
	public void setXcItem1(String xcItem1) {
		this.xcItem1 = xcItem1;
	}

	/**
	 * 
	 */
	public String getXcItem2() {
		return xcItem2;
	}

	/**
	 * 
	 */
	public void setXcItem2(String xcItem2) {
		this.xcItem2 = xcItem2;
	}

	/**
	 * 
	 */
	public String getXcItem3() {
		return xcItem3;
	}

	/**
	 * 
	 */
	public void setXcItem3(String xcItem3) {
		this.xcItem3 = xcItem3;
	}

	/**
	 * 
	 */
	public String getXcItem4() {
		return xcItem4;
	}

	/**
	 * 
	 */
	public void setXcItem4(String xcItem4) {
		this.xcItem4 = xcItem4;
	}

	/**
	 * 
	 */
	public String getXcItem5() {
		return xcItem5;
	}

	/**
	 * 
	 */
	public void setXcItem5(String xcItem5) {
		this.xcItem5 = xcItem5;
	}

	/**
	 * 
	 */
	public String getXcItem6() {
		return xcItem6;
	}

	/**
	 * 
	 */
	public void setXcItem6(String xcItem6) {
		this.xcItem6 = xcItem6;
	}

	/**
	 * 
	 */
	public String getXcItem7() {
		return xcItem7;
	}

	/**
	 * 
	 */
	public void setXcItem7(String xcItem7) {
		this.xcItem7 = xcItem7;
	}

	/**
	 * 
	 */
	public String getXcItem8() {
		return xcItem8;
	}

	/**
	 * 
	 */
	public void setXcItem8(String xcItem8) {
		this.xcItem8 = xcItem8;
	}

	/**
	 * 
	 */
	public String getXcItem9() {
		return xcItem9;
	}

	/**
	 * 
	 */
	public void setXcItem9(String xcItem9) {
		this.xcItem9 = xcItem9;
	}

	/**
	 * 
	 */
	public String getXcItem10() {
		return xcItem10;
	}

	/**
	 * 
	 */
	public void setXcItem10(String xcItem10) {
		this.xcItem10 = xcItem10;
	}

	/**
	 * 
	 */
	public String getXcItem11() {
		return xcItem11;
	}

	/**
	 * 
	 */
	public void setXcItem11(String xcItem11) {
		this.xcItem11 = xcItem11;
	}

	/**
	 * 
	 */
	public String getXcItem12() {
		return xcItem12;
	}

	/**
	 * 
	 */
	public void setXcItem12(String xcItem12) {
		this.xcItem12 = xcItem12;
	}

	/**
	 * 
	 */
	public String getRectDate() {
		return rectDate;
	}

	/**
	 * 
	 */
	public void setRectDate(String rectDate) {
		this.rectDate = rectDate;
	}

	/**
	 * 
	 */
	public String getPxquantity() {
		return pxquantity;
	}

	/**
	 * 
	 */
	public void setPxquantity(String pxquantity) {
		this.pxquantity = pxquantity;
	}

	/**
	 * 
	 */
	public String getTrainingA() {
		return trainingA;
	}

	/**
	 * 
	 */
	public void setTrainingA(String trainingA) {
		this.trainingA = trainingA;
	}

	/**
	 * 
	 */
	public String getMeno() {
		return meno;
	}

	/**
	 * 
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * 
	 */
	public Integer getAgnDepartId() {
		return agnDepartId;
	}

	/**
	 * 
	 */
	public void setAgnDepartId(Integer agnDepartId) {
		this.agnDepartId = agnDepartId;
	}

	/**
	 * 
	 */
	public String getAgnDepartName() {
		return agnDepartName;
	}

	/**
	 * 
	 */
	public void setAgnDepartName(String agnDepartName) {
		this.agnDepartName = agnDepartName;
	}

	/**
	 * 
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 
	 */
	public Integer getActionId() {
		return actionId;
	}

	/**
	 * 
	 */
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	/**
	 * 
	 */
	public Integer getCcdepId() {
		return ccdepId;
	}

	/**
	 * 
	 */
	public void setCcdepId(Integer ccdepId) {
		this.ccdepId = ccdepId;
	}

	/**
	 * 
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * 
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 
	 */
	public Date getModTime() {
		return modTime;
	}

	/**
	 * 
	 */
	public void setModTime(Date modTime) {
		this.modTime = modTime;
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
	public String getAddIp() {
		return addIp;
	}

	/**
	 * 
	 */
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	/**
	 * 
	 */
	public String getFp_bumen() {
		return fp_bumen;
	}

	/**
	 * 
	 */
	public void setFp_bumen(String fp_bumen) {
		this.fp_bumen = fp_bumen;
	}

	/**
	 * 
	 */
	public String getLive_three() {
		return live_three;
	}

	/**
	 * 
	 */
	public void setLive_three(String live_three) {
		this.live_three = live_three;
	}

	/**
	 * 
	 */
	public String getDoorNum() {
		return doorNum;
	}

	/**
	 * 
	 */
	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}

	/**
	 * 
	 */
	public String getDoorTime1() {
		return doorTime1;
	}

	/**
	 * 
	 */
	public void setDoorTime1(String doorTime1) {
		this.doorTime1 = doorTime1;
	}

	/**
	 * 
	 */
	public String getDoorTime2() {
		return doorTime2;
	}

	/**
	 * 
	 */
	public void setDoorTime2(String doorTime2) {
		this.doorTime2 = doorTime2;
	}

	/**
	 * 
	 */
	public String getApp_xuncha_id() {
		return app_xuncha_id;
	}

	/**
	 * 
	 */
	public void setApp_xuncha_id(String app_xuncha_id) {
		this.app_xuncha_id = app_xuncha_id;
	}

	/**
	 * 
	 */
	public Integer getArchiveNum() {
		return archiveNum;
	}

	/**
	 * 
	 */
	public void setArchiveNum(Integer archiveNum) {
		this.archiveNum = archiveNum;
	}

	/**
	 * 
	 */
	public Date getArchiveTime() {
		return archiveTime;
	}

	/**
	 * 
	 */
	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

}
