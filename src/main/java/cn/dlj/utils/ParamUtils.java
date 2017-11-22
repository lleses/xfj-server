package cn.dlj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web参数处理工具类
 * 
 */
public class ParamUtils {

	private ParamUtils() {
	}

	/** 年月日期格式 */
	public static final DateFormat MONTH_FORMAT = new SimpleDateFormat("yyyyMM");

	/**
	 * 获取字符串参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static String paramStr(HttpServletRequest request, String paramName) {
		return paramStr(request, paramName, true, false);
	}

	/**
	 * 获取字符串参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否Attribute
	 * @return
	 */
	public static String paramStr(HttpServletRequest request, String paramName, boolean attr) {
		return paramStr(request, paramName, attr, false);
	}

	/**
	 * 获取字符串参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否Attribute
	 * @param utf8
	 *            是否utf8编码
	 * @return
	 */
	public static String paramStr(HttpServletRequest request, String paramName, boolean attr, boolean utf8) {
		String str = getStr(request, paramName, utf8);
		if (null != str && attr) {
			request.setAttribute(paramName, str);// attribute参数
		}
		return str;
	}

	/**
	 * 获取字符串参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否Attribute
	 * @param charset
	 *            根据charset编码
	 * @return
	 */
	public static String paramStr(HttpServletRequest request, String paramName, boolean attr, String charset) {
		String str = getStr(request, paramName, charset);
		if (null != str && attr) {
			request.setAttribute(paramName, str);// attribute参数
		}
		return str;
	}

	/**
	 * 获取Integer参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return Integer/null
	 */
	public static Integer getInt(HttpServletRequest request, String paramName) {
		return paramInt(request, paramName, false);
	}

	/**
	 * 获取Integer参数并传递(Attribute)
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static Integer paramInt(HttpServletRequest request, String paramName) {
		return paramInt(request, paramName, true);
	}

	/**
	 * 获取Integer参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Integer paramInt(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str && str.matches("[0-9-]{0,11}")) {
			try {
				int i = Integer.parseInt(str);
				if (attr) {
					request.setAttribute(paramName, i);// attribute参数
				}
				return i;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 获取Long参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String paramName) {
		return paramLong(request, paramName, false);
	}

	/**
	 * 获取Long参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return Long/null
	 */
	public static Long paramLong(HttpServletRequest request, String paramName) {
		return paramLong(request, paramName, true);
	}

