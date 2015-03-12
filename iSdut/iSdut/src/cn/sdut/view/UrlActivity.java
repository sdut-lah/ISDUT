package cn.sdut.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import cn.sdut.R;

/**
 * 网址-Activity
 *
 */
public class UrlActivity extends Activity {
	WebView WebView01;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infourl);
		WebView01 = (WebView) findViewById(R.id.urlwebView);
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> 欢迎您 </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> 各网站信息 <a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"\">"
				+ "</a></h2>");
		sb.append("<h2> <a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.sdut.edu.cn/\">"
				+ "山东理工大学</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jwch.sdut.edu.cn/\">"
				+ "山东理工大学教务处</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jxxy.sdut.edu.cn/\">"
				+ "机械工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jtxy.sdut.edu.cn/\">"
				+ "交通与车辆工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://qgxy.sdut.edu.cn/\">"
				+ "农业工程与食品科学学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://dz.sdut.edu.cn/index.seam\">"
				+ "电气与电子工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jsjxy.net/\">"
				+ "计算机科学与技术学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://hgxy.sdut.edu.cn/\">"
				+ "化学工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jgxy.sdut.edu.cn/\">"
				+ "建筑工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://ziyuan.sdut.edu.cn/\">"
				+ "资源与环境工程学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xcl.sdut.edu.cn/\">"
				+ "材料科学与工程学院 </a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://http://smkx.sdut.edu.cn/\">"
				+ "生命科学学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lxy.sdut.edu.cn/\">"
				+ "理学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://bs.sdut.edu.cn/\">"
				+ "商学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://wxy.sdut.edu.cn/index.html\">"
				+ "文学与新闻传播学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://wyxy.sdut.edu.cn/\">"
				+ "外国语学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://law.sdut.edu.cn/uploads/index.html\">"
				+ "法学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://mksxy.sdut.edu.cn/\">"
				+ "马克思主义学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://msxy.sdut.edu.cn/\">"
				+ "美术学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://yyx.sdut.edu.cn/\">"
				+ "音乐学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://tyx.sdut.edu.cn/\">"
				+ "体育学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://gfjy.sdut.edu.cn/\">"
				+ "国防教育学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://fzxy.sdut.edu.cn/\">"
				+ "鲁泰纺织服装学院</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdlgdxxsh.tuanjuwang.com/\">"
				+ "山东理工大学学生会</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://stgz.lgqn.cn/\">"
				+ "山东理工大学学生社团联合会</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.lgqn.cn/\">"
				+ "理工青年网</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.youthol.cn/\">"
				+ "青春在线</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://ecard.sdut.edu.cn/\">"
				+ "校园一卡通自助查询平台</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://youth.sdut.edu.cn/swzx/index.aspx\">"
				+ "大学生事务中心</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.xszzw.net/Home/Default.aspx\">"
				+ "学生资助网</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://yjsh.sdut.edu.cn/index.html\">"
				+ "研究生处</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://office.sdut.edu.cn/\">"
				+ "党委校长办公室</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgwindow.sdut.edu.cn/\">"
				+ "山东理工大学新闻网</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lib.sdut.edu.cn/index.html\">"
				+ "山东理工大学图书馆</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xy.sdut.edu.cn/\">"
				+ "山东理工大学校友网</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgys.sdut.edu.cn/\">"
				+ "山东理工大学饮食服务中心</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgradio.sdut.edu.cn/\">"
				+ "理工大之声</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgrt.sdut.edu.cn/\">"
				+ "山东理工大学广播电视台</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://zszx.sdut.edu.cn/\">"
				+ "山东理工大学 招生在线</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgjy.sdut.edu.cn/gongyu/a/notice/2014/0515/21841.html\">"
				+ "理工家园(公寓中心)</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.wdou.cn/\">"
				+ "网兜网[网络信息中心综合门户网站]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://atc.sdut.edu.cn/\">"
				+ "分析测试中心</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xiaoboshi.sdut.edu.cn/\">"
				+ "幼教中心</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jingpin.sdut.edu.cn/\">"
				+ "精品课程</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgrt.sdut.edu.cn/index.aspx\">"
				+ "山东理工大学广播电视台</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdgc.cbpt.cnki.net/WKA2/WebPublication/index.aspx?\">"
				+ "山东理工大学学报[自然版]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://journal.sdut.edu.cn/index.htmmid=sdgc\">"
				+ "山东理工大学学报[社会版]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdlgny.sdut.edu.cn/Article/Index.asp\">"
				+ "能源服务管理系统</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgwy.sdut.edu.cn/\">"
				+ "理工物业</a></h2>");
		sb.append("</body>");
		sb.append("</html>");

		WebView01.loadDataWithBaseURL(null, sb.toString(), "text/html",
				"utf-8", null);
	}

}
