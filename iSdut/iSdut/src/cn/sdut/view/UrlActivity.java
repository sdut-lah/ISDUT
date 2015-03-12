package cn.sdut.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import cn.sdut.R;

/**
 * ��ַ-Activity
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
		sb.append("<title> ��ӭ�� </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> ����վ��Ϣ <a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"\">"
				+ "</a></h2>");
		sb.append("<h2> <a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.sdut.edu.cn/\">"
				+ "ɽ������ѧ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jwch.sdut.edu.cn/\">"
				+ "ɽ������ѧ����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jxxy.sdut.edu.cn/\">"
				+ "��е����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jtxy.sdut.edu.cn/\">"
				+ "��ͨ�복������ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://qgxy.sdut.edu.cn/\">"
				+ "ũҵ������ʳƷ��ѧѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://dz.sdut.edu.cn/index.seam\">"
				+ "��������ӹ���ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jsjxy.net/\">"
				+ "�������ѧ�뼼��ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://hgxy.sdut.edu.cn/\">"
				+ "��ѧ����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jgxy.sdut.edu.cn/\">"
				+ "��������ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://ziyuan.sdut.edu.cn/\">"
				+ "��Դ�뻷������ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xcl.sdut.edu.cn/\">"
				+ "���Ͽ�ѧ�빤��ѧԺ </a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://http://smkx.sdut.edu.cn/\">"
				+ "������ѧѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lxy.sdut.edu.cn/\">"
				+ "��ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://bs.sdut.edu.cn/\">"
				+ "��ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://wxy.sdut.edu.cn/index.html\">"
				+ "��ѧ�����Ŵ���ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://wyxy.sdut.edu.cn/\">"
				+ "�����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://law.sdut.edu.cn/uploads/index.html\">"
				+ "��ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://mksxy.sdut.edu.cn/\">"
				+ "���˼����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://msxy.sdut.edu.cn/\">"
				+ "����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://yyx.sdut.edu.cn/\">"
				+ "����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://tyx.sdut.edu.cn/\">"
				+ "����ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://gfjy.sdut.edu.cn/\">"
				+ "��������ѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://fzxy.sdut.edu.cn/\">"
				+ "³̩��֯��װѧԺ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdlgdxxsh.tuanjuwang.com/\">"
				+ "ɽ������ѧѧ����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://stgz.lgqn.cn/\">"
				+ "ɽ������ѧѧ���������ϻ�</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.lgqn.cn/\">"
				+ "��������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.youthol.cn/\">"
				+ "�ഺ����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://ecard.sdut.edu.cn/\">"
				+ "У԰һ��ͨ������ѯƽ̨</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://youth.sdut.edu.cn/swzx/index.aspx\">"
				+ "��ѧ����������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.xszzw.net/Home/Default.aspx\">"
				+ "ѧ��������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://yjsh.sdut.edu.cn/index.html\">"
				+ "�о�����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://office.sdut.edu.cn/\">"
				+ "��ίУ���칫��</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgwindow.sdut.edu.cn/\">"
				+ "ɽ������ѧ������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lib.sdut.edu.cn/index.html\">"
				+ "ɽ������ѧͼ���</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xy.sdut.edu.cn/\">"
				+ "ɽ������ѧУ����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgys.sdut.edu.cn/\">"
				+ "ɽ������ѧ��ʳ��������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgradio.sdut.edu.cn/\">"
				+ "����֮��</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgrt.sdut.edu.cn/\">"
				+ "ɽ������ѧ�㲥����̨</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://zszx.sdut.edu.cn/\">"
				+ "ɽ������ѧ ��������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgjy.sdut.edu.cn/gongyu/a/notice/2014/0515/21841.html\">"
				+ "����԰(��Ԣ����)</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://www.wdou.cn/\">"
				+ "������[������Ϣ�����ۺ��Ż���վ]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://atc.sdut.edu.cn/\">"
				+ "������������</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://xiaoboshi.sdut.edu.cn/\">"
				+ "�׽�����</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://jingpin.sdut.edu.cn/\">"
				+ "��Ʒ�γ�</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgrt.sdut.edu.cn/index.aspx\">"
				+ "ɽ������ѧ�㲥����̨</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdgc.cbpt.cnki.net/WKA2/WebPublication/index.aspx?\">"
				+ "ɽ������ѧѧ��[��Ȼ��]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://journal.sdut.edu.cn/index.htmmid=sdgc\">"
				+ "ɽ������ѧѧ��[����]</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://sdlgny.sdut.edu.cn/Article/Index.asp\">"
				+ "��Դ�������ϵͳ</a></h2>");
		sb.append("<h2><a style=\"color:#31d3e1;font-size:18px;text-decoration : none;\" href=\"http://lgwy.sdut.edu.cn/\">"
				+ "����ҵ</a></h2>");
		sb.append("</body>");
		sb.append("</html>");

		WebView01.loadDataWithBaseURL(null, sb.toString(), "text/html",
				"utf-8", null);
	}

}
