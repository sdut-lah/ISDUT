package cn.sdut.view;

/**
 * ��ʾ��ǰѧ�ڳɼ�-Activity
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
import cn.sdut.R;
import cn.sdut.dao.Person;
import cn.sdut.util.ConCurrentS;

public class CurrentScoreActivity extends Activity {

	private List<Person> list = null;
	private ImageView ivBack = null;

	private void init() {
		ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
		tableTitle.setBackgroundColor(Color.rgb(49, 179, 225));
		ivBack = (ImageView) findViewById(R.id.ivscorecurrentback);
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
		setContentView(R.layout.scorecurrenttitle);
		init();
		setEvent();
		list = MainActivity.list;
		if (list == null) {
			System.out.println("kong");
			new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("��ѧ�ڿ�ʼ����ʱû�п��Գɼ���")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									arg0.dismiss();
									CurrentScoreActivity.this.finish();
								}
							}).show();
			return;
		} else {
			for (int i = 0;i < list.size(); i++){
				System.out.println(list.get(i));
			}
	
			ListView tableListView = (ListView) findViewById(R.id.list);
			ConCurrentS adapter = new ConCurrentS(this, list);
			tableListView.setAdapter(adapter);
		}
	}
}
