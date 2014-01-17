package cn.scau.zzzd.xst.base;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.scau.zzzd.xst.R;
import cn.scau.zzzd.xst.util.CacheHelper;
import cn.scau.zzzd.xst.util.DateUtil;
import cn.scau.zzzd.xst.util.DeviceUtil;
import cn.scau.zzzd.xst.util.ToastHelper;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * 所有页面继承于baseUi
 * 
 * @author gccd
 *
 */
public abstract class BaseUi extends Activity implements AppMode{


	protected boolean showLoadBar = false;
	protected boolean showDebugMsg = true;
	protected BaseTaskPool taskPool;
	protected BaseApp app;
	
	protected static long lastclick = -1;
	protected final static long MAX_CLICK_INTERVAL = 500;
	
	protected final static long MAX_GET_INTERVAL = 5000;//5秒
	/**
	 * taskId,时间 请求成功时放入,如果小于设定的值 MAX_GET_INTERVAL ,则不进行请求 直接完成
	 */
	protected static Map<Integer,Long> get_time_map = new HashMap<Integer, Long>();
	protected DisplayImageOptions options = null;
	protected ImageLoader imageLoader = null;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// debug memory
		debugMemory("onCreate");
		// async task handler
		this.handler = new BaseHandler(this);
		// init task pool
		this.taskPool = new BaseTaskPool(this);
		// init application
		this.app = (BaseApp) this.getApplicationContext();
		
		
		options = new DisplayImageOptions.Builder()  
		.showImageOnLoading(R.drawable.ic_error)  
        .showImageForEmptyUri(R.drawable.ic_error)  
        .showImageOnFail(R.drawable.ic_error)  
        .resetViewBeforeLoading(true)  
        .cacheOnDisc(true)  
        .imageScaleType(ImageScaleType.EXACTLY)  
        .bitmapConfig(Bitmap.Config.RGB_565)
        .displayer(new FadeInBitmapDisplayer(300))  
        .build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
				
