package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 基于joda-time的工具方法 参考资料 http://blog.csdn.net/dhdhdh0920/article/details/7415359
 * http://persevere.iteye.com/blog/1755237 timezone
 * 资料http://joda-time.sourceforge.net/timezones.html
 * 
 * @author admin
 *
 */
public class TimeUtils {

	public static final DateTimeZone tz = DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+7:00"));// 设置时区为北京时间

	private static final DateTimeFormatter FORMAT_MONTH = DateTimeFormat.forPattern("yyyyMM");// 自定义日期格式
	private static final DateTimeFormatter FORMAT_YEAR = DateTimeFormat.forPattern("yyyy");// 自定义日期格式
	private static final DateTimeFormatter FORMAT_DAY = DateTimeFormat.forPattern("yyyy-MM-dd");// 自定义日期格式
	private static final DateTimeFormatter FORMAT_DAY_INT = DateTimeFormat.forPattern("yyyyMMdd");// 自定义日期格式
	private static final DateTimeFormatter FORMAT_CH_YEAR = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter FORMAT_EN_YEAR = DateTimeFormat.forPattern("HH:mm:ss yyyy-MM-dd");// 自定义日期格式
	private static final DateTimeFormatter FORMAT_CH_HOUR = DateTimeFormat.forPattern("yyyy-MM-dd HH");
	private static final DateTimeFormatter FORMAT_CH_TIME = DateTimeFormat.forPattern("HH:mm");

	public final static long YEAR = 365 * 24 * 60 * 60 * 1000l;
	public final static long DAY = 24 * 60 * 60 * 1000l;
	public final static int DAY_INTEGER = 24 * 60 * 60;
	public final static long HOUR = 60 * 60 * 1000l;
	public final static long MINUTE = 60 * 1000l;
	public final static long SECOND = 1000l;
	public final static int WEEK = 24 * 60 * 60 * 7;

	public static String formatHour(DateTime dt) {
		return dt.toString(FORMAT_CH_HOUR);
	}

	public static String formatDay(DateTime dt) {
		return dt.toString(FORMAT_DAY);
	}

	public static String formatHours(DateTime dt) {
		return dt.toString(FORMAT_CH_TIME);
	}

	public static String formatDay(long time) {
		return formatDay(getTime(time));
	}

	public static String formatYear(DateTime dt) {
		return dt.toString(FORMAT_CH_YEAR);
	}

	public static String formatYear(long time) {
		return formatYear(getTime(time));
	}

	public static String formatAnnum(long time) {
		return formatAnnum(getTime(time));
	}

	public static String formatHours(long time) {
		return formatHours(getTime(time));
	}

	public static String formatAnnum(DateTime dt) {
		return dt.toString(FORMAT_YEAR);
	}

	// /**
	// * 检测开始日期和结束日期，判断是否需要处理该条记录
	// * @param weekArray
	// * @param begin_date
	// * @param end_date
	// * @param now
	// * @return
	// */
	// public static boolean checkTime(String[] weekArray, String begin_date,
	// String end_date, DateTime now) {
	// if (weekArray == null || weekArray.length == 0) {//不需要判断星期
	// if (begin_date == null) {
	// // 跳出
	// return false;
	// } else {
	// // 按照日期处理
	// DateTime sDate = new DateTime(begin_date);
	// DateTime eDate = new DateTime(end_date);
	// if (now.lteq(eDate) && sDate.lteq(now)) {
	// // 在当前日期中判断是否在开始和结束时间内
	// return true;
	// }
	// }
	// } else {
	// // 按照星期处理
	// int day = now.getWeekDay();
	// for (int i = 0; i < weekArray.length; i++) {
	// logger.debug("week i==" + i + " ===" + weekArray[i]+" now day= "+day);
	// if (day == Integer.parseInt(weekArray[i])) {
	// // 在当前星期中
	// return true;
	// }
	// }
	// System.out.println("@@@@@@@day===" + now.toString());
	// return false;
	// }
	// return false;
	// }
	/**
	 * 检测是否是同一天
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isSameDay(long start, long end) {
		DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		String d1 = formatDay(startDate);
		String d2 = formatDay(endDate);
		if (d1.equals(d2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * TODO 检测两个时间之间相差几天
	 * 
	 * @param start
	 * @param end
	 * @return boolean
	 */
	public static int isBetweenDay(long start, long end) {
		DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		String d1 = formatDay(startDate);
		String d2 = formatDay(endDate);
		long start1 = getTimes(d1 + " 00:00:00");
		long start2 = getTimes(d2 + " 00:00:00");
		int day = (int) ((start2 - start1) / DAY);
		if (day >= 0) {
			return day;
		} else {
			return -1;
		}
	}

