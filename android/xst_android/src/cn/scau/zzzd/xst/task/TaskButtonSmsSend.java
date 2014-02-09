package cn.scau.zzzd.xst.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * 点击重发短信计时task
 * @author gccd
 * 2013-12-13
 */
public class TaskButtonSmsSend extends AsyncTask<Integer, Integer, String>{
	TextView tv = null;
	private final static int M = 60;
	int passSecond = 0;
	public TaskButtonSmsSend(Context context,TextView tv) {
		// TODO Auto-generated constructor stub
		this.tv = tv;
		if(this.tv == null)
			this.tv = new TextView(context);
	}
	
	public TextView getTv() {
		return tv;
	}

	public void setTv(TextView tv) {
		this.tv = tv;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if(completeListener!=null)
			completeListener.onComplete();
		tv.setText("重发");
		tv.setEnabled(true);
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		tv.setText(M-values[0]+"秒后可重发");
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		if(completeListener!=null)
			completeListener.onStart();
		tv.setText(M+"秒后可重发");
		tv.setEnabled(false);
	}
	@Override
	protected String doInBackground(Integer[] params) {
		for(int i = passSecond ;i<M;i++){
			Log.i("TaskButtonSmsSend", "doInBackground i :  "+i);
			publishProgress(i);
			completeListener.onUpdate(i);
			try {
				Thread.sleep(1000);//1秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	};
	
	
	public int getPassSecond() {
		return passSecond;
	}

	public void setPassSecond(int passSecond) {
		this.passSecond = passSecond;
	}


	private OnCompleteListener completeListener = null;
	
	public void setCompleteListener(OnCompleteListener completeListener) {
		this.completeListener = completeListener;
	}

	public interface OnCompleteListener{
		void onStart();
		void onUpdate(int second);
		void onComplete();
	}
}
