package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dlj.utils.DateUtils;
import cn.dlj.utils.WxConfig;

/**
 * 巡查登记
 * 
 */
public class Xuncha {

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
	/** 备注 **/
	private String meno;
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
	/** 添加时间 **/
	private Date addTime;
	/** 修改时间 **/
	private Date modTime;
	/** 添加者ID **/
	private Integer userId;
	/** IP地址 **/
	private String addIp;
	/** 培训人数 **/
	private String pxquantity;
	/** 培训内容(trainingA1,trainingA2,trainingA3,trainingA4,trainingA5) **/
	private String trainingA;
	/** 住人三人以上(0:否,1:是) **/
	private String liveThree;
	/** 关门次数 **/
	private String doorNum;
	/** 关门时间 **/
	private String doorTime1;
	/** 关门时间 **/
	private String doorTime2;
	/** 图片basc64 **/
	transient private String img64;
	/** 完整图片地址 */
	transient private String serverBimg = "";
	/** app监管单位ID **/
	transient private String uid;
	/** app巡查ID **/
	transient private String appXunchaId;

	public String getXcTimeStr() {
		String format = "";
		if (xcTime != null) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			format = simple.format(xcTime);
		}
		return format;
	}

	/**
	 * 完整图片地址
	 */
	public String getServerBimg() {
		if (null != img64 && !"".equals(img64.trim()) && img64 != "null" && img64 != "undefined") {
			String[] imgs = img64.split(",", -1);
			for (String img : imgs) {
				serverBimg += "," + WxConfig.XUNCHA_IMG + img;
			}
			serverBimg = serverBimg.substring(1);
		}
		return serverBimg;
	}

	public String getLastDay() {
		if (xcTime == null) {
			return "";
		}
		Date sevenDay = DateUtils.nextDay(xcTime, 7);
		int n = DateUtils.compareDate(new Date(), sevenDay);
		if (n > 0) {
			return "已过期";
		} else {
			int numDay = DateUtils.differentDaysByMillisecond(new Date(), sevenDay);
			return numDay + "天";
		}
	}

	public String getLastDayN() {
		if (xcTime == null) {
			return "";
		}
		Date sevenDay = DateUtils.nextDay(xcTime, 7);
		int n = DateUtils.compareDate(new Date(), sevenDay);
		if (n > 0) {
			return "-1";
		} else {
			int numDay = DateUtils.differentDaysByMillisecond(new Date(), sevenDay);
			return numDay + "";
		}
	}

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
	 * 1人员密集场所巡查2三少场所巡查3工业企业巡查
	 */
	public String getXctype() {
		return xctype;
	}

	/**
	 * 1人员密集场所巡查2三少场所巡查3工业企业巡查
	 */
	public void setXctype(String xctype) {
		this.xctype = xctype;
	}

	/**
	 * 对应t_xcunit的ID，查看巡查时的单位信息
	 */
	public Integer getUnitId() {
		return unitId;
	}

	/**
	 * 对应t_xcunit的ID，查看巡查时的单位信息
	 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * 单位名称
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 单位名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 社区/村ID，对应t_department
	 */
	public String getDepartId() {
		return departId;
	}

	/**
	 * 社区/村ID，对应t_department
	 */
	public void setDepartId(String departId) {
		this.departId = departId;
	}

	/**
	 * 部门名称
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * 部门名称
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * 镇区ID，对应t_town
	 */
	public Integer getTownId() {
		return townId;
	}

	/**
	 * 镇区ID，对应t_town
	 */
	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	/**
	 * 镇区名称
	 */
	public String getTownName() {
		return townName;
	}

	/**
	 * 镇区名称
	 */
	public void setTownName(String townName) {
		this.townName = townName;
	}

	/**
	 * 巡查时间
	 */
	public Date getXcTime() {
		return xcTime;
	}

	/**
	 * 巡查时间
	 */
	public void setXcTime(Date xcTime) {
		this.xcTime = xcTime;
	}

	/**
	 * 起初的巡查时间
	 */
	public Date getOxcTime() {
		return oxcTime;
	}

	/**
	 * 起初的巡查时间
	 */
	public void setOxcTime(Date oxcTime) {
		this.oxcTime = oxcTime;
	}

	/**
	 * 巡查员姓名
	 */
	public String getXcPerson() {
		return xcPerson;
	}

	/**
	 * 巡查员姓名
	 */
	public void setXcPerson(String xcPerson) {
		this.xcPerson = xcPerson;
	}

	/**
	 * 陪同人员
	 */
	public String getEtPerson() {
		return etPerson;
	}

	/**
	 * 陪同人员
	 */
	public void setEtPerson(String etPerson) {
		this.etPerson = etPerson;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem1() {
		return xcItem1;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem1(String xcItem1) {
		this.xcItem1 = xcItem1;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem2() {
		return xcItem2;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem2(String xcItem2) {
		this.xcItem2 = xcItem2;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem3() {
		return xcItem3;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem3(String xcItem3) {
		this.xcItem3 = xcItem3;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem4() {
		return xcItem4;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem4(String xcItem4) {
		this.xcItem4 = xcItem4;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem5() {
		return xcItem5;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem5(String xcItem5) {
		this.xcItem5 = xcItem5;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem6() {
		return xcItem6;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem6(String xcItem6) {
		this.xcItem6 = xcItem6;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem7() {
		return xcItem7;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem7(String xcItem7) {
		this.xcItem7 = xcItem7;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem8() {
		return xcItem8;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem8(String xcItem8) {
		this.xcItem8 = xcItem8;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem9() {
		return xcItem9;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem9(String xcItem9) {
		this.xcItem9 = xcItem9;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem10() {
		if ("undefined".equals(xcItem10)) {
			return null;
		} else if ("".equals(xcItem10)) {
			return null;
		}
		return xcItem10;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem10(String xcItem10) {
		this.xcItem10 = xcItem10;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem11() {
		if ("undefined".equals(xcItem11)) {
			return null;
		} else if ("".equals(xcItem11)) {
			return null;
		}
		return xcItem11;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem11(String xcItem11) {
		this.xcItem11 = xcItem11;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public String getXcItem12() {
		if ("undefined".equals(xcItem12)) {
			return null;
		} else if ("".equals(xcItem12)) {
			return null;
		}
		return xcItem12;
	}

	/**
	 * 消防安全状况(1是2否)
	 */
	public void setXcItem12(String xcItem12) {
		this.xcItem12 = xcItem12;
	}

	/**
	 * 整改日期
	 */
	public String getRectDate() {
		return rectDate;
	}

	/**
	 * 整改日期
	 */
	public void setRectDate(String rectDate) {
		this.rectDate = rectDate;
	}

	/**
	 * 备注
	 */
	public String getMeno() {
		return meno;
	}

	/**
	 * 备注
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * 1:及格 3:不及格
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 1:及格 3:不及格
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

	/**
	 * 修改时间
	 */
	public Date getModTime() {
		return modTime;
	}

	/**
	 * 修改时间
	 */
	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	/**
	 * 添加者ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 添加者ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * IP地址
	 */
	public String getAddIp() {
		return addIp;
	}

	/**
	 * IP地址
	 */
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	/**
	 * 手机本地监管记录ID
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * 手机本地监管记录ID
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 图片basc64
	 */
	public String getImg64() {
		return img64;
	}

	/**
	 * 图片basc64
	 */
	public void setImg64(String img64) {
		this.img64 = img64;
	}

	/**
	 * app巡查ID
	 */
	public String getAppXunchaId() {
		return appXunchaId;
	}

	/**
	 * app巡查ID
	 */
	public void setAppXunchaId(String appXunchaId) {
		this.appXunchaId = appXunchaId;
	}

	/**
	 * 培训人数
	 */
	public String getPxquantity() {
		return pxquantity;
	}

	/**
	 * 培训人数
	 */
	public void setPxquantity(String pxquantity) {
		this.pxquantity = pxquantity;
	}

	/**
	 * 培训内容
	 */
	public String getTrainingA() {
		return trainingA;
	}

	/**
	 * 培训内容
	 */
	public void setTrainingA(String trainingA) {
		this.trainingA = trainingA;
	}

	/**
	 * 住人三人以上(0:否,1:是)
	 */
	public String getLiveThree() {
		return liveThree;
	}

	/**
	 * 住人三人以上(0:否,1:是)
	 */
	public void setLiveThree(String liveThree) {
		this.liveThree = liveThree;
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

}
