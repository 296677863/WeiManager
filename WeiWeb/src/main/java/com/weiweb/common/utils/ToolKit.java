package com.weiweb.common.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 字符串的一些操作，看后期是否和StringUtil合并
 */
public class ToolKit {
	
	/**
	 * 不分大小写equal
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equalNoCase(String a,String b) {
		if(a.toUpperCase().equals(b.toUpperCase()))
		{
			return true;
		}
		return false;
	}

	public static boolean isnull(String tmp) {
		if ((tmp == null) || tmp.length() < 1 || tmp.equalsIgnoreCase("null"))
			return true;
		else
			return false;
	}

	public static String toHtml(String s) {
		if (s == null || s.length() < 1)
			return s;
		s = replace(s, "&", "&amp;"); // &amp;
		s = replace(s, "<", "&lt;"); // &#60;
		s = replace(s, ">", "&gt;"); // &#62;
		s = replace(s, "\'", "&#039;"); // &apos;
		s = replace(s, "\"", "&#034;"); // &quot;
		s = replace(s, "\\", "&#92;");
		s = replace(s, "\r", "&#13;");
		s = replace(s, "\n", "&#10;");
		return s;
	}

	public static String toView(String s) {
		if (s == null || s.length() < 1)
			return s;
		s = replace(s, "&#039;", "\'"); // &apos;
		s = replace(s, "&#034;", "\""); // &quot;
		s = replace(s, "&#92;", "\\");
		s = replace(s, "&lt;", "<");
		s = replace(s, "&gt;", ">");
		s = replace(s, "&#13;", "\r");
		s = replace(s, "&#10;", "\n");
		s = replace(s, "&amp;", "&");
		return s;
	}

	public static String XmlEscape(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '<') {
				sb.append("&lt;");
			} else if (c == '>') {
				sb.append("&gt;");
			} else if (c == '\'') {
				sb.append("&#039;"); // &apos;
			} else if (c == '&') {
				sb.append("&amp;");
			} else if (c == '"') {
				sb.append("&#034;"); // &quot;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toFilterSql(String s) {
		String[] filter = { "\\", "'", "\"" };
		String[] ok = { "\\\\", "\\'", "\\\"" };
		if (s == null || s.length() < 1)
			return "";
		for (int i = 0; i < filter.length; i++) {
			s = replace(s, filter[i], ok[i]);
		}
		return s;
	}

	public static String toFilterLikeSql(String s) {
		String[] filter = { "\\", "'", "\"", "%", "_" };
		String[] ok = { "\\\\\\\\", "\\'", "\\\"", "\\%", "\\_" };
		if (s == null || s.length() < 1)
			return "";
		for (int i = 0; i < filter.length; i++) {
			s = replace(s, filter[i], ok[i]);
		}
		return s;
	}

	public static String replace(String source, String oldString,
			String newString) {
		if (source == null)
			return null;
		if (source.length() == 0)
			return "";
		StringBuffer output = new StringBuffer();
		int lengOfSource = source.length();
		int lengOfOld = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfOld;
		}
		if (posStart < lengOfSource)
			output.append(source.substring(posStart));
		return output.toString();
	}

	public static String showDate(String datetime) {
		String temp = datetime;
		if (!isnull(datetime) && datetime.length() >= 10)
			temp = datetime.substring(0, 10);
		return temp;
	}

	public static String nullTrans(String s, String def) {
		if (isnull(s))
			s = def;
		return s;
	}

	public static String trim(String s) {
		if (s != null)
			s = s.trim();
		return s;
	}

	public static String toString(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}

	public static String toString(String[] array) {
		if (array == null || array.length < 1)
			return "";
		else {
			String temp = "";
			for (int i = 0; i < array.length; i++) {
				if (i == 0)
					temp += array[i];
				else
					temp += "," + array[i];
			}
			return temp;
		}
	}

	public static String toString(double d) {
		String value = null;
		try {
			d = formatDouble(d);
			DecimalFormat f = null;
			if (d % 1 == 0)
				f = new DecimalFormat("0");
			else
				f = new DecimalFormat("0.000");
			value = f.format(d);
		} catch (Exception e) {
			// ignore
		}
		return value;
	}

	public static String trans(String s) {
		String result = "";
		if (isnull(s))
			return "";
		try {
			byte[] bytes = s.getBytes("ISO-8859-1");
			result = new String(bytes, "UTF-8");
		} catch (Exception e) {
			System.out.println("trans error: iso8859-1 to utf-8!");
		}
		return result;
	}

	public static String trans(String source, String old_charset,
			String new_charset) {
		String result = "";
		if (isnull(source))
			return "";
		try {
			byte[] bytes = source.getBytes(old_charset);
			result = new String(bytes, new_charset);
		} catch (Exception e) {
			System.out.println("trans error: " + old_charset + " to "
					+ new_charset + "!");
		}
		return result;
	}
	
	public static String[] split(String source, String slipter) {
		String[] strs = new String[0];
		if (source == null)
			return strs;
		StringTokenizer st = new StringTokenizer(source, slipter);
		ArrayList list = new ArrayList();
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		if (list.size() > 0) {
			strs = new String[list.size()];
			for (int i = 0; i < strs.length; i++) {
				strs[i] = (String) list.get(i);
			}
		}
		return strs;
	}

	public static boolean parseBoolean(String s) {
		boolean b = false;
		if ("true".equalsIgnoreCase(s) || "Y".equalsIgnoreCase(s)
				|| "yes".equalsIgnoreCase(s) || "1".equalsIgnoreCase(s))
			b = true;
		return b;
	}

	public static boolean parseBoolean(String s, boolean def) {
		boolean b = def;
		if (!isnull(s))
			b = parseBoolean(s);
		return b;
	}

	public static boolean parseBoolean(Boolean bl) {
		boolean b = false;
		if (bl != null && bl.toString().equalsIgnoreCase("true"))
			b = true;
		return b;
	}

	/**
	 * 如果传入的b为false,则解析bl. 注意b为首选值而非默认值
	 * 
	 * @param bl
	 * @param b
	 * @return
	 */
	public static boolean parsePriority(Boolean bl, boolean b) {
		if (!b)
			b = parseBoolean(bl);
		return b;
	}

