package cn.sdut.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager; 
import cn.sdut.R;
import cn.sdut.view.LoginActivity;

public class SplashActivity extends Activity {

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(SplashActivity.this,LoginActivity.class);
			finish();
			startActivity(intent);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				handler.sendEmptyMessage(1); // 给UI主线程发送消息
			}
		}, 3000); // 启动等待3秒钟
	}
}