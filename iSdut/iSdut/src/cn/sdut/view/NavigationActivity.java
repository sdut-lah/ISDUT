package cn.sdut.view;

import cn.sdut.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import dalvik.system.*;

/**
 * 缩放图片界面-Activity
 * 
 */
@SuppressLint("FloatMath")
public class NavigationActivity extends Activity {
	public static final String TAG = "ImgDisplayActivity";

	private ImageView imgDisPlay;
	@SuppressWarnings("unused")
	private LinearLayout lLayoutDisplay;
	private FrameLayout fLayoutDisplay;

	private float scaleWidth = 1;
	private float scaleHeight = 1;
	public static final int NONE = 0;
	public static final int DRAG = 1;
	public static final int ZOOM = 2;
	private int mode = NONE;

	private Matrix matrix;
	private Matrix currMatrix;

	private PointF starPoint;
	private PointF midPoint;

	private float startDistance;
	private final static float TARGET_HEAP_UTILIZATION = 0.75f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//VMRuntime.getRuntime().setTargetHeapUtilization(TARGET_HEAP_UTILIZATION); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation);
		// 初始化
		fLayoutDisplay = (FrameLayout) findViewById(R.id.flayout_img_display);
		lLayoutDisplay = (LinearLayout) findViewById(R.id.linearLayout_img_display);
		imgDisPlay = (ImageView) findViewById(R.id.img_display);
		matrix = new Matrix();
		currMatrix = new Matrix();
		starPoint = new PointF();
		imgDisPlay.setImageResource(R.drawable.west);
		imgDisPlay.setOnTouchListener(new ImageViewOnTouchListener());
		
	}

	@SuppressWarnings("unused")
	private void reSizeBmp(double scale) {
		scaleWidth = (float) (scaleWidth * scale);
		scaleHeight = (float) (scaleHeight * scale);
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight); // 设计缩放比例
		imgDisPlay.setImageMatrix(matrix);
	}

	final class ImageViewOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: // 一只手指按下
				currMatrix.set(matrix);
				starPoint.set(event.getX(), event.getY());
				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				startDistance = distance(event);
				Log.i(TAG, startDistance + "");
				if (startDistance > 5f) {
					mode = ZOOM;
					currMatrix.set(matrix);
					midPoint = getMidPoint(event);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == DRAG) {
					float dx = event.getX() - starPoint.x;
					float dy = event.getY() - starPoint.y;
					matrix.set(currMatrix);
					matrix.postTranslate(dx, dy);
				} else if (mode == ZOOM) {
					float distance = distance(event);
					if (distance > 5f) {
						matrix.set(currMatrix);
						float cale = distance / startDistance;
						matrix.preScale(cale, cale, midPoint.x, midPoint.y);
					}
				}
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				break;
			default:
				break;
			}
			imgDisPlay.setImageMatrix(matrix);
			return true;
		}
	}

	private float distance(MotionEvent e) {

		float eX = e.getX(1) - e.getX(0);
		float eY = e.getY(1) - e.getY(0);
		return FloatMath.sqrt(eX * eX + eY * eY);
	}

	@SuppressLint("FloatMath")
	private PointF getMidPoint(MotionEvent event) {

		float x = (event.getX(1) - event.getX(0)) / 2;
		float y = (event.getY(1) - event.getY(0)) / 2;
		return new PointF(x, y);
	}
}
