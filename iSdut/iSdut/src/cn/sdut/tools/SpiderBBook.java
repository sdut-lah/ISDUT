package cn.sdut.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于抓取图书馆图书信息
 * 
 */
public class SpiderBBook {

	private static String surl = null;
	private static URL url = null;
	private static URLConnection connection = null;
	private static OutputStreamWriter out = null;
	private static String sCurrentLine = null;
	private static InputStream l_urlStream = null;
	private static BufferedReader l_reader = null;
	private static String postStr = null;
	private static String xh = null;
	private static String pw = null;

	/**
	 * 把utf字符转为正常字符
	 */
	private static String toStr(String utf) {
		String[] strs = utf.split("&#x");
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < strs.length; i++) {
			int temp = Integer.parseInt(
					strs[i].substring(0, strs[i].length() - 1), 16);
			sb.append((char) temp);
		}
		return sb.toString();
	}

	/**
	 * 执行爬虫的抓取
	 * 
	 * @return
	 * @throws Exception
	 */
	private static String doSpider() throws Exception {
		url = new URL(surl);
		connection = url.openConnection();
		HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
		httpUrlConnection.setDoOutput(true);
		out = new OutputStreamWriter(httpUrlConnection.getOutputStream(),
				"UTF-8");
		postStr = "xh=" + xh + "&" + "pw=" + pw;
		out.write(postStr);
		out.flush();
		out.close();
		out = null;
		sCurrentLine = "";
		l_urlStream = httpUrlConnection.getInputStream();
		StringBuffer sb = new StringBuffer();
		l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sb.append(sCurrentLine);
		}
		return sb.toString();
	}

	/**
	 * 图书馆的登录
	 * 
	 * @param xh
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public static boolean login(String xh, String pw) throws Exception {
		surl = "http://gotit.asia/libr";
		SpiderBBook.xh = xh;
		SpiderBBook.pw = pw;
		String jsonStr = doSpider();
		System.out.println(xh + " " + pw);
		System.out.println(jsonStr);
		if (jsonStr.contains("密码错误")) {
			return false;
		}
		return true;
	}

	/**
	 * 得到借书信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getBook() throws Exception {
		List ret = new ArrayList();
		surl = "http://gotit.asia/libr";
		String jsonStr = doSpider();
		jsonStr = jsonStr.substring(jsonStr.indexOf("</tr>") + 5);
		jsonStr = jsonStr.trim();
		String[] args = jsonStr.split("</tr>");
		for (int i = 0; i < args.length - 1; i++) {
			args[i] = args[i].trim();
			String[] temp = args[i].split("</td>");
			for (int j = 0; j < temp.length; j++) {
				temp[j] = temp[j].trim().substring(
						temp[j].indexOf("class=\"whitetext\""));
				temp[j] = temp[j].trim().substring(
						temp[j].indexOf("=\"whitetext\"") + 25);
			}
			Map tt = new HashMap();
			tt.put("id", temp[0]);
			tt.put("name", toStr(temp[1].substring(temp[1].indexOf("&#"))
					.replace("</a>", "")));
			tt.put("author", toStr(temp[2]));
			tt.put("btime", temp[3]);
			tt.put("etime", temp[4].substring(13, 23));
			tt.put("loc", temp[5]);
			ret.add(tt);
		}
		return ret;
	}

}
