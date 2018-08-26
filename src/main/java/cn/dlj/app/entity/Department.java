package cn.dlj.app.entity;

/**
 * 部门
 */
public class Department {

	/** ID */
	private Integer id;
	/** 部门名称 **/
	private String name;
	/** 对应镇区ID **/
	private Integer townId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

}
