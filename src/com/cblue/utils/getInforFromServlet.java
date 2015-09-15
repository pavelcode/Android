package com.cblue.utils;

public class getInforFromServlet {
	public static String getXml(String str) {
		StringBuilder sb = new StringBuilder(
				"<?xml version='1.0' encoding='utf-8'?>");
		sb.append("\r\n");
		sb.append("<students>");
		sb.append("\r\n");
		str = str.replace("{", "");
		str = str.replace("}", "");
		String[] subStr = str.split("<br>");
		for (int i = 0; i < subStr.length; i++) {
			sb.append("<student>");
			sb.append("\r\n");
			String subStr1[] = subStr[i].split(", ");
			for (int j = 0; j < subStr1.length; j++) {
				String[] subStr2 = subStr1[j].split("=");
				sb.append("<" + subStr2[0] + ">" + subStr2[1] + "</"
						+ subStr2[0] + ">");
				sb.append("\r\n");
			}
			sb.append("</student>");
			sb.append("\r\n");
		}
		sb.append("<students>");
		return sb.toString();
	}

	public static void main(String[] args) {
		String url = "http://localhost:8080/Android/adminServlet";
		String params = "administrator=chaimengge&password=mengge";
		byte[] buf = HttpURLConnHelper.doPostSubmit(url, params);
		String str = new String(buf);
		// System.out.println(getXml(str));
		new MyIOHelper().writeTextFile(getXml(str), "f:\\test.xml");

	}

}
