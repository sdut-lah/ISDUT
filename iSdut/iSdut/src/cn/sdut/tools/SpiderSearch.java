package cn.sdut.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *用于爬取读书馆藏书剩余信息 
 *
 */
public class SpiderSearch {

    private static URL url = null;
    private static URLConnection connection = null;
    private static String sCurrentLine = null;
    private static InputStream l_urlStream = null;
    private static BufferedReader l_reader = null;
    
    private String toStr(String utf) {
        String[] strs = utf.split("&#x");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < strs.length; i++) {
            strs[i] = strs[i].replace(";&nbsp", "");
        	int temp = Integer.parseInt(
            strs[i].substring(0, strs[i].length() - 1), 16);
            sb.append((char) temp);
        }
        return sb.toString();
    }
    
	@SuppressWarnings({"rawtypes", "unchecked" })
	public List getBook(String bookName) throws Exception{
		String strUtf = URLEncoder.encode(bookName, "UTF-8");
		String urlGet = "http://222.206.65.12/opac/openlink.php?historyCount=1&"
				+ "strText="+strUtf
				+ "&doctype=ALL&strSearchType=title&match_flag=forward&displaypg=20"
				+ "&sort=CATA_DATE&orderby=desc&showmode=list&dept=ALL";
        url = new URL(urlGet);
        connection = url.openConnection();
        HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
        sCurrentLine = "";
        l_urlStream = httpUrlConnection.getInputStream();
        StringBuffer sb = new StringBuffer();
        l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "UTF8"));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sb.append(sCurrentLine);
        }
        String  str = sb.toString();
        int loc = str.indexOf("<div class=\"list_books\" id=\"list_books\">");
        if(loc==-1){
        	return null;
        }
        str = str.substring(loc);
        //System.out.println(str);
        String []books = str.split("</div>");
        List ret = new ArrayList();
        for(int i = 0; i < books.length-6; i++){
        	Map dto = new HashMap();
        	books[i] = books[i].trim().substring(134);
        	String []t1 = books[i].split("</a>");
        	dto.put("name", toStr(t1[0].trim()));
        	String []t2 = t1[1].split("</h3>");
        	dto.put("id", toStr(t2[0].trim()));
        	String temp = t2[1].substring(t2[1].indexOf("</strong>")+9);
        	t1 = temp.split("<br />");
        	dto.put("express", toStr(t1[2].substring(0, t1[2].indexOf("</p>")).trim()));
        	dto.put("total", t1[0].trim());
        	temp = t1[1].substring(t1[1].indexOf("</strong>")+9);
        	t2 = temp.split("</span>");
        	dto.put("left", t2[0].trim());
        	t1 = t2[1].split("<br />");
        	dto.put("author", toStr(t1[0].trim()));
        	ret.add(dto);
        }
        System.out.println(ret.size());
        return ret;
	}

}
