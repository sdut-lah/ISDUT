package cn.sdut.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.sdut.R;

/**
 * …Ë÷√-Activity
 *
 */
public class SettingActivity extends Activity {
	
	private ImageView ivsettingback;
	
	private void init(){
		ivsettingback = (ImageView) findViewById(R.id.ivsettingback);
	}
	
	private void setEvent(){
		ivsettingback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        init();
        setEvent();
    }
}