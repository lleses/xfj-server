package cn.dlj.utils;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * 
 * <pre>
 * 根据配置的info、debug开关打印日志
 * </pre>
 * 
 * @author HeHongxin
 * @date 2015-3-3
 * 
 */
public class Log {

	private Log() {
	}

	/** 是否打印debug级别日志 */
	private static boolean debugEnabled = false;
	/** 是否打印info级别日志 */
	private static boolean infoEnabled = true;
	/** 是否打印sql */
	private static boolean sqlEnabled = false;

	private static final Logger logger = LoggerFactory.getLogger(Log.class);
	static {
		// 初始化日志打印级别
		debugEnabled = logger.isDebugEnabled();
		infoEnabled = logger.isInfoEnabled();

		String s = Config.get("jdbc.showsql");
		sqlEnabled = null != s && s.trim().equalsIgnoreCase("true");
	}

	/**
	 * 是否启用info级别日志
	 * 
	 * @return true:启用, false:未启用
	 */
	public static boolean isInfoEnabled() {
		return infoEnabled;
	}

	/**
	 * 是否启用debug级别日志
	 * 
	 * @return true:启用, false:未启用
	 */
	public static boolean isDebugEnabled() {
		return debugEnabled;
	}

	/**
	 * 开启/关闭SQL打印
	 * 
	 * @param enabled
	 */
	public static void sqlEnable(boolean enabled) {
		sqlEnabled = enabled;
	}

	/**
	 * 是否打印SQL
	 * 
	 * @return true:使用Log.sql(...)输出SQL语句
	 */
	public static boolean isSqlEnabled() {
		return sqlEnabled;
	}

	/**
	 * 打印debug级别日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public static void debug(String log) {
		if (debugEnabled) {
			logger.debug(log);
		}
	}

	/**
	 * 打印info级别日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public static void info(String log) {
		if (infoEnabled) {
			logger.info(log);
		}
	}

	/**
	 * 打印SQL
	 * 
	 * @param sql
	 */
	public static void sql(String sql) {
		if (sqlEnabled) {
			if (null != sql) {
				sql = sql.replaceAll("\\s", " ").replaceAll("\\s{2,}", " ");
			}
			logger.info("SQL: " + sql);
		}
	}

	/**
	 * 打印SQL
	 * 
	 * @param sql
	 * @param args
	 *            ?参数数组
	 */
	public static void sql(String sql, Object... args) {
		if (sqlEnabled) {
			if (null != sql && null != args && args.length > 0) {
				// 格式化SQL
				sql = sql.replaceAll("\\s", " ").replaceAll("\\s{2,}", " ");

				int i = 0;
				while (sql.indexOf("?") != -1) {
					sql = sql.replaceFirst("\\?", "#arg" + i + "#");
					i++;
				}
				if (null != args && args.length > 0) {
					for (int j = 0; j < args.length; j++) {
						Object o = args[j];
						if (null == o) {
							sql = sql.replace("#arg" + j + "#", "null");
						} else if (o instanceof Number) {
							sql = sql.replace("#arg" + j + "#", o.toString());
						} else {
							sql = sql.replace("#arg" + j + "#", "'" + o.toString() + "'");
						}
					}
				}
				sql = sql.replaceAll("#arg([0-9]+)#", "\\?");
			}
			logger.info("SQL: " + sql);
		}
	}

	/**
	 * 打印SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @param arg
	 *            参数
	 */
	public static void sqlWithArg(String sql, Object arg) {
		if (sqlEnabled) {
			// 打印sql
			if (null != sql) {
				// 格式化SQL
				sql = sql.replaceAll("\\s", " ").replaceAll("\\s{2,}", " ");
			}
			StringBuilder info = new StringBuilder();
			char b = sql.charAt(0);
			boolean cc = (b == 'i' || b == 'I' || b == 'b' || b == 'B');
			if (cc) {
				if (sql.length() > 1000) {
					info.append(sql.substring(0, 1000));
				} else {
					info.append(sql);
				}
			} else {
				info.append(sql);
			}
			if (null != arg && sql.indexOf("?") > 0) {
				info.append("\t -- args: ");
				info.append(arg);
			}
			if (cc && info.length() > 1000) {
				logger.info("SQL: " + info.substring(0, 1000) + " ...");
			} else {
				logger.info("SQL: " + info.toString());
			}
		}
	}

	/**
	 * 打印SQL
	 * 
	 * @param sql
	 * @param args
	 *            参数数组集合
	 */
	public static void sqlBatch(String sql, List<Object[]> args) {
		if (sqlEnabled) {
			StringBuilder sb = new StringBuilder();
			sb.append(sql);
			sb.append(";--Args：");
			for (Object[] arg : args) {
				sb.append(Arrays.toString(arg));
			}
			logger.info("SQL: " + sb);
		}
	}

	/**
	 * 打印error级别日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public static void error(String log) {
		logger.error(log);
	}

	/**
	 * 打印error级别日志
	 * 
	 * @param log
	 *            日志内容
	 * @param e
	 *            异常
	 */
	public static void error(String log, Throwable e) {
		logger.error(log, e);
	}

	/**
	 * 支撑系统打印debug级别日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public static void dljDebug(String log) {
		if (debugEnabled) {
			logger.debug("【支撑系统】" + log);
		}
	}

	/**
	 * 支撑系统打印info级别日志
	 * 
	 * @param log
	 *            日志内容
	 */
	public static void dljInfo(String log) {
		if (infoEnabled) {
			logger.info("【支撑系统】" + log);
		}
	}

	/**
	 * 支撑系统打印error级别日志
	 * 
	 * @param log
	 *            日志内容
	 * @param e
	 *            异常
	 */
	public static void dljError(String log) {
		logger.error("【支撑系统】" + log);
	}

	/**
	 * 支撑系统打印error级别日志
	 * 
	 * @param log
	 *            日志内容
	 * @param e
	 *            异常
	 */
	public static void dljError(String log, Throwable e) {
		logger.error("【支撑系统】" + log, e);
	}

}
