package cn.sdut.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sdut.R;

/**
 * 显示课表-Activity
 * 
 */
public class CourseActivity extends Activity {

	@SuppressWarnings("rawtypes")
	List<Map> list = new ArrayList<Map>();
	private ImageView ivBack = null;

	private void init() {
		ivBack = (ImageView) findViewById(R.id.ivsourseviewback);
	}

	private void setEvent() {
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courseview);
		init();
		setEvent();
		final Map m = new HashMap<String, String>();
		m.put("周一", "1");
		m.put("周二", "2");
		m.put("周三", "3");
		m.put("周四", "4");
		m.put("周五", "5");
		m.put("周六", "6");
		m.put("周日", "7");
		list = MainActivity.list1;
		if (list != null) {
			String str = null;
			for (int i = 0; i < list.size(); i++) {
				Map dto = (Map) list.get(i);

				String s = (String) m.get(dto.get("dayOfWeek"));
				s = "c" + s + dto.get("sectionFrom") + dto.get("sectionTo");
				if (s.equals("c112")) {
					TextView tv = (TextView) findViewById(R.id.c112);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c134")) {
					TextView tv = (TextView) findViewById(R.id.c134);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c156")) {
					TextView tv = (TextView) findViewById(R.id.c156);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c178")) {
					TextView tv = (TextView) findViewById(R.id.c178);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c190")) {
					TextView tv = (TextView) findViewById(R.id.c190);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c212")) {
					TextView tv = (TextView) findViewById(R.id.c212);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c234")) {
					TextView tv = (TextView) findViewById(R.id.c234);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c256")) {
					TextView tv = (TextView) findViewById(R.id.c256);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					// System.out.println(tv.getText().toString());
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c278")) {

					TextView tv = (TextView) findViewById(R.id.c278);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c290")) {

					TextView tv = (TextView) findViewById(R.id.c290);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c312")) {

					TextView tv = (TextView) findViewById(R.id.c312);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c334")) {

					TextView tv = (TextView) findViewById(R.id.c334);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c356")) {

					TextView tv = (TextView) findViewById(R.id.c356);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c378")) {

					TextView tv = (TextView) findViewById(R.id.c378);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c390")) {

					TextView tv = (TextView) findViewById(R.id.c390);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c412")) {

					TextView tv = (TextView) findViewById(R.id.c412);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c434")) {

					TextView tv = (TextView) findViewById(R.id.c434);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c456")) {

					TextView tv = (TextView) findViewById(R.id.c456);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c478")) {

					TextView tv = (TextView) findViewById(R.id.c478);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c490")) {

					TextView tv = (TextView) findViewById(R.id.c490);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c512")) {

					TextView tv = (TextView) findViewById(R.id.c512);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c534")) {

					TextView tv = (TextView) findViewById(R.id.c534);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c556")) {

					TextView tv = (TextView) findViewById(R.id.c556);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c578")) {

					TextView tv = (TextView) findViewById(R.id.c578);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c590")) {

					TextView tv = (TextView) findViewById(R.id.c590);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c612")) {
					TextView tv = (TextView) findViewById(R.id.c612);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c634")) {

					TextView tv = (TextView) findViewById(R.id.c634);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c656")) {

					TextView tv = (TextView) findViewById(R.id.c656);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c678")) {

					TextView tv = (TextView) findViewById(R.id.c678);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c690")) {

					TextView tv = (TextView) findViewById(R.id.c690);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c712")) {

					TextView tv = (TextView) findViewById(R.id.c712);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c734")) {

					TextView tv = (TextView) findViewById(R.id.c734);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c756")) {

					TextView tv = (TextView) findViewById(R.id.c756);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
				if (s.equals("c778")) {

					TextView tv = (TextView) findViewById(R.id.c778);
					str = dto.get("name") + "\n" + dto.get("location") + "\n";
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}

				if (s.equals("c790")) {

					TextView tv = (TextView) findViewById(R.id.c790);
					str = dto.get("name") + "\n" + dto.get("location");
					str = str + dto.get("plan") + "\n" + dto.get("teacherName")
							+ "\n";
					str += "\n" + tv.getText().toString();
					tv.setText(str);
				}
			}
		}
		if (list == null) {
			System.out.println("kong");
			new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("暂无课表信息！")
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									arg0.dismiss();
									CourseActivity.this.finish();
								}
							}).show();
			return;
		}
	}
}