	/**
	 * 获取Long参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return Long/null
	 */
	public static Long paramLong(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str && str.matches("[0-9-]{0,20}")) {
			try {
				long l = Long.parseLong(str);
				if (attr) {
					request.setAttribute(paramName, l);// attribute参数
				}
				return l;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 获取Double参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Double paramDouble(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str && str.matches("[0-9-.]*")) {
			try {
				double d = Double.parseDouble(str);
				if (attr) {
					request.setAttribute(paramName, d);// attribute参数
				}
				return d;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 获取BigDecimal参数并根据参数名称attribute
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static BigDecimal paramBigDecimal(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str && str.matches("[0-9-.]*")) {
			try {
				BigDecimal d = new BigDecimal(str);
				if (attr) {
					request.setAttribute(paramName, d);
				}
				return d;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 获取日期(yyyy-MM-dd)参数并传递(Attribute)
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName) {
		return paramDate(request, paramName, null, true);
	}

	/**
	 * 获取日期(yyyy-MM-dd)参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName, boolean attr) {
		return paramDate(request, paramName, null, attr);
	}

	/**
	 * 获取日期参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param pattern
	 *            日期格式，缺省yyyy-MM-dd
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName, String pattern, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str) {
			try {
				Date date = null;
				if (null == pattern) {
					date = DateUtils.DF_DATE.parse(str);
				} else {
					date = new SimpleDateFormat(pattern).parse(str);
				}
				if (null != date && attr) {
					request.setAttribute(paramName, str);// attribute参数
				}
				return date;
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/**
	 * 获取日期段参数(参数格式:yyyy-MM-dd;参数名称:begin,end)
	 * 
	 * <pre>
	 * 返回结果保证为length=2的Date数组;
	 * 开始日期为null时返回null;
	 * 截止日期为null时返回明天,否者返回指定日期下一天;
	 * 自动attribute开始日期和截止日期.
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return Date[]{开始日期,截止日期+1}
	 */
	public static Date[] paramDatePeriod(HttpServletRequest request) {
		return paramDatePeriod(request, "begin", "end");
	}

	/**
	 * 获取日期段参数(参数格式:yyyy-MM-dd)
	 * 
	 * <pre>
	 * 返回结果保证为length=2的Date数组;
	 * 开始日期为null时返回今天;
	 * 截止日期为null时返回明天,否者返回指定日期下一天;
	 * 自动attribute开始日期和截止日期.
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramBegin
	 *            开始日期参数名称
	 * @param paramEnd
	 *            截止参数名称
	 * @return Date[]{开始日期,截止日期+1}
	 */
	public static Date[] paramDatePeriod(HttpServletRequest request, String paramBegin, String paramEnd) {
		Date begin = DateUtils.parseDate(getStr(request, paramBegin));
		Date end = DateUtils.parseDate(getStr(request, paramEnd));
		if (null != begin && null != end) {
			if (begin.after(end)) {
				Date d = begin;
				begin = end;
				end = d;
				d = null;
			}
		} else {
			Date today = DateUtils.nextDay(new Date(), 0);
			if (null == begin) {
				begin = today;
			}
			if (null == end) {
				end = today;
			}
			today = null;
		}
		request.setAttribute(paramBegin, DateUtils.formatDate(begin));// attr开始日期
		request.setAttribute(paramEnd, DateUtils.formatDate(end));// attr截止日期
		return new Date[] { begin, DateUtils.nextDay(end) };
	}

	/**
	 * 获取时间(yyyy-MM-dd HH:mm:ss)参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Date paramTime(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str) {
			try {
				Date date = DateUtils.DF_TIME.parse(str);
				if (null != date && attr) {
					request.setAttribute(paramName, str);// attribute参数
				}
				return date;
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/**
	 * 检查字符串是否指定日期格式
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param pattern
	 *            日期格式(yyyyMMdd / yyyyMMdd HH:mm:ss / ...)
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static String fmtDate(HttpServletRequest request, String paramName, String pattern, boolean attr) {
		String str = request.getParameter(paramName);
		if (null != str) {
			str = fmtDate(str, pattern);
			if (null != str && attr) {
				request.setAttribute(paramName, str);
			}
		}
		return str;
	}

	/**
	 * 检查字符串是否指定日期格式
	 * 
	 * @param str
	 *            字符串
	 * @param pattern
	 *            日期格式(yyyyMMdd / yyyyMMdd HH:mm:ss / ...)
	 * @return
	 */
	public static String fmtDate(String str, String pattern) {
		if (null != str && null != pattern) {
			str = str.trim();
			pattern = pattern.trim();
			if (str.length() > 1 && str.length() == pattern.length()) {
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				try {
					df.setLenient(false);
					df.parse(str);
					return str;
				} catch (ParseException e) {
				}
			}
		}
		return null;
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName) {
		return getStr(request, paramName, null);
	}

	/**
	 * Url地址编码
	 * 
	 * <pre>
	 * URLEncoder.encode(str, &quot;UTF-8&quot;)
	 * </pre>
	 * 
	 * @param url
	 *            地址
	 * @return 编码后的地址
	 */
	public static String urlEncode(String url) {
		if (null != url) {
			try {
				return URLEncoder.encode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		return null;
	}

	/**
	 * Url地址编码
	 * 
	 * <pre>
	 * URLEncoder.encode(str, &quot;UTF-8&quot;)
	 * </pre>
	 * 
	 * @param url
	 *            地址
	 * @return 编码后的地址
	 */
	public static String urlDecode(String url) {
		if (null != url) {
			try {
				return URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		return null;
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param utf8
	 *            是否utf8编码字符串
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName, boolean utf8) {
		return getStr(request, paramName, utf8 ? "UTF-8" : null);
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param charset
	 *            编码字符串
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName, String charset) {
		String str = request.getParameter(paramName);
		if (null == str) {
			Object o = request.getAttribute(paramName);
			if (null != o) {
				str = o.toString();
			}
		}
		if (null != str) {
			str = StringUtils.htmlRmv(str.trim());
			if (str.length() > 0) {
				if (null != charset) {
					try {
						str = new String(str.getBytes("ISO-8859-1"), charset);
					} catch (UnsupportedEncodingException e) {
					}
				}
				return str;
			}
		}
		return null;
	}

	/**
	 * 根据年月返回指定月开始与下一月开始时间
	 * 
	 * @param month
	 *            月份(格式yyyyMM)
	 * @return Date{指定月份开始时间,下一月开始时间}
	 */
	public static Date[] monthBeginAndEnd(String month) {
		try {
			Date begin = MONTH_FORMAT.parse(month);// 指定月时间
			Date[] ds = new Date[2];
			ds[0] = begin;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(begin);
			calendar.add(Calendar.MONTH, 1);
			ds[1] = calendar.getTime();
			return ds;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获取枚举对象
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param clazz
	 *            枚举对象class
	 * @param paramName
	 *            参数名称
	 * @return 枚举对象
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T paramEnum(HttpServletRequest request, Class<? extends Enum> clazz, String paramName) {
		return paramEnum(request, clazz, paramName, false);
	}

	/**
	 * 获取枚举对象
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param clazz
	 *            枚举对象class
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return 枚举对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T paramEnum(HttpServletRequest request, Class<? extends Enum> clazz, String paramName, boolean attr) {
		String value = getStr(request, paramName);
		if (null != value) {
			try {
				T eu = (T) Enum.valueOf(clazz, value);
				if (null != eu && attr) {
					request.setAttribute(paramName, value);
				}
				return eu;
			} catch (IllegalArgumentException e) {
			}
		}
		return null;
	}

	/**
	 * 获取枚举对象数组
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param clazz
	 *            枚举对象class
	 * @param paramName
	 *            参数名称
	 * @return 枚举对象数组
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T[] paramEnums(HttpServletRequest request, Class<? extends Enum> clazz, String paramName) {
		return paramEnums(request, clazz, paramName, false);
	}

	/**
	 * 获取枚举对象数组
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param clazz
	 *            枚举对象class
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return 枚举对象数组
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T[] paramEnums(HttpServletRequest request, Class<? extends Enum> clazz, String paramName, boolean attr) {
		String vals[] = request.getParameterValues(paramName);
		if (null != vals && vals.length > 0) {
			try {
				T[] arr = (T[]) Array.newInstance(clazz, vals.length);
				int i = 0;
				for (String val : vals) {
					arr[i] = (T) Enum.valueOf(clazz, val);
					i++;
				}
				if (attr) {
					request.setAttribute(paramName, arr);
				}
				return arr;
			} catch (IllegalArgumentException e) {
			}
		}
		return null;
	}

	/**
	 * 字符串转换为int型set
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static Set<Integer> intSet(String str) {
		if (null != str && str.length() > 0) {
			Set<Integer> set = new HashSet<Integer>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.matches("[0-9-]{0,11}")) {
					try {
						set.add(Integer.parseInt(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!set.isEmpty()) {
				return set;
			}
		}
		return null;
	}

	/**
	 * 字符串转换为int型数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static Integer[] intArr(String str) {
		if (null != str && str.length() > 0) {
			List<Integer> list = new ArrayList<Integer>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.matches("[0-9-]{0,11}")) {
					try {
						Integer i = Integer.parseInt(s);
						list.add(i);
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list.toArray(new Integer[list.size()]);
			}
		}
		return null;
	}

	/**
	 * 字符串转换为long型集合
	 * 
	 * @param str
	 *            字符串
	 * @return long型集合/null
	 */
	public static List<Long> longList(String str) {
		if (null != str && str.length() > 0) {
			List<Long> list = new ArrayList<Long>();
			for (String s : str.split(",")) {
				s = s.trim();
				if (s.matches("[0-9-]{0,20}")) {
					try {
						list.add(Long.parseLong(s));
					} catch (NumberFormatException e) {
					}
				}
			}
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	/**
	 * 字符串转换为long型数组
	 * 
	 * @param str
	 *            字符串
	 * @return long型数组/null
	 */
	public static Long[] longArr(String str) {
		List<Long> list = longList(str);
		if (null != list) {
			return list.toArray(new Long[list.size()]);
		} else {
			return null;
		}
	}

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return IP地址
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	/**
	 * 页面输出错误信息
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param msg
	 *            输出内容
	 * @throws IOException
	 */
	public static void printError(HttpServletResponse response, String msg) throws IOException {
		print(response, msg);
	}

	/**
	 * 页面直接输出明文内容
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param msg
	 *            输出内容(text/html; charset=UTF-8)
	 */
	public static void print(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * 页面直接输出明文内容
	 * 
	 * @param response
	 *            HttpServletResponse对象
	 * @param msg
	 *            输出内容
	 * @param character
	 *            编码(UTF-8,GBK,...,缺省UTF-8)
	 * @param type
	 *            内容格式(html,plain,xml,json,...,缺省html)
	 */
	public static void print(HttpServletResponse response, String msg, String character, String type) {
		try {
			if (null == character) {
				character = StringUtils.UTF8_NAME;
			}
			if (null == type) {
				type = "html";
			}
			response.setContentType("text/" + type + "; charset=" + character);
			response.setCharacterEncoding(character);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取http请求数据(UTF-8)
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 请求数据
	 */
	public static String httpData(HttpServletRequest request) {
		return httpData(request, null);
	}

	/**
	 * 读取http请求数据
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param charset
	 *            编码名称(缺省UTF-8)
	 * @return 请求数据
	 */
	public static String httpData(HttpServletRequest request, String charset) {
		StringBuilder source = null;
		InputStream is = null;
		try {
			if (null == charset) {
				charset = StringUtils.UTF8_NAME;
			}
			// 读取http请求内容
			source = new StringBuilder();
			is = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
			String buffer = null;
			while ((buffer = br.readLine()) != null) {
				source.append(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != is) {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		return null != source ? source.toString() : null;
	}

	/**
	 * 获取Post的json数据并转换为Map<String,Object>
	 * 
	 * <pre>
	 * jquery.json.min.js
	 * $.post(url, $.toJSON(params),...)
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(HttpServletRequest request) {
		String data = httpData(request);
		if (null != data) {
			try {
				return StringUtils.MAPPER.readValue(data, Map.class);
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 读取http请求数据，校付通专用请勿删除
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param charset
	 *            编码名称(缺省UTF-8)
	 * @return 请求数据
	 */
	public static String postData(HttpServletRequest request, String charset) {
		StringBuilder source = null;
		InputStream is = null;
		try {
			if (null == charset) {
				charset = StringUtils.UTF8_NAME;
			}
			// 读取http请求内容
			source = new StringBuilder();
			is = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
			String buffer = null;
			while ((buffer = br.readLine()) != null) {
				source.append(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != is) {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		return null != source ? source.toString() : null;
	}
}
