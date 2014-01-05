package cn.scau.zzzd.xst.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
/**
 * 发送短信,检查网络...
 * @author gccd
 * 2013-12-13
 */
public class DeviceUtil {
	private Context context;

	/**
	 * 拨打电话
	 * @param context
	 * @param number
	 */
	public static void dial(Context context,String number) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ number));
		context.startActivity(intent);
	}
	/**
	 * 发送短信
	 * @param context
	 * @param number 目的电话
	 * @param content 内容
	 */
	public static void sendSms(Context context,String number,String content){
		Uri uri = Uri.parse("smsto:"+number);            
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);            
		intent.putExtra("sms_body", content);
		context.startActivity(intent);
	}
	/**
	 * 检查网络是否可用
	 * @return
	 */
	public boolean checkNetWork(){
		 ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 if(connectivity != null){
			 for(NetworkInfo info : connectivity.getAllNetworkInfo()){
				 if(info.isConnected()){
					 Log.i("checkNetWork","the "+info.getTypeName()+" is on;");
					 return true;
				 }else{
					 Log.i("checkNetWork","the "+info.getTypeName()+" is off;");
				 }
			 }
		 }
		 return false;
	}
	/**
	 * 获取imei
	 * @param context
	 * @return
	 */
	public static String getImei(Context context){
		String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		return imei;
	}
	/**
	 * 关闭输入法
	 * @param context
	 */
	public static void closeInput(Activity context){
		 if (context.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
             //关闭输入法
             InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                             .getSystemService(Context.INPUT_METHOD_SERVICE);

             imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		 }
	}
	
	/**
	 * 获取耗费内存
	 * @return 已用内存
	 */
	public static long getUsedMemory () {
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		return total - free;
	}
	
	private void abortTM(){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		  tm.getCallState();//int     
		       
		  tm.getCellLocation();//CellLocation     
		       
		  tm.getDeviceId();//String     
		       
		  tm.getDeviceSoftwareVersion();//String     
		       
		  tm.getLine1Number();//String     
		       
		  tm.getNeighboringCellInfo();//List<NeighboringCellInfo>     
		       
		  tm.getNetworkCountryIso();//String     
		       
		  tm.getNetworkOperator();//String     
		       
		  tm.getNetworkOperatorName();//String     
		       
		  tm.getNetworkType();//int     
		       
		  tm.getPhoneType();//int     
		       
		  tm.getSimCountryIso();//String     
		       
		  tm.getSimOperator();//String     
		       
		  tm.getSimOperatorName();//String     
		       
		  tm.getSimSerialNumber();//String     
		       
		  tm.getSimState();//int     
		       
		  tm.getSubscriberId();//String     
		       
		  tm.getVoiceMailAlphaTag();//String     
		       
		  tm.getVoiceMailNumber();//String     
		       
		  tm.hasIccCard();//boolean     
		       
		  tm.isNetworkRoaming();//     
	}
}