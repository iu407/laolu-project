package com.prs.jy.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Hellfire
 * @version 1.0
 */
public class Function {

	public static String format(Date date, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	public static Date parse(String src, String fmt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.parse(src);
	}

	public static boolean isBlank(String str) {
		return null == str || str.length() == 0;
	}

	public static boolean isNumber(String str) {
		if (null == str) {
			return false;
		} else {
			return str.matches("[1-9]\\d*");
		}
	}

	public static boolean isInteger(String str) {
		if (null == str) {
			return false;
		} else {
			return str.matches("[+-]?\\d+");
		}
	}

	public static boolean isFloat(String str) {
		if (null == str) {
			return false;
		} else {
			return str.matches("[+-]?(\\d*\\.\\d+|\\d+)");
		}
	}

	public static boolean contain(Object[] objs, Object obj) {
		if (null == objs || null == obj) {
			return false;
		}
		for (Object item : objs) {
			if (obj.equals(item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contain(String[] strs, String str) {
		if (null == strs || null == str) {
			return false;
		}
		for (String item : strs) {
			if (str.equals(item)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contain(String commaStr, String str) {
		if (null == commaStr || null == str) {
			return false;
		}
		String[] strs = commaStr.split(",");
		return contain(strs, str);
	}

	public static String array2String(String[] strs) {
		if (null == strs) {
			return null;
		}
		StringBuffer re = new StringBuffer();
		for (String str : strs) {
			re.append(',');
			re.append(str);
		}
		if (re.length() > 0) {
			return re.substring(1);
		} else {
			return "";
		}
	}

	public static int parseInt(Object object) {
		if (null == object) {
			throw new NumberFormatException("null");
		} else if (object instanceof Integer) {
			return (Integer) object;
		} else if (object instanceof Long) {
			return ((Long) object).intValue();
		} else if (object instanceof BigInteger) {
			return ((BigInteger) object).intValue();
		} else if (object instanceof BigDecimal) {
			return ((BigDecimal) object).intValue();
		} else if (object instanceof String) {
			return Integer.parseInt((String) object);
		} else {
			return Integer.parseInt(object.toString());
		}
	}
	
	public static int parseInt(long id) {
		return String.valueOf(id).length();
	}

}
