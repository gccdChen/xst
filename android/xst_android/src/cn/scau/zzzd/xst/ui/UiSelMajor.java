package cn.scau.zzzd.xst.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.adapter.MajorListAdapter;
import cn.scau.zzzd.xst.base.BaseMessage;
import cn.scau.zzzd.xst.base.BaseUi;
import cn.scau.zzzd.xst.base.C;
import cn.scau.zzzd.xst.entity.Major;
import cn.scau.zzzd.xst.widget.xlistview.XListView;
import cn.scau.zzzd.xst.widget.xlistview.XListView.IXListViewListener;

/**
 * 选择专业
 * 
 * @author gccd
 * 
 */
public class UiSelMajor extends BaseUi implements IXListViewListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_select_major);
		init();
		initData();
		update();
	}

	private Button btn_search;
	private EditText et_keyword;
	private XListView lv_content;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		btn_search = (Button) findViewById(R.id.btn_search);
		et_keyword = (EditText) findViewById(R.id.et_keyword);
		lv_content = (XListView) findViewById(R.id.lv_content);
	}

	private String keyword = "";
	private int pageNo = 0;
	private MajorListAdapter adapter = null;
	private List<Major> majors = null;

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
		adapter = new MajorListAdapter(this, majors);
		lv_content.setAdapter(adapter);
		lv_content.setPullLoadEnable(true);
		lv_content.setFooterDividersEnabled(false);
		lv_content.setXListViewListener(this);
		et_keyword.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
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
				String str = s.toString().trim();
				if (keyword.length() > 2) {
					keyword = str;
					pageNo = 0;
					search();
				}
			}
		});
	}

	protected void search() {
		// TODO Auto-generated method stub
		if (keyword.length() > 2) {
			Map<String, String> taskArgs = new HashMap<String, String>();
			taskArgs.put("keyword", keyword);
			doTaskAsync(R.id.request_search_major, C.api.search_major, taskArgs);
		}
	}

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		try {
			switch (taskId) {
			case R.id.request_search_major:
				List<Major> list = (List<Major>) message.getResultList("Major");
				adapter.update(list);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

}
