package cn.dlj.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class DateUtils {

	private DateUtils() {
	}

	/** 时间格式yyyyMMddHHmmssSSS(17位) */
	public static final DateFormat DF_FULL = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * 获取指定时间的下一天
	 * 
	 * @param thisDay
	 *            指定时间
	 * @return 下一天(0点整)
	 */
	public static Date nextDay(Date thisDay) {
		return nextDay(thisDay, 1);
	}

	/**
	 * 获取指定时间的下/上指定天数
	 * 
	 * @param thisDay
	 *            指定时间
	 * @param days
	 *            相隔天数(>0为向后，<0为向前,0为格式化时间为当天开始时间)
	 * @return 指定日期(0点整)(calendar.add(Calendar.DATE, days))
	 */
	public static Date nextDay(Date thisDay, int days) {
		if (null != thisDay) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(thisDay);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			if (days != 0) {
				calendar.add(Calendar.DATE, days);
			}
			return calendar.getTime();
		}
		return thisDay;
	}

	/**
	 * 比较日期(仅对比年月日)
	 * 
	 * @param s
	 *            需要对比的时间
	 * @param t
	 *            被对比的时间
	 * @return s&gt;t:1；s&lt;t:-1；s=t:0
	 */
	public static int compareDate(Date s, Date t) {
		if (null == s && null == t) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		if (null != t) {
			calendar.setTime(t);
		}
		int ty = calendar.get(Calendar.YEAR);// 当前年份
		int td = calendar.get(Calendar.DAY_OF_YEAR);// 当前天
		if (null != s) {
			calendar.setTime(s);
		}
		int sy = calendar.get(Calendar.YEAR);// 对比年份
		int sd = calendar.get(Calendar.DAY_OF_YEAR);// 对比天
		if (ty == sy) {
			if (td == sd) {
				return 0;
			} else if (td > sd) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return ty > sy ? -1 : 1;
		}
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}
}
