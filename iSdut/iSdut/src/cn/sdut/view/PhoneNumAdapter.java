package cn.sdut.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * 显示电话号码-Adapter
 *
 */
public class PhoneNumAdapter extends BaseExpandableListAdapter {

	private Context mContext;

	// 父列表数据
	private String[] groups = { "    党委(校长)办公室", "    纪委 监察处", "    党委组织部",
			"    党委宣传部 新闻中心", "    党委统战部 港澳台事务办公室", "    学生工作部（处）武装部",
			"    学生公寓管理中心", "    团委", "    人事处", "    教务处", "    学科建设处",
			"    科技处", "    社科处", "    研究生处", "    招生就业处",
			"    国际合作与交流处（国际教育学院）", "    资产管理处", "    计划财务处", "    审计处",
			"    基建处", "    保卫处", "    后勤管理处", "    饮食服务中心", "    能源管理中心",
			"    物业管理中心", "    国际学术交流中心", "    幼教中心", "    离退休工作处",
			"    省职教师资培训中心", "    远程与继续教育学院（淄博教育学院）", "    法律事务室", "    图书馆",
			"    学报（自然科学版）编辑部", "    学报（社会科学版）编辑部", "    网络信息中心",
			"    校友联谊办公室", "    奥星科技发展有限责任公司", "    机械厂 工程实训中心", "    齐文化研究院",
			"    校医院", "    档案馆", "    分析测试中心", "    机械工程学院", "    交通与车辆工程学院",
			"    轻工与农业工程学院", "    电气与电子工程学院", "    计算机科学与技术学院", "    化学工程学院",
			"    建筑工程学院", "    资源与环境工程学院", "    材料科学与工程学院", "    生命科学学院",
			"    理学院", "    文学与新闻传播学院", "    外国语学院", "    法学院", "    商学院",
			"    马克思主义学院", "    美术学院", "    音乐学院", "    国防教育学院", "    体育学院",
			"    鲁泰纺织服装学院", "    驻校机构", "    中国人民解放军驻山东理工大学选培办" };

