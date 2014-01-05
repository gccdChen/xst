package cn.scau.zzzd.xst.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * 日期,日期long,日期字符串相互转换
 * @author gccd
 * 2013-12-13
 */
public class DateUtil {
	public static final String FORMATER = "yyyy-MM-dd";
	public static final String FORMATER2 = "yyyyMMdd";
	public static final String FORMATER3 = "MM月dd日 HH:mm";
	public static final String FORMATER4 = "MM月dd";
	public static final String FORMATER_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATER_TIME2 = "MM月dd日 HH:mm";
	public static SimpleDateFormat sdf = null;
	public static SimpleDateFormat sdf2 = null;
	public static SimpleDateFormat sdf3 = null;
	public static SimpleDateFormat sdf4 = null;
	public static SimpleDateFormat sdfTime = null;
	public static SimpleDateFormat sdfTime2 = null;
	static {
		sdf = new SimpleDateFormat(FORMATER);
		sdf2 = new SimpleDateFormat(FORMATER2);
		sdf3 = new SimpleDateFormat(FORMATER3);
		sdf4 = new SimpleDateFormat(FORMATER4);
		sdfTime = new SimpleDateFormat(FORMATER_TIME);
		sdfTime2 = new SimpleDateFormat(FORMATER_TIME2);
	}

	public static Date strToDate(String str) {
		if (null == str || "".equals(str))
			return null;
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date strToTime(String time) {
		if (null == time || "".equals(time))
			return null;
		try {
			return sdfTime.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @return "yyyy-MM-dd"
	 */
	public static String dateToStr(Date date) {
		return sdf.format(date);
	}
	private static final int MinuteUnit =  60 ;
	private static final int HourUnit =  60 * 60;
	private static final int DayUnit =  60 * 60 * 24;
	private static final DecimalFormat decimalFormat =new DecimalFormat("00");
	/**
	 * 
	 * @param time 秒级的long
	 * @return "yyyyMMdd" 如果一小时之内则输出 XX分钟前 一天之内 则输出 今天 hh:MM 
	 */
	public static String longToStr(Long time){
		Date date = new Date();
		long now = date.getTime()/ 1000;
		Long nowHour = now / HourUnit *HourUnit;
		Long nowDay = now / DayUnit *DayUnit;
		if(time > nowHour){
			return (now - time) / MinuteUnit+"分钟前";
		}else if(time > nowDay){
			return "今天 "+decimalFormat.format( ( time%DayUnit /HourUnit ) )+":" + decimalFormat.format( (time % HourUnit /MinuteUnit) );
		}else{
			return sdf3.format(new Date(time*1000));
		}
	}
	/**
	 * 
	 * @param time 毫秒级的long
	 * @return "yyyyMMdd" 如果一小时之内则输出 XX分钟前 一天之内 则输出 今天 hh:MM 
	 */
	public static String long2ToStr(Long time){
		time = time / 1000;
		Date date = new Date();
		long now = date.getTime()/ 1000;
		Long nowMinute = now / MinuteUnit *MinuteUnit;
		Long nowHour = now / HourUnit *HourUnit;
		Long nowDay = now / DayUnit *DayUnit;
		if(time > nowMinute){
//			return (now - time) +"秒前";
			return "刚刚";
		}else if(time > nowHour){
			return (now - time) / MinuteUnit+"分钟前";
		}else if(time > nowDay){
			return "今天 "+decimalFormat.format( ( time%DayUnit /HourUnit ) )+":" + decimalFormat.format( (time % HourUnit /MinuteUnit) );
		}else{
			return sdf3.format(new Date(time*1000));
		}
	}
	/**
	 * 
	 * @param time 毫秒级的long
	 * @return "yyyyMMdd" 如果一小时之内则输出 XX分钟前 一天之内 则输出 今天 hh:MM 
	 */
	public static String long2ToStr2(Long time){
		if(time == null)
			return sdfTime2.format(new Date());
		return sdfTime2.format(new Date(time));
	}
			
			
	/**
	 * 
	 * @param time 秒级的long
	 * @return "yyyyMMdd" 如果一小时之内则输出 XX分钟前 一天之内 则输出 今天 hh:MM 
	 */
	public static String intToStr(Integer time){
		long t = time;
		Date date = new Date();
		long now = date.getTime()/ 1000;
		Long nowHour = now / HourUnit *HourUnit;
		Long nowDay = now / DayUnit *DayUnit;
		if(time > nowHour){
			return (now - time) / MinuteUnit+"分钟前";
		}else if(time > nowDay){
			return "今天 "+decimalFormat.format( ( time%DayUnit /HourUnit ) )+":" + decimalFormat.format( (time % HourUnit /MinuteUnit) );
		}else{
			return sdf3.format(new Date(t*1000));
		}
	}
	/**
	 * 
	 * @param time 秒级的long
	 * @return "yyyyMMdd" 如果一小时之内则输出 XX分钟前 一天之内 则输出 今天 hh:MM 否则是 XX月XX
	 */
	public static String intToStr2(Integer time){
		long t = time;
		Date date = new Date();
		long now = date.getTime()/ 1000;
		Long nowHour = now / HourUnit *HourUnit;
		Long nowDay = now / DayUnit *DayUnit;
		if(time > nowHour){
			return (now - time) / MinuteUnit+"分钟前";
		}else if(time > nowDay){
			return ""+decimalFormat.format( ( time%DayUnit /HourUnit ) )+":" + decimalFormat.format( (time % HourUnit /MinuteUnit) );
		}else{
			return sdf4.format(new Date(t*1000));
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return "yyyyMMdd"
	 */
	public static String dateToStr2(Date date) {
		return sdf2.format(date);
	}

	public static String timeToStr(Date date) {
		return sdfTime.format(date);
	}

	public static Integer getDateInt() {
		Long t = new Date().getTime() / 1000;
		return Integer.parseInt("" + t);
	}

}
