package cn.sdut.view;

/**
 * 总分&绩点处理-Activity
 * 
 */
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
import android.widget.TextView;
import cn.sdut.R;
import cn.sdut.dao.Person;
import cn.sdut.util.ConAllS;

public class AllScoreActivity extends Activity {

	private String gpa;
	private TextView gpanum;
	private List<Person> list = null;
	private ViewGroup tableTitle = null;
	private ImageView ivBack = null;

	private void init() {
		tableTitle = (ViewGroup) findViewById(R.id.table_title2);
		ivBack = (ImageView) findViewById(R.id.ivscoreallback);
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
		setContentView(R.layout.scoreall);
		init();
		setEvent();
		tableTitle.setBackgroundColor(Color.rgb(49, 179, 225));
		gpa = MainActivity.gpa;
		list = MainActivity.list;
		gpanum = (TextView) findViewById(R.id.gpanum);
		gpanum.setText(gpa);
		if (list == null) {
			new AlertDialog.Builder(this)
					.setTitle("提示")
					.setMessage("暂时没有成绩记录！")
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									arg0.dismiss();
									AllScoreActivity.this.finish();
								}
							}).show();
			return;
		} else {
			ListView tableListView = (ListView) findViewById(R.id.list2);
			ConAllS adapter = new ConAllS(this, list, 1);
			tableListView.setAdapter(adapter);
		}
	}
}
