package cn.scau.zzzd.xst.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.entity.Major;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MajorListAdapter extends BaseAdapter{

	private BaseUi baseUi = null;
	private List<Major> majors = new ArrayList<Major>();
	private LayoutInflater inflater = null;
	
	public MajorListAdapter(BaseUi baseUi, List<Major> majors) {
		super();
		this.baseUi = baseUi;
		if(majors != null)
			this.majors = majors;
		inflater = LayoutInflater.from(baseUi);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return majors.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		convertView = inflater.inflate(R.layout.item_major, null);
		ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		
		return convertView;
	}

	public void update(List<Major> list) {
		// TODO Auto-generated method stub
		this.majors = list;
		notifyDataSetChanged();
	}

}
