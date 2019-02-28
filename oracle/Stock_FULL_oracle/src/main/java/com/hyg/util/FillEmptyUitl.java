package com.hyg.util;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 填充空格
 * 
 * @author Administrator
 *
 */
public class FillEmptyUitl {
	public static final String[] QUAN_STR = { "？", "，", "。", "：", "；", "　",
			"！", "“", "”" };

	public static void main(String[] args) {

		// System.out.println(s1.contains("？"));
		// System.out.println(s.getBytes().length + "-------" +
		// s1.getBytes().length);
		// System.out.println(s2.getBytes().length + "-------" +
		// s3.getBytes().length);

		// String input = "1234我是中国人？4564阿斯顿发斯蒂芬";
		// String temp = null;
		// Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
		// Matcher m = p.matcher(input);
		// while (m.find())
		// {
		//
		// System.out.println("input的长度是:" + input.getBytes().length);
		// temp = m.group(0);
		// System.out.println(temp + ":" + temp.length());
		// }
		// UserServiceI userServiceI = new UserServiceImpl();
		// userServiceI.searchUser(new Scanner(System.in));

		// System.out.printf("%-8s", "sdf阿斯");

		String s1 = "阿斯蒂？";
		String s2 = "阿斯顿";
		int s1len = s1.getBytes().length;
		int s2len = s2.getBytes().length;

		if (s1len > s2len) {
//			System.out.println(toSemiangle(s1) + "--" + s2);
//			System.out.println(toSemiangle(s2) + "\u0020--" + s1);
//			String format = "%-" + 20 + "s";
//			System.out.println(format + "----" + String.format(format, s1)
//					+ "-------------");
//
//			System.out.println(format + "----" + String.format(format, s2)
//					+ "-------------");
		}
		
		System.out.println("---中国s中國s" + (char) 32 + "----");
		System.out.println("---中国中中國S" + (char) 12288 + "----");

		// System.out.println(s1 );
		//
		// System.out.println(String.format("%-8s", "阿斯sdf阿斯") + "----" +
		// String.format("%-8s", "sdf阿斯顿"));
		// System.out.println(String.format("%-8s", "sdf阿斯") + "----" +
		// String.format("%-8s", "sdf阿斯顿？"));

	}

	public static String formatColumn(int totalLen, String... content) {
		StringBuffer s = null;
		StringBuffer formartString = new StringBuffer();
		String lastStr = "";

		for (int i = 0; i < content.length; i++) {
			int length = content[i].length();
			String tempStr = content[i];
			// System.out.println(tempStr+"的长度是:" + tempStr.getBytes().length);

			if (length > totalLen) {
				s = new StringBuffer(content[i].substring(0, totalLen - 3)
						+ "...");
				s.append("\t\t");
			} else if (length < totalLen) {
				int subLength = totalLen - length;
				s = new StringBuffer(content[i]);
				if (length > 0) {
					lastStr = s.substring(s.length() - 1, s.length());
				}
				// while(subLength > 0){
				// if(containQuanStr(lastStr) || isChinese(lastStr)){
				// s.insert(s.length(),"　");
				// }else{
				// s.insert(s.length(),"          ");
				s.append("\t");
				// }

				subLength--;
				// }
			} else {
				s = new StringBuffer(content[i]);
			}
			formartString.append(s);
		}
		return formartString.toString();

	}

	/**
	 * 判断是否是全角字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean containQuanStr(String s) {
		boolean quanFlag = false;
		for (int i = 0; i < QUAN_STR.length; i++) {
			if (s.contains(QUAN_STR[i])) {
				quanFlag = true;
				break;
			}
		}
		return quanFlag;
	}

	/**
	 * 判断是否是中文
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isChinese(String s) {
		Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
		Matcher m = p.matcher(s);
		return m.find();
	}

	private static int b2i(byte n) {
		int m = n;
		if (n < 0) {
			m = n + 256;
			return m;
		}
		return m;
	}

	private static byte i2b(int n) {
		byte m = (byte) n;
		if (n > 127) {
			m = (byte) (n - 256);
			return m;
		}
		return m;
	}

	private static byte[] getOutputList(byte[] m) { // 过滤byte数组中为0的元素
		int len = m.length;
		StringBuffer temp = new StringBuffer(1024);
		for (int i = 0; i < len - 1; i++) {
			if (m[i] != 0) {
				temp.append(m[i]);
				temp.append(",");
			}
		}
		temp.append(m[len - 1]);
		StringTokenizer st = new StringTokenizer(temp.toString(), ",");
		int total = st.countTokens();
		byte[] newList = new byte[total];
		for (int i = 0; i < total; i++) {
			newList[i] = (byte) Integer.parseInt(st.nextToken());
		}
		return newList;

	}
	
	
	public static String toSemiangle(String src) {
        char[] c = src.toCharArray();
        for (int index = 0; index < c.length; index++) {
            if (c[index] == 12288) {// 全角空格
                c[index] = (char) 32;
            } else if (c[index] > 65280 && c[index] < 65375) {// 其他全角字符
                c[index] = (char) (c[index] - 65248);
            }
        }
        return String.valueOf(c);
    }

}
