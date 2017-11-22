package cn.dlj.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页数据对象
 * 
 * <pre>
 * 使用Paging执行分页查询(PagingInterceptor)务必保证：
 * 1、SQL中字段、表名不能包含“form”、“group by”、“order by”等关键字(拦截器会根据这些关键字进行解析)
 * 2、复杂SQL语句，请尽量使用#c#统计标签，详细信息{@link PagingInterceptor}
 * 3、使用order(column,asc)执行排序时，请保证SQL中已有的order by条件在SQL句末(如有)
 * </pre>
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class Paging implements Serializable {

	private static final long serialVersionUID = -8687427837954125779L;

	/** 当前页，缺省1 */
	private int currentPage = 1;
	/** 每页数据量，缺省10 */
	private int pageSize = 10;
	/** 总数据量 */
	private int amount = 0;
	/** 总页数 */
	private int pages = 0;
	/** 查询到的数据集合，需要自行set */
	private List<?> data = null;
	/** 添加参数map */
	private Map<String, Object> params = new HashMap<String, Object>();

	/** 排序<字段,顺序> */
	private Map<String, String> orders = null;

	/**
	 * 添加排序
	 * 
	 * @param column
	 *            排序字段(查询SQL中完整的字段名称)
	 * @param asc
	 *            是否升序
	 */
	public void order(String column, boolean asc) {
		if (StringUtils.isOrdinary(column)) {
			if (null == orders) {
				orders = new LinkedHashMap<String, String>();
			}
			orders.put(column, asc ? "asc" : "desc");
		}
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
	 * 添加条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(为null时不做任何操作)
	 */
	public void addParam(String key, Object value) {
		if (null != key && null != value) {
			params.put(key, value);
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加%value%，为null时不做任何操作)
	 */
	public void addLike(String key, String value) {
		if (null != key && null != value) {
			params.put(key, "%" + value + "%");
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加%value，为null时不做任何操作)
	 */
	public void addLikeLeft(String key, String value) {
		if (null != key && null != value) {
			params.put(key, "%" + value);
		}
	}

	/**
	 * 添加like条件（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param value
	 *            条件value(自动添加value%，为null时不做任何操作)
	 */
	public void addLikeRight(String key, String value) {
		if (null != key && null != value) {
			params.put(key, value + "%");
		}
	}

	/**
	 * 添加like条件手机号码（params.put(key,value)）
	 * 
	 * @param key
	 *            条件KEY
	 * @param phone
	 *            手机号码(自动添加%phone%，为null时不做任何操作，长度为11时不添加通配符)
	 */
	public void addLikePhone(String key, String phone) {
		if (null != key && null != phone) {
			if (phone.length() == 11) {
				params.put(key, phone);
			} else {
				params.put(key, "%" + phone + "%");
			}
		}
	}

	/**
	 * 移除条件
	 * 
	 * @param key
	 *            条件KEY
	 */
	public void remove(String key) {
		if (null != key) {
			params.remove(key);
		}
	}

	/**
	 * 获取条件值
	 * 
	 * @param key
	 *            条件KEY
	 * @return 值
	 */
	public Object get(String key) {
		return null != key ? params.get(key) : null;
	}

	/** 输出所有属性 */
	public String toString() {
		return "Paging [currentPage=" + currentPage + ", pageSize=" + pageSize + ", amount=" + amount + ", pages=" + pages + ", data=" + data + ", params=" + params + "]";
	}

	/**
	 * 分页数据对象 - 缺省构造
	 */
	public Paging() {
	}

	/**
	 * 分页数据对象 - 构造
	 * 
	 * @param currentPage
	 *            当前页
	 */
	public Paging(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 分页数据对象 - 构造
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页数据量
	 */
	public Paging(Integer currentPage, Integer pageSize) {
		if (null != currentPage) {
			int page = currentPage.intValue();
			if (page > 0) {
				this.currentPage = page;
			}
		}
		if (null != pageSize) {
			int size = pageSize.intValue();
			if (size > 0) {
				if (size == Integer.MAX_VALUE) {
					size--;
				}
				this.pageSize = size;
			}
		}
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if (pageSize == Integer.MAX_VALUE) {
			pageSize--;
		}
		this.pageSize = pageSize;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
		// 在设置总条数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		this.pages = amount % pageSize == 0 ? amount / pageSize : amount / pageSize + 1;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * 获取条件，请在sql中自行具体实现
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 获取排序<字段,顺序>
	 */
	public Map<String, String> getOrders() {
		return orders;
	}

}
