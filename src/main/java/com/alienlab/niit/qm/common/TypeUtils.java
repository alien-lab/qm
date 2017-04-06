package com.alienlab.niit.qm.common;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实现Java基本类型之间转换
 * 
 */
public class TypeUtils {

	/**
	 * 判断对象是否为空,(包括null and "")
	 * 
	 * @param o
	 *            判断对象
	 * @return 为空返回True,不为空返回False
	 */
	public static boolean isEmpty(Object o) {

		return (o == null || o.toString().equals("")) ? true : false;
	}

	public static boolean isEmpty(String s) {

		return s == null || s.equals("");
	}

	public static boolean isEmpty(List<?> list) {

		return (list == null || list.size() <= 0);
	}

	public static String getString(Object o) {
		return o != null ? o.toString() : "";
	}

	public static String getStringForEdit(Object o) {
		return o != null && !"".equals(o.toString()) ? o.toString() : "null";
	}

	public static String subString(String s, int index, int length) {
		return s != null ? s.substring(index, length) : "";
	}

	public static int getIntx(String s) {
		return getIntx(s, 0);
	}

	public static int getIntx(String s, int defaultValue) {
		if (s != null) {
			s = s.replaceAll("[^\\d]", "");
			return getInt(s, defaultValue);
		}
		return defaultValue;
	}

	public static int getInt(String s) {
		return getInt(s, 0);
	}

	public static boolean getBoolean(String s) {

		return Boolean.parseBoolean(s);
	}

	public static int getInt(String s,int def) {
		int num=def;
		if(s!=null&&!"".equals(s)){
			num=new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		}
		return num;
	}

	public static int getInt(Object o, int defaultValue) {
		return getInt(getString(o), defaultValue);
	}

	public static int getInt(Object o) {
		return getInt(o, 0);
	}

	public static float getFloat(String s, float defaultValue) {
		float i = 0;
		if (!isEmpty(s)) {
			try {
				i = Float.parseFloat(s);
			} catch (NumberFormatException e) {
				i = defaultValue;
			}
		} else {
			i = defaultValue;
		}
		return i;
	}

	public static float getFloat(String s) {
		return getFloat(s, 0);
	}

	public static float getFloat(Object o, float defaultValue) {
		return getFloat(getString(o), defaultValue);
	}

	public static float getFloat(Object o) {
		return getFloat(getString(o), 0);
	}

	public static String getDecimal(Object o, String format) {
		DecimalFormat df = new DecimalFormat(format);
		float f = getFloat(o, 0);
		String str = df.format(f);
		if (str.indexOf(".") == 0)
			str = "0" + str;
		return str;
	}

	public static String dcodeUtf8(String str) {
		str = TypeUtils.getString(str);
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static double getMinNum(double[] args) {
		if (args != null & args.length > 0) {
			double max = 0;
			double min = 99999999999L;
			for (double f : args) {
				// 顺序排序�? 水位 r
				if (args.length == 1) {
					min = f;
				} else {
					if (f > max) {
						max = f;
					}
					if (f < min) {
						min = f;
					}
				}

			}
			return min;
		}
		return 0;
	}

	public static double getMaxNum(double[] args) {

		if (args != null & args.length > 0) {
			double max = 0;
			double min = 99999999999d;
			for (double f : args) {
				// 顺序排序�? 水位 r
				if (args.length == 1) {
					max = f;
				} else {
					if (f > max) {
						max = f;
					}
					if (f < min) {
						min = f;
					}
				}

			}
			return max;
		}
		return 0;
	}

	/**
	 * 把文本编码为Html代码
	 * 
	 * @param str
	 *            字符�?
	 * @return 编码后的字符�?
	 */
	public static String htmEncode(String str) {
		if (str == null)
			return "";
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("'", "''");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\n", "<br/>");
		return str;
	}

	/**
	 * 获取当前时间，yyyyMMddhhmmss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(c.getTime());
	}
	
	/**
	 * 获取当前时间，yyyyMMddhhmmss格式
	 * 
	 * @return
	 */
	public static String getTime(String specialformat) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(specialformat);
		return format.format(c.getTime());
	}
	
	public static String getTime(Date date,String specialformat) {
		SimpleDateFormat format = new SimpleDateFormat(specialformat);
		return format.format(date);
	}
	
	public static Date str2date(String datestr,String format){
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date date=new Date();
		try {
			date = sformat.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	

	public static String logMap(Map<String, String> map) {
		StringBuffer result = new StringBuffer();
		for (String key : map.keySet()) {
			result.append("key:" + key + "\t");
			result.append("value:" + map.get(key) + ";\n");
		}
		return result.toString();
	}
	public static String string2Unicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }


	public static String unicode2String(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

	   
//		public static void main(String [] args){
////			TypeUtils t=new TypeUtils();
////			String str="";
////			try {
////				str = new String(("我是中国�?".getBytes("UTF-8")),"UTF-8");
////			} catch (UnsupportedEncodingException e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
////			String ustr="";
////			try {
////				System.out.println(str);
////				ustr = t.string2Unicode(str);
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			System.out.println(ustr);
////			System.out.println(t.unicode2String(ustr));
//			Date dt=new Date();
//			dt.setTime((Long.parseLong("1460020047")*1000));
//			System.out.println(TypeUtils.getTime(dt,"yyyyMMddHHmmss")+","+dt.getTime());
//		}
	  


}
