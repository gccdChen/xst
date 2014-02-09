package cn.scau.zzzd.xst.ui;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.widget.WeViewPager;

/**
 * 首页
 * @author gccd
 *
 */
public class UiIndex  extends BaseUi{
	private View views[] = new View[3];
	private List<View> pages = new ArrayList<View>();
	private WeViewPager pager ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_index);
		init();
		initData();
		update();
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		pager = (WeViewPager) findViewById(R.id.vPager);
		views[0] = getLayout(R.layout.ui_home);
		views[1] = getLayout(R.layout.ui_info);
		views[2] = getLayout(R.layout.ui_setting);
		for(int i = 0;i<views.length;i++){
			pages.add(views[i]);
		}
		pager.setAdapter(new MyViewPagerAdapter(pages));
		pager.setCurrentItem(0);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

}