	public static double formatDouble(double num) {
		return Math.round(num * 1000) / 1000.0;
	}

	private static DecimalFormat decimalFormat = new DecimalFormat("#.00");// 保留两位小数

	/**
	 * 将数字转换为百分比数字，并保留两位小数，四舍五入。如：0.5763 → 57.63
	 * @param num 数字
	 * @return 百分比数字
	 */
	public static double decimalHandle(double num) {
		return Double.parseDouble(decimalFormat.format(num * 100));
	}

	/**
	 * 保留两位小数，四舍五入。如：16.366 → 16.37
	 * @param num 数字
	 * @return 保留两位小数的数字
	 */
	public static double doubleHandle(double num) {
		return Double.parseDouble(decimalFormat.format(num));
	}

	public static int parseInt(String s, int def) {
		int i = def;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return i;
	}

	public static double parseDouble(String s, double def) {
		double d = def;
		try {
			d = Double.parseDouble(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static int parseInt(Object obj) {
		int i = 0;
		if (obj == null)
			return i;
		if (obj instanceof Number) {
			i = ((Number) obj).intValue();
		} else {
			String s = obj.toString();
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
		return i;
	}

	public static double parseDouble(Object obj) {
		double d = 0;
		if (obj == null)
			return d;
		if (obj instanceof Number) {
			d = ((Number) obj).doubleValue();
		} else {
			String s = obj.toString();
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				// ignore
			}
		}
		return d;
	}

	/**
	 * 将12.34转为:壹拾贰元叁角肆分
	 * 
	 * @param b
	 * @return
	 */
	public static String toRMB(double b) {
		String source = Long.toString(Math.round(b * 100));
		int length = source.length();
		String temp = new String(source);

		HashMap unit = new HashMap();
		unit.put(new Integer(0), "分");
		unit.put(new Integer(1), "角");
		unit.put(new Integer(2), "圆");
		unit.put(new Integer(3), "拾");
		unit.put(new Integer(4), "佰");
		unit.put(new Integer(5), "仟");
		unit.put(new Integer(6), "万");
		unit.put(new Integer(7), "拾");
		unit.put(new Integer(8), "佰");
		unit.put(new Integer(9), "仟");
		unit.put(new Integer(10), "亿");
		unit.put(new Integer(11), "拾");
		unit.put(new Integer(12), "佰");
		unit.put(new Integer(13), "仟");
		for (int i = 0; i < length; i++) {
			// temp前面部分不会改变
			temp = insert(length - i - 1, temp, unit.get(new Integer(i))
					.toString());
		}

		HashMap num = new HashMap();
		num.put(new Integer(0), "零");
		num.put(new Integer(1), "壹");
		num.put(new Integer(2), "贰");
		num.put(new Integer(3), "叁");
		num.put(new Integer(4), "肆");
		num.put(new Integer(5), "伍");
		num.put(new Integer(6), "陆");
		num.put(new Integer(7), "柒");
		num.put(new Integer(8), "捌");
		num.put(new Integer(9), "玖");
		for (int i = 0; i < num.size(); i++) {
			temp = replace(temp, Integer.toString(i), num.get(new Integer(i))
					.toString());
		}
		temp = replace(temp, "零分", "");
		temp = replace(temp, "零角", "零");
		temp = replace(temp, "零拾", "零");
		temp = replace(temp, "零佰", "零");
		temp = replace(temp, "零仟", "零");
		while (temp.indexOf("零零") > 0) {
			temp = replace(temp, "零零", "零");
		}
		temp = replace(temp, "零万", "万");
		temp = replace(temp, "零亿", "亿");
		temp = replace(temp, "零圆", "圆");
		temp = replace(temp, "亿万", "亿");

		// 判断是否加上 整
		if (temp.indexOf("角") == -1 && temp.indexOf("分") == -1)
			temp = temp.substring(0, temp.indexOf("圆") + 1) + "整";
		return temp;
	}

	/**
	 * 向目标字符串中插入指定字符串
	 * 
	 * @param index
	 *            目标位置
	 * @param source
	 *            目标字符串
	 * @param value
	 *            插入的字符串
	 * @return
	 */
	public static String insert(int index, String source, String value) {
		if (isnull(source))
			return value;
		String temp = "";
		if (index < 0)
			temp = value + source;
		else if (index >= source.length() || index == source.length())
			temp = source + value;
		else {
			String pre = source.substring(0, index + 1);
			String last = source.substring(index + 1);
			temp = pre + value + last;
		}
		return temp;
	}

	public static String formatLength(String s, int len) {
		if (s != null && s.length() > len) {
			s = s.substring(0, len) + "...";
		}
		return s;
	}

	/**
	 * 将字符串按指定长度分解成数组
	 * 
	 * @param s
	 * @param size
	 * @return
	 */
	public static String[] split(String s, int size) {
		String[] array = new String[0];
		if (s == null || s.length() <= 0)
			return array;
		int olength = s.length();
		if (olength % size == 0)
			array = new String[s.length() / size];
		else
			array = new String[s.length() / size + 1];
		int i = 0;
		while (s.length() > size) {
			array[i] = s.substring(0, size);
			s = s.substring(size);
			i++;
		}
		array[array.length - 1] = s;
		return array;
	}

	/**
	 * 将2个字符串数组合成一个数组
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] arrayjoin(String[] a, String b[]) {
		String[] s = new String[a.length + b.length];
		System.arraycopy(a, 0, s, 0, a.length);
		System.arraycopy(b, 0, s, a.length, b.length);
		return s;
	}

	/**
	 * 除法运算。如果分母为0则返回0，否则做除法运算。
	 * @param numerator 分子
	 * @param denominator 分母
	 * @return 结果
	 */
	public static double division(double numerator, double denominator) {
		return denominator == 0 ? 0 : numerator / denominator;
	}

	public static double zeroHandler(double num, double Expression) {
		double Result = 0.0;
		if (num != 0) {
			Result = doubleHandle(Expression);
		} else {
			Result = 0.0;

		}
		return Result;

	}

	// 除数为零的处理 zParam 除数 Expression 计算公式
	public static List<Double> AdvancedHandler(double zParam,
			double... Expression) {

		List<Double> result = new LinkedList<Double>();

		if (zParam != 0) {
			for (double dparam : Expression) {

				result.add(doubleHandle(dparam));
			}

		} else {

			for (@SuppressWarnings("unused")
			double dparam : Expression) {
				result.add(0.0);
			}
		}
		return result;
	}
	
	public static  String doubleFormat(double d)
	{
		
		DecimalFormat   df   =   new   DecimalFormat( "#,###,##0.00"); 
		return   df.format(d); 
	} 
	
	public static  String intFormat(int d)
	{
		DecimalFormat   df   =   new   DecimalFormat( "#,###,###"); 
		return   df.format(d); 
	} 
	
	
	

	public static void main(String[] args) {
		System.out.println(ToolKit.doubleFormat(0));
	}
}
