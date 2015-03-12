package cn.sdut.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import cn.sdut.R;

/**
 * 自定义进度条
 * 
 */
public class CustomProgressDialog extends ProgressDialog {

	public CustomProgressDialog(Context context) {
		super(context);
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_progress);
	}

	public static CustomProgressDialog show(Context ctx) {
		CustomProgressDialog d = new CustomProgressDialog(ctx);
		d.show();
		return d;
	}
}