	/**
	 * TODO 检测两个时间之间相差几天
	 * 
	 * @param start
	 * @param end
	 * @return boolean
	 */
	public static int isBetweenDay(String start, long end) {
		// DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		// String d1 = formatDay(startDate);
		String d2 = formatDay(endDate);
		long start1 = getTimes(start);
		long start2 = getTimes(d2 + " 00:00:00");
		int day = (int) ((start2 - start1) / DAY);
		if (day >= 0) {
			return day;
		} else {
			return -1;
		}
	}

	/**
	 * 检测是否是同一天
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isSameDay(long time) {
		return isSameDay(now().getMillis(), time);
	}

	/**
	 * 检测和昨天是否同一天
	 */
	public static boolean isSameYesDay(long time) {
		return isSameDay(now().getMillis() - 24 * 3600 * 1000l, time);
	}

	public static int zoneRawOffsetHour() {
		return tz.getOffset(0) / 3600000;
	}

	/**
	 * 检测是否是同一天
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isSameWeek(long start, long end) {
		DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTimeInMillis(start);
		cal2.setTimeInMillis(end);
		// 判断是否同年同月
		if (startDate.toString(FORMAT_MONTH).equals(endDate.toString(FORMAT_MONTH))) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
			return false;
		} else {
			return false;
		}

	}

	/**
	 * 与现在是否同一周 周日到周一 为一周
	 * 
	 * @param end
	 * @return
	 */
	public static boolean isSameWeek(long time) {
		DateTime startDate = now();
		DateTime endDate = TimeUtils.getTime(time);
		// System.out.println(startDate);
		// System.out.println(endDate);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(time);
		// 判断是否同年
		if (startDate.toString(FORMAT_YEAR).equals(endDate.toString(FORMAT_YEAR))) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				//GameLog.info(cal1.get(Calendar.WEEK_OF_YEAR) + "::" + cal2.get(Calendar.WEEK_OF_YEAR));
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前该时间与当前时间是否同一周（周一到周日为一周）
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isSameZhWeek(long time) {

		Date date1 = new Date(nowLong());
		Date date2 = new Date(time);

		Calendar calendarMonday = Calendar.getInstance();
		calendarMonday.setTime(date1);

		// 获取firstDate在当前周的第几天. （星期一~星期日：1~7）
		int monday = calendarMonday.get(Calendar.DAY_OF_WEEK);
		if (monday == 0)
			monday = 7;

		// 星期一开始时间
		calendarMonday.add(Calendar.DAY_OF_MONTH, -monday + 2);
		calendarMonday.set(Calendar.HOUR, 0);
		calendarMonday.set(Calendar.MINUTE, 0);
		calendarMonday.set(Calendar.SECOND, 0);

		// 星期日结束时间
		Calendar calendarSunday = Calendar.getInstance();
		calendarSunday.setTime(calendarMonday.getTime());
		calendarSunday.add(Calendar.DAY_OF_MONTH, 6);
		calendarSunday.set(Calendar.HOUR, 23);
		calendarSunday.set(Calendar.MINUTE, 59);
		calendarSunday.set(Calendar.SECOND, 59);
		//GameLog.info("calendarMonday" + getTime(calendarMonday.getTimeInMillis()) + "calendarSunday" + getTime(calendarSunday.getTimeInMillis()));

		// 比较第二个时间是否与第一个时间在同一周
		if (date2.getTime() >= calendarMonday.getTimeInMillis() && date2.getTime() <= calendarSunday.getTimeInMillis()) {
			return true;
		}
		return false;
	}

	/**
	 * 与现在是否同一月
	 * 
	 * @param end
	 * @return
	 */
	public static boolean isSameMonth(long time) {
		DateTime startDate = now();
		DateTime endDate = TimeUtils.getTime(time);
		// System.out.println(startDate);
		// System.out.println(endDate);
		// 判断是否同年同月
		if (startDate.toString(FORMAT_MONTH).equals(endDate.toString(FORMAT_MONTH))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断现在是否在指定的时间段
	 * 
	 * @param start
	 *            小时
	 * @param end
	 *            小时
	 * @return
	 */
	public static boolean isInside(int start, int end) {
		int now = now().getHourOfDay();
		if (now >= start && now < end) {
			return true;
		}
		return false;
	}

	public static boolean isInsideSce(int start, int end) {
		int now = nowInteger();
		if (now >= start && now < end) {
			return true;
		}
		return false;
	}

	/**
	 * 获得每周周几
	 * 
	 * @return
	 */
	public static int getWeek() {
		return now().getDayOfWeek();
		// long a = getTime("2014-11-2 12:00:00").getMillis();
		// return getTime(a).getDayOfWeek();
	}

	/**
	 * 获得第几周
	 * 
	 * @return
	 */
	public static int getWeekOfYear() {
		return now().getWeekOfWeekyear();
	}

	/**
	 * 
	 * @Title: getYearWeek
	 * @Description: 年和第几周
	 * 
	 * @return int
	 * @return
	 */
	public static int getYearWeek() {
		return now().getYear() * 100 + getWeekOfYear();
	}

	/**
	 * 在指定的周几时间内
	 * 
	 * @param par
	 * @return
	 */
	public static boolean isWeekDay(int... par) {
		int wd = getWeek();
		for (int i : par) {
			if (wd == i) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据long获得时间
	 * 
	 * @param time
	 * @return
	 */
	public static DateTime getTime(long time) {
		return new DateTime(time);
	}

	/**
	 * 根据字符串获得时间
	 * 
	 * @param str
	 * @return
	 */
	public static DateTime getTime(String str) {
		return DateTime.parse(str, FORMAT_CH_YEAR);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static DateTime now() {
		return DateTime.now(tz);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static long nowLong() {
		return now().getMillis();
	}

	public static String nowTimeString() {
		return now().toDateTime().toString(FORMAT_CH_YEAR);
	}

	public static void main(String[] args) {
		System.out.println(nowTimeString());
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static int nowInteger() {
		return (int) (now().getMillis() / 1000);
	}

	// /**
	// * 返回当前的日期，如2012-07-23
	// * @return
	// */
	// public static DateTime today(){
	// return DateTime.today(tz);
	// }
	/**
	 * 返回两个日期之间的全部日期
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static String[] getDays(String start, String end) {

		DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		// 返回两个日期之间的时间差,如2012-11-1 2012-11-20 差值=19
		Days _days = Days.daysBetween(startDate, endDate);
		int num = _days.getDays();
		String days[] = new String[num + 1];
		days[0] = start;
		for (int i = 1; i < days.length; i++) {
			DateTime d = startDate.plusDays(i);
			days[i] = d.toString(FORMAT_DAY);
		}
		return days;
	}

	/**
	 * 将字符串时间转化为long
	 * 
	 * @param time
	 * @return
	 */
	public static long getTimes(String time) {
		if (StringUtil.isNull(time)) {
			return 0l;
		}
		return getTime(time).getMillis();
	}

	public static long getHours(String hours) {
		if (StringUtil.isNull(hours)) {
			return 0l;
		}
		DateTime data = DateTime.parse(hours, FORMAT_CH_TIME);
		return data.getMillis();
	}

	/**
	 * 英文时间显示
	 * 
	 * @param time
	 * @return
	 */
	public static synchronized String enDate(long time) {
		return getTime(time).toString(FORMAT_EN_YEAR);
	}

	/**
	 * 中文时间显示
	 * 
	 * @param time
	 * @return
	 */
	public static synchronized String chDate(long time) {
		return getTime(time).toString(FORMAT_CH_YEAR);
	}

	/**
	 * 争夺战开始结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static int getWarTime(String hour) {
		String day = now().toString(FORMAT_DAY);
		String all = day + hour;
		return (int) (getTime(all).getMillis() / 1000);
	}

	public static int dayDifference(long start, long end) {
		DateTime startDate = TimeUtils.getTime(start);
		DateTime endDate = TimeUtils.getTime(end);
		// 判断是否同年同月
		int startInt = Integer.parseInt(startDate.toString(FORMAT_DAY_INT));
		int endInt = Integer.parseInt(endDate.toString(FORMAT_DAY_INT));
		return endInt - startInt;
	}

	// public static int getOnly

	/**
	 * 增加秒数
	 * 
	 * @param time
	 * @return
	 */
	public static synchronized Timestamp addSecond(long paramLong, int paramInt) {
		return new Timestamp(paramLong + paramInt * 1000);
	}

	/**
	 * 
	 * @Title: setWeekTime
	 * @Description: 设定每周几
	 * 
	 * @return int
	 * @param weekDay
	 *            星期几
	 * @param hour
	 *            小时
	 * @param milute
	 *            分
	 * @param second
	 *            秒
	 * @return
	 */
	public static int setWeekTime(int weekDay, int hour, int milute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(TimeUtils.nowLong()));
		if (weekDay == 1) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day != 1) {
				cal.set(Calendar.DAY_OF_WEEK, 7);
				// 周日需要向后一天
				cal.add(Calendar.DAY_OF_YEAR, +1);
			}

		} else {
			cal.set(Calendar.DAY_OF_WEEK, weekDay);
		}
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, milute);
		cal.set(Calendar.SECOND, second);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	/**
	 * 判断是不是昨天
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isYesterday(Date a) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		Date today = c.getTime();
		// System.out.println(today);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today).equals(format.format(a));
	}

	/**
	 * 得到昨天几点
	 * 
	 * @param hour
	 * @return
	 */
	public static long getYesterdayTime(int hour) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTimeInMillis();

	}

	/**
	 * 
	 * @Title: getDateTime
	 * @Description: 字符串转时间
	 * 
	 * @return long
	 * @param time
	 * @return
	 */
	public static long getDateTime(String time) {
		if (StringUtil.isNull(time)) {
			return 0;
		} else {
			return getTime(time).getMillis();
		}
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(long time1, long time2) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 */
	public static String getDistanceTime(long time1, long time2) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day + "天" + hour + "小时" + min + "分" + sec + "秒";
	}

	public static String getDistanceTime(long diff) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day + "天" + hour + "小时" + min + "分" + sec + "秒";
	}

	/**
	 * 得到今天24点
	 * 
	 * @param hour
	 * @return
	 */
	public static long getTodayTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTimeInMillis();
	}

	/**
	 * TODO de
	 * 
	 * @return Long
	 */
	public static long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.DATE, todayStart.get(Calendar.DATE));
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);

		return todayStart.getTimeInMillis();
	}

	/**
	 * 得到比赛时间
	 * 
	 * @param second
	 * @return
	 */
	public static String matchScond(int second) {
		int min = second / 60;
		int seconds = second % 60;
		if (min > 0) {
			return min + ":" + seconds;
		} else {
			return "0:" + seconds;
		}
	}

	//
	// public static void main(String[] args) {
	// Date d =new Date();
	// d.setTime(TimeUtils.getTodayTime());
	// System.out.println(d);
	// }

	//
	// public static void main(String[] args) {
	// TimeUtils.dayDifference(sendTime, TimeUtils.nowLong())
	// }
	//

	// public static void main(String[] args) {
	// // int time = TimeUtils.setWeekTime(1,20,0,0);
	// // DateTime endDate = TimeUtils.getTime(time*1000l);
	// // System.out.println(time);
	// Date d = new Date();
	// d.setTime(1453860205315l);
	// System.out.println(d);
	// System.out.println(TimeUtils.formatYear(TimeUtils.now()));
	// }

	// public static void main(String[] args) {
	// System.out.println(nowInteger() % 30 == 0);
	// System.out.println(getTimes(formatDay(getTime(nowLong())) +
	// " 20:00:00"));
	// // Calendar c = Calendar.getInstance();
	// // int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	// // if (day_of_week == 0)
	// // day_of_week = 7;
	// // c.add(Calendar.DATE, -day_of_week + 7);
	// // System.out.println(c.getTime());
	// //
	// // System.out.println(TimeUtils.dayDifference(TimeUtils.nowLong(),
	// // TimeUtils.nowLong()));
	// //
	// // System.out.println(TimeUtils.formatHour(TimeUtils.now()));
	// int data = setWeekTime(1, 23, 59, 0);
	// System.out.println(formatYear(1470585611 * 1000l));
	// int data1 = setWeekTime(1, 24, 0, 0);
	// System.out.println(TimeUtils.isInside(21, 24));
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(new Date(TimeUtils.nowLong()));
	// System.out.println(cal.get(Calendar.DAY_OF_WEEK));
	// System.out.println(Long.MAX_VALUE);
	// System.out.println("XXXXXXXXXXXXX=" + TimeUtils.getWeek());
	// System.out.println(getTimes("2035-01-01 00:00:00") / 1000);
	// System.out.println(Integer.MAX_VALUE);
	// System.out.println(1467338856556l - 1467338809939l);
	// }

}
