package cn.dlj.entity;

import java.util.Date;

import cn.dlj.utils.Config;

/**
 * 监管单位(巡查单位)
 * 
 */
public class UnitStr {

	/** ID */
	private String id;
	/** 单位名称 **/
	private String name;
	/** 所属社区 **/
	private String departmentId;
	/** 所属镇区 **/
	private String townId;
	/** 单位编码 **/
	private String code;
	/** 营业执照 **/
	private String license;
	/** 单位地址 **/
	private String address;
	/**
	 * 单位类型 11
	 * 酒店类12教育类13市场类14大娱乐场所15出租屋16其他21小作坊22小档口23小娱乐场所31工地类32卫生类33消防重点单位34工厂企业35十五类场所
	 **/
	private String type;
	/** 消防安全责任人 **/
	private String safedLinkman;
	/** 消防安全责任人联系电话 **/
	private String safedTelphone;
	/** 消防安全管理人 **/
	private String manageLinkman;
	/** 消防安全管理人联系电话 **/
	private String manageTelphone;
	/** 单位归属建筑物 - 对应数据库表t_unit_building的“bid”,用逗号连接 **/
	private String stationId;
	/** 单位归属巡查员ID **/
	private String belongUserId;
	/** 添加时间 **/
	private Date addTime;
	/** 修改时间 **/
	private Date modTime;
	/** 添加者ID **/
	private String userId;
	/** 申请删除标志1申请2驳回 **/
	private String delflag;
	/** 是否巡查流转过程中0否1是 **/
	private String isxc;
	/** 备注 **/
	private String meno;
	/** 图片 **/
	private String bimg;
	/** 单位面积(平方米) **/
	private String area;
	/** 所在楼层 **/
	private String buildingsLayer;
	/** 消防重点单位(0:否,1:是) **/
	private String keyUnit;
	/** 添加者 **/
	transient private String userName;
	/** 镇区名称 **/
	transient private String townName;
	/** 公司名称 **/
	transient private String departmentName;
	/** app建筑物ID **/
	transient private String appBuildingId;
	/** app监管单位ID **/
	transient private String appUnitId;

	/** 异常标记位(-1:异常 其他则正常) **/
	transient private String err;
	/** 图片basc64 **/
	transient private String img64 = "";
	/** 完整图片地址 */
	transient private String serverBimg = "";
	/** 绑定的建筑物名称（用,号隔开，格式: AA,BB,CC) */
	transient private String builNames = "";
	
	

	/** 建筑物图片查看地址 */
	public static final String UNIT_IMG_SERVER_PATH = Config.get("build.img.server.path");

	/**
	 * 完整图片地址
	 */
	public String getServerBimg() {
		if (null != bimg && !"".equals(bimg.trim()) && bimg != "null" && bimg != "undefined") {
			String[] imgs = bimg.split(",", -1);
			for (String img : imgs) {
				serverBimg += "," + UNIT_IMG_SERVER_PATH + img;
			}
			serverBimg = serverBimg.substring(1);
		}
		return serverBimg;
	}

	/**
	 * 
	 */
	public String getId() {
		return id == null ? "" : id;
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
	public String getName() {
		return name == null ? "" : name;
	}

	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public String getDepartmentId() {
		return departmentId == null ? "" : departmentId;
	}

	/**
	 * 
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 
	 */
	public String getTownId() {
		return townId == null ? "" : townId;
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
	public String getCode() {
		return code == null ? "" : code;
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
		return license == null ? "" : license;
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
		return address == null ? "" : address;
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
		return type == null ? "" : type;
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
		return safedLinkman == null ? "" : safedLinkman;
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
		return safedTelphone == null ? "" : safedTelphone;
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
		return manageLinkman == null ? "" : manageLinkman;
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
		return manageTelphone == null ? "" : manageTelphone;
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
	public String getStationId() {
		return stationId == null ? "" : stationId;
	}

	/**
	 * 
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * 
	 */
	public String getBelongUserId() {
		return belongUserId == null ? "" : belongUserId;
	}

	/**
	 * 
	 */
	public void setBelongUserId(String belongUserId) {
		this.belongUserId = belongUserId;
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
	public String getUserId() {
		return userId == null ? "" : userId;
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
	public String getDelflag() {
		return delflag == null ? "" : delflag;
	}

	/**
	 * 
	 */
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	/**
	 * 
	 */
	public String getIsxc() {
		return isxc == null ? "" : isxc;
	}

	/**
	 * 
	 */
	public void setIsxc(String isxc) {
		this.isxc = isxc;
	}

	/**
	 * 
	 */
	public String getUserName() {
		return userName == null ? "" : userName;
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
	public String getTownName() {
		return townName == null ? "" : townName;
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
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * 
	 */
	public String getAppBuildingId() {
		return appBuildingId;
	}

	/**
	 * 
	 */
	public void setAppBuildingId(String appBuildingId) {
		this.appBuildingId = appBuildingId;
	}

	/**
	 * 
	 */
	public String getAppUnitId() {
		return appUnitId;
	}

	/**
	 * 
	 */
	public void setAppUnitId(String appUnitId) {
		this.appUnitId = appUnitId;
	}

	/**
	 * 
	 */
	public String getMeno() {
		return meno == null ? "" : meno;
	}

	/**
	 * 
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * 图片
	 */
	public String getBimg() {
		return bimg == null ? "" : bimg;
	}

	/**
	 * 图片
	 */
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	/**
	 * 单位面积
	 */
	public String getArea() {
		return area == null ? "" : area;
	}

	/**
	 * 单位面积
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 所在楼层
	 */
	public String getBuildingsLayer() {
		return buildingsLayer == null ? "" : buildingsLayer;
	}

	/**
	 * 所在楼层
	 */
	public void setBuildingsLayer(String buildingsLayer) {
		this.buildingsLayer = buildingsLayer;
	}

	/**
	 * 异常标记位(-1:异常 其他则正常)
	 */
	public String getErr() {
		return err;
	}

	/**
	 * 异常标记位(-1:异常 其他则正常)
	 */
	public void setErr(String err) {
		this.err = err;
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
	 * 消防重点单位(0:否,1:是)
	 */
	public String getKeyUnit() {
		return keyUnit;
	}

	/**
	 * 消防重点单位(0:否,1:是)
	 */
	public void setKeyUnit(String keyUnit) {
		this.keyUnit = keyUnit;
	}

	/**
	 * 绑定的建筑物名称（用,号隔开，格式: AA,BB,CC)
	 */
	public String getBuilNames() {
		return builNames;
	}

	/**
	 * 绑定的建筑物名称（用,号隔开，格式: AA,BB,CC)
	 */
	public void setBuilNames(String builNames) {
		this.builNames = builNames;
	}
	
	

}
