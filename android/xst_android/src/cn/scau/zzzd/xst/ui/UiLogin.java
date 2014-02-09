package cn.scau.zzzd.xst.ui;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.ClearTextWatcher;
import cn.scau.zzzd.xst.base.BaseMessage;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.entity.User;
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
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		Map<String,String> taskArgs = new HashMap<String, String>();
		taskArgs.put("username", username);
		taskArgs.put("password", password);
		doTaskAsync(R.id.request_login, C.api.login, taskArgs);
	}
	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		if(message != null)
			toast(message.getMessage());
		switch (taskId) {
				case R.id.request_login:
				if(message.issuccess()){
					try {
						User user = (User)message.getResult(User.class);
						toast(user.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
	
			default:
				break;
		}
	}
	//btn action
	public void toReg(View view){
		overlay(UiReg.class);
	}
	public void doLogin(View view){
		login();
	}
}
