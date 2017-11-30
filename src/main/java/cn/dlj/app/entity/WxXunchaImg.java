package cn.dlj.app.entity;

/**
 * 巡查登记
 * 
 */
public class WxXunchaImg {

	/** ID **/
	private Integer id;
	/** 巡查ID **/
	private Integer xunchaId;
	/** 图片名称 **/
	private String picName;
	/** 序号(用于定位消防安全状况的位置) **/
	private int num;
	/** '0' 待通过 ,'1' 审核通过' , '2' 审核不通过 **/
	private int flag;
	/** 审核意见 **/
	private String remark;

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
	public Integer getXunchaId() {
		return xunchaId;
	}

	/**
	 * 
	 */
	public void setXunchaId(Integer xunchaId) {
		this.xunchaId = xunchaId;
	}

	/**
	 * 
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * 
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * 
	 */
	public int getNum() {
		return num;
	}

	/**
	 * 
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * 
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
