package cn.scau.zzzd.xst.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.entity.Book;
import cn.scau.zzzd.xst.entity.Sellitem;
import cn.scau.zzzd.xst.util.StringUtil;

public class SellItemAdapter extends BaseAdapter{
	private BaseUi baseUi = null;
	private List<Sellitem> sellitems = new ArrayList<Sellitem>();
	private LayoutInflater inflater = null;
	private boolean isPackage = false;
	private final DecimalFormat fnum = new DecimalFormat("##0.00"); 
	public SellItemAdapter(BaseUi baseUi, List<Sellitem> sellitems) {
		super();
		this.baseUi = baseUi;
		if(sellitems != null)
			this.sellitems = sellitems;
		inflater = LayoutInflater.from(baseUi);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sellitems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sellitems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return sellitems.get(position).getId();
	}

	
	public boolean isPackage() {
		return isPackage;
	}

	public void setPackage(boolean isPackage) {
		this.isPackage = isPackage;
		notifyDataSetChanged();
	}

	public List<Sellitem> getSellitems() {
		return sellitems;
	}

	public void setSellitems(List<Sellitem> sellitems) {
		this.sellitems = sellitems;
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		ViewHolder holder = null;
		if(v == null){
			v = inflater.inflate(R.layout.item_sell, null);
			holder = new ViewHolder();
			holder.cb_sel = (CheckBox) v.findViewById(R.id.cb_sel);
			holder.tv_title = (TextView) v.findViewById(R.id.tv_title);
			holder.et_price = (TextView) v.findViewById(R.id.et_price);
			holder.btn_pic = (Button) v.findViewById(R.id.btn_pic);
			holder.ll_pic = (LinearLayout) v.findViewById(R.id.ll_pic);
		}else{
			holder = (ViewHolder) v.getTag();
		}
		Sellitem sellitem = sellitems.get(position);
		Book book = sellitem.getGoods();
		holder.tv_title.setText(book.getTitle());
		if(StringUtil.isBlank(""+holder.et_price.getText()))
			holder.et_price.setText(fnum.format(book.getFloatPrice()*C.DEFAULT_SELL_PRICE));
		if(isPackage){
			holder.et_price.setEnabled(false);
		}else{
			holder.et_price.setEnabled(true);
		}
		holder.et_price.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Float price = Float.parseFloat(s.toString());
				sellitems.get(position).setPrice(price);
			}
		});
		return v;
	}

	class ViewHolder{
		CheckBox cb_sel;
		TextView tv_title;
		TextView et_price;
		Button btn_pic;
		LinearLayout ll_pic;
	}

	public void addAll(List<Book> books) {
		// TODO Auto-generated method stub
		Book b = null;
		for(int i=0;i<books.size();i++){
			b = books.get(i);
			if(!contain(b))
				sellitems.add(new Sellitem(b));
		}
		notifyDataSetChanged();
	}
	
	private boolean contain(Book book){
		for(int i=0;i<sellitems.size();i++){
			if(sellitems.get(i).getBookid() == book.getCid())
				return true;
			if(sellitems.get(i).getGoods().getIsbn13() .equals(book.getIsbn13()))
				return true;
		}
		return false;
	}
}
