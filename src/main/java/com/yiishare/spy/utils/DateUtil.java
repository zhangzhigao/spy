package com.yiishare.spy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * 时间工具类 <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月12日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.utils <br>
 */
public class DateUtil {
    
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(DateUtil.class);
    
    /**
     * 定义一个全局的时间格式转换器，格式为2016-04-08 21:53:56 666
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    
    /**
     * 定义一个全局的年月的时间格式转换器，格式为201604
     */
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    
    /**
     * 定义一个全局的年月的时间格式转换器，格式为20160411
     */
    private static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 
     * 得到系统当前时间的时间戳 <br> 
     *  
     * @author zhang.zhigao<br>
     * @return 时间戳<br>
     */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }
    
    /**
     * 
     * 得到当前的标准时间字符串 <br> 
     *  
     * @author zhang.zhigao<br>
     * @return 标准时间字符串<br>
     */
    public static String getCurrentTimeString() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
    
    /**
     * 
     * 得到当前时间Date <br> 
     *  
     * @author zhang.zhigao<br>
     * @return Date<br>
     */
    public static Date getCurrentTimeDate() {
        String currentTimeString = getCurrentTimeString();
        try {
            return simpleDateFormat.parse(currentTimeString);
        }
        catch (ParseException e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * 
     * 得到当前的年月 <br> 
     *  
     * @author zhang.zhigao<br>
     * @return 形如201604的一个时间值<br>
     */
    public static String getCurrentYearMonth() {
        return monthFormat.format(new Date());
    }
    
    /**
     * 
     * 得到当前的年月日 <br> 
     *  
     * @author zhang.zhigao<br>
     * @return 形如201604的一个时间值<br>
     */
    public static String getCurrentYearMonthDay() {
        return ymdFormat.format(new Date());
    }
    
    /**
     * 
     * 字符串转化成Date的时间类型，要求时间格式为yyyy-MM-dd HH:mm:ss SSS <br> 
     *  
     * @author zhang.zhigao<br>
     * @param time 时间
     * @return Date
     * @throws ParseException 如果时间的格式不正确，将会抛出这个异常 <br>
     */
    public static Date stringToDate(String time) throws ParseException {
        return simpleDateFormat.parse(time);
    }
    
    /**
     * 
     * 字符串转化成Date的时间类型 <br> 
     *  
     * @author zhang.zhigao<br>
     * @param simpleDateFormat 自定义的简单时间转换器
     * @param time 时间
     * @return Date
     * @throws ParseException 如果时间格式错误，将会抛出这个异常 <br>
     */
    public static Date stringToDate(SimpleDateFormat simpleDateFormat, String time) throws ParseException {
        return simpleDateFormat.parse(time);
    }
}