package cn.dlj.utils;

import java.util.Date;

/**
 * ID工具
 * 
 */
public class IdUtils {

	private IdUtils() {
	}

	/**
	 * ID 生成 - 按时间生成,yyyyMMddHHmmssSSS(17位)+(len-17)位随机数字
	 * 
	 * @param len
	 *            长度,超过17的填充随机数字
	 * @return 指定长度的数字字符串
	 */
	public static String secId(int len) {
		return secId(len, new Date());
	}

	/**
	 * ID 生成 - 按时间生成,yyyyMMddHHmmssSSS(17位)+(len-17)位随机数字
	 * 
	 * @param len
	 *            长度,超过17的填充随机数字
	 * @param date
	 *            指定时间
	 * @return 指定长度的数字字符串
	 */
	public static String secId(int len, Date date) {
		StringBuilder id = new StringBuilder();
		id.append(DateUtils.DF_FULL.format(null != date ? date : new Date()));
		int l = len - 17;
		if (l > 0) {
			id.append(StringUtils.ranNum(l));
			return id.toString();
		} else {
			return id.substring(0, len);
		}
	}

	/**
	 * 根据当前时间生成32位ID
	 * 
	 * @return yyyyMMddHHmmssSSS+ranNum(15)
	 */
	public static String id32() {
		return secId(32);
	}

}
