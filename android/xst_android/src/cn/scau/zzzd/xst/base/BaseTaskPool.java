package cn.scau.zzzd.xst.base;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import cn.scau.zzzd.xst.util.HttpHelper;

/**
 * 网络任务池
 * @author gccd
 *
 */
public class BaseTaskPool {
	
	// task thread pool
	static private ExecutorService taskPool;
	
	// for HttpUtil.getNetType
	private Context context;
	
	public BaseTaskPool (BaseUi ui) {
		this.context = ui.getContext();
		taskPool = Executors.newCachedThreadPool();
	}
	
	// http post task with params
	public void addTask (int taskId, String taskUrl, Map<String, String> taskArgs, BaseTask baseTask, int delayTime) {
		baseTask.setId(taskId);
		try {
			taskPool.execute(new TaskThread(context, taskUrl, taskArgs, null,baseTask, delayTime));
		} catch (Exception e) {
			taskPool.shutdown();
		}
	}
	public void addFileTask (int taskId, String taskUrl,  Map<String , File> files,BaseTask baseTask, int delayTime) {
		addTask(taskId,taskUrl,null,files,baseTask,delayTime);
	}
	public void addTask (int taskId, String taskUrl, Map<String, String> taskArgs, Map<String , File> files,BaseTask baseTask, int delayTime) {
		baseTask.setId(taskId);
		try {
			taskPool.execute(new TaskThread(context, taskUrl, taskArgs, files,baseTask, delayTime));
		} catch (Exception e) {
			taskPool.shutdown();
		}
	}
	
	// http post task without params
	public void addTask (int taskId, String taskUrl, BaseTask baseTask, int delayTime) {
		baseTask.setId(taskId);
		try {
			taskPool.execute(new TaskThread(context, taskUrl, null,null, baseTask, delayTime));
		} catch (Exception e) {
			taskPool.shutdown();
		}
	}
	
	// custom task
	public void addTask (int taskId, BaseTask baseTask, int delayTime) {
		baseTask.setId(taskId);
		try {
			taskPool.execute(new TaskThread(context, null, null,null, baseTask, delayTime));
		} catch (Exception e) {
			taskPool.shutdown();
		}
	}
	
	// task thread logic
	private class TaskThread implements Runnable {
		private Context context;
		private String taskUrl;
		private Map<String, String> taskArgs;
		private BaseTask baseTask;
		private int delayTime = 0;
		private Map<String, File> files;
		public TaskThread(Context context, String taskUrl, Map<String, String> taskArgs, Map<String, File> files,BaseTask baseTask, int delayTime) {
			this.context = context;
			this.taskUrl = taskUrl;
			this.taskArgs = taskArgs;
			this.baseTask = baseTask;
			this.files = files;
			this.delayTime = delayTime;
		}
		@Override
		public void run() {
			try {
				baseTask.onStart();
				if(!baseTask.excu)
					return ;
				String httpResult = null;
				// set delay time
				if (this.delayTime > 0) {
					Thread.sleep(this.delayTime);
				}
				try {
					// remote task
					if (this.taskUrl != null) {
						HttpHelper httphelper = new HttpHelper();
						if (taskArgs == null) {
							httpResult = httphelper.get(this.taskUrl);
						} else if(files == null ){
							httpResult = httphelper.post(this.taskUrl,this.taskArgs);
						} else{
							httpResult = httphelper.postFile(this.taskUrl,this.taskArgs,this.files);
						}
					}
					// remote task
					if (httpResult != null) {
						baseTask.onComplete(httpResult);
					// local task
					} else {
						baseTask.onComplete();
					}
				} catch (Exception e) {
					baseTask.onError(e.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					baseTask.onStop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}