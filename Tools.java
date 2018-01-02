package com.sd.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * �����г��õĹ��÷���
 */
public class Tools {
	
	private static final String MOBILE_REGEX = "1[3-9][0-9]{9}";

	
    public static <T extends Object> void increment(T key, int increment, Map<T, Integer> map) {
        Integer orignal = map.get(key);
        if(orignal == null) {
            orignal = 0;
        }
        orignal += increment;
        map.put(key, orignal);
    }

    public static int parseInt(String str, int defaultValue) {
        if(Tools.isBlank(str)) {
            return defaultValue;
        }
        int result = defaultValue;
        try {
            result = Integer.parseInt(str);
        }catch(Exception e) {
            result = defaultValue;
        }
        return result;
    }

    public static long parseLong(String str, long defaultValue) {
        if(Tools.isBlank(str)) {
            return defaultValue;
        }
        long result = defaultValue;
        try {
            result = Long.parseLong(str);
        }catch(Exception e) {
            result = defaultValue;
        }
        return result;
    }
 

    /**
     * ȥ���ַ�����β�Ŀհ��ַ�
     *
     * @param str
     * @return
     *
     * @author Gao Baowen
     * @since 2009-6-1 ����02:53:39
     */
    public static String trim(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        return str.trim();
    }

    /**
     * ȥ�������еĿհ��ַ�
     * @param str
     * @return
     */
    public static String removeSpace(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        return str.trim().replaceAll("\\s", "");
    }

