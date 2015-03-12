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
 * ��ʾ������Ϣ-Activity
 * 
 */
public class BookActivity extends Activity {
	private String[] names = null;
	private String[] descs = null;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private ImageView ivBack = null;

	private void init() {
		ivBack = (ImageView) findViewById(R.id.ivbbookback);
	}

	private void setEvent() {
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bbook);
		init();
		setEvent();
		list = MainActivity.list1;
		System.out.println(list);
		if (list != null) {
			names = new String[list.size()];
			descs = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<?, ?> dto = list.get(i);
				System.out.println(i + " " + dto);
				names[i] = (String) dto.get("name");
				String det = "�����:  " + dto.get("id") + "\n" + "������:  "
						+ dto.get("author") + "\n" + "��������: "
						+ dto.get("btime") + "\n" + "Ӧ������: " + dto.get("etime")
						+ "\n" + "�ݲص�:  " + dto.get("loc");
				descs[i] = det;
			}
		}
		if (list == null) {
			System.out.println("kong");
			new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("û�н����¼��")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									arg0.dismiss();
									BookActivity.this.finish();
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
				R.layout.bbook_item, new String[] { "personName", "desc" },
				new int[] { R.id.name, R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
	}
}