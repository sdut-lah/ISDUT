package cn.sdut.tools;

import java.io.BufferedReader;
import java.io.IOException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sdut.dao.Person;

/**
 * 爬虫，哇偶 早起的爬虫被鸟吃
 */

public class Spider {

	private static String surl = null;
	private static URL url = null;
	private static URLConnection connection = null;
	private static OutputStreamWriter out = null;
	private static String sCurrentLine = null;
	private static HttpURLConnection httpUrlConnection = null;
	private static InputStream l_urlStream = null;
	private static BufferedReader l_reader = null;
	private static String postStr = null;
	private static String uid = null;
	private String xh = null;
	private String pw = null;
	private static JSONObject jo = null;

	private String cookieVal = null;
	private String sessionId = "";
	private String key = null;

	private static class Instance {
		private static final Spider INSTANCE = new Spider();
	}

	public static Spider getInstance() {
		return Instance.INSTANCE;
	}

	/**
	 * 登陆，验证教务处学号和密码
	 * 
	 * @param xh
	 * @param pw
	 * @return
	 * @throws Exception
	 */

	public boolean login(String xh, String pw) throws Exception {
		this.xh = xh;
		this.pw = pw;
		System.out.println("spider login:" + xh + " " + pw);
		return init();
	}

	/**
	 * 初始化，得到uid顺带验证用户名密码
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean init() throws Exception {
		surl = "http://api.gotit.asia/user/login.json?nocode=true";
		url = new URL(surl);
		connection = url.openConnection();
		HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
		httpUrlConnection.setRequestProperty("accesstoken",
				"2436dba7713b4680802eeacbd275ffd0");
		httpUrlConnection.setDoOutput(true);
		out = new OutputStreamWriter(httpUrlConnection.getOutputStream(),
				"UTF-8");
		postStr = "{\"xh\": \"" + xh + "\",\"pw\": \"" + pw + "\"}";
		System.out.println(postStr);
		out.write(postStr);
		out.flush();
		out.close();
		out = null;
		sCurrentLine = "";
		StringBuffer sb = new StringBuffer();
		try {
			l_urlStream = httpUrlConnection.getInputStream();
			l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		} catch (Exception ignor) {
		}
		String jsonStr = sb.toString();
		System.out.println(jsonStr);
		if (jsonStr == null || jsonStr.contains("密码")
				|| jsonStr.contains("用户名不存在") || jsonStr.contains("请求错误")) {
			return false;
		}
		try {
			jo = new JSONObject(jsonStr);
			uid = jo.getJSONObject("data").getString("uid");
			System.out.println(uid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * let jvm do gc
	 * 
	 * @throws IOException
	 */
	private void gc() throws IOException {
		surl = null;
		url = null;
		connection = null;
		out = null;
		sCurrentLine = null;
		httpUrlConnection = null;
		l_reader.close();
		l_urlStream.close();
		l_urlStream = null;
		l_reader = null;
		postStr = null;
		jo = null;
	}

	/**
	 * 抓取数据
	 * 
	 * @return
	 * @throws Exception
	 */
	private String doSpider() throws Exception {
		url = new URL(surl);
		connection = url.openConnection();
		httpUrlConnection = (HttpURLConnection) connection;
		httpUrlConnection.setRequestProperty("accesstoken",
				"2436dba7713b4680802eeacbd275ffd0");
		httpUrlConnection.setDoOutput(true);
		out = new OutputStreamWriter(httpUrlConnection.getOutputStream(),
				"UTF-8");
		jo = new JSONObject();
		try {
			jo.put("uid", uid);
			jo.put("xh", xh);
			jo.put("pw", pw);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		postStr = jo.toString();
		System.out.println(postStr);
		out.write(postStr);
		out.flush();
		out.close();
		out = null;
		sCurrentLine = "";
		// httpUrlConnection.setDoOutput(false);
		int status = httpUrlConnection.getResponseCode();
		System.out.println("response code::" + status);
		l_urlStream = httpUrlConnection.getInputStream();
		StringBuffer sb = new StringBuffer();
		l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sb.append(sCurrentLine);
		}
		gc();
		return sb.toString();
	}

