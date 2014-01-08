package cn.scau.zzzd.xst.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.ClearTextWatcher;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.ui.reg.UiReg;

public class UiLogin extends BaseUi{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login);
		init();
		initData();
		update();
	}
	private EditText et_username = null;
	private EditText et_password = null;
	private View btn_clear1,btn_clear2 = null;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_clear1 = findViewById(R.id.btn_clear1);
		btn_clear2 = findViewById(R.id.btn_clear2);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
		et_username.addTextChangedListener(new ClearTextWatcher(btn_clear1, et_username));
		et_password.addTextChangedListener(new ClearTextWatcher(btn_clear2, et_password));
	}
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	//bis 
	private void login(){
		
	}
	//btn action
	public void toReg(View view){
		overlay(UiReg.class);
	}
	public void doLogin(View view){
		login();
	}
}
