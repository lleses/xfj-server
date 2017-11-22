package cn.dlj.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页数据对象
 * 
 */
public class PagingMySql {

	/** 当前页，缺省1 */
	private int currentPage = 1;
	/** 每页数据量，缺省20 */
	private int pageSize = 20;
	/** 添加参数map */
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * 当前页，缺省1
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 当前页，缺省1
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 每页数据量，缺省20
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 每页数据量，缺省20
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 从第几天记录开始: (当前页码-1)*页面容量
	 */
	public int getFirst() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 添加条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(为null时不做任何操作)
	 */
	public void add(String key, Object value) {
		if (null != key && null != value) {
			params.put(key, value);
		}
	}

	/**
	 * 获取条件，请在sql中自行具体实现
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagingMySql [currentPage=");
		builder.append(currentPage);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", params=");
		builder.append(params);
		builder.append(", first=");
		builder.append(getFirst());
		builder.append("]");
		return builder.toString();
	}
}
