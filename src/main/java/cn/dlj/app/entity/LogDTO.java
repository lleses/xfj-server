package cn.dlj.app.entity;


/**
 * 日志对象
 * 
 * @author LiuWenzhao
 * 
 */
public class LogDTO {

	/** 主键 */
	private String id;
	/** 节点类型 */
	private String type;
	/** 请求参数 */
	private String request;
	/** 响应结果 */
	private String response;
	/** 操作人ID */
	private String uid;
	/** 操作人姓名 */
	private String uname;
	/** 备注 */
	private String memo;
	/** 结果 0:成功,其他:失败 */
	private String result;

	/**
	 * 获取主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取节点类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置节点类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取请求参数
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * 设置请求参数
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * 获取响应结果
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * 设置响应结果
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * 获取操作人ID
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * 设置操作人ID
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 获取操作人姓名
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * 设置操作人姓名
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * 获取备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取结果 0:成功,其他:失败
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 设置结果 0:成功,其他:失败
	 */
	public void setResult(String result) {
		this.result = result;
	}

}