		//SocializeConstants.APPKEY="52aa986c56240bc24908ed65";
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		debugMemory("onRestart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// debug memory
		debugMemory("onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		// debug memory
		debugMemory("onPause");
	}

	@Override
	public void onStart() {
		super.onStart();
		// debug memory
		debugMemory("onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		// debug memory
		debugMemory("onStop");
	}
	
	/**
	 * 初始化
	 * findViewById(...)
	 */
	protected abstract void init();
	/**
	 * 初始化数据
	 */
	protected void initData(){
		// 
	}
	/**
	 * 更新
	 */
	protected abstract void update();
	/**
	 * 加载
	 */
	protected void loadmore(){
	}
	// //////////////////////////////////////////////////////////////////////////////////////////////
	// util method

	protected void toast(String msg) {
		ToastHelper.showToast(this, msg, 1000);
	}
	protected void toast(int msg_id) {
		ToastHelper.showToast(this, msg_id, 1000);
	}

	/**
	 * 转跳 没有finish
	 * @param classObj
	 */
	public void overlay(Class<?> classObj) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.setClass(this, classObj);
		startActivity(intent);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}
	/**
	 * 转跳 没有finish
	 * @param classObj
	 * @param params
	 */
	public void overlay(Class<?> classObj, Bundle params) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.setClass(this, classObj);
		intent.putExtras(params);
		startActivity(intent);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}
	/**
	 * 转跳 没有finish
	 * @param classObj
	 * @param requestCode
	 */
	public void overlayForResult(Class<?> classObj,int requestCode) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.setClass(this, classObj);
		startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}
	/**
	 * 转跳 没有finish
	 * @param classObj
	 * @param requestCode
	 * @param params
	 */
	public void overlayForResult(Class<?> classObj,int requestCode, Bundle params) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.setClass(this, classObj);
		intent.putExtras(params);
		startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}
	
	/**
	 * 转跳 有finish
	 * @param classObj
	 */
	public void forward(Class<?> classObj) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
		this.finish();
	}
	
	/**
	 * 转跳 有finish
	 * @param classObj
	 * @param params
	 */
	public void forward(Class<?> classObj, Bundle params) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtras(params);
		this.startActivity(intent);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
		this.finish();
	}

	public Context getContext() {
		return this;
	}

	public LayoutInflater getLayoutInflater() {
		return (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getLayout(int layoutId) {
		return getLayoutInflater().inflate(layoutId, null);
	}

	/**
	 * 打开waiting dialog
	 */
	public void showLoadBar() {
		
//		if(progressDialog==null){
//			progressDialog =ProgressDialog.createDialog(this);
//			progressDialog.setMessage("正在加载中...");
//		}
//		progressDialog.show();
		showLoadBar = true;
	}
	/**
	 * 收起waiting dialog
	 */
	public void hideLoadBar() {
		if (showLoadBar) {
//			if(progressDialog!=null)
//				progressDialog.dismiss();
			showLoadBar = false;
		}
	}

	
	// //////////////////////////////////////////////////////////////////////////////////////////////
	// logic method

	public void doFinish() {
		this.finish();
		System.exit(0);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
//		if(!(this instanceof UiMain))
//			overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// debug method

	public void debugMemory(String tag) {
		if (this.showDebugMsg) {
			Log.w(this.getClass().getSimpleName(),tag + ":" + DeviceUtil.getUsedMemory());
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// common classes

	protected final static int CAMERA_REQUEST_CODE = 1;
	protected final static int ABLUM_REQUEST_CODE = 2;

	/**
	 * 打开系统图册
	 * 
	 * @param activity
	 */
	protected static void showPicAblum(Activity activity) {
		Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
		getAlbum.setType("image/*");
		activity.startActivityForResult(getAlbum, 2);
	}

	/**
	 * 打开系统相机
	 * 
	 * @param activity
	 */
	protected static void showCamera(Activity activity) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		activity.startActivityForResult(intent, 1);
	}

	/**
	 * 打开选择图片
	 */
	protected void showSelectPicDialog() {
		new AlertDialog.Builder(this)
				.setTitle("")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(new String[] { "从相册", "从照相机" }, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									showPicAblum(BaseUi.this);
									break;
								default:
									showCamera(BaseUi.this);
									break;
								}
								dialog.dismiss();
							}
						}).show();
	}

	/**
	 * 清除缓存
	 */
	protected void clearCache() {
		CacheHelper.clearCache();
		Intent intent = new Intent(C.INTENT.action.ACTION_NAME_UI_CHANGE);
		intent.putExtra(C.ACTION_CODE, 200);
		sendBroadcast(intent);
	}
	
	//syn network
	protected BaseHandler handler;
	public void sendMessage(int what) {
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}

	public void sendMessage(int what, String data) {
		Bundle b = new Bundle();
		b.putString("data", data);
		Message m = new Message();
		m.what = what;
		m.setData(b);
		handler.sendMessage(m);
	}

	public void sendMessage(int what, int taskId, String data) {
		Bundle b = new Bundle();
		b.putInt("task", taskId);
		b.putString("data", data);
		Message m = new Message();
		m.what = what;
		m.setData(b);
		handler.sendMessage(m);
	}
	public void doTaskAsync(int taskId, String taskUrl) {
		showLoadBar();
		taskPool.addTask(taskId, taskUrl, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE, this.getId(), httpResult);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
			}
		}, 0);
	}

	public void doTaskAsync(int taskId, String taskUrl,
			Map<String, String> taskArgs, BaseTask baseTask) {
		showLoadBar();
		taskPool.addTask(taskId, taskUrl, taskArgs, baseTask, 0);
	}

	public void doTaskFileAsync(int taskId, String taskUrl,
			Map<String, File> files) {
		doTaskAsync(taskId, taskUrl, null, files, null);
	}

	public void doTaskFileAsync(int taskId, String taskUrl,
			Map<String, File> files, BaseTask baseTask) {
		doTaskAsync(taskId, taskUrl, null, files, baseTask);
	}

	public void doTaskAsync(int taskId, String taskUrl,
			Map<String, String> taskArgs, Map<String, File> files,
			BaseTask baseTask) {
		showLoadBar();
		if (baseTask == null)
			baseTask = new BaseTask() {
				@Override
				public void onComplete(String httpResult) {
					sendMessage(BaseTask.TASK_COMPLETE, this.getId(),
							httpResult);
				}

				@Override
				public void onError(String error) {
					sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
				}
			};
		taskPool.addTask(taskId, taskUrl, taskArgs, files, baseTask, 0);
	}

	/**
	 * 会包装result
	 * 
	 * @param taskId
	 * @param taskUrl
	 * @param taskArgs
	 */
	public void doTaskAsync(int taskId, String taskUrl,Map<String, String> taskArgs) {
		showLoadBar();
		taskPool.addTask(taskId, taskUrl, taskArgs, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE, this.getId(), httpResult);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
			}
		}, 0);
	}
	/**
	 * 
	 * @param taskId
	 * @param taskUrl
	 * @param taskArgs
	 * @param isLoadBar 是否显示等待框
	 */
	public void doTaskAsync(int taskId, String taskUrl,Map<String, String> taskArgs,boolean isLoadBar) {
		if(isLoadBar)
			showLoadBar();
		taskPool.addTask(taskId, taskUrl, taskArgs, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE, this.getId(), httpResult);
			}
			
			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
			}
		}, 0);
	}

	/**
	 * 不会包装result ,把字符串直接返回
	 * 
	 * @param taskId
	 * @param taskUrl
	 * @param taskArgs
	 */
	public void doTaskAsyncSimple(int taskId, String taskUrl,Map<String, String> taskArgs) {
		showLoadBar();
		taskPool.addTask(taskId, taskUrl, taskArgs, new BaseTask() {
			@Override
			public void onComplete(String httpResult) {
				sendMessage(BaseTask.TASK_COMPLETE_SIMPLE, this.getId(),
						httpResult);
			}

			@Override
			public void onError(String error) {
				sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
			}
		}, 0);
	}
	
	/**
	 * 
	 * @param taskId
	 * @param message
	 */
	public void onTaskComplete(int taskId, BaseMessage message) {
		hideLoadBar();
		get_time_map.put(taskId, System.currentTimeMillis());//放入
	}

	public void onTaskComplete(int taskId) {

	}

	public String getTime(int taskId){
		if(!get_time_map.containsKey(taskId)){
			return "";
		}
		long t = get_time_map.get(taskId);
		return DateUtil.long2ToStr2(t);
	}
	
	public void onNetworkError(int taskId) {
		//toast(C.err.network);
	}

	//btn
	public void doBack(View view){
		DeviceUtil.closeInput(this);
		this.finish();
	}
	
	protected void initTitle(View view,String title,boolean isBack,boolean isSearch){
		View v,v1,v2;
		if(view != null){
			v = view.findViewById(R.id.tv_title);
			v1 = view.findViewById(R.id.btn_back);
			v2 = view.findViewById(R.id.btn_search);
		}else{
			v = findViewById(R.id.tv_title);
			v1 = findViewById(R.id.btn_back);
			v2 = findViewById(R.id.btn_search);
		}
		if(v != null){
			((TextView)(v)).setText(title);
		}
		if(v1 != null){
			if(isBack)
				v1.setVisibility(View.VISIBLE);
			else
				v1.setVisibility(View.GONE);
		}
		if (v2!=null) {
			if (isSearch) {
				v2.setVisibility(View.VISIBLE);
			}else{
				v2.setVisibility(View.GONE);
			}
		}
	}
	/**
	 * 初始化标题栏
	 * @param view
	 * @param title_id
	 * @param isBack
	 */
	protected void initTitle(View view,int title_id,boolean isBack,boolean isSearch){
		initTitle(view, getString(title_id), isBack,isSearch);
	}
	
	private SharedPreferences preferences = null;
	Context sharedContext = null;
	public SharedPreferences getMyPreferences() {
		// TODO Auto-generated method stub
		if(preferences != null)
			return preferences;
		try {
			sharedContext = createPackageContext("cn.scau.zzzd.xst",Context.CONTEXT_IGNORE_SECURITY);
			preferences = sharedContext.getSharedPreferences(C.CONFIGKEY.NAME,Context.MODE_WORLD_READABLE);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preferences;
	}
	
	//dp转px
	 public  int dip2px(Context context, float dpValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dpValue * scale + 0.5f);  
	 }  
	
	protected final static Pattern PHONE_PATTERN = Pattern.compile("1[3-8][0-9]{9}");
	
	/**
	 * 
	 */
	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}
	//app mode
	
	
}
/**
 * 这个app特有方法
 *
 */
interface AppMode{
	
}