package cn.sdut.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.sdut.R;
import cn.sdut.tools.SpiderSearch;
import cn.sdut.util.CustomProgressDialog;

/**
 * 图书搜索-Activity
 * 
 */
public class BookSearchActivity extends Activity {
	boolean isOk = false;
	private String[] names = null;
	private String[] descs = null;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private ImageView ivBook = null;
	private EditText etBook = null;
	private CustomProgressDialog cpd = null;
	private SimpleAdapter simpleAdapter = null;
	private ImageView ivBack = null;

	private void init() {
		ivBook = (ImageView) findViewById(R.id.ivBook);
		ivBack = (ImageView) findViewById(R.id.ivbookback);
		etBook = (EditText) findViewById(R.id.etBookSearch);
	}

	private void setEvent() {
		ivBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = etBook.getText().toString().trim();
				if (str.trim() == null) {
					Toast.makeText(BookSearchActivity.this, "请输入搜索关键字",
							Toast.LENGTH_SHORT).show();
				} else {
					doSearch(str);
				}
			}
		});
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void doSearch(final String name) {

		cpd = new CustomProgressDialog(BookSearchActivity.this);
		cpd.show();
		new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					try {
						list = new SpiderSearch().getBook(name);
						System.out.println(list);
						if (list == null || list.size() == 0) {
							// System.out.println("kong");
							Log.i("TAG", "没搜到");

							handler.sendEmptyMessage(2);
							return;
						}
						if (list != null) {
							names = new String[list.size()];
							descs = new String[list.size()];
							for (int i = 0; i < list.size(); i++) {
								@SuppressWarnings("rawtypes")
								Map dto = (Map) list.get(i);
								names[i] = (String) dto.get("name") + " "
										+ dto.get("id");
								String det = "作者: " + dto.get("author") + "\n"
										+ "出版社" + dto.get("express") + "\n"
										+ "馆藏副本: " + dto.get("total") + "  "
										+ "可借副本: " + dto.get("left");
								descs[i] = det;
							}
							List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
							for (int i = 0; i < names.length; i++) {
								Map<String, Object> listItem = new HashMap<String, Object>();
								listItem.put("personName", names[i]);
								listItem.put("desc", descs[i]);
								listItems.add(listItem);
							}
							simpleAdapter = new SimpleAdapter(
									BookSearchActivity.this, listItems,
									R.layout.simple_item, new String[] {
											"personName", "desc" }, new int[] {
											R.id.name, R.id.desc });
							handler.sendEmptyMessage(1);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				cpd.dismiss();
				cpd = null;
				ListView list = (ListView) findViewById(R.id.mylist);
				list.setAdapter(simpleAdapter);
				break;
			case 2:
				new AlertDialog.Builder(BookSearchActivity.this)
						.setTitle("提示")
						.setMessage("没有找到与之相关的书！")
						.setPositiveButton(
								"确定",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated
										// method stub
										arg0.dismiss();
										BookSearchActivity.this.finish();
									}
								}).show();

				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_main);
		init();
		setEvent();
	}
}