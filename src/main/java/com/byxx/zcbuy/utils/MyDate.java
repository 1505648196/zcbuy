package com.byxx.zcbuy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:日期时间操作的工具类
 */
public class MyDate {

	public static final String yyyy = "yyyy";
	public static final String yyyyMM = "yyyyMM";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHH = "yyyyMMddHH";
	public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String HH_mm_ss = "HH:mm:ss";
	public static final String yyyy_MM = "yyyy-MM";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * DateFormat缓存
	 */
	public static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

	/**
	 * 获取 DateFormat
	 */
	public static DateFormat getDateFormat(String formatStr) {
		DateFormat df = dateFormatMap.get(formatStr);
		if (df == null) {
			df = new SimpleDateFormat(formatStr);
			dateFormatMap.put(formatStr, df);
		}
		return df;
	}

	/**
	 * 按照 yyyy-MM-dd 格式，转化stringDate为Date类型
	 */
	public static Date getDateNormalStr(String stringDate) {
		return getDate(stringDate, yyyy_MM_dd);
	}

	/**
	 * 按照 yyyy-MM-dd HH:mm:ss 格式，转化 stringDateTime 为 Date类型
	 */
	public static Date getDateNormalStrTime(String stringDateTime) {
		return getDate(stringDateTime, yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 */
	public static Date getDate(String stringTime, String formatStr) {
		if (stringTime == null || stringTime.equals("")) {
			return null;
		}
		DateFormat sdf = getDateFormat(formatStr);
		try {
			return sdf.parse(stringTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 把字符串转为 Date
	 * 数字的分隔符不限，数字必须是双数，年4,月6,日8,时10,分12,秒14
	 * 采用charAt(int index) 方法要知道数字 0-9对应的char的int类型的数据 为 48 -57
	 */
	public static Date getDate(String stringTime) {
		if (stringTime == null) {
			return null;
		}
		StringBuilder sBuffer = new StringBuilder();
		for (int i = 0; i < stringTime.length(); i++) {
			char ch = stringTime.charAt(i);
			if (48 <= ch && ch <= 57) {
				sBuffer.append(ch);
			}
		}
		Date date = null;
		String str = sBuffer.toString();//将时间转换成字符串
		if (str.length() == 5) {
			String a = str.substring(0, 4);
			String b = str.substring(4);
			str = a + 0 + b;
		}
		//System.err.println("=============传入字符串:" + stringTime + ",提取数字后的日期:" + str);
		if (str.length() == 4) {
			date = getDate(str, yyyy);
		} else if (str.length() == 6) {
			date = getDate(str, yyyyMM);
		} else if (str.length() == 8) {
			date = getDate(str, yyyyMMdd);
		} else if (str.length() == 10) {
			date = getDate(str, yyyyMMddHH);
		} else if (str.length() == 12) {
			date = getDate(str, yyyyMMddHHmm);
		} else if (str.length() == 14) {
			date = getDate(str, yyyyMMddHHmmss);
		}
		return date;
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, yyyy_MM_dd);
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 */
	public static String dateTimeToString(Date date) {
		return dateToString(date, yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 将Date转换成指定格式的字符串
	 */
	public static String dateToString(Date date, String formatStr) {
		DateFormat df = dateFormatMap.get(formatStr);
		if (df == null) {
			df = new SimpleDateFormat(formatStr);
			dateFormatMap.put(formatStr, df);
		}
		return df.format(date);
	}

	/**
	 * 把日期字符串转换成 0点的 date 类型 ,不给值默认今天
	 */
	public static Date todayBegin(String dateStr) {
		if (dateStr == null) {
			dateStr = dateToString(new Date());
		}
		return getDateNormalStr(dateStr);
	}

	/**
	 * 把日期转换成最后最后一毫秒的 date 类型 ,不给值默认今天最后一毫秒
	 */
	public static Date todayEnd(Date date) {
		if (date == null) {
			date = new Date();
		}
		return new Date(date.getTime() + 24 * 60 * 60 * 1000 - 1);
	}

	/**
	 * 得到指定年月份的第一天, 参数为空默认当月第 1天
	 *
	 * @return Date
	 */
	public static Date getDayOfFirstDay(String dateStr) {
		if (dateStr == null) {
			dateStr = dateToString(new Date());
		}
		return getDate(dateStr, yyyy_MM);
	}

	/**
	 * 得到指定年月份的第一天, 参数为空默认当月第 1天
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getStrOfFirstDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		String date2 = dateToString(date, yyyy_MM);
		date2 = date2 + "-01";
		return date2;
	}

	/**
	 * 得到指定年月份的最后一天,参数为空默认当月最后 1天
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getStrOfLastDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		String date2 = dateToString(date, yyyy_MM);
		date2 = date2 + "-" + getMonthDay(date);
		return date2;
	}

	/**
	 * 得到指定年月指定天
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getDayOfThisMoon(Date yearMonth, String num) {
		String date = dateToString(yearMonth, yyyy_MM);
		if (num.length() == 1) {
			date = date + "-0" + num;
		} else {
			date = date + "-" + num;
		}
		return date;
	}


	/**
	 * 比较两个字符日期之间相差多少毫秒,time2-time1
	 *
	 * @param time1
	 * @param time2
	 */
	public static long compareDateStr(String time1, String time2) {
		Date d1 = getDate(time1);
		Date d2 = getDate(time2);
		return d2.getTime() - d1.getTime();
	}

	/**
	 * 获得指定日期月份的天数
	 */
	public static int getMonthDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 获得指定日期月份的天数
	 */
	public static int getMonthDay(String strDate) {
		Date date = getDate(strDate);
		return getMonthDay(date);
	}

	/**
	 * 获得系统当前月份的天数
	 */
	public static int getCurentMonthDay() {
		Date date = Calendar.getInstance().getTime();
		return getMonthDay(date);
	}

	/**
	 * 获取2019形式的年
	 *
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 获取月份，1-12月
	 *
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取xxxx-xx-xx的日
	 *
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取Date中的小时(24小时)
	 *
	 * @param d
	 * @return
	 */
	public static int getHour(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的分钟
	 *
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的秒
	 *
	 * @param d
	 * @return
	 */
	public static int getSecond(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 时间一和时间二相差的秒数
	 * startDate2 大于 startDate
	 *
	 * @return 比如...600秒等等..
	 */
	public static int calLastedTime(Date startDate, Date startDate2) {
		long a = startDate2.getTime();
		long b = startDate.getTime();
		return (int) ((a - b) / 1000);
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的天间隔
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
	}

	/**
	 * 得出一时间加上多少分钟后的时间
	 */
	public static String addDateMinute(String day, int x)//返回的是字符串型的时间，输入的
	//是String day, int x
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");// 24小时制
		//引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
		//量day格式一致
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null) {
			return "";
		}
		System.out.println("front:" + format.format(date)); //显示输入的日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		date = cal.getTime();
		System.out.println("after:" + format.format(date));  //显示更新后的日期
		cal = null;
		return format.format(date);

	}

	public static boolean compareDate(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		int result = c1.compareTo(c2);
		return result >= 0;
	}

	/**
	 * 日期差，0--毫秒，7 365一年
	 */
	public static long dateDifference(Date startDate, Date endDate, int type) {
		long time = endDate.getTime() - startDate.getTime();
		long returnTime = 0;
		switch (type) {
			case 0://毫秒
				returnTime = time;
				break;
			case 1://秒
				returnTime = time / 1000;
				break;
			case 2://分
				returnTime = time / (1000 * 60);
				break;
			case 3://时
				returnTime = time / (1000 * 60 * 60);
				break;
			case 4://天
				returnTime = time / (1000 * 60 * 60 * 24);
				break;
			case 5://周（7天）
				returnTime = time / (1000 * 60 * 60 * 24 * 7);
				break;
			case 6://月（30天）
				returnTime = time / (1000 * 60 * 60 * 24 * 30);
				break;
			case 7://年（365天）
				returnTime = time / (1000 * 60 * 60 * 24 * 365);
				break;
			default:
				break;
		}
		return returnTime;
	}

	/**
	 * 计算两个时间差
	 */
	public static String getStrDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}
	/**
	 * 返回 2个 Date ,参数 3 个 Integer, 年, 季, 月 ,全为 null 默认当前年
	 */
	public static Date[] getTwoDays(Integer year,Integer quarter,Integer month) {
		int year1=year==null?getYear(new Date()):year;
		int month1=1;
		int month2=12;
		int day2=31;
		if(month!=null){
			month1=month2=month;
			day2 = getMonthDay(year1 +""+ month1);
		} else if(quarter!=null){
			month1=(quarter-1)*3+1;
			month2=month1+2;
			day2 = getMonthDay(year1 +""+ month2);
		}
		Date date1 = getDate("" + year1 + month1);
		Date date2;
		if(month2<10){
			date2 = getDate("" + year1 + "0"+ month2 + day2 + "235959");
		}else {
			date2 = getDate("" + year1 + month2 + day2 + "235959");
		}
		System.err.println(dateTimeToString(date1));
		System.err.println(dateTimeToString(date2));
		return new Date[]{date1,date2};
	}

	public static void main(String[] args) {
		//Date dateAfterDays = MyDate.getDateAfterDays(new Date(), 30);
		/*String dayOfFirstDay = MyDate.getStrOfFirstDay(null);
		System.err.println(dayOfFirstDay);
		String dayOfLastDay = MyDate.getStrOfLastDay(null);
		System.err.println(dayOfLastDay);*/
		/*Date[] list = MyDate.getTwoDays(2018, 2, null);
		System.err.println(dateTimeToString(list[0]));
		System.err.println(dateTimeToString(list[1]));*/

	}
}


