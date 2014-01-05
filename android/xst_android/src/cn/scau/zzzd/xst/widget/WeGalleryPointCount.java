package cn.scau.zzzd.xst.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.scau.zzzd.xst.R;
/**
 * 相册显示当前页控件
 * @author gccd
 *
 */
public class WeGalleryPointCount extends LinearLayout{
	private Context context = null;
	public WeGalleryPointCount(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public WeGalleryPointCount(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public WeGalleryPointCount(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	int length = 0;
	ImageView[] ivs = null;
	
	/**
	 * 图片数量
	 * @param length
	 */
	public void updateCount(int length) {
		// TODO Auto-generated method stub
		removeAllViews();
		ivs = new ImageView[length];
		for(int i=0;i<ivs.length;i++){
			ivs[i] = new ImageView(context);
			ivs[i].setPadding(5, 0,0, 0);
			if(i == 0)
				ivs[i].setImageResource(R.drawable.white_dot);
			else
				ivs[i].setImageResource(R.drawable.white_dot2);
			addView(ivs[i]);
		}
	}
	
	int lastSel = 0;
	private void setPointPosition(int position){
		if(ivs != null){
			ivs[lastSel].setImageResource(R.drawable.white_dot2);
			ivs[position%ivs.length].setImageResource(R.drawable.white_dot);
			lastSel = position%ivs.length;
		}
	}
	
	private Gallery gallery = null;
	/**
	 * 调用一次就好了
	 * @param gallery
	 */
	public void setGallery(Gallery gallery){
		this.gallery = gallery;
		this.gallery.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			if(galleryChangeListenter != null)
				galleryChangeListenter.onChange(position);
			setPointPosition(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	}
	private onGalleryChangeListenter galleryChangeListenter;
	
	public void setOnGalleryChangeListenter(
			onGalleryChangeListenter galleryChangeListenter) {
		this.galleryChangeListenter = galleryChangeListenter;
	}

	public interface onGalleryChangeListenter{
		public void onChange(int position);
	}
}

