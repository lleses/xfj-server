package cn.dlj.app.entity;

import java.util.Date;

import cn.dlj.utils.WxConfig;

/**
 * 建筑物信息
 * 
 */
public class Building {

	/** ID */
	private Integer id;
	/** 所属类别 */
	private Integer stationId;
	/** 建筑物名称 **/
	private String name;
	/** 登记日期 */
	private Date birthdate;
	/** 变更日期 **/
	private Date workTime;
	/** 建筑物地址 */
	private String obligation;
	/** 建筑负责人(联系人) */
	private String lxname;
	/** 联系电话 */
	private String telphone;
	/** 消防控制室('0':无,'1':有) */
	private String isControl;
	/** 建筑分类('工业建筑','单多层民用建筑','高层民用建筑') */
	private String classification;
	/** 占地面积 */
	private String areaa;
	/** 建筑面积 */
	private String acreage;
	/** 建筑高度 */
	private String jzheight;
	/** 地上层数 */
	private String ground;
	/** 地下层数 */
	private String underground;
	/** 疏散防烟 */
	private String evacuationA;
	/** 疏散封闭 */
	private String evacuationB;
	/** 疏散敞开 */
	private String evacuationC;
	/** 建筑物结构 */
	private String structure;
	/** 耐火级别('0'：一级,'1'：二级,'2'：三级,'3'：四级) */
	private String refractory;
	/** 消防设施A */
	private String facilityA;
	/** 消防培训0无1有 */
	private String isTrain;
	/** 添加时间 */
	private Date addTime;
	/** 修改时间 */
	private Date modTime;
	/** 添加的IP */
	private String addIp;
	/** 添加者ID */
	private Integer userId;
	/** 所属镇区 */
	private Integer townId;
	/** 所属社区 */
	private Integer departmentId;
	/** 图片 */
	private String bimg = "";
	/** 备注 */
	private String message = "";
	/** 完整图片地址 */
	transient private String serverBimg = "";
	/** 图片basc64 **/
	transient private String img64 = "";
	/** 删除标记(0:不删除,1：删除) **/
	transient private String dr = "0";
	/** 添加者 **/
	transient private String userName;
	/** 镇区名称 **/
	transient private String townName;
	/** 公司名称 **/
	transient private String departmentName;
	/** app建筑物ID **/
	transient private String appBuildingId;
	/** 异常标记位(-1:异常 其他则正常) **/
	transient private String err;

	public Building() {
		this.name = "";
		this.addIp = "";
		this.bimg = "";
		this.classification = "";
		this.departmentName = "";
		this.facilityA = "";
		this.ground = "";
		this.isControl = "";
		this.jzheight = "";
		this.lxname = "";
		this.message = "";
		this.obligation = "";
		this.refractory = "";
		this.structure = "";
		this.telphone = "";
		this.townName = "";
		this.underground = "";
		this.userName = "";
		this.evacuationA = "";
		this.evacuationB = "";
		this.evacuationC = "";
		this.areaa = "";
		this.acreage = "";
	}

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

	/**
	 * 完整图片地址
	 */
	public void setServerBimg(String serverBimg) {
		this.serverBimg = serverBimg;
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
	 * 所属类别
	 */
	public Integer getStationId() {
		return stationId;
	}

	/**
	 * 所属类别
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	/**
	 * 建筑物名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 建筑物名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 登机日期
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * 登机日期
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * 建筑物地址
	 */
	public String getObligation() {
		return obligation;
	}

	/**
	 * 建筑物地址
	 */
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}

	/**
	 * 建筑负责人
	 */
	public String getLxname() {
		return lxname;
	}

	/**
	 * 建筑负责人
	 */
	public void setLxname(String lxname) {
		this.lxname = lxname;
	}

	/**
	 * 变更时间
	 */
	public Date getWorkTime() {
		return workTime;
	}

	/**
	 * 变更时间
	 */
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	/**
	 * 联系电话
	 */
	public String getTelphone() {
		return telphone;
	}

	/**
	 * 联系电话
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	/**
	 * 消防控制室('0':无,'1':有)
	 */
	public String getIsControl() {
		return isControl;
	}

	/**
	 * 消防控制室('0':无,'1':有)
	 */
	public void setIsControl(String isControl) {
		this.isControl = isControl;
	}

	/**
	 * 建筑分类('工业建筑','单多层民用建筑','高层民用建筑')
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * 建筑分类('工业建筑','单多层民用建筑','高层民用建筑')
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * 占地面积
	 */
	public String getAreaa() {
		return areaa;
	}

	/**
	 * 占地面积
	 */
	public void setAreaa(String areaa) {
		this.areaa = areaa;
	}

	/**
	 * 建筑面积
	 */
	public String getAcreage() {
		return acreage;
	}

	/**
	 * 建筑面积
	 */
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	/**
	 * 建筑高度
	 */
	public String getJzheight() {
		return jzheight;
	}

	/**
	 * 建筑高度
	 */
	public void setJzheight(String jzheight) {
		this.jzheight = jzheight;
	}

	/**
	 * 地上层数
	 */
	public String getGround() {
		return ground;
	}

	/**
	 * 地上层数
	 */
	public void setGround(String ground) {
		this.ground = ground;
	}

	/**
	 * 地下层数
	 */
	public String getUnderground() {
		return underground;
	}

	/**
	 * 地下层数
	 */
	public void setUnderground(String underground) {
		this.underground = underground;
	}

	/**
	 * 疏散防烟
	 */
	public String getEvacuationA() {
		return evacuationA;
	}

	/**
	 * 疏散防烟
	 */
	public void setEvacuationA(String evacuationA) {
		this.evacuationA = evacuationA;
	}

	/**
	 * 疏散封闭
	 */
	public String getEvacuationB() {
		return evacuationB;
	}

	/**
	 * 疏散封闭
	 */
	public void setEvacuationB(String evacuationB) {
		this.evacuationB = evacuationB;
	}

	/**
	 * 疏散敞开
	 */
	public String getEvacuationC() {
		return evacuationC;
	}

	/**
	 * 疏散敞开
	 */
	public void setEvacuationC(String evacuationC) {
		this.evacuationC = evacuationC;
	}

	/**
	 * 建筑物结构
	 */
	public String getStructure() {
		return structure;
	}

	/**
	 * 建筑物结构
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}

	/**
	 * 耐火级别('0'：一级,'1'：二级,'2'：三级,'3'：四级)
	 */
	public String getRefractory() {
		return refractory;
	}

	/**
	 * 耐火级别('0'：一级,'1'：二级,'2'：三级,'3'：四级)
	 */
	public void setRefractory(String refractory) {
		this.refractory = refractory;
	}

	/**
	 * 消防设施A
	 */
	public String getFacilityA() {
		return facilityA;
	}

	/**
	 * 消防设施A
	 */
	public void setFacilityA(String facilityA) {
		this.facilityA = facilityA;
	}

	/**
	 * 消防培训0无1有
	 */
	public String getIsTrain() {
		return isTrain;
	}

	/**
	 * 消防培训0无1有
	 */
	public void setIsTrain(String isTrain) {
		this.isTrain = isTrain;
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
	 * 添加的IP
	 */
	public String getAddIp() {
		return addIp;
	}

	/**
	 * 添加的IP
	 */
	public void setAddIp(String addIp) {
		this.addIp = addIp;
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
	 * 备注
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 备注
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 删除标记(0:不删除,1：删除)
	 */
	public String getDr() {
		return dr;
	}

	/**
	 * 删除标记(0:不删除,1：删除)
	 */
	public void setDr(String dr) {
		this.dr = dr;
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
	 * 获取登记日期long(东八区)值
	 */
	public String getBirthdateStr() {
		return null == birthdate ? "" : String.valueOf(birthdate.getTime() + 8 * 60 * 60 * 1000);
	}

}
