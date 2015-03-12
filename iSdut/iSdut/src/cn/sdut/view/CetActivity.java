package cn.sdut.view;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import cn.sdut.R;
import cn.sdut.dao.Person;
import cn.sdut.util.ConAllS;

/**
 * 显示四六级成绩-Activity
 * 
 */
public class CetActivity extends Activity {

	private List<Person> list = null;
	private ImageView ivBack = null;
	private ViewGroup tableTitle = null;

	private void init() {
		ivBack = (ImageView) findViewById(R.id.ivscorecetback);
		tableTitle = (ViewGroup) findViewById(R.id.table_title2);
	}

	private void setEvent() {
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scorecet);
		init();
		setEvent();

		tableTitle.setBackgroundColor(Color.rgb(49, 179, 225));
		list = MainActivity.list;
		if (list == null) {
			new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("暂时没有您的CET考试记录！")
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									arg0.dismiss();
									CetActivity.this.finish();
								}
							}).show();
			return;
		} else {
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setId(i + 1);
			}
			ListView tableListView = (ListView) findViewById(R.id.list2);
			ConAllS adapter = new ConAllS(this, list, 2);
			tableListView.setAdapter(adapter);
		}
	}
}
