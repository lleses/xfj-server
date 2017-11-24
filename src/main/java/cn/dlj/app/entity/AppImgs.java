package cn.dlj.app.entity;

/**
 * app同步提交的图片
 * 
 */
public class AppImgs {

	/** appId **/
	private String appId;
	/** 名称 **/
	private String name;
	/** 旧图片名称(监管单位) **/
	private String bimg;
	/** 图片basc64 **/
	private String img64;
	/** 类型(1:建筑 2:监管 3:巡查) **/
	private String type;

	/**
	 * appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 旧图片名称(监管单位)
	 */
	public String getBimg() {
		return bimg;
	}

	/**
	 * 旧图片名称(监管单位)
	 */
	public void setBimg(String bimg) {
		this.bimg = bimg;
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
	 * 类型(1:建筑 2:监管 3:巡查)
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类型(1:建筑 2:监管 3:巡查)
	 */
	public void setType(String type) {
		this.type = type;
	}

}
