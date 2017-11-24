package cn.dlj.app.entity;

import cn.dlj.utils.Config;

/**
 * 巡查RD(职能部门)
 * 
 */
public class XunchaRd {

	/** ID **/
	private String id;
	/**  **/
	private String xunchaId;
	/**  **/
	private String xctype;
	/**  **/
	private String unitId;
	/**  **/
	private String unitName;
	/**  **/
	private String departId;
	/**  **/
	private String departName;
	/**  **/
	private String townId;
	/**  **/
	private String townName;
	/**  **/
	private String code;
	/**  **/
	private String license;
	/**  **/
	private String address;
	/**  **/
	private String type;
	/**  **/
	private String safedLinkman;
	/**  **/
	private String safedTelphone;
	/**  **/
	private String manageLinkman;
	/**  **/
	private String manageTelphone;
	/**  **/
	private String area;
	/**  **/
	private String crewSize;
	/**  **/
	private String buildingsLayer;
	/**  **/
	private String unitMeno;
	/**  **/
	private String xcTime;
	/**  **/
	private String oxcTime;
	/**  **/
	private String xcPerson;
	/**  **/
	private String etPerson;
	/**  **/
	private String xcItem1;
	/**  **/
	private String xcItem2;
	/**  **/
	private String xcItem3;
	/**  **/
	private String xcItem4;
	/**  **/
	private String xcItem5;
	/**  **/
	private String xcItem6;
	/**  **/
	private String xcItem7;
	/**  **/
	private String xcItem8;
	/**  **/
	private String xcItem9;
	/**  **/
	private String xcItem10;
	/**  **/
	private String xcItem11;
	/**  **/
	private String xcItem12;
	/**  **/
	private String rectDate;
	/**  **/
	private String meno;
	/**  **/
	private String flag;
	/**  **/
	private String imgIds;
	/** 图片url **/
	private String imgUrls;
	/**  **/
	private String addTime;
	/**  **/
	private String userId;
	/**  **/
	private String userName;
	/**  **/
	private String liveThree;

	/** 建筑物图片查看地址 */
	public static final String XUNCHA_IMG_SERVER_PATH = Config.get("xuncha.img.server.path");

	/**
	 * 完整图片地址
	 */
	public String getServerBimg() {
		String serverBimg = "";
		if (null != imgUrls && !"".equals(imgUrls.trim())) {
			String[] imgs = imgUrls.split(",", -1);
			for (String img : imgs) {
				serverBimg += "," + XUNCHA_IMG_SERVER_PATH + img;
			}
			serverBimg = serverBimg.substring(1);
		}
		return serverBimg;
	}

	/**
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 */
	public String getXunchaId() {
		return xunchaId;
	}

	/**
	 * 
	 */
	public void setXunchaId(String xunchaId) {
		this.xunchaId = xunchaId;
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
	public String getUnitId() {
		return unitId;
	}

	/**
	 * 
	 */
	public void setUnitId(String unitId) {
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
	public String getTownId() {
		return townId;
	}

	/**
	 * 
	 */
	public void setTownId(String townId) {
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
	public String getCode() {
		return code;
	}

	/**
	 * 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * 
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 */
	public void setAddress(String address) {
		this.address = address;
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
	public String getSafedLinkman() {
		return safedLinkman;
	}

	/**
	 * 
	 */
	public void setSafedLinkman(String safedLinkman) {
		this.safedLinkman = safedLinkman;
	}

	/**
	 * 
	 */
	public String getSafedTelphone() {
		return safedTelphone;
	}

	/**
	 * 
	 */
	public void setSafedTelphone(String safedTelphone) {
		this.safedTelphone = safedTelphone;
	}

	/**
	 * 
	 */
	public String getManageLinkman() {
		return manageLinkman;
	}

	/**
	 * 
	 */
	public void setManageLinkman(String manageLinkman) {
		this.manageLinkman = manageLinkman;
	}

	/**
	 * 
	 */
	public String getManageTelphone() {
		return manageTelphone;
	}

	/**
	 * 
	 */
	public void setManageTelphone(String manageTelphone) {
		this.manageTelphone = manageTelphone;
	}

	/**
	 * 
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 
	 */
	public String getCrewSize() {
		return crewSize;
	}

	/**
	 * 
	 */
	public void setCrewSize(String crewSize) {
		this.crewSize = crewSize;
	}

	/**
	 * 
	 */
	public String getBuildingsLayer() {
		return buildingsLayer;
	}

	/**
	 * 
	 */
	public void setBuildingsLayer(String buildingsLayer) {
		this.buildingsLayer = buildingsLayer;
	}

	/**
	 * 
	 */
	public String getUnitMeno() {
		return unitMeno;
	}

	/**
	 * 
	 */
	public void setUnitMeno(String unitMeno) {
		this.unitMeno = unitMeno;
	}

	/**
	 * 
	 */
	public String getXcTime() {
		return xcTime;
	}

	/**
	 * 
	 */
	public void setXcTime(String xcTime) {
		this.xcTime = xcTime;
	}

	/**
	 * 
	 */
	public String getOxcTime() {
		return oxcTime;
	}

	/**
	 * 
	 */
	public void setOxcTime(String oxcTime) {
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
	public String getImgIds() {
		return imgIds;
	}

	/**
	 * 
	 */
	public void setImgIds(String imgIds) {
		this.imgIds = imgIds;
	}

	/**
	 * 
	 */
	public String getAddTime() {
		return addTime;
	}

	/**
	 * 
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	/**
	 * 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getLiveThree() {
		return liveThree;
	}

	/**
	 * 
	 */
	public void setLiveThree(String liveThree) {
		this.liveThree = liveThree;
	}

	/**
	 * 
	 */
	public String getImgUrls() {
		return imgUrls;
	}

	/**
	 * 
	 */
	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

}
