package cn.scau.zzzd.xst.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class WeViewPager extends ViewPager {  
	  
    private boolean isCanScroll = true;  
  
    public WeViewPager(Context context) {  
        super(context);  
    }  
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
    	// TODO Auto-generated method stub
    	if(arg0.getAction() == MotionEvent.ACTION_MOVE)
    		Log.i("WeViewPager", "is move");
    	return false;
    }
  
    public WeViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void setScanScroll(boolean isCanScroll){  
        this.isCanScroll = isCanScroll;  
    }  
  
    @Override  
    public void scrollTo(int x, int y){  
        if (isCanScroll){  
            super.scrollTo(x, y);  
        }  
    }
}
