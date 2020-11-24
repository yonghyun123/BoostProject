package kr.or.connect.project3.util;

public class Util {
	public static String escapeJson(String str){
		return str.replace("\\", "\\\\")
				.replace("\'", "\\\'")
				.replace("\"", "\\\"")
				.replace("\r\n", "\\n")
				.replace("\n", "\\n")
				.replace("'", "\"");
	}
}