	/**
	 * 抓取数据，with cookie
	 * 
	 * @param postStr
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private String doSpider2(String postStr, int type) throws Exception {
		url = new URL(surl);
		connection = url.openConnection();
		httpUrlConnection = (HttpURLConnection) connection;
		httpUrlConnection.setRequestProperty("accesstoken",
				"2436dba7713b4680802eeacbd275ffd0");
		if (type == 1) {
			httpUrlConnection.setRequestProperty("Cookie", sessionId);
		}
		if (type == 0) {
			httpUrlConnection.setDoOutput(true);
			out = new OutputStreamWriter(httpUrlConnection.getOutputStream(),
					"UTF-8");
			out.write(postStr);
			out.flush();
			out.close();
			out = null;
		}
		for (int i = 1; (key = httpUrlConnection.getHeaderFieldKey(i)) != null; i++) {
			if (key.equalsIgnoreCase("set-cookie")) {
				cookieVal = httpUrlConnection.getHeaderField(i);
				cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
				sessionId = sessionId + cookieVal + ";";
			}
		}
		sCurrentLine = "";
		l_urlStream = httpUrlConnection.getInputStream();
		StringBuffer sb = new StringBuffer();
		l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sb.append(sCurrentLine);
		}
		gc();
		return sb.toString();
	}

	/**
	 * 得到考试时间
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTime() throws Exception {
		List ret = new ArrayList();
		surl = "http://gotit.asia/zheng/nocode";
		postStr = "xh=" + xh + "&" + "pw=" + pw;
		doSpider2(postStr, 0);
		surl = "http://gotit.asia/more/kaoshi";
		String html = doSpider2("", 1);
		if (html.indexOf("<tr>") == -1 || html.lastIndexOf("</tr>") == -1) {
			return null;
		}
		html = html.substring(html.indexOf("<tr>"), html.lastIndexOf("</tr>"));
		String[] names = html.split("</tr>");
		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].trim();
			String[] temp = names[i].split("</td>");
			for (int j = 0; j < temp.length; j++) {
				temp[j] = temp[j].substring(4);
			}
			Map tt = new HashMap();
			tt.put("course", temp[1]);
			tt.put("time", temp[3]);
			tt.put("location", temp[4]);
			tt.put("num", temp[6]);
			ret.add(tt);
		}
		System.out.println(ret);
		return ret;
	}

	/**
	 * 得到当前学期考试成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Person> getCurrentScore() throws Exception {
		surl = "http://api.gotit.asia/score/current_semester.json?nocode=true";
		String jsonStr = doSpider();
		System.out.println("jieguo="+jsonStr);
		List<Person> list = new ArrayList<Person>();
		try {
			jo = new JSONObject(jsonStr);
			JSONObject res = jo.getJSONObject("data");
			System.out.println("res==" + res);
			String string = res.toString();
			string = string.replace("}", "");
			string = string.replace("{", "");
			string = string.replace("\"", "");
			System.out.println("string==" + string);
			String[] temp = string.split(",");
			for (int i = 1; i <= temp.length; i++) {
				String[] tt = temp[i - 1].split(":");
				list.add(new Person(i, tt[0], tt[1]));
				System.out.println("fenge"+tt[0]+" "+tt[1]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null; 
		}
        System.out.println(list);
		return list;
	}

	/**
	 * 得到课表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List getSchedule() throws Exception {
		surl = "http://api.gotit.asia/user/timetable/current_semester.json?nocode=true";
		String jsonStr = doSpider();
		// System.out.println(jsonStr);
		jo = new JSONObject(jsonStr);
		JSONArray ja = jo.getJSONArray("data");
		List<Map> list = new ArrayList<Map>();
		String sb = null;
		for (int i = 0; i < ja.length(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", ja.getJSONObject(i).getString("name"));
			String str1 = ja.getJSONObject(i).getString("plan");
			String[] p = str1.split("\\{");
			String[] pp = p[1].split("\\}");
			map.put("plan", pp[0]);
			map.put("teacherName", ja.getJSONObject(i).getString("teacherName"));
			map.put("dayOfWeek", ja.getJSONObject(i).getString("dayOfWeek"));
			sb = ja.getJSONObject(i).getString("oddEvenFlag");
			map.put("oddEvenFlag", sb);
			map.put("sectionFrom", ja.getJSONObject(i).getString("sectionFrom"));
			map.put("sectionTo", ja.getJSONObject(i).getString("sectionTo"));
			deal(ja.getJSONObject(i).getString("location"), map, list);
		}
		System.out.println(list);
		return list;

	}

	/**
	 * 处理课表
	 * 
	 * @param sb
	 * @param map
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void deal(String sb, Map map, List list) {

		String[] lot = sb.split("<br /><br />");
		map.put("location", lot[0]);
		list.add(map);
		System.out.println(map);
		for (int i = 1; i < lot.length; i++) {
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("dayOfWeek", (String) map.get("dayOfWeek"));
			map1.put("sectionFrom", (String) map.get("sectionFrom"));
			map1.put("sectionTo", (String) map.get("sectionTo"));
			String[] arr1 = lot[i].split("<br />");
			String[] temp1 = arr1[1].split("\\{");
			String[] temp2 = temp1[1].split("\\}");
			arr1[1] = temp2[0];

			String[] mark = new String[5];
			mark[0] = "name";
			mark[1] = "plan";
			mark[2] = "teacherName";
			mark[3] = "location";
			for (int j = 0; j < arr1.length; j++) {
				map1.put(mark[j], arr1[j]);
			}
			list.add(map1);
			System.out.println(map);
		}
		System.out.println(list);
	}

	/**
	 * 得到所有成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Person> getAllScore() throws Exception {

		surl = "http://api.gotit.asia/user/score/all.json?nocode=true";
		String jsonStr = doSpider();
		List<Person> list2 = new ArrayList<Person>();
		try {
			jo = new JSONObject(jsonStr);
			JSONObject res = jo.getJSONObject("data");
			String string = res.toString();
			string = string.replace("{", "");
			string = string.replace("\"", "");
			String[] temp = string.split("\\},");
			for (int i = 1; i <= temp.length; i++) {
				String[] tt = temp[i - 1].split(",");
				tt[1] = tt[1].replace("}", "");
				String[] tt1 = tt[0].split(":");
				String[] tt2 = tt[1].split(":");
				if (tt1[1].equals("initial")) {
					if (isNumeric(tt1[1])) {
						double in = Double.parseDouble(tt1[1]);
						if (in < 60.0) {
							tt1[2] = tt2[1];
						}
					} else {
						if (tt1[2].equals("不及格") || tt1[2].equals("不合格")
								|| tt1[2].equals("缺考")) {
							if (tt2[1] != null)
								tt1[2] = tt2[1];
						}
					}
					list2.add(new Person(i, tt1[0], tt1[2]));
					System.out.println(i + " " + tt1[0] + " " + tt1[2]);
				} else {
					if (isNumeric(tt2[1])) {
						double in = Double.parseDouble(tt2[1]);
						if (in < 60.0) {
							tt2[1] = tt1[2];
						}
					} else {
						if (tt2[1].equals("不及格") || tt2[1].equals("不合格")
								|| tt2[1].equals("缺考")) {
							if (tt2[1] != null)
								tt2[1] = tt1[2];
						}
					}

					list2.add(new Person(i, tt1[0], tt2[1]));
					System.out.println(i + " " + tt1[0] + " " + tt2[1]);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return list2;
	}

	/**
	 * 数字判断
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 得到绩点
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getGpa() throws Exception {
		surl = "http://api.gotit.asia/user/score/gpa.json?nocode=true";
		String jsonStr = doSpider();
		jo = new JSONObject(jsonStr);
		String s = jo.getJSONObject("data").getString("gpa");
		return s;
	}

	/**
	 * 得到四六级成绩
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Person> getCet() throws Exception {
		surl = "http://api.gotit.asia/user/score/former_cet.json?nocode=true";
		List<Person> list3 = new ArrayList<Person>();
		String jsonStr = doSpider();
		jo = new JSONObject(jsonStr);
		JSONObject res = jo.getJSONObject("data");
		String string = res.toString();
		string = string.replace("{", "");
		string = string.replace("}", "");
		string = string.replace("\"", "");
		String[] arr = string.split(",");
		for (int i = 0; i < arr.length; i++) {
			String[] s = arr[i].split(":");
			String[] ss = s[0].split("-");
			int time = Integer.parseInt(ss[0]);
			list3.add(new Person(i + 1, time + "--" + ss[1], s[1]));
		}
		return list3;
	}

}
