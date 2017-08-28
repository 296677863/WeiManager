package com.weiweb.common.utils;

import java.text.*;
import java.util.*;

public class DateUtil {

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_PATTERN = "yyyyMMddHHmmss";
   
    /**
     * 则个
     */
    private static boolean LENIENT_DATE = false;


    private static Random random = new java.util.Random();
    private static final int ID_BYTES = 10;

    public synchronized static String generateId() {
        StringBuffer result = new StringBuffer();
        result = result.append(System.currentTimeMillis());
        for (int i = 0; i < ID_BYTES; i++) {
            result = result.append(random.nextInt(10));
        }
        return result.toString();
    }

    protected static final float normalizedJulian(float JD) {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * Returns the Date from a julian. The Julian date will be converted to noon GMT,
     * such that it matches the nearest half-integer (i.e., a julian date of 1.4 gets
     * changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD) {

        /* To convert a Julian Day Number to a Gregorian date, assume that it is for 0 hours, Greenwich time (so
         * that it ends in 0.5). Do the following calculations, again dropping the fractional part of all
         * multiplicatons and divisions. Note: This method will not give dates accurately on the
         * Gregorian Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
         * calendar backwards to years earlier than 1582. using the Gregorian leap year
         * rules. In particular, the method fails if Y<400. */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ((Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ((B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ((B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12) {
            month = month - 12;
        }

        int year = (int) (C - 4715); //(if Month is January or February) or C-4716 (otherwise)

        if (month > 2) {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * @param isoString  
     * @param fmt 
     * @param field   Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount 
     * @return
     * @throws ParseException
     */
    public static final String dateIncrease(String isoString, String fmt,
                                            int field, int amount) {

        try {
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                    "GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @param expanded use formating char's
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field,
                                    boolean up) throws ParseException {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up) throws
            ParseException {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     *  java.util.Date
     * @param dateText  
     * @param format  
     * @param lenient  
     * @return
     */
    public static Date stringToDate(String dateText, String format,
                                    boolean lenient) {

        if (dateText == null) {

            return null;
        }

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * @return Timestamp
     */
    public static java.sql.Timestamp getCurrentTimestamp() {
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }

    /** java.util.Date
     * @param dateText  
     * @param format  
     * @return
     */
    public static Date stringToDate(String dateString, String format) {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * java.util.Date
     * @param dateText  
     */
    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**  
     * @return 
     * @param pattern 
     * @param date  
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /**  
     * @return  
     */
    public static Date getCurrentDateTime() {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     *  
     * @param pattern  
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     *   yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回固定格式的当前时间
     *   yyyy-MM-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String dateToStringWithTime( ) {

        return dateToString(new java.util.Date(), DATETIME_PATTERN);
    }

    
    /**
     *   yyyy-MM-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date) {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     *  
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     *  
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }

    /**
     *  
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                "GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }

    /**
     *  
     * @param date   yyyy-MM-dd
     * @param days
     * @return  yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days) {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * @param date  
     * @param fmt  
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days) {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     *  
     * @param src  
     * @param srcfmt  
     * @param desfmt 
     * @return
     */
    public static String stringToString(String src, String srcfmt,
                                        String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     *  
     * @param date  
     * @return string
     */
    public static String getYear(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(
                "yyyy");
        String cur_year = formater.format(date);
        return cur_year;
    }

    /**
     *  
     * @param date  
     * @return string
     */
    public static String getMonth(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(
                "MM");
        String cur_month = formater.format(date);
        return cur_month;
    }

    /**
     * @param date  
     * @return string
     */
    public static String getDay(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(
                "dd");
        String cur_day = formater.format(date);
        return cur_day;
    }
    
    /**
     * @param date  
     * @return string
     */
    public static String getHour(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(
                "HH");
        String cur_day = formater.format(date);
        return cur_day;
    }    

    public static int getMinsFromDate(java.util.Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return ((hour * 60) + min);
    }

    /**
     * Function to convert String to Date Object. If invalid input then current or next day date
     * is returned (Added by Ali Naqvi on 2006-5-16).
     * @param str String input in YYYY-MM-DD HH:MM[:SS] format.
     * @param isExpiry boolean if set and input string is invalid then next day date is returned
     * @return Date
     */
    public static java.util.Date convertToDate(String str, boolean isExpiry) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date dt = null;
        try {
            dt = fmt.parse(str);
        } catch (ParseException ex) {
            Calendar cal = Calendar.getInstance();
            if (isExpiry) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
            } else {
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
            }
            dt = cal.getTime();
        }
        return dt;
    }

 

    public static String dateFromat(Date date, int minute) {
        String dateFormat = null;
        int year = Integer.parseInt(getYear(date));
        int month = Integer.parseInt(getMonth(date));
        int day = Integer.parseInt(getDay(date));
        int hour = minute / 60;
        int min = minute % 60;
        dateFormat = String.valueOf(year)
                     +
                     (month > 9 ? String.valueOf(month) :
                      "0" + String.valueOf(month))
                     +
                     (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day))
                     + " "
                     +
                     (hour > 9 ? String.valueOf(hour) : "0" + String.valueOf(hour))
                     +
                     (min > 9 ? String.valueOf(min) : "0" + String.valueOf(min))
                     + "00";
        return dateFormat;
    }
    public static String sDateFormat() {
    	return new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());	
    }
    public static void main(String[] args)
	{
    	String timeDir=DateUtil.dateToString(new Date(),DateUtil.ISO_EXPANDED_DATE_FORMAT);
		System.out.println(timeDir);
	}
    
  
    	public static final String getFormateDate(String formate) {
    		SimpleDateFormat f = new SimpleDateFormat(formate, Locale.US);
    		return f.format(new Date());
    	}

    	public static final String getDateTime() {
    		return getFormateDate("yyyy-MM-dd HH:mm:ss");
    	}

    	public static final String getDate() {
    		return getFormateDate("yyyy-MM-dd");
    	}

    	public static final String getYear() {
    		return getFormateDate("yyyy");
    	}

    	public static final String getShortYear() {
    		return getFormateDate("yy");
    	}

    	public static final String getMonth() {
    		return getFormateDate("MM");
    	}

    	public static final String getShortMonth() {
    		return getFormateDate("M");
    	}

    	public static final String getDay() {
    		return getFormateDate("dd");
    	}

    	public static final String getShortDay() {
    		return getFormateDate("d");
    	}

    	public static final String getTime() {
    		return getFormateDate("HH:mm:ss");
    	}

    	public static final boolean isDate(String dateStr) {
    		Date dt = parseSimpleDate(dateStr);
    		return dt != null ? true : parseSimpleDateTime(dateStr) != null;
    	}

    	public static final boolean isDate(String pattern, String dateStr) {
    		return parseSimpleDT(pattern, dateStr) != null;
    	}

    	public static final Date parseDateTime(String dateStr) {
    		try {
    			return DateFormat.getDateTimeInstance().parse(dateStr);
    		} catch (ParseException arg1) {
    			return null;
    		}
    	}

    	public static final Date parseDate(String dateStr) {
    		try {
    			return DateFormat.getDateInstance().parse(dateStr);
    		} catch (ParseException arg1) {
    			return null;
    		}
    	}

    	public static final Date parseSimpleDateTime(String dateStr) {
    		return parseSimpleDT("yyyy-MM-dd HH:mm:ss", dateStr);
    	}

    	public static final Date parseSimpleDate(String dateStr) {
    		return parseSimpleDT("yyyy-MM-dd", dateStr);
    	}

    	public static final Date parseSimpleTime(String timeStr) {
    		return parseSimpleDT("HH:mm:ss", timeStr);
    	}

    	public static final Date parseSimpleDT(String pattern, String dateStr) {
    		try {
    			return (new SimpleDateFormat(pattern, Locale.US)).parse(dateStr);
    		} catch (ParseException arg2) {
    			return null;
    		}
    	}

    	public static final int compareDate(Date date1, Date date2) {
    		return date1.before(date2) ? -1 : (date1.after(date2) ? 1 : 0);
    	}

    	public static final boolean isBefore(Date date1, Date date2) {
    		return date1 != null && date2 != null ? date1.before(date2) : false;
    	}

    	public static final boolean isBeforeNow(Date date1) {
    		return isBefore(date1, Calendar.getInstance().getTime());
    	}

    	public static final boolean isAfter(Date date1, Date date2) {
    		return date1 != null && date2 != null ? date1.after(date2) : false;
    	}

    	public static final boolean isAfterNow(Date date1) {
    		return isAfter(date1, Calendar.getInstance().getTime());
    	}

    	public static final boolean isEquals(Date date1, Date date2) {
    		return date1 != null && date2 != null ? date1.equals(date2) : false;
    	}

    	public static final boolean isEqualsNow(Date date1) {
    		return isEquals(date1, Calendar.getInstance().getTime());
    	}

    	public static final Date getNowDate(int... deviation) {
    		return setDate(new Date(), deviation);
    	}

    	public static final Date setDate(Date date, int... deviation) {
    		Calendar cal = Calendar.getInstance(Locale.US);
    		cal.setTime(date);
    		if (deviation.length < 1) {
    			return cal.getTime();
    		} else {
    			int[] filed = new int[] { 1, 2, 5, 11, 12, 13 };

    			for (int i = 0; i < deviation.length; ++i) {
    				cal.add(filed[i], deviation[i]);
    			}

    			return cal.getTime();
    		}
    	}

    	public static final String dateTimeTips(Date dt) {
    		Calendar cal = Calendar.getInstance();
    		long times = cal.getTimeInMillis() - dt.getTime();
    		return times <= 60000L ? "1 分钟前"
    				: (times <= 3600000L ? times / 60000L + " 分钟前"
    						: (times <= 86400000L ? times / 3600000L + " 小时前"
    								: (times <= 604800000L ? times / 86400000L + " 天前"
    										: (times <= 2592000000L ? times / 604800000L + " 星期前"
    												: (times <= 31104000000L ? times / 2592000000L + " 个月前"
    														: times / 31104000000L + " 年前")))));
    	}

    	public static final String dateTips(String dateStr) {
    		Date dt = parseSimpleDate(dateStr);
    		return dt == null ? dateStr : dateTimeTips(dt);
    	}

    	public static final String dateTimeTips(String dateTime) {
    		Date dt = parseSimpleDateTime(dateTime);
    		return dt == null ? dateTime : dateTimeTips(dt);
    	}

    	public static String getNow() {
    		Date nowDate = new Date();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		return format.format(nowDate);
    	}

    	public static String formatDate(Date date, String fmt) {
    		SimpleDateFormat format = new SimpleDateFormat(fmt);
    		return format.format(date);
    	}

    	public static String getMinuteSelectHtml(String selectName) {
    		StringBuffer sb = (new StringBuffer("<select id=\'")).append(selectName).append("\' name=\'").append(selectName)
    				.append("\'>");

    		for (int i = 0; i < 60; ++i) {
    			sb.append("<option value=\'").append(i).append("\'>").append(i).append("</option>");
    		}

    		sb.append("</select>");
    		return sb.toString();
    	}

    	public static String getFirstDay() {
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Calendar calendar = Calendar.getInstance();
    		Date theDate = calendar.getTime();
    		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
    		gcLast.setTime(theDate);
    		gcLast.set(5, 1);
    		String day_first = df.format(gcLast.getTime());
    		StringBuffer str = (new StringBuffer()).append(day_first).append(" 00:00:00");
    		return str.toString();
    	}

    	public static String getFirstDay(String date) throws ParseException {
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Date theDate = df.parse(date);
    		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
    		gcLast.setTime(theDate);
    		gcLast.set(5, 1);
    		String day_first = df.format(gcLast.getTime());
    		StringBuffer str = (new StringBuffer()).append(day_first).append(" 00:00:00");
    		return str.toString();
    	}

    	public static String getCompleteDate(String date) {
    		String[] dateTemp = date.split("-");
    		if (dateTemp[1].length() == 1 && Integer.parseInt(dateTemp[1]) < 10) {
    			dateTemp[1] = "0" + dateTemp[1];
    		}

    		if (dateTemp[1].length() == 1 && Integer.parseInt(dateTemp[2]) < 10) {
    			dateTemp[2] = "0" + dateTemp[2];
    		}

    		return dateTemp[0] + "-" + dateTemp[1] + "-" + dateTemp[2];
    	}

    	public static String getLastDay() {
    		Calendar calendar = Calendar.getInstance();
    		calendar.set(5, calendar.getActualMaximum(5));
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		return format.format(calendar.getTime());
    	}

    	public static String getLastDay(String date) throws ParseException {
    		Calendar calendar = Calendar.getInstance();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		calendar.setTime(sdf.parse(date));
    		calendar.set(5, calendar.getActualMaximum(5));
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		return format.format(calendar.getTime());
    	}

    	public static String getMoveMonth(String dateStr, int pos) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date dt = sdf.parse(dateStr, new ParsePosition(0));
    		Calendar rightNow = Calendar.getInstance();
    		rightNow.setTime(dt);
    		rightNow.add(2, pos);
    		Date dt1 = rightNow.getTime();
    		return sdf.format(dt1);
    	}

    	public static String[] getMothList(String da1, String da2) throws ParseException {
    		StringBuffer sb = new StringBuffer();
    		Date d1 = (new SimpleDateFormat("yyyy-MM")).parse(da1);
    		Date d2 = (new SimpleDateFormat("yyyy-MM")).parse(da2);
    		Calendar dd = Calendar.getInstance();
    		dd.setTime(d1);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    		while (dd.getTime().before(d2)) {
    			String str = sdf.format(dd.getTime());
    			sb.append(str + ",");
    			dd.add(2, 1);
    		}

    		sb.append(sdf.format(dd.getTime()));
    		return sb.toString().split(",");
    	}

    	public static String[] getWeeks(String da1, String da2) throws ParseException {
    		GregorianCalendar c_begin = new GregorianCalendar();
    		GregorianCalendar c_end = new GregorianCalendar();
    		StringBuffer sb = new StringBuffer();
    		c_begin.setTime(convertToDate(da1));
    		c_end.setTime(convertToDate(da2));
    		int count = 1;
    		c_end.add(6, 1);

    		for (; c_begin.before(c_end); c_begin.add(6, 1)) {
    			if (c_begin.get(7) == 1) {
    				sb.append("第" + count + "周,");
    				++count;
    			}
    		}

    		return sb.toString().split(",");
    	}

    	

    	public static String convertToStr(Date time) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		return sdf.format(time);
    	}

    	public static String convertToStrwithformat(Date time, String format) {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.format(time);
    	}

    	public static Date convertToDate(String time) throws ParseException {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		return sdf.parse(time);
    	}

    	public static Date convertToDate(String time, String format) throws ParseException {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.parse(time);
    	}
    
}