	// 子列表数据
	private String[][] children = {
			{ "    秘书科——2780190", "    行政科——2780510——2780512",
					"    督查科——2780073", "    机要科——2787116", "    交流科——2780073",
					"    车辆服务中心——2780666-2780166" },
			{ "    办公室——2780046" },
			{ "    干部科——2780672", "    组织科——2780672", "    组织员办公室——2786987",
					"    党校办公室——2780004" },
			{ "    宣传科——2780515", "    理论科——2782626", "    报社编辑部——2780067",
					"    电  视——2782083", "    广  播——2782082",
					"    理工视窗网站——2786727" },
			{ "    秘书室——2781353" },
			{ "    学生教育科——2780505", "    学生管理科——2780619",
					"    心理健康教育中心——2787223——2786869",
					"    学生资助管理中心——2786668——2780619",
					"    大学生事务中心——2786611——2786688" },
			{ "    办公室 ——2783125" },
			{ "    组织宣传部——2782236", "    文化科技部——2782236", "    社团部——2780305",
					"    素质拓展部——2780305", "    校学生会——2781280",
					"    学生社团联合会——2786286" },
			{ "    师资科——2781219", "    人事调配科——2780019", "    劳资科——2780501",
					"    档案室——2781219" },
			{ "    综 合 科——2780024", "    学籍管理科——2780393", "    教学研究科——2781967",
					"    实验教学科——27808845", "    素质教育科——2782373",
					"    质量管理科——2780378", "    教材科——2782307",
					"    考务科——2781326", "    选课中心  ——2780561",
					"    教育技术中心——2780031" },
			{ "    管理科——27872078——2787098", "    建设科——27878060" },
			{ "    综合科——2786556" },
			{ "    计划科——2786278", "    成果科——2786278" },
			{ "    招生办公室——2782143", "    培养科、学位办公室——2782518",
					"    管理科——2781997", "    在职教育科——2781868" },
			{ "    综合科——2780819", "    招生科——2780673", "    就业企划科——2781540",
					"    就业信息科——2787133", "    就业指导科——2781822" },
			{ "    综合科——2782380" },
			{ "    办公室——2780674" },
			{ "    计划管理科——2780015", "    会计核算科——2781982", "    收费管理科——2780016",
					"    校产财务科——2782215", "    后勤财务科——2780549",
					"    基建财务科——2781200", "    财务结算中心2786136",
					"    远程财务——2836475" },
			{ "    审计科——2781362" },
			{ "    综合科——2782124" },
			{ "    综合科——2781505", "    校园110——2781110" },
			{ "    综合科——2780010" },
			{ "    办公室——2782237", "    花园餐厅  ——2781234", "    民族餐厅  ——2783917" },
			{ "    办公室——2781936" },
			{ "    办 公 室 ——27832996", "    物业服务热线——2787777" },
			{ "    杏园办公室——2785200", "    杏园总台——2782066", "    杏园餐厅——2780798",
					"    大酒店总台——2305111", "    大酒店餐厅——2305088",
					"    东校招待所——2313189" },
			{ "    瑞贤园——2760329", "    北校办——2776027" },
			{ "    综合科——2781008" },
			{ "    办公室——2785377" },
			{ "    办公室——2313606", "    教务科——2303306", "    学生科——2313607" },
			{ "    秘书室——2780074" },
			{ "    办公室——2781985-—2781428-6000", "    东校办——2313554-8000" },
			{ "    编辑室——2780711" },
			{ "    编辑室——2782057" },
			{ "    信息中心 ——2780028" },
			{ "    办公室——27834477" },
			{ "    综合办公室——2782101" },
			{ "    办公室——23118089" },
			{ "    办公室——2782106" },
			{ "    办公室——2782207" },
			{ "    业务指导部——2780013", "    综合管理部——2781218", "    校史办公室——2787229" },
			{ "    办公室——2786781" },
			{ "    办公室——2786910", "    学生科、团总支——2786911",
					"    教学管理科——27867912", "    教学管理科（研究生）——2782089" },
			{ "    办公室——2786837", "    学生科、团总支2786850", "    教学管理科——2786985",
					"    驾训学校——2313208——2782213" },
			{ "    办公室——2786398", "    学生科、团总支——2785887", "    教学管理科——2786557" },
			{ "    办公室——2781262", "    学生科、团总支——2781041", "    教学管理科——27828229" },
			{ "    办公室——2782727", "    学生科、团总支——2783908", "    教学管理科——2786260" },
			{ "    办公室——2781664", "    学生科、团总支——2781640", "    教学管理科——2781203" },
			{ "    办公室——2780964", "    学生科、团总支——2782175", "    教学管理科——2786078" },
			{ "    办公室——2781766", "    学生科、团总支——2782073", "    教学管理科——2782219" },
			{ "    办公室——2781665", "    学生科、团总支——2780903", "    教学管理科——2781357" },
			{ "    办公室——2781329", "    学生科、团总支——2781933", "    教学管理科——27868512" },
			{ "    办公室——2786289", "    学生科、团总支——2782194", "    教学管理科——2782328" },
			{ "    办公室 ——2782070", "    学生科、团总支 ——2782235",
					"    教学管理科 ——2786327" },
			{ "    办公室——2782071", "    学生科、团总支——2782204", "    教学管理科——2782265" },
			{ "    办公室——2786618", "    学生科、团总支——2782275", "    教学管理科——2786233" },
			{ "    办公室——2786272", "    MBA办公室——2786861" },
			{ "    办公室——2787827" },
			{ "    办公室——27807551", "    学生科、团总支——2782095", "    教学管理科——2782292" },
			{ "    办公室——27806315", "    学生科、团总支——2781957", "    教学管理科——2781853" },
			{ "    办公室——2781600", "    学生科、团总支——2781169", "    教学管理科——2786660" },
			{ "    办公室——2781327", "    学生科、团总支——2781303", "    教学管理科——27826151" },
			{ "    综合办公室——2787506", "    团总支——2787326" },
			{ "    学区派出所——2787110", "    农行大学分理处——2781848", "    邮电支局——2782362" },
			{ "    办公室——2782224——27824225" } };

	PhoneNumAdapter(Context context) {
		mContext = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return children[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView textView = getGenericView();
		;
		textView.setText(getChild(groupPosition, childPosition).toString());
		return textView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return children[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groups.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = getGenericView();
		textView.setText(getGroup(groupPosition).toString());

		return textView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("deprecation")
	private TextView getGenericView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 48);
		TextView textView = new TextView(mContext);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setPadding(32, 0, 0, 0);
		return textView;
	}

}