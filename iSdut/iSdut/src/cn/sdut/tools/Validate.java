package cn.sdut.tools;



/**
 * 一些东西的验证类
 * @author qinchuan
 *
 */
public class Validate {
	/**
	 * 验证学号，10或11位数字；
	 * 11级及以前为10位，12级及以后为11位
	 * @param str
	 * @return
	 */
	public static boolean checkUname(String str){
		return str.matches("\\d{10,11}");
	}
	
}
