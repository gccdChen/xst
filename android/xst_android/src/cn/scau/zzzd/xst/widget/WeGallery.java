package cn.scau.zzzd.xst.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 相册控件
 * 
 * @author gccd
 * 
 */
public class WeGallery extends Gallery {

	public WeGallery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public WeGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public WeGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	int FLINGTHRESHOLD = 0;
	int SPEED = 100;

	private void init() {
		float scale = getResources().getDisplayMetrics().density;
		FLINGTHRESHOLD = (int) (20.0f * scale + 0.5f);
		;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.d("OnePageGallery:onFling:",
				String.format("VX:%f, VY:%f", velocityX, velocityY));
		// cap the velocityX to scroll only one page
		if (velocityX > FLINGTHRESHOLD) {
			return super.onFling(e1, e2, SPEED, velocityY);
		} else if (velocityX < -FLINGTHRESHOLD) {
			return super.onFling(e1, e2, -SPEED, velocityY);
		} else {
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}