    /**
     * �� byte[] д���ļ�
     * @param bys
     * @param file
     * @throws IOException
     */
    public static void writeBytes2File(byte[] bys, File path, String filename) throws IOException {
        if(Tools.isBlank(bys) || path == null || isBlank(filename)) {
            return;
        }
        BufferedOutputStream bos = null;
        try {
            File file = new File(path, filename);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bys);
        } finally {
            bos.close();
        }
    }

    /**
     * ����ļ��ĺ�׺��
     * @param filename
     * @return
     */
    public static String getLowerCaseFileSuffixName(String filename) {
        int idx = filename.lastIndexOf(".");
        if(idx < 0) {
            return "";
        }
        return filename.substring(idx).toLowerCase();
    }

    /**
     * �� yyyy-MM-dd HH:mm:ss �ĸ�ʽ�������ڵĸ�ʽ��
     */
    public static String defaultFormat(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String shortFormat(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    /**
     * yyyy-MM-dd HH:mm:ss��ʽ��ʱ��
     * @param date
     * @return
     */
    public static Date getCurrentFormatDate(){
    	String dateStr  = getFormatDateString(new Date(),"yyyy-MM-dd HH:mm:ss");
    	return parseDate(dateStr);
    }
    
    /**
     * YYYYMMDD��ʽ
     * @param date
     * @return
     */
    public static String shortFormatDate(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }
    /**
     * YYYYMMDDhhmmss��ʽ
     * @param date
     * @return
     */
    public static String LongFormatDate(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyyMMddhhmmss").format(date);
    }
    /**
     * hhmmss��ʽ
     * @param date
     * @return
     */
    public static String shortFormatTime(Date date) {
        if(date == null) {
            return null;
        }
        return new SimpleDateFormat("HHmmss").format(date);
    }
    
    
    /**
     * �жϼ����Ƿ�Ϊ�ջ����ǿհ��ַ�
     */
    public static boolean isBlank(Collection<?> c) {
        if(c == null || c.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * �жϼ����Ƿ�Ϊ�ջ����ǿհ��ַ�
     */
    public static boolean isBlank(String[] str) {
        if(str == null || str.length == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * �ж������Ƿ�Ϊ��
     */
    public static boolean isBlank(Number num) {
        return num == null;
    }

    /**
     * ��� byte[] �Ƿ�Ϊ��
     */
    public static boolean isBlank(byte[] bys) {
        return (bys == null || bys.length == 0);
    }

    /**
     * ���������Ƿ��в�Ϊ�յ�ֵ��ֻҪ��һ����Ϊ��ʱ�ͷ��� true
     */
    public static boolean hasAnyNotBlank(Object...args) {
        for(int i = 0; i < args.length; i++) {
            if(!isBlank(args[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(Object obj) {
        if(obj == null) {
            return true;
        }
        if(obj instanceof String) {
            return ((String)obj).trim().length() == 0;
        }
        if(obj instanceof Collection<?>) {
            return ((Collection<?>)obj).size() == 0;
        }
        if(obj instanceof byte[]) {
            return ((byte[])obj).length == 0;
        }
        if(obj instanceof String[]) {
            return ((String[])obj).length == 0;
        }
        throw new IllegalArgumentException("undefined type: " + obj.getClass().getName());
    }

    /**
     * �� Map �ļ�ֵ��ת
     */
    public static <K, V> Map<V, K> convertKey2Value(Map<K, V> map) {
        if(map == null) {
            return null;
        }
        Map<V, K> result = new LinkedHashMap<V, K>();
        for(Iterator<K> i = map.keySet().iterator(); i.hasNext(); ) {
            K key = i.next();
            result.put(map.get(key), key);
        }
        return result;
    }

    /**
     * ��ô������ڵ�ǰһ�죬�ض����գ�ʱ���֡��롢����ȫ�����㣩
     */
    public static Date getPreDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    /**
     * ����ַ�����
     */
    public static boolean checkLength(String str, int maxChar, String infoPrefix) {
        if(str == null || str.length() == 0) {
            return true;
        }
        int len = getStringCharLength(str);
        if(len > maxChar) {
            return false;
        }
        return true;
    }

    /**
     * ����ַ����ֽڳ��ȣ�������Ϊ 2 �����ȣ���ĸ������Ϊ 1 ������
     */
    public static boolean checkByteLength(String str, int maxByte, String infoPrefix) {
        if(str == null || str.length() == 0) {
            return true;
        }
        int len = getStringByteLength(str);
        if(len > maxByte) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String str) {
        if(str == null || str.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isBlank(String str) {
        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * ��� yyyyMM ��ʽ��һ����ֵ
     */
    public static int nextMonth(int ym) {
        int m = ym % 100;
        return (ym / 100 + m / 12) * 100 + (m % 12) + 1;
    }

    public static int decrementOneMonth(int ym) {
        int m = ym % 100 - 1;
        int y = ym / 100;
        if(m == 0) {
            m = 12;
            y--;
        }
        return y * 100 + m;
    }


    /**
     * �� Map �ļ�ֵ��ת
     */
    public static Map<Integer, String> convertValue2Key(Map<String, Integer> map) {
        Map<Integer, String> result = new LinkedHashMap<Integer, String>();
        if(map == null || map.size() == 0) {
            return result;
        }
        for(Iterator<String> i = map.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            result.put(map.get(key), key);
        }
        return result;
    }

    /**
     * �����ֽ�ĳ���ַ����ĳ��ȣ���ĸ�� ASCII �ַ���Ϊһ�����ȣ����ֵ�ȫ���ַ���Ϊ�������ȡ�
     */
    public static int getStringByteLength(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        int count = 0;
        char[] chs = str.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            count += (chs[i] > 0xff) ? 2 : 1;
        }
        return count;
    }

    /**
     * �����ַ�����ĳ���ַ����ĳ��ȡ�
     */
    public static int getStringCharLength(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        return str.length();
    }

    /**
     * �ж��ַ����Ƿ�Ϊ�գ�Ϊ������ null or "";
     */
    public static boolean isEmpty(String string){

        if(string==null||string.trim().equals("")) return true;
        else return false;

    }
    
    public static Date parseShortDate(String str) {
        if(isBlank(str)) {
            return null;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * ����ָ�������ں�ʱ���ʽȡ����Ӧ���ַ��� <br>
     * ������null
     */
    public static String getFormatDateString(Date date,String pattern){
        String formatDateString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            formatDateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sdf = null;
        }
        return formatDateString;
    }

    /**
     * ����ָ���������ַ��������ڸ�ʽȡ��Dateʵ�� <br>
     * ������ null
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date getDateFromString(String dateString,String pattern){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sdf = null;
        }
        return date;
    }

    public static Date parseDate(String date) {
        if(date == null || date.length() == 0) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean containsDate(Date current, Date start, Date end) {
        long currentMillis = current.getTime();
        return (currentMillis >= start.getTime()) && (currentMillis < end.getTime());
    }
    
    /**
     * �õ�����µĵ�һ�죬�������ֶ�����
     */
    public static Date getMonthFirstDate() {
        Calendar c = getCurrentCalendar();
        c.set(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * �õ����죬�������ֶ�����
     */
    public static Date getCurrentDate() {
        return getCurrentCalendar().getTime();
    }

    /**
     * �õ�����ĺ�һ�죬�������ֶ�����
     */
    public static Date getNextDate() {
        Calendar c = getCurrentCalendar();
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
    
    /**
     * �õ�����ĺ�N�죬�������ֶ�����
     */
    public static Date getDateForDayNum(int dayNum) {
        Calendar c = getCurrentCalendar();
        c.add(Calendar.DATE, dayNum);
        return c.getTime();
    }

    /**
     * �õ��¸��µĵ�һ�죬�������ֶ�����
     */
    public static Date getNextMonthFirstDate() {
        Calendar c = getCurrentCalendar();
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public static Date getCurrentMonthLastSecond() {
        Date date = getNextMonthFirstDate();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }

    public static Calendar getCurrentCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
    
    public static Date getBeforeCalendar(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day*(-1));
        return c.getTime();
    }

    public static Date incrementMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }
    
    public static Date incrementHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    /**
     * setת���ַ���

     * @param set
     * @return
     */
    public static String set2String(Set<String> set) {
        if(Tools.isBlank(set)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(Iterator<String> i = set.iterator(); i.hasNext(); ) {
            if(k++ > 0) {
                sb.append(",");
            }
            sb.append("'").append(i.next()).append("'");
        }
        return sb.toString();
    }


    /**
     * setת���ַ���

     * @param set
     * @return
     */
    public static String set2Long(Set<Long> set) {
        if(Tools.isBlank(set)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(Iterator<Long> i = set.iterator(); i.hasNext(); ) {
            if(k++ > 0) {
                sb.append(",");
            }
            sb.append("'").append(i.next()).append("'");
        }
        return sb.toString();
    }

    /**
     * ���ڵ����ļ�ʱ�ļ�������
     */
    public static String encodeFilename(String filename) {
        if(Tools.isBlank(filename)) {
            return "undefined";
        }
        String str = null;
        try {
            str = new String(filename.getBytes("GB2312"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "undefined";
            int index = filename.lastIndexOf(".");
            if(index > 0) {
                str += filename.substring(index);
            }
        }
        return str;
    }
 
    /**
     * ��ȡ�ַ��������ڽ�ȡ���ַ��������ָ����׺������ַ�������С��ָ������ʱ����Ӻ�׺
     * ԭ�����ء�
     */
    public static String truncate(String str, int length, String suffix) {
        if((str == null) || (str.length() == 0) || (length < 1)) {
            return str;
        }
        char[] chs = str.toCharArray();
        int len = 0;
        int offset = 0;
        for(int i = 0; i < chs.length; i++, offset++) {
            len += (chs[i] > 0xff) ? 2 : 1;
            if(len > length) {
                break;
            }
        }
        if(offset == chs.length) {
            return str;
        }
        if(suffix == null || suffix.trim().length() == 0) {
            return new String(chs, 0, offset);
        }
        return new String(chs, 0, offset) + suffix.trim();
    }

    /**
     * ϵͳ���ٺ�����
     * ϵͳ���ٺŵ��ղ������ظ���ȫΪ����(ǰ��λ�Ѿ�������SysParm.properties�����MIDID)
     * �˴����ɺ�ʮλ(��ʽ��MMddHHmmss����ʱ����)
     * @return
     */
	public static String genMid() {
		String date = new SimpleDateFormat("MMddHHmmss").format(new Date());
		return date;
	}
	
	public static String genSeq() {
		String date = new SimpleDateFormat("mmss").format(new Date());
		return date;
	}
	
	/**
	 * ���ɶ�����
	 * @return
	 */
	public static String genOrderID() {
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return date;
	}

	/**
	 * �ַ�������ָ�����Ȳ�ȫ�ո�
	 * @param inStr
	 * @param length
	 * @return
	 */
	public static String fillBlank(String inStr,int length){
		inStr = trim(inStr);
		if(isBlank(inStr)){
			inStr = "";
		}
		int len = inStr.length();
		if(len<length){
			for(int i=0;i<(length-len);i++){
				inStr = inStr + " "; 
			}
		}
		return inStr;
	}
	
	/**
	 * �ַ������ֽ�ָ�����Ȳ�ȫ�ո�
	 * @param inStr
	 * @param length
	 * @return
	 */
	public static String fillBlankBytes(String inStr,int length){
		inStr = trim(inStr);
		if(inStr== null ||inStr.length() == 0){
			return inStr;
		}else{
			 byte[] strByte = inStr.getBytes();   
             int strLen = strByte.length;  
			
			if(strLen<length){
				for(int i=0;i<(length-strLen);i++){
					inStr = inStr + " "; 
				}
			}
		}
		return inStr;
	}
	
	
	/**
	 * �ַ�������ָ��������߲�ȫ0(������͵��ַ���)
	 * @param inStr
	 * @param length
	 * @return
	 */
	public static String fillZero(String inStr,int length){
		inStr = trim(inStr);
		if(isBlank(inStr)){
			inStr = "0";
		}
		int len = inStr.length();
		if(len<length){
			for(int i=0;i<(length-len);i++){
				inStr = "0"+inStr ; 
			}
		}
		return inStr;
	}
	
	/**
	 * �ַ�������ָ�������ұ߲�ȫ�ַ���
	 * @param inStr
	 * @param length
	 * @return
	 */
	public static String fillString(String inStr,int length,String flag){
		inStr = trim(inStr);
		if(isBlank(inStr)){
			inStr = "0";
		}
		int len = inStr.length();
		if(len<length){
			for(int i=0;i<(length-len);i++){
				inStr = inStr+"0" ; 
			}
		}
		return inStr;
	}
	

	/**
	 * ���㴫�����ڵ�ǰN��
	 * 
	 * @param date
	 * @author bati
	 */
	public static Date getPreDay(Date date, int deltaDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DATE, deltaDay);
		return c.getTime();
	}
	
	/**
     * ��ô������ڵ�ǰ������
     */
    public static Date getPreMinutes(Date date,int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes*(-1));
        return c.getTime();
    }
	
	
	/**
	 * ����㷨
	 * ��n����ͬ������ȡm�����ֵ����й�ʽ
	 * n!/(n-m)!
	 */
	public static int zHNumber(int n ,int m){
		int p =1;
		if(m<=0||n<=0||n<m){
				return 0;
		}
		for(int i=0;i<m;i++){
			p = p * (n-i)/(i+1);
		}
		return p;
	}
	
	public static Timestamp getTimestamp(Date date){
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * ʱ���
	 * @return
	 */
	public static String getTimestamptoString(Date date) {
		String d = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return d;
	}
	
	/**
	 * ʱ���
	 * @return
	 */
	public static String getTimestamp() {
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return date;
	}
	
	public static String getFormatTimestamp(Timestamp ts,String pattern) {
		String str = "";
		try {
			DateFormat sdf = new SimpleDateFormat(pattern);
			str = sdf.format(ts);
		} catch (Exception e) {
		}
		return str;
	}
	
	/**
	 * ��л��MD5����һ��
	 */
	public static boolean checkSign(String timestamp,String digest){
		String sign = MD5.encryptStringWithMD5(timestamp);
		if(Tools.isBlank(timestamp)||Tools.isBlank(digest)){
			return false;
		}
		if(!digest.equals(sign)){
			return false;
		}
		return true;
	}
	
	public static int getFormatNumber(double number){
		 BigDecimal t = new BigDecimal(String.valueOf(number)).setScale(0, BigDecimal.ROUND_HALF_UP);
		 return t.intValue();
	}
	
	public static int getRandomNumber(int min,int max){
		if(min>=max){
			return min;
		}
		int num = (int)(Math.random() * max)+1;
		return num;
	}
	
	//У���ֻ���
	public static boolean isMobile(String mobile) {
		if(Tools.isBlank(mobile)){
			return true;
		}
        if(mobile.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile(MOBILE_REGEX);
        return pattern.matcher(mobile).matches();
    }
	
	//��ȡ����һ���º������
	public static String nextMonthDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,1); //����ǰ���ڼ�һ����
		return sdf.format(c.getTime());
		}
	
	public static Date getMonthLastDate(){
	       Calendar c = getCurrentCalendar();   
	       c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
	       return c.getTime();
	    }
	
	public static String getRandomString(int length) { //length��ʾ�����ַ����ĳ���
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	
	  public static String getPingYin(String src) {  
	        char[] t1 = null;  
	        t1 = src.toCharArray();  
	        String[] t2 = new String[t1.length];  
	        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
	          
	        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
	        String t4 = "";  
	        int t0 = t1.length;  
	        try {  
	            for (int i = 0; i < t0; i++) {  
	                // �ж��Ƿ�Ϊ�����ַ�  
	                if (java.lang.Character.toString(t1[i]).matches(  
	                        "[\\u4E00-\\u9FA5]+")) {  
	                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
	                    t4 += t2[0];  
	                } else  
	                    t4 += java.lang.Character.toString(t1[i]);  
	            }  
	            return t4;  
	        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
	            e1.printStackTrace();  
	        }  
	        return t4;  
	    }  
	  
	    // �������ĵ�����ĸ  
	    public static String getPinYinHeadChar(String str) {  
	  
	        String convert = "";  
	        for (int j = 0; j < str.length(); j++) {  
	            char word = str.charAt(j);  
	            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
	            if (pinyinArray != null) {  
	                convert += pinyinArray[0].charAt(0);  
	            } else {  
	                convert += word;  
	            }  
	        }  
	        return convert;  
	    }  
	    
	    public static List<String> getIntersection(List<String> list1,
				List<String> list2) {
			List<String> result = new ArrayList<String>();
			for (String integer : list2) {//����list1
				if (list1.contains(integer)) {//������������
					result.add(integer);//�Ž�һ��list���棬���list���ǽ���
				}
			} 
			return result;
		}
	    
	
}