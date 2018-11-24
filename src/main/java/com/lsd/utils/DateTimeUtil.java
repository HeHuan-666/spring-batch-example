package com.lsd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 时间工具类
 * @Author: lishengdong
 * @DateTime: 2015年11月27日 上午11:45:34
 * @Version: 1.0.0
 * @Copyright:
 * @Company:
 */
public class DateTimeUtil {
	// 测试用例
	public static void main(String[] args) {
		System.out.println("当前时间:  " + getNowTime());
		System.out.println("当前指定格式时间:  " + getTimeForFormat("yyyy-MM-dd hh:mm"));
		System.out.println("几天后的日期:  " + getTimeForFormat("yyyy-MM-dd", getDateAfter(new Date(), 2)));
		System.out.println("几天前的日期:  " + getTimeForFormat("yyyy-MM-dd", getDateBefore(new Date(), 2)));
		System.out.println("自定义的时间:  " + getTimeOfDay(24, 17, 7));
		System.out.println("指定年份有多少天:  " + getTotalDayOfYear("2013"));

		// 取一段时间内的日期
		String start = "2014-11-26";
		String end = "2014-12-10";
		Date beginDate = getDateFromString("yyyy-MM-dd", start);
		Date endDate = getDateFromString("yyyy-MM-dd", end);
		List<Date> lDate = findDates(beginDate, endDate);
		for (Date date : lDate) {
			System.out.println(getTimeForFormat("yyyy-MM-dd", date));
		}

		String start_01 = "2015-08-08 00:00:00";
		compareDate("yyyy-MM-dd HH:mm:ss", start_01);
	}

	/**
	 * 指定时间与当前时间比较
	 * 
	 * @param dateformat
	 * @param dateStr
	 * @return 1:当前时间大于指定时间;0:两时间相等;-1:当前时间小于指定时间;-2:异常
	 */
	public static int compareDate(String dateformat, String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		try {
			if (dateStr != null && dateStr.length() > 0) {
				Date date = dateFormat.parse(dateStr);
				Date currentDate = new Date();
				return currentDate.compareTo(date);
			} else {
				return -2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return -2;
		}
	}

	/**
	 * 得到当前时间
	 * 
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String getNowTime() {
		return getTimeForFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param dateformat
	 * @return
	 */
	public static String getTimeForFormat(String dateformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式

		return dateFormat.format(new Date());
	}

	public static String getTimeForFormat(String dateformat, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式

		return dateFormat.format(date);
	}

	public static Date getDateFromString(String dateformat, String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getTimeForFormat(Date date, String dateformat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式

		return dateFormat.format(date);
	}

	/**
	 * 获取指定日期，几天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + (days - 1));

		return now.getTime();
	}

	/**
	 * 获取指定日期，几天前的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateBefore(Date date, int days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - (days - 1));

		return now.getTime();
	}

	/**
	 * 得到每天自定义的时间
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getTimeOfDay(int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);

		return calendar.getTime();
	}

	/**
	 * 获得指定年总天数
	 * 
	 * @return
	 */
	public static int getTotalDayOfYear(String year) {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.YEAR, Integer.valueOf(year));
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。

		return cd.get(Calendar.DAY_OF_YEAR);
	}

	public static List<Date> findDates(Date begin, Date end) {
		List<Date> dateList = new ArrayList<Date>();
		dateList.add(begin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(begin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(end);
		// 测试此日期是否在指定日期之后
		while (end.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(calBegin.getTime());
		}

		return dateList;
	}
}
