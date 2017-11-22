package cn.dlj.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http工具类
 * 
 */
public class HttpUtils {

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

	private HttpUtils() {
	}

	/**
	 * 格式化url
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return 符合http规则的url
	 */
	public static String fmtUrl(String url) {
		url = StringUtils.trim(url);
		if (null != url) {
			return url.replaceAll(" ", "%20");
		}
		return null;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String get(String url) {
		return get(url, null, null);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param charset
	 *            编码charset
	 * @return 所代表远程资源的响应结果
	 */
	public static String get(String url, String param, Charset charset) {
		return get(url, param, charset, false);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param charset
	 *            编码charset
	 * @return 所代表远程资源的响应结果
	 */
	public static String get(String url, String param, Charset charset, boolean gzip) {
		url = fmtUrl(url);
		if (null == url) {
			return null;
		}
		BufferedReader in = null;
		try {
			param = StringUtils.trim(param);
			if (null != param) {
				if (url.indexOf("?") > 0) {
					url += "&" + param;
				} else {
					url += "?" + param;
				}
			}
			if (null == charset) {
				charset = StringUtils.UTF8;
			}
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			conn.connect();// 建立连接
			if (gzip) {
				in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), charset));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error("HttpUtils.Get,url='" + url + "',err:" + e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param charset
	 *            编码charset(缺省UTF-8)
	 * @return 所代表远程资源的响应结果
	 */
	public static String post(String url, String param, Charset charset) {
		return post(url, param, charset, null);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @param charset
	 *            编码charset(缺省UTF-8)
	 * @param timeout
	 *            超时(毫秒)
	 * @return 所代表远程资源的响应结果
	 */
	public static String post(String url, String param, Charset charset, Integer timeout) {
		url = fmtUrl(url);
		if (null == url) {
			return null;
		}
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			if (null == charset) {
				charset = StringUtils.UTF8;
			}
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestMethod("POST");
			if (null == timeout || timeout < 10) {
				timeout = 60000;// 1分钟
			}
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			param = StringUtils.trim(param);
			if (null != param) {
				// 获取URLConnection对象对应的输出流
				OutputStreamWriter ow = new OutputStreamWriter(conn.getOutputStream(), charset);
				out = new PrintWriter(ow);
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error("HttpUtils.Post,url='" + url + "',err:" + e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * POST请求求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param json
	 *            json格式参数
	 * @param charset
	 *            编码charset(缺省UTF-8)
	 * @return 返回结果
	 */
	public static String postJson(String url, String json, Charset charset) {
		url = fmtUrl(url);
		if (null == url) {
			return null;
		}
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			if (null == charset) {
				charset = StringUtils.UTF8;
			}
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json; charset=" + charset.displayName().toLowerCase());
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			json = StringUtils.trim(json);
			if (null != json) {
				// 获取URLConnection对象对应的输出流
				OutputStreamWriter ow = new OutputStreamWriter(conn.getOutputStream(), charset);
				out = new PrintWriter(ow);
				// 发送请求参数
				out.write(json);
				// flush输出流的缓冲
				out.flush();
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error("HttpUtils.PostJson,url='" + url + "',err:" + e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

}
