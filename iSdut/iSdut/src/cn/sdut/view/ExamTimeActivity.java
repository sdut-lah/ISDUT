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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.sdut.R;

/**
 * 显示考试时间-Activity
 * 
 */
public class ExamTimeActivity extends Activity {
	boolean isOk = false;
	private String[] names = null;
	private String[] descs = null;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchexam);
		init();
		setEvent();
		list = MainActivity.list1;
		if (list != null) {
			names = new String[list.size()];
			descs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map dto = (Map) list.get(i);
				names[i] = (String) dto.get("course");
				String det = "考试时间: " + dto.get("time") + "\n" + "考试地点"
						+ dto.get("location") + "\n" + "座号: " + dto.get("num");
				descs[i] = det;
			}
			for (int i = 0; i < names.length; i++) {
				System.out.println(names[i]);
				System.out.println(descs);
			}
		}
		if (list == null) {
			System.out.println("kong");
			new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("暂无考试信息")
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									arg0.dismiss();
									ExamTimeActivity.this.finish();
								}
							}).show();
			return;
		}
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.simple_item, new String[] { "personName", "desc" },
				new int[] { R.id.name, R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
	}
}
