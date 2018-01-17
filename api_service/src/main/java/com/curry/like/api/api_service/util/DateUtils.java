package com.curry.like.api.api_service.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
    public static final String STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final long DAYS_OF_MILLISECONDS = 1000 * 60 * 60 * 24;


    /**
     * 字符串转换为自定义格式日期
     *
     * @param date
     * @param formatType
     * @return
     */
    public static Date string2Date(String date, String formatType) {
        if (StringUtils.isEmpty(date)
                || StringUtils.isEmpty(formatType)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatType);
            sdf.setLenient(false);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }


    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 取得两个时间段的时间间隔,间隔天数不包含开始天和包含结束天数 return endDate与startDate的间隔天数
     * throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(String startDate, String endDate) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(YYYYMMDD_PATTERN);
        Date startDate0 = sdf.parse(startDate);
        Date endDate0 = sdf.parse(endDate);

        return getBetweenDays(startDate0, endDate0);
    }

    /**
     * 取得两个时间段的时间间隔,间隔天数不包含开始天和包含结束天数 return endDate与startDate的间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(Date startDate, Date endDate) {
        int betweenDays = (int) (Math.abs((startDate.getTime() - endDate.getTime())) / (DAYS_OF_MILLISECONDS) + 0.5);
        return betweenDays;
    }

    /**
     * @param @param startDate yyyyMMdd
     * @param @param endDate yyyyMMdd
     * @param @param months 相隔月份
     * @throws
     * @Title: checkBetweenMonth
     * @Description: 比较两个日期相隔是否是参数months月份数，不足一个月补齐
     */
    public static boolean checkBetweenMonth(String startDate, String endDate, int months) {
        if (StringUtils.isEmpty(startDate)
                || StringUtils.isEmpty(endDate)) {
            return false;
        }
        Date d1 = string2Date(startDate, YYYYMMDD_PATTERN); // 开始日期
        Date d2 = string2Date(endDate, YYYYMMDD_PATTERN); // 结束日期

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int betweenMonth = Math.abs(c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH))
                + Math.abs((c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR))) * 12;
        return betweenMonth == months;
    }


    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 时间朝前推2天 小时朝前推1小时
     *
     * @param date
     * @return
     */
    public static Date nowDateTo2Days(Date date) {
        return dateToNDays(date, -2, -1);
    }

    public static Date nowDateTo5Days(Date date) {
        return dateToNDays(date, -5, -1);
    }

    private static Date dateToNDays(Date date, int dayOfWeek, int hourOfDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hourOfDay);
        c.add(Calendar.DAY_OF_WEEK, dayOfWeek);

        SimpleDateFormat format = new SimpleDateFormat(STANDARD_PATTERN);
        Date date1 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_PATTERN);
            date1 = format.parse(sdf.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


    public static String getCurrentDate(String partten) {
        return format(new Date(), partten);
    }

    public static String getTomorrowDate(String partten) {
        return format(dateToNDays(new Date(), 1, 0), partten);
    }

    public static String getToday() {
        return format(new Date(), YYYYMMDD_PATTERN);
    }

    public static String format(Date date, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten);
        return sdf.format(date);
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(getBetweenDays("20150101", "20151231"));
//        System.out.println(getBetweenDays("20150101", "20160101"));
//
//        System.out.println(getBetweenDays("20160211", "20160215"));
//
//        System.out.println(checkBetweenMonth("20150103", "20150202", 1));
        String yyyymmdDhhmmss = date2TimeStamp("20170917000000", "yyyyMMddHHmmss");
        System.out.println("yyyymmdDhhmmss " + yyyymmdDhhmmss);
        String timeStamp2Date = timeStamp2Date(yyyymmdDhhmmss, "yyyy-MM-dd HH-mm-ss");
        System.out.println("timeStamp2Date "+timeStamp2Date);
    }
}