package cn.scau.zzzd.xst.ui.reg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.ClearTextWatcher;
import cn.scau.zzzd.xst.base.BaseUi;

public class UiReg extends BaseUi{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_reg);
		init();
		initData();
		update();
	}
	private LayoutInflater inflater = null;
	private View[] views = new View[4]; 
	//
	//view0
	private EditText et_username = null;
	private EditText et_phone = null;
	private LinearLayout layout_content = null;
	private View btn_clear1;
	private View btn_clear2;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		inflater = LayoutInflater.from(this);
		views[0] = inflater.inflate(R.layout.ui_reg1, null);
		views[1] = inflater.inflate(R.layout.ui_reg2, null);
		views[2] = inflater.inflate(R.layout.ui_reg3, null);
		views[3] = inflater.inflate(R.layout.ui_reg4, null);
		
		layout_content = (LinearLayout) findViewById(R.id.content);
		for(int i =0 ;i<views.length;i++){
			layout_content.addView(views[i]);
		}
		selectView(0);
		//view0
		et_username = (EditText) views[0].findViewById(R.id.et_username);
		et_phone = (EditText) views[0].findViewById(R.id.et_phone);
		btn_clear1 = views[0].findViewById(R.id.btn_clear1);
		btn_clear2 = views[0].findViewById(R.id.btn_clear2);
		//view1
	}
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
		et_username.addTextChangedListener(new ClearTextWatcher(btn_clear1,et_username));
		et_phone.addTextChangedListener(new ClearTextWatcher(btn_clear2,et_phone));
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	int cur_page = 0;
	int getCurPage(){
		return cur_page;
	}
	private void selectView(int sel){
		sel = sel%views.length;
		for(int i=0;i<views.length;i++){
			if(i == sel){
				views[i] .setVisibility(View.VISIBLE);
			}else{
				views[i] .setVisibility(View.GONE);
			}
		}
		cur_page = sel;
	}
	
	private void check() {
		// TODO Auto-generated method stub
		
	}
	
	//btn
	public void next(View view){
		switch (getCurPage()) {
			case 0:
				check();
				selectView(1);
				break;
			case 1:
				check();
				selectView(2);
				break;
			case 2:
				check();
				selectView(3);
				break;
			default:
				break;
		}
		
	}

	
	
}
