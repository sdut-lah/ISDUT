package cn.sdut.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.sdut.R;

/**
 * Ð£Àú-Activity
 *
 */
public class TimeTableActivity extends Activity{

	private ImageView ivtimetableback;
	
	private void init(){
		ivtimetableback = (ImageView) findViewById(R.id.ivtimetableback);
	}
	
	private void setEvent(){
		ivtimetableback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable);
		init();
		setEvent();
	}
	
}
