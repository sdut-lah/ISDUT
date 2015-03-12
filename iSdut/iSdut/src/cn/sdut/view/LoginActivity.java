package cn.sdut.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import cn.sdut.R;
import cn.sdut.tools.Spider;
import cn.sdut.tools.SpiderBBook;
import cn.sdut.tools.Validate;
import cn.sdut.util.CustomProgressDialog;

/**
 * 登录界面-Activity
 * 
 */

public class LoginActivity extends Activity {

	private EditText etPwdLogin, etPwdCard;
	private Button btnLogin, btnReset;
	private CheckBox autoLogin;

	private SharedPreferences sp;

	private String loginName;
	private String autoString[];

	private AutoCompleteTextView etName;

	private CustomProgressDialog cpd = null;
	private Boolean iflogin, biflogin = false;
	private String uName = null; // 学号
	private String pwd = null; // 教务处密码
	private String pwdcard = null; // 图书馆密码

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_mxj);
		init();
		setClick();
	}

	private void init() {

		sp = this.getSharedPreferences("autoLoginUserInfo",
				Context.MODE_WORLD_READABLE);

		etPwdLogin = (EditText) findViewById(R.id.edt_mxj_pwd_login);
		  EditText tv = (EditText)this.findViewById(R.id.edt_mxj_pwd_login);            
	        tv.setTextColor(0xff000000);   
		View v = findViewById(R.id.edt_mxj_pwd_login);
		v.getBackground().setAlpha(50);//透明度值 ，值越小越透明
		etPwdCard = (EditText) findViewById(R.id.edt_mxj_pwd_card);
		v = findViewById(R.id.edt_mxj_pwd_card);
		v.getBackground().setAlpha(50);
		autoLogin = (CheckBox) findViewById(R.id.cb_auto);

		btnLogin = (Button) findViewById(R.id.login_btnLogin);
		btnReset = (Button) findViewById(R.id.login_btnReset);
		etName = (AutoCompleteTextView) findViewById(R.id.edt_mxj_name);
		v = findViewById(R.id.edt_mxj_name);  
		v.getBackground().setAlpha(50);
		if (readUserInfoFile() != null) {
			autoString = readUserInfoFile().split(",");
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line, autoString);
			etName.setAdapter(adapter);
			etName.setThreshold(1);
		} else {
			etName = (AutoCompleteTextView) findViewById(R.id.edt_mxj_name);
		}
	}
	private void setClick() {
		// 判断自动登录多选框状态
		if (sp.getBoolean("AUTO_ISCHECK", false)) {
			autoLogin.setChecked(true);

			String username_sp = sp.getString("USERNAME", "");
			String username_pwd = sp.getString("PWDLOGIN", "");
			String username_pwdcard = sp.getString("PWDCARD", "");
			uName = username_sp;
			pwd = username_pwd;
			pwdcard = username_pwdcard;
			//System.out.println("---sp---" + username_sp + "  " + username_pwd
					//+ " " + username_pwdcard);
			etName.setText(username_sp);
			etPwdLogin.setText(username_pwd);
			etPwdCard.setText(username_pwdcard);
			if (uName == null || "".equals(uName.trim())) {
				Toast.makeText(LoginActivity.this, "请填写学号",
						Toast.LENGTH_SHORT).show();
			} else if (pwd == null || "".equals(pwd.trim())) {
				Toast.makeText(LoginActivity.this, "请填写教务处登陆密码",
						Toast.LENGTH_SHORT).show();
			} else if (pwdcard == null || "".equals(pwdcard.trim())) {
				Toast.makeText(LoginActivity.this, "请填写图书馆登陆密码",
						Toast.LENGTH_SHORT).show();
			} else if (Validate.checkUname(uName) == false) {
				Toast.makeText(LoginActivity.this, "学号为10或11位数字",
						Toast.LENGTH_SHORT).show();
			}else{
			ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			// 检查网络连接，如果无网络可用，就不需要进行连网操作等
			NetworkInfo info = mConnectivity.getActiveNetworkInfo();
			if (info == null || !mConnectivity.getBackgroundDataSetting()) {
				Toast.makeText(LoginActivity.this, "无网络连接", Toast.LENGTH_SHORT)
						.show();
			} else {
				cpd = new CustomProgressDialog(LoginActivity.this);
				cpd.show();
				new Thread(new Runnable() {
					public void run() {
						try {
							try {
								Looper.prepare();
								iflogin = Spider.getInstance().login(uName, pwd);
								biflogin = SpiderBBook.login(uName, pwdcard);
								//System.out.println(iflogin + "  " + biflogin);
//								iflogin = true; biflogin = true;
								if (iflogin == true && biflogin == true) {
									handler.sendEmptyMessage(1);
									Intent intent = new Intent();
									intent.setClass(LoginActivity.this,
											MainActivity.class);
									startActivity(intent);
									finish();
								} else {
									handler.sendEmptyMessage(2);
								}
							} catch (Exception ignore) {
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			}

		}else{
			autoLogin.setChecked(false);
			String username_sp = sp.getString("USERNAME", "");
			String username_pwd = sp.getString("PWDLOGIN", "");
			String username_pwdcard = sp.getString("PWDCARD", "");
			uName = username_sp;
			pwd = username_pwd;
			pwdcard = username_pwdcard;
			//System.out.println("---sfsdfsdfp---" + username_sp + "  " + username_pwd
					//+ " " + username_pwdcard);
			etName.setText(username_sp);
			etPwdLogin.setText(username_pwd);
			etPwdCard.setText(username_pwdcard);
		}

		// 登录按钮监听事件
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				uName = etName.getText().toString();
				pwd = etPwdLogin.getText().toString();
				pwdcard = etPwdCard.getText().toString();

				if (uName == null || "".equals(uName.trim())) {
					Toast.makeText(LoginActivity.this, "请填写学号",
							Toast.LENGTH_SHORT).show();
				} else if (pwd == null || "".equals(pwd.trim())) {
					Toast.makeText(LoginActivity.this, "请填写教务处登陆密码",
							Toast.LENGTH_SHORT).show();
				} else if (pwdcard == null || "".equals(pwdcard.trim())) {
					Toast.makeText(LoginActivity.this, "请填写图书馆登陆密码",
							Toast.LENGTH_SHORT).show();
				} else if (Validate.checkUname(uName) == false) {
					Toast.makeText(LoginActivity.this, "学号为10或11位数字",
							Toast.LENGTH_SHORT).show();
				} else {
					ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo info = mConnectivity.getActiveNetworkInfo();
					if (info == null
							|| !mConnectivity.getBackgroundDataSetting()) {
						Toast.makeText(LoginActivity.this, "无网络连接",
								Toast.LENGTH_SHORT).show();
					} else {
						cpd = new CustomProgressDialog(LoginActivity.this);
						cpd.show();
						new Thread(new Runnable() {
							public void run() {
								try {
									try {
										Looper.prepare();
										iflogin = Spider.getInstance().login(uName, pwd);
										biflogin = SpiderBBook.login(uName, pwdcard);
										System.out.println(iflogin + "  " + biflogin);
										//iflogin = true; biflogin = true;
										if (iflogin == true && biflogin == true) {
											// 凡选择自动登录用户，记住账户和密码到autoLoginUserInfo.xml文件中
											Editor editor = sp.edit();
											editor.putString("USERNAME",
													uName.trim());
											editor.putString("PWDLOGIN", pwd);
											editor.putString("PWDCARD", pwdcard);
											editor.commit();
											// 凡登录应用程序，将其账户号码记入到loginUserInfo.txt文件，用于自动填充
											loginName = etName.getText()
													.toString() + ",";
											writeUserInfoFile(loginName);
											handler.sendEmptyMessage(1);
											Intent intent = new Intent();
											intent.setClass(LoginActivity.this,
													MainActivity.class);
											startActivity(intent);
											finish();
										} else {
											handler.sendEmptyMessage(2);
										}

									} catch (Exception e) {

										e.printStackTrace();
									}
								} catch (Exception e) {

									e.printStackTrace();
								}
							}
						}).start();
					}
				}
			}
		});

		// 重置按钮监听事件
		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etName.setText("");
				etPwdLogin.setText("");
				etPwdCard.setText("");
			}
		});

		// 自动登录监听事件
		autoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (autoLogin.isChecked()) {
					System.out.println("选中");
					sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
				} else {
					System.out.println("没有选中");
					sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}
			}
		});

	}

	/**
	 * 追加文件：使用FileOutputStream
	 * 
	 * @param fileName
	 * @param content
	 */
	@SuppressWarnings("unused")
	private void writeUserInfoFile(String content) {

		try {
			FileOutputStream fos = openFileOutput("loginUserInfo.txt",
					Activity.MODE_APPEND);
			PrintWriter outPrintWriter = new PrintWriter(fos);

			String testString[] = readUserInfoFile().split(",");
			for (int i = 0; (testString[i]) != null; i++) {
				if (content.equals(testString[i] + ",")) {
					outPrintWriter.close();
					break;
				} else {
					outPrintWriter.println(content);
					outPrintWriter.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件，存放至字符串数组
	 */
	private String readUserInfoFile() {
		try {
			FileInputStream fis = openFileInput("loginUserInfo.txt");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					fis));
			String temp = bReader.readLine();

			String ss = "";
			while (temp != null) {
				ss = ss + temp;
				temp = bReader.readLine();
			}
			//System.out.println(ss);
			return ss;

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return null;

	}
	
	Handler handler = new Handler() {
        public void handleMessage(Message msg) {
                switch (msg.what) {
                case 1:
                   cpd.dismiss();
                   cpd = null;
                   Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                   break;
                case 2:
                    cpd.dismiss();
                    cpd = null;
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                        break;
                }
                super.handleMessage(msg);
        }
	};

}