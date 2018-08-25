package com.wwlwxg.springmvc.common.util;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple= "yyyy-MM-dd HH:mm:ss";
    
	private DateUtil() {
	}
	
	public static Date date2date(java.sql.Date date) {
		return new Date(date.getTime());
	}
	
	public static java.sql.Date date2date(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}
	
	public static Date getDate(String pattern) {
		return formatDate(getNow(), pattern);
	}

	public static String getDate() {
		return getDateTime("yyyy-MM-dd");
	}

	public static String getYM(){
		return getDateTime("yyyy-MM");
	}

	public static String getYyyyMm(){
		return getDateTime("yyyyMM");
	}

	public static String getYyyyMMddHHmm(){
		return getDateTime("yyyyMMddHHmm");
	}	
	public static String getDateTime() {
		return getDateTime("yyyy-MM-dd HH:mm:ss");
	}
	
	//获得当前日期前一天
	public static String getPreDate(){
		Date cur = Calendar.getInstance().getTime();
		Date pre = new Date(cur.getTime()-24*60*60*1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(pre);
	}
	
	//得到后一天日期
	public static Date getLastDate(Date date,String pattern){
		Date pre = new Date(date.getTime()+24*60*60*1000);		
		return pre;
	}
	
	//得到后一天日期
	public static Date getLastDate(String datestr,String pattern){
		Date date = parse(datestr, pattern);
		Date pre = new Date(date.getTime()+24*60*60*1000);		
		return pre;
	}
	
	public static String getPreDateTime(){
		Date cur = Calendar.getInstance().getTime();
		Date pre = new Date(cur.getTime()-24*60*60*1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(pre);
	}
	
	
	//获得当前月的第一天
	public static String getFirstDayOfMonth(){
		Calendar	c   =   Calendar.getInstance(); 
		Calendar   calfirst   =   Calendar.getInstance();  
		int   now   =   c.get(Calendar.DAY_OF_MONTH);   
		calfirst.add(Calendar.DATE,1-now);  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calfirst.getTime());
	}
	
	public static String getDateTime(String pattern) {
		Date datetime = Calendar.getInstance().getTime();
		return getDateTime(datetime, pattern);
	}

	public static String getDateTime(Date date, String pattern) {
		if (pattern == null || "".equals(pattern))
			pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	public static String getDateTimeForFile() {
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(1);
	}

	public static int getCurrentMonth() {
		return Calendar.getInstance().get(2) + 1;
	}

	public static int getCurrentDay() {
		return Calendar.getInstance().get(5);
	}

	public static Date addDays(int days) {
		return add(getNow(), days, 5);
	}

	public static Date addDays(Date date, int days) {
		return add(date, days, 5);
	}

	public static Date addMinutes(Date date, int minutes) {
		return add(date,minutes,Calendar.MINUTE);
	}
	
	public static Date addMonths(int months) {
		return add(getNow(), months, 2);
	}

	public static Date addMonths(Date date, int months) {
		return add(date, months, 2);
	}
	
	/**
	 * 获取前一年
	 * @param curYear
	 * @return
	 */
	public static String getPreYear(String curYear){
		int curY = Integer.parseInt(curYear);
		int preY = curY-1;
		return preY+"";
	}
	
	private static Date add(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	public static long diffDays(Date one, Date two) {
		return diffDays(one, two, 0).longValue();
	}
	
	public static long diffMinutes(Date one, Date two) {
		return diffMinutes(one, two, 0).longValue();
	}
	
	public static long diffHours(Date one, Date two) {
		return diffHours(one, two, 0).longValue();
	}
	
	/**
	 * 有精度的天数差
	 * @param one
	 * @param two
	 * @param scale
	 * @return
	 */
	public static BigDecimal diffDays(Date one, Date two, int scale) {
		BigDecimal  dfdays = new BigDecimal(0);
		Date datetwo = formatDate(two, DEFAULT_DATETIME_FORMAT);
		Date dateone = formatDate(one, DEFAULT_DATETIME_FORMAT);
		long ldifftime= dateone.getTime() - datetwo.getTime();
		
		dfdays = new BigDecimal(ldifftime).divide(new BigDecimal(0x5265c00L), scale, BigDecimal.ROUND_HALF_UP);
		return dfdays;
	}
	
	/**
	 * 有精度的小时差
	 * @param one
	 * @param two
	 * @param scale
	 * @return
	 */
	public static BigDecimal diffHours(Date one, Date two, int scale) {
	    BigDecimal  dfhours = new BigDecimal(0);
		Date datetwo = formatDate(two, DEFAULT_DATETIME_FORMAT);
		Date dateone = formatDate(one, DEFAULT_DATETIME_FORMAT);
		long ldifftime= dateone.getTime() - datetwo.getTime();
		
		dfhours = new BigDecimal(ldifftime).divide(new BigDecimal(1000), scale, BigDecimal.ROUND_HALF_UP);
		dfhours = dfhours.divide(new BigDecimal(60), scale, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(60), scale, BigDecimal.ROUND_HALF_UP);
		
		return dfhours;
	}
	
	/**
	 * 有精度的分钟差
	 * @param one
	 * @param two
	 * @param scale
	 * @return
	 */
	public static BigDecimal diffMinutes(Date one, Date two, int scale) {
		BigDecimal  dfminutes = new BigDecimal(0);
		Date datetwo = formatDate(two, DEFAULT_DATETIME_FORMAT);
		Date dateone = formatDate(one, DEFAULT_DATETIME_FORMAT);
		
		dfminutes = new BigDecimal(dateone.getTime() - datetwo.getTime()).divide(new BigDecimal(1000), scale, BigDecimal.ROUND_HALF_UP);
		dfminutes = dfminutes.divide(new BigDecimal(60), scale, BigDecimal.ROUND_HALF_UP);
		
		return dfminutes;
		
	}
	

	public static int diffMonths(Date one, Date two) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(one);
		int yearOne = calendar.get(1);
		int monthOne = calendar.get(2);
		calendar.setTime(two);
		int yearTwo = calendar.get(1);
		int monthTwo = calendar.get(2);
		return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
	}

	public static Date parse(String datestr, String pattern) {
		if (datestr == null || "".equals(datestr))
			return null;
		Date date = null;
		String p = pattern;
		if (pattern == null || "".equals(pattern))
			p = "yyyy-MM-dd";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(p);
			date = dateFormat.parse(datestr);
		} catch (ParseException parseexception) {
			parseexception.printStackTrace();
		}
		return date;
	}
	
	public static String parseString(String datestr, String pattern) {
		Date datetime = parse(datestr, pattern);
		return format(datetime, pattern);
	}

	public static String format(Date date, String pattern) {
		if(date == null){
			return null;
		}
		String p;
		p = pattern;
		if (pattern == null || "".equals(pattern))
			p = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(p);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static Date formatDate(Date date, String pattern) {
		String datetime = format(date, pattern);
		return parse(datetime, pattern);
	}

	public static Date getMonthLastDay() {
		return getMonthLastDay(getNow());
	}

	public static Date getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(1), calendar.get(2) + 1, 1);
		calendar.add(5, -1);
		return calendar.getTime();
	}

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static int getIntervalDays(Date startday, Date endday) {
		// 确保startday在endday之前
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		// 分别得到两个时间的毫秒数
		long sl = startday.getTime();
		long el = endday.getTime();

		long ei = el - sl;
		// 根据毫秒数计算间隔分钟数
		return (int) (ei / (1000 * 60*60*24));
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
	   Date date = null;
	   try {
		   if(!str.contains(":")){
			   str+=" 00:00:00";
		   }
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}

	/**
	 * 根据开始结束时间计算距离解禁天数小时分钟
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Map<String, Object> countDayHourMinute(Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(startTime != null && endTime != null) {
			Date now = formatDate(DateUtil.getNow(), DEFAULT_DATETIME_FORMAT);
			long day = diffDays(endTime, now);//距离解禁天数
			long hour = diffHours(endTime, now) - day*24;//距离解禁小时
			long minute = diffMinutes(endTime, now) - day*24*60 - hour*60;//距离解禁分钟
			map.put("unlockDay", day);
			map.put("unlockHour", hour);
			map.put("unlockMinute", minute);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Date date1 = new Date(2017, 2, 24, 16, 30);
		Date date2 = new Date(2017, 2, 23, 17, 40);
		Timestamp  tmsp = Timestamp.valueOf("2017-02-24 12:20:45");
		BigDecimal lhours = diffHours(date1, date2,0);
		long lminu =diffMinutes(date1, date2);
		long lday = diffDays(date1,date2);
		Date now = formatDate(DateUtil.getNow(), DEFAULT_DATETIME_FORMAT);
		BigDecimal lnowhours =  diffHours(now, tmsp, 2);
		boolean flag = lnowhours.compareTo(new BigDecimal(2))>0;
		System.out.println(flag);
		System.out.println(lday);
		System.out.println(lhours);
		System.out.println(lminu);
	}

}
