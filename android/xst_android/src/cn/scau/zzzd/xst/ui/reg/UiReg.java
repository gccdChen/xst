package cn.scau.zzzd.xst.ui.reg;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.ClearTextWatcher;
import cn.scau.zzzd.xst.base.BaseMessage;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.task.TaskButtonSmsSend;
import cn.scau.zzzd.xst.ui.UiIndex;
import cn.scau.zzzd.xst.util.PatternUtil;

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
	private EditText et_vertify = null;
	private LinearLayout layout_content = null;
	private View btn_clear1;
	private View btn_clear2;
	private Button btn_sendsms = null;
	
	//view1
	private Spinner sp_school;
	private Spinner sp_college;
	private Spinner sp_major;
	private RadioGroup rg_grade;
	private RadioButton radio0;
	private RadioButton radio1;
	private RadioButton radio2;
	
	//view2
	private TextView et_opassword;
	private TextView et_password;
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
		et_phone = (EditText) views[0].findViewById(R.id.et_phone1);
		et_vertify = (EditText) views[0].findViewById(R.id.et_vertify);
		btn_clear1 = views[0].findViewById(R.id.btn_clear1);
		btn_clear2 = views[0].findViewById(R.id.btn_clear2);
		btn_sendsms  = (Button) views[0].findViewById(R.id.btn_sendsms);
		//view1
		sp_school = (Spinner) views[1].findViewById(R.id.sp_school);
		sp_college = (Spinner) views[1].findViewById(R.id.sp_college);
		sp_major = (Spinner) views[1].findViewById(R.id.sp_major);
		rg_grade = (RadioGroup) views[1].findViewById(R.id.rg_grade);
		radio0 = (RadioButton) views[1].findViewById(R.id.radio0);
		radio1 = (RadioButton) views[1].findViewById(R.id.radio1);
		radio2 = (RadioButton) views[1].findViewById(R.id.radio2);
		//view2
		et_opassword = (TextView) views[2].findViewById(R.id.et_opassword);
		et_password = (TextView) views[2].findViewById(R.id.et_password);
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
	
	private void signup3() {
		// TODO Auto-generated method stub
		String password = et_opassword.getText().toString();
		Map<String, String> taskArgs = new HashMap<String, String>();
		taskArgs.put("password", password);
		doTaskAsync(R.id.request_signup3, C.api.signup3, taskArgs);
	}

	private void signup2() {
		// TODO Auto-generated method stub
		String majorid =  sp_major.getSelectedItem().toString();
		String grade = null;
		grade = ((RadioButton)views[1].findViewById(rg_grade.getCheckedRadioButtonId())).getText().toString();
		if(grade == null)
			grade = "" + (Calendar.getInstance().get(Calendar.YEAR) - 1);
		Map<String, String> taskArgs = new HashMap<String, String>();
		taskArgs.put("majorid", majorid);
		taskArgs.put("grade", grade);
		doTaskAsync(R.id.request_signup2, C.api.signup2,taskArgs);
	}

	private void signup1() {
		// TODO Auto-generated method stub
		String username = et_username.getText().toString();
		String phone = et_phone.getText().toString();
		String vertify = et_vertify.getText().toString();
		Map<String, String> taskArgs = new HashMap<String, String>();
		taskArgs.put("phone", phone);
		taskArgs.put("username", username);
		taskArgs.put("vertify", vertify);
		doTaskAsync(R.id.request_signup1, C.api.signup1, taskArgs);
	}
	
	private boolean check() {
		// TODO Auto-generated method stub
		switch (getCurPage()) {
			case 0:
				String username = et_username.getText().toString();
				String phone = et_phone.getText().toString();
				String vertify = et_vertify.getText().toString();
				if(PatternUtil.isPhone(phone) && 
						PatternUtil.isUsername(username) &&
						vertify.length() == 6)
					return true;
				break;
			case 1:
				return  true;
			case 2:
				String password = et_password.getText().toString();
				String opassword = et_opassword.getText().toString();
				if(password.equals(opassword) ){
					if(PatternUtil.isPassword(password)){
						return true;
					}else{
						toast(R.string.msg_pw_fotmat_err);
					}
				}else{
					toast(R.string.msg_pw_unequals_err);
				}
				return false;
			default:
				break;
		}
		return false;
	}
	
	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch (taskId) {
			case R.id.request_getvertify:
				if(message.issuccess()){
					toast(message.getMessage());
					TaskButtonSmsSend buttonSmsSend = new TaskButtonSmsSend(this, btn_sendsms);
					//重置发送按钮
					//监听短信
				}
				break;
			case R.id.request_signup1:
				if(message.issuccess()){
					selectView(1);
				}else
					toast(message.getMessage());
			case R.id.request_signup2:
				if(message.issuccess()){
					selectView(2);
				}else
					toast(message.getMessage());
			case R.id.request_signup3:
				if(message.issuccess()){
					selectView(3);
				}else
					toast(message.getMessage());
			default:
				break;
		}
	}
	
	//btn
	public void next(View view){
		boolean flag = false;
		switch (getCurPage()) {
			case 0:
				if(flag = check()){
					signup1();
				}
				if(!flag)
					toast(R.string.msg_info_integrity_err);
				break;
			case 1:
				if(flag = check()){
					signup2();
				}
				if(!flag)
					toast(R.string.msg_info_integrity_err);
				break;
			case 2:
				if(flag = check()){
					selectView(3);
					signup3();
				}
				break;
			case 3:
				forward(UiIndex.class);
				break;
			default:
				break;
		}
		
	}

	
	String phone = null;
	public void getVertify(View view){
		phone = et_phone.getText().toString();
		doTaskAsync(R.id.request_getvertify, C.api.getVertufy+"?phone="+phone);
	}
	
}
