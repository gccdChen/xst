package cn.scau.zzzd.xst.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.scau.zzzd.xst.base.BaseModel;
/**
 * json 工具类
 * 主要方法  json2model
 * @author gccd
 * 2013-12-13
 */
public class JsonUtil {
	private static List<String> INTCOLS = new ArrayList<String>();
	static{
	}
	private static List<String> DOUBLECOLS = new ArrayList<String>();
	static{
	}
	public static BaseModel json2model(String modelClassName,JSONObject modelJsonObject) {
		// auto-load model class
		BaseModel modelObj = null;
		String varField = null ;
		try {
			modelObj = (BaseModel) Class.forName(modelClassName).newInstance();
			Class<? extends BaseModel> modelClass = modelObj.getClass();
			// auto-setting model fields
			Iterator<String> it = modelJsonObject.keys();
			while (it.hasNext()) {
				varField = it.next();
				String svarField = varField.toLowerCase();
				Field field = null;
				if(varField.endsWith("id")){
					Long varValue = modelJsonObject.getLong(varField);
					field = modelClass.getDeclaredField(svarField);
					field.setAccessible(true);
					field.set(modelObj, varValue);
				}else if (INTCOLS.contains(varField)) {
					Integer varValue = modelJsonObject.getInt(varField);
					field = modelClass.getDeclaredField(svarField);
					field.setAccessible(true);
					field.set(modelObj, varValue);
				}else if(DOUBLECOLS.contains(varField)){
					Double varValue = modelJsonObject.getDouble(varField);
					field = modelClass.getDeclaredField(svarField);
					field.setAccessible(true);
					field.set(modelObj, varValue);
				}else {
					String varValue = modelJsonObject.getString(varField);
					field = modelClass.getDeclaredField(svarField);
					field.setAccessible(true);
					field.set(modelObj, varValue);
				}
			}
			return modelObj;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			Log.e("InstantiationException", varField);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			Log.e("IllegalAccessException", varField);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			Log.e("NoSuchFieldException", varField);
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			Log.e("IllegalArgumentException", varField);
			e.printStackTrace();
		}
		return modelObj;
	}
	public static <T> List<T> json2models(String modelClassName,JSONArray modelJsonArray) {
		List<T> list = new ArrayList<T>();
		if(modelJsonArray==null || modelJsonArray.length()==0)
			return list;
		for(int i=0;i<modelJsonArray.length();i++){
			try {
				list.add((T)json2model(modelClassName,modelJsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
