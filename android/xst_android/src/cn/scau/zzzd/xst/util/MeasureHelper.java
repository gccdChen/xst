package cn.scau.zzzd.xst.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * 自适应
 * @author gccd
 * 2013-12-19
 */
public class MeasureHelper {
	int screenWidth = -1;//屏宽
	int screenHeight = -1;//屏宽
	private Activity activity;
	private MeasureHelper() {
	}
	private MeasureHelper(Activity activity){
		this.activity = activity;
		initSize();
	}
	private static MeasureHelper mMeasureUtil = null;
	public static MeasureHelper getInstance(Activity activity){
		if(mMeasureUtil == null){
			if(activity != null)
				mMeasureUtil = new MeasureHelper(activity);
			else
				return null;
		}
		return mMeasureUtil;
	}
	/**
	 * 
	 * @param view
	 * @param params 
	 * @param scale 宽高比
	 * @param allHorizontalMargin 水平外边距
	 */
	public void fit(View view,LayoutParams params,float scale,int allHorizontalMargin){
		int width = screenWidth - (int)(allHorizontalMargin * density);
		int height = (int) (width / scale);
		Log.i("MeasureHelper", "width:"+width+",height:"+height);
		if(params == null)
			params = new LayoutParams(width, height);
		else{
			params.width = width;
			params.height = height;
		}
		view.setLayoutParams(params);
	}
	/**
	 * 
	 * @param view
	 * @param params 
	 * @param quantity 数量
	 * @param scale 宽高比
	 * @param allHorizontalMargin 水平外边距
	 */
	public void fit(View view,LayoutParams params,int quantity,boolean isRight,float scale,int allHorizontalMargin){
		int width = (int)((screenWidth - allHorizontalMargin * density)/quantity);
		int height = (int) (width / scale);
		Log.i("MeasureHelper", "width:"+width+",height:"+height);
		if(params == null)
			params = new LayoutParams(width, height);
		else{
			params.width = width;
			params.height = height;
		}
		int margin = (int) (allHorizontalMargin*density/(quantity+1));
		
		if(params instanceof android.widget.LinearLayout.LayoutParams){
			if(isRight)
				((android.widget.LinearLayout.LayoutParams)(params)).setMargins(margin, (int)(margin*0.5), margin, 0);
			else
				((android.widget.LinearLayout.LayoutParams)(params)).setMargins(margin, (int)(margin*0.5), 0, 0);
		}
		view.setLayoutParams(params);
	}
	/**
	 * 
	 * @param params 
	 * @param scale 宽高比
	 * @param allHorizontalMargin 水平外边距
	 */
	public LayoutParams fit(LayoutParams params,float scale,int allHorizontalMargin){
		int width = screenWidth - (int)(allHorizontalMargin * density);
		int height = (int) (width / scale);
		if(params == null)
			params = new LayoutParams(width, height);
		else{
			params.width = width;
			params.height = height;
		}
		return params;
	}
	
	float density = 1.0f;
	int densityDPI = 120;
	float xdpi,ydpi;
	static DisplayMetrics dm = new DisplayMetrics();
	private void initSize(){
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		density  = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）  
		densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320） 
		xdpi = dm.xdpi;          
		ydpi = dm.ydpi;
//		screenWidth  = (int)(dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）  
		screenWidth  = (int)(dm.widthPixels );      // 屏幕宽（px，如：480px）  
		screenHeight = (int)(dm.heightPixels );     // 屏幕高（px，如：800px）  
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	
	
}
