package cn.sdut.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.sdut.R;
import cn.sdut.dao.Person;
import cn.sdut.tools.Spider;
import cn.sdut.tools.SpiderBBook;
import cn.sdut.util.CustomProgressDialog;

/**
 * 主界面-Activity
 * 
 */
public class MainActivity extends Activity implements OnTouchListener {

	public static List<Person> list = null;
	@SuppressWarnings("rawtypes")
	public static List list1 = null;
	public static String gpa = "";
	private CustomProgressDialog cpd = null;
	private long mExitTime;
	/**
	 * 滚动显示和隐藏menu时，手指滑动需要达到的速度。
	 */
	public static final int SNAP_VELOCITY = 200;
	public WebView MyWebView;
	private int screenWidth;
	private int leftEdge;
	private int rightEdge = 0;
	private int menuPadding = 90;
	private View content;
	private View menu;
	private LinearLayout.LayoutParams menuParams;
	private float xDown;
	private float xMove;
	private float xUp;
	private boolean isMenuVisible;
	private VelocityTracker mVelocityTracker;
	private Intent intent = new Intent();
	private TextView tvAbouts;
	private Button btChange;
	private Button btQuit;
	private TextView main_tvSets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initValues();
		content.setOnTouchListener(this);
		onInitMenu();
		onIniti();
		setEvnet();

		MyWebView = (WebView) findViewById(R.id.main_tvWether);
		MyWebView.loadUrl("file:///android_asset/weather.html");
		WebSettings webSettings = MyWebView.getSettings();
		MyWebView.setBackgroundColor(0);
		MyWebView.getBackground().setAlpha(0);
		webSettings.setJavaScriptEnabled(true);
	}

	/**
	 * 菜单界面初始化数据
	 */
	private void onInitMenu() {
		
		this.tvAbouts = (TextView) findViewById(R.id.main_tvAbountUs);
		this.btChange = (Button) findViewById(R.id.main_btChange);
		this.btQuit = (Button) findViewById(R.id.main_btQuit);
		this.main_tvSets = (TextView) findViewById(R.id.main_tvSets);
	}

	/**
	 * 添加事件
	 */
	private void setEvnet() {

		// 退出单击事件
		this.btQuit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(MainActivity.this);
				builder.setMessage("确定离开Isdut");
				builder.setCancelable(true);
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// 程序退出
                                
								
                               System.exit(0);
                               System.exit(0);
                               System.exit(0);
							

							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});

		// 切换账户单击事件
		this.btChange.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 清除自动登陆文件
				SharedPreferences sp = getSharedPreferences(
						"autoLoginUserInfo", Context.MODE_WORLD_WRITEABLE);
				sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				
				// 跳转到登陆界面
				intent.setClass(MainActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
		// 关于我们单击事件
		this.tvAbouts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到关于我们的界面
				intent.setClass(MainActivity.this, AboutUsActivity.class);
				startActivity(intent);
			}
		});
		this.main_tvSets.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.setClass(MainActivity.this, SettingActivity.class);
				startActivity(intent);

			}
		});
	}

	private void initValues() {
		WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = window.getDefaultDisplay().getWidth();
		content = findViewById(R.id.menutitle);
		menu = findViewById(R.id.menu);
		menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();
		menuParams.width = screenWidth - menuPadding;
		leftEdge = -menuParams.width;
		menuParams.leftMargin = leftEdge;
		content.getLayoutParams().width = screenWidth;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDown = event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = event.getRawX();
			int distanceX = (int) (xMove - xDown);
			if (isMenuVisible) {
				menuParams.leftMargin = distanceX;
			} else {
				menuParams.leftMargin = leftEdge + distanceX;
			}
			if (menuParams.leftMargin < leftEdge) {
				menuParams.leftMargin = leftEdge;
			} else if (menuParams.leftMargin > rightEdge) {
				menuParams.leftMargin = rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			if (wantToShowMenu()) {
				if (shouldScrollToMenu()) {
					scrollToMenu();
				} else {
					scrollToContent();
				}
			} else if (wantToShowContent()) {
				if (shouldScrollToContent()) {
					scrollToContent();
				} else {
					scrollToMenu();
				}
			}
			recycleVelocityTracker();
			break;
		}
		return true;
	}

	private boolean wantToShowContent() {
		return xUp - xDown < 0 && isMenuVisible;
	}

	private boolean wantToShowMenu() {
		return xUp - xDown > 0 && !isMenuVisible;
	}

	private boolean shouldScrollToMenu() {
		return xUp - xDown > screenWidth / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	private boolean shouldScrollToContent() {
		return xDown - xUp + menuPadding > screenWidth / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	private void scrollToMenu() {
		new ScrollTask().execute(30);
	}

	private void scrollToContent() {
		new ScrollTask().execute(-30);
	}

	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMargin = menuParams.leftMargin;
			// 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。
			while (true) {
				leftMargin = leftMargin + speed[0];
				if (leftMargin > rightEdge) {
					leftMargin = rightEdge;
					break;
				}
				if (leftMargin < leftEdge) {
					leftMargin = leftEdge;
					break;
				}
				publishProgress(leftMargin);
				sleep(20);
			}
			if (speed[0] > 0) {
				isMenuVisible = true;
			} else {
				isMenuVisible = false;
			}
			return leftMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... leftMargin) {
			menuParams.leftMargin = leftMargin[0];
			menu.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer leftMargin) {
			menuParams.leftMargin = leftMargin;
			menu.setLayoutParams(menuParams);
		}
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void onIniti() {
		GridView gridviewEAS = (GridView) findViewById(R.id.gridviewEAS);
		GridView gridviewlibrary = (GridView) findViewById(R.id.gridviewlibrary);
		GridView gridviewother = (GridView) findViewById(R.id.gridviewother);
		int[] images = new int[] { R.drawable.score, R.drawable.allscore,
				R.drawable.cet, R.drawable.course, R.drawable.exam,
				R.drawable.book, R.drawable.sbook, R.drawable.scard,
				R.drawable.scalendar, R.drawable.info, R.drawable.navigation };
		String[] namestr = new String[] { "成绩查询", "总分&绩点", "CET", "课表查询",
				"考试查询", "借书查询", "书籍搜索", "校园卡", "校历", "校园信息", "校园地图" };

		List<HashMap<String, Object>> lstImageItem1 = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);
			map.put("ItemText", namestr[i]);
			lstImageItem1.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem1,
				R.layout.night_itemeas,
				new String[] { "ItemImage", "ItemText" }, new int[] {
						R.id.ItemImage1, R.id.ItemText });
		gridviewEAS.setAdapter(saImageItems);
		List<HashMap<String, Object>> lstImageItem2 = new ArrayList<HashMap<String, Object>>();
		for (int i = 5; i < 7; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);
			map.put("ItemText", namestr[i]);
			lstImageItem2.add(map);
		}
		saImageItems = new SimpleAdapter(this, lstImageItem2,
				R.layout.night_itemlibrary, new String[] { "ItemImage",
						"ItemText" }, new int[] { R.id.ItemImage2,
						R.id.ItemText });
		gridviewlibrary.setAdapter(saImageItems);

		List<HashMap<String, Object>> lstImageItem3 = new ArrayList<HashMap<String, Object>>();
		for (int i = 7; i < 11; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);
			map.put("ItemText", namestr[i]);
			lstImageItem3.add(map);
		}
		saImageItems = new SimpleAdapter(this, lstImageItem3,
				R.layout.night_itemother, new String[] { "ItemImage",
						"ItemText" }, new int[] { R.id.ItemImage3,
						R.id.ItemText });
		gridviewother.setAdapter(saImageItems);

		gridviewEAS.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				startactivity(id);
			}

		});
		gridviewlibrary.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				startactivity(id + 5);
			}
		});
		gridviewother.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				startactivity(id + 7);
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
				finish();
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void startactivity(long id) {
		// TODO Auto-generated method stub
		int temp = (int) id;
		switch (temp) {
		case 0: {
			list = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try {
							list = Spider.getInstance().getCurrentScore();// 获取当前成绩函数
							intent.setClass(MainActivity.this,
									CurrentScoreActivity.class);
							cpd.dismiss();
							cpd = null;
							startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		case 1: {
			list = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try {
							Looper.prepare();
							gpa = Spider.getInstance().getGpa();
							gpa = gpa.substring(0, 6);
							list = Spider.getInstance().getAllScore();
							intent.setClass(MainActivity.this,
									AllScoreActivity.class);
							cpd.dismiss();
							cpd = null;
							startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		case 2: {
			list = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			System.out.println("start");
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						list = Spider.getInstance().getCet();
						intent.setClass(MainActivity.this, CetActivity.class);
						cpd.dismiss();
						cpd = null;
						startActivity(intent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			break;
		}
		case 3: {
			list1 = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						list1 = Spider.getInstance().getSchedule();
						intent.setClass(MainActivity.this, CourseActivity.class);
						cpd.dismiss();
						cpd = null;
						startActivity(intent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		case 4: {
			list1 = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try {
							list1 = Spider.getInstance().getTime();
							intent.setClass(MainActivity.this,
									ExamTimeActivity.class);
							cpd.dismiss();
							cpd = null;
							startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		case 5: {
			list1 = null;
			cpd = new CustomProgressDialog(MainActivity.this);
			cpd.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try {
							list1 = SpiderBBook.getBook();
							intent.setClass(MainActivity.this,
									BookActivity.class);
							cpd.dismiss();
							cpd = null;
							startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		case 6: {
			intent.setClass(MainActivity.this, BookSearchActivity.class);
			startActivity(intent);
			break;
		}
		case 7: {
			Uri uri = Uri.parse("http://ecard.sdut.edu.cn/");
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
			break;
		}

		case 8: {
			intent.setClass(MainActivity.this, TimeTableActivity.class);
			startActivity(intent);
			break;
		}
		case 9: {
			intent.setClass(MainActivity.this, InfoActivity.class);
			startActivity(intent);
			break;
		}
		case 10: {
			intent.setClass(MainActivity.this, NavigationActivity.class);
			startActivity(intent);
			break;
		}
		}

	}
}