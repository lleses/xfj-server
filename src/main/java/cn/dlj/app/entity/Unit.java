package cn.dlj.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.dlj.utils.WxConfig;

/**
 * 监管单位(巡查单位)
 * 
 */
public class Unit {

	/** ID */
	private Integer id;
	/** 单位名称 **/
	private String name;
	/** 所属社区 **/
	private Integer departmentId;
	/** 所属镇区 **/
	private Integer townId;
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
	private Integer belongUserId;
	/** 添加时间 **/
	private Date addTime;
	/** 修改时间 **/
	private Date modTime;
	/** 添加者ID **/
	private Integer userId;
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
	/** 所在楼层(对应app的 floor) **/
	private String buildingsLayer;
	/** 安全状态等级(1:合格 2:一般 3:严重) **/
	private Integer starLevel = 1;
	/** 消防重点单位(0:否,1:是) **/
	private String keyUnit;
	/** 草稿(1:是,其他:否) **/
	private String iscg;
	/** 是否关门(1:是,其他:否) **/
	private String isgm;
	/** 验证码 **/
	private String yzm;
	/** 上次巡查时间 **/
	private Date lastXunchaTime;
	/** 添加者 **/
	transient private String userName;
	/** 镇区名称 **/
	transient private String townName;
	/** 公司名称 **/
	transient private String departmentName;
	/** app建筑物ID **/
	transient private String appBuildingId;
	/** 建筑物名称 **/
	transient private String buildingName;
	/** app监管单位ID **/
	transient private String appUnitId;
	/** 异常标记位(-1:异常 其他则正常) **/
	transient private String err;
	/** 图片basc64 **/
	transient private String img64 = "";
	/** 完整图片地址 */
	transient private String serverBimg = "";
	/** 监管单位归属建筑物ID集合 */
	transient private Set<Integer> buildingSet = new HashSet<>();

	/**
	 * 完整图片地址
	 */
	public String getServerBimg() {
		if (null != bimg && !"".equals(bimg.trim()) && bimg != "null" && bimg != "undefined") {
			String[] imgs = bimg.split(",", -1);
			if (imgs.length > 0) {
				for (String img : imgs) {
					serverBimg += "," + WxConfig.BUILD_IMG + img;
				}
				serverBimg = serverBimg.substring(1);
			}
		}
		return serverBimg;
	}

	public String getLastXunchaTimeStr() {
		String rsTime = "----";
		if (lastXunchaTime != null) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			rsTime = simple.format(lastXunchaTime);
		}
		return rsTime;
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
	 * 单位名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 单位名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 所属社区
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * 所属社区
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 所属镇区
	 */
	public Integer getTownId() {
		return townId;
	}

	/**
	 * 所属镇区
	 */
	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	/**
	 * 单位编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 单位编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 营业执照
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * 营业执照
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * 单位地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 单位地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 单位类型 11
	 * 酒店类12教育类13市场类14大娱乐场所15出租屋16其他21小作坊22小档口23小娱乐场所31工地类32卫生类33消防重点单位34工厂企业35十五类场所
	 */
	public String getType() {
		return type;
	}

	/**
	 * 单位类型 11
	 * 酒店类12教育类13市场类14大娱乐场所15出租屋16其他21小作坊22小档口23小娱乐场所31工地类32卫生类33消防重点单位34工厂企业35十五类场所
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 消防安全责任人
	 */
	public String getSafedLinkman() {
		return safedLinkman;
	}

	/**
	 * 消防安全责任人
	 */
	public void setSafedLinkman(String safedLinkman) {
		this.safedLinkman = safedLinkman;
	}

	/**
	 * 消防安全责任人联系电话
	 */
	public String getSafedTelphone() {
		return safedTelphone;
	}

	/**
	 * 消防安全责任人联系电话
	 */
	public void setSafedTelphone(String safedTelphone) {
		this.safedTelphone = safedTelphone;
	}

	/**
	 * 消防安全管理人
	 */
	public String getManageLinkman() {
		return manageLinkman;
	}

	/**
	 * 消防安全管理人
	 */
	public void setManageLinkman(String manageLinkman) {
		this.manageLinkman = manageLinkman;
	}

	/**
	 * 消防安全管理人联系电话
	 */
	public String getManageTelphone() {
		return manageTelphone;
	}

	/**
	 * 消防安全管理人联系电话
	 */
	public void setManageTelphone(String manageTelphone) {
		this.manageTelphone = manageTelphone;
	}

	/**
	 * 单位归属建筑物
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * 单位归属建筑物
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * 单位归属巡查员ID
	 */
	public Integer getBelongUserId() {
		return belongUserId;
	}

	/**
	 * 单位归属巡查员ID
	 */
	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
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
	 * 申请删除标志1申请2驳回
	 */
	public String getDelflag() {
		return delflag;
	}

	/**
	 * 申请删除标志1申请2驳回
	 */
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	/**
	 * 是否巡查流转过程中0否1是
	 */
	public String getIsxc() {
		return isxc;
	}

	/**
	 * 是否巡查流转过程中0否1是
	 */
	public void setIsxc(String isxc) {
		this.isxc = isxc;
	}

	/**
	 * 添加者
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 添加者
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * 公司名称
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 公司名称
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * app建筑物ID
	 */
	public String getAppBuildingId() {
		return appBuildingId;
	}

	/**
	 * app建筑物ID
	 */
	public void setAppBuildingId(String appBuildingId) {
		this.appBuildingId = appBuildingId;
	}

	/**
	 * app监管单位ID
	 */
	public String getAppUnitId() {
		return appUnitId;
	}

	/**
	 * app监管单位ID
	 */
	public void setAppUnitId(String appUnitId) {
		this.appUnitId = appUnitId;
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
	 * 图片
	 */
	public String getBimg() {
		return bimg;
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
		return area;
	}

	/**
	 * 单位面积
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 所在楼层(对应app的 floor)
	 */
	public String getBuildingsLayer() {
		return buildingsLayer;
	}

	/**
	 * 所在楼层(对应app的 floor)
	 */
	public void setBuildingsLayer(String buildingsLayer) {
		this.buildingsLayer = buildingsLayer;
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
	 * 建筑物名称
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * 建筑物名称
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**
	 * 安全状态等级(1:合格 2:一般 3:严重)
	 */
	public Integer getStarLevel() {
		return starLevel;
	}

	/**
	 * 安全状态等级(1:合格 2:一般 3:严重)
	 */
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	/**
	 * 获取监管单位归属建筑物ID集合
	 */
	public Set<Integer> getBuildingSet() {
		return buildingSet;
	}

	/**
	 * 设置监管单位归属建筑物ID集合
	 */
	public void setBuildingSet(Set<Integer> buildingSet) {
		this.buildingSet.addAll(buildingSet);
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
	 * 
	 */
	public String getIscg() {
		return iscg;
	}

	/**
	 * 
	 */
	public void setIscg(String iscg) {
		this.iscg = iscg;
	}

	/**
	 * 
	 */
	public String getIsgm() {
		return isgm;
	}

	/**
	 * 
	 */
	public void setIsgm(String isgm) {
		this.isgm = isgm;
	}

	/**
	 * 
	 */
	public String getYzm() {
		return yzm;
	}

	/**
	 * 
	 */
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	/**
	 * 
	 */
	public Date getLastXunchaTime() {
		return lastXunchaTime;
	}

	/**
	 * 
	 */
	public void setLastXunchaTime(Date lastXunchaTime) {
		this.lastXunchaTime = lastXunchaTime;
	}

}
