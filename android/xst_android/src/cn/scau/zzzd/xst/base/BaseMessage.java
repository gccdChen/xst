package cn.scau.zzzd.xst.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.scau.zzzd.xst.util.JsonUtil;
import cn.scau.zzzd.xst.util.StringUtil;

/**
 * 解释json data格式类
 * @author gccd
 * 2013-12-13
 */
public class BaseMessage {
	
	private String code;
	private String message;
	private String resultSrc;
	private Map<String, BaseModel> resultMap;
	private Map<String, ArrayList<? extends BaseModel>> resultList;

	public boolean issuccess(){
		return code.equals("0");
	}
	
	public BaseMessage() {
		this.resultMap = new HashMap<String, BaseModel>();
		this.resultList = new HashMap<String, ArrayList<? extends BaseModel>>();
	}
	private String jsonStr;
	public String getJsonStr(){
		return jsonStr;
	}
	/* 获取 Message */
	static public BaseMessage getMessage (String jsonStr) throws Exception {
		BaseMessage message = new BaseMessage();
		JSONObject jsonObject = null;
		message.jsonStr = jsonStr;
		try {
			jsonObject = new JSONObject(jsonStr);
			if (jsonObject != null) {
				message.setCode(""+jsonObject.getInt("status_code"));
				message.setMessage(jsonObject.getString("description"));
				if(jsonObject.has("data")){
					message.setResult(jsonObject.getString("data"));
				}else{
					message.setResult("");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new Exception("Json format error");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String toString() {
		return code + " | " + message + " | " + resultSrc;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return this.resultSrc;
	}

	/**
	 * 
	 * @param modelName eg com.szl.friendship.model.Artcle
	 * @return
	 * @throws Exception
	 */
	public Object getResult(String modelName) throws Exception{
		Object model = this.resultMap.get(modelName);
		// catch null exception
		if (model == null) {
			Log.i("getResult", "null");
			throw new EntityEmptyException("Message data is empty");
		}
		return model;
	}

	public ArrayList<? extends BaseModel> getResultList(String modelName)
			throws Exception {
		ArrayList<? extends BaseModel> modelList = this.resultList.get(modelName);
		// catch null exception
		if (modelList == null || modelList.size() == 0) {
			throw new EntityEmptyException("Message data listupdate is empty");
		}
		return modelList;
	}

	/**
	 * 
	 * @param result
	 *            json like { "user":[{"acount":"admin"},{"acount":"user"}]
	 *            ,"msg":[{"content":"the card is finded!"},{"content",
	 *            "the server will update in 12:00"}] }
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void setResult(String result) throws Exception {
		this.resultSrc = result;
		if (result.length() > 0) {
			JSONObject jsonObject = null;
			jsonObject = new JSONObject(result);
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				// initialize
				String jsonKey = it.next();
				String modelName = getModelName(jsonKey);
				String modelClassName = C.packname.packpath + ".model."+ modelName;
				JSONArray modelJsonArray = jsonObject.optJSONArray(jsonKey);
				// JSONObject
				if (modelJsonArray == null) {
					JSONObject modelJsonObject = jsonObject
							.optJSONObject(jsonKey);
					if (modelJsonObject == null) {
						throw new Exception("Message result is invalid");
					}
					this.resultMap.put(modelName,JsonUtil.json2model(modelClassName, modelJsonObject));
					// JSONArray
				} else {
					ArrayList<BaseModel> modelList = new ArrayList<BaseModel>();
					for (int i = 0; i < modelJsonArray.length(); i++) {
						JSONObject modelJsonObject = modelJsonArray
								.optJSONObject(i);
						modelList.add(JsonUtil.json2model(modelClassName,modelJsonObject));
					}
					this.resultList.put(modelName, modelList);
				}
			}
		}
	}

	private String getModelName(String str) {
		String[] strArr = str.split("\\W");
		if (strArr.length > 0) {
			str = strArr[0];
		}
		return StringUtil.ucfirst(str);
	}
	public class EntityEmptyException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = -665972753209119468L;

		public EntityEmptyException(String string) {
			super(string);
		}
	}
}