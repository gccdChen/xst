package cn.scau.zzzd.xst.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class ClearTextWatcher implements TextWatcher{
	private View btn_clear = null;
	EditText et_content = null;
	
	public ClearTextWatcher(View clear, EditText et_content) {
		super();
		this.btn_clear = clear;
		this.et_content = et_content;
		check(et_content.getText().toString());
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clear();
			}
		});
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		check(s.toString());
	}

	private void check(String s){
		if(s.length() != 0){
			btn_clear.setVisibility(View.VISIBLE);
		}else{
			btn_clear.setVisibility(View.GONE);
		}
	}
	void clear(){
		et_content.setText("");
	}
}
