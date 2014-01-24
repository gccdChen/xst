package cn.scau.zzzd.xst.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gccd.json.Json;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.SellItemAdapter;
import cn.scau.zzzd.xst.base.BaseMessage;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.entity.Book;
import cn.scau.zzzd.xst.entity.Sell;
import cn.scau.zzzd.xst.entity.Sellitem;
/**
 * 出售页
 * @author gccd
 *
 */
public class UiSell extends BaseUi{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_sell);
		init();
		initData();
		update();
	}

	private ListView lv_sellitems = null;
	private Spinner sp_prices = null;
	private TextView tv_title = null;
	private Button btn_sell;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		sp_prices = (Spinner) findViewById(R.id.sp_prices);
		lv_sellitems = (ListView) findViewById(R.id.lv_sellitems);
		tv_title = (TextView) findViewById(R.id.tv_title);
		btn_sell = (Button) findViewById(R.id.btn_search);
	}
	private final static List<String> pricesList = new ArrayList<String>();
	static{
		pricesList.add("统一定价");
		pricesList.add("3折");
		pricesList.add("2折");
		pricesList.add("1折");
	}
	private SellItemAdapter sellItemAdapter = null;
	private boolean isPackage = false;
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
		tv_title.setText("卖书");
		btn_sell.setText("卖掉");
		btn_sell.setOnClickListener(new SellClickListenter());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pricesList);
		sp_prices.setTop(0);
		sp_prices.setAdapter(adapter);
		sp_prices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		sellItemAdapter = new SellItemAdapter(this, null);
		lv_sellitems.setAdapter(sellItemAdapter);
	}
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void toMajor(View view){
		overlayForResult(UiMajorBook.class, R.id.request_uimajorbook);
	}
	
	public void toScan(View view){
		overlayForResult(UiScan.class, R.id.request_uiscan);
	}
	
	class SellClickListenter implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			List<Sellitem> sellitems = sellItemAdapter.getSellitems();
			List<Sell> sells = new ArrayList<Sell>();
			if(isPackage){
				Sell sell = new Sell(100.0f,sellitems,"");
				sells.add(sell);
			}else{
				Sell sell = null;
				for (int i = 0; i < sellitems.size(); i++) {
					sell = new Sell(sellitems.get(i));
					sells.add(sell);
				}
			}
			Map<String,String> params = new HashMap<String,String>();
			params.put(C.PARAMSNAME.SELLS, Json.toJson(sells));
			doTaskAsync(C.task.sell, C.api.sell, params);
		}
	}
	
	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch (taskId) {
			case C.task.sell:
				
				break;
			default:
				break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(resultCode != RESULT_OK)
			return ;
		switch (requestCode) {
			case R.id.request_uiscan:
				List<Book> books = (List<Book>) data.getSerializableExtra("books");
				sellItemAdapter.addAll(books);
				break;
			default:
				break;
		}
	}
}
