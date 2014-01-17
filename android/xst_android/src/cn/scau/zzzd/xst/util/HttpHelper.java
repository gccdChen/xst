package cn.scau.zzzd.xst.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.protocol.HTTP;

import android.util.Log;
import cn.scau.zzzd.xst.base.C;
/**
 * 网络工具类
 * 	主要方法 get,post
 * @author gccd
 * 2013-12-13
 */
public class HttpHelper {

	// logic variables
	private static HttpClient httpClient;

	private final static int SO_TIMEOUT = 5000;
	private final static int CONNECTION_TIMEOUT = 5000;

	private String charset = HTTP.UTF_8;

	public HttpHelper() {
		initClient();
	}

	private void initClient() {
		if (httpClient == null) {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			httpClient = new HttpClient(connectionManager);
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		}
	}
	public static void clear(){
		httpClient = null;
	}

	/**
	 * get
	 * @param taskurl
	 * @return
	 * @throws Exception
	 */
	public String get(String taskurl) throws Exception {
		String apiUrl = C.api.base + taskurl;
        long time = System.nanoTime();
		GetMethod httpGet = null;
		try {
			Log.w("HttpHelper.get.url", apiUrl);
			httpGet = getHttpGet(apiUrl);
			// send get request
			if (httpClient.executeMethod(httpGet) == HttpStatus.SC_OK) {
				String httpResult = httpGet.getResponseBodyAsString();
				Log.i("HttpHelper.get.result time:"+(System.nanoTime()-time)+"ns", httpResult);
				return httpResult;
			} else {
				return null;
			}
		} catch (ConnectTimeoutException e) {
			throw new Exception(C.err.network);
		} catch (Exception e) {
//			e.printStackTrace();
			throw e;
		} finally {
			httpGet.releaseConnection();
		}
	}
	/**
	 * post
	 * @param taskUrl
	 * @param keyvalues
	 * @return
	 * @throws Exception
	 */
	public String post(String taskUrl,Map<String, String> keyvalues) throws Exception {
		String apiUrl = C.api.base + taskUrl;
		long time = System.nanoTime();
		PostMethod httpPost = getHttpPost(apiUrl);
		NameValuePair[] params = new NameValuePair[keyvalues.size()];
		Iterator<Entry<String, String>> it = keyvalues.entrySet().iterator();
		int i = 0;
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			if(entry.getValue()==null)
				params[i++]=new NameValuePair(entry.getKey(), "");
			else
				params[i++]=new NameValuePair(entry.getKey(), entry.getValue());
		}
		httpPost.setRequestBody(params);
		StringBuilder sb = new StringBuilder("?");
		for(NameValuePair pair : params){
			sb.append(pair.getName()+"="+pair.getValue()+"&");
		}
		Log.w("HttpHelper.post.api", apiUrl + sb.toString());
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception(C.err.network);
			}
			String responseBody = httpPost.getResponseBodyAsString();
			Log.i("HttpHelper.post.httpResult time:"+(System.nanoTime()-time)+"ns",responseBody );
			return responseBody;
		} catch (ConnectTimeoutException e) {
			Log.w("ConnectTimeoutException", "ConnectTimeoutException");
			throw new Exception(C.err.network);
		} catch (SocketTimeoutException e) {
			throw new Exception(C.err.network);
		} catch (HttpException e) {
			throw new Exception(C.err.network);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(C.err.other);
		} finally {
			httpPost.releaseConnection();
		}
	}
	

	/**
	 * 
	 * @param params
	 *            非文件参数
	 * @param files
	 *            文件
	 * @return
	 * @throws Exception
	 */
	public String postFile(String taskUrl,Map<String, String> params, Map<String, File> files) throws Exception {
		String apiUrl = C.api.base + taskUrl;
		long time = System.nanoTime();
		PostMethod httpPost = getHttpPost(apiUrl);
		int length = (params == null ? 0 : params.size())
				+ (files == null ? 0 : files.size());
		Part[] parts = new Part[length];
		int i = 0;
		Log.w("HttpHelper.post.api", apiUrl);
		if (params != null)
			for (String name : params.keySet())
				parts[i++] = new StringPart(name, String.valueOf(params
						.get(name)), charset);
		if (files != null)
			for (String file : files.keySet()) {
				try {
					parts[i++] = new FilePart(file, files.get(file));
					Log.w("HttpHelper.post.files", file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

		httpPost.setRequestEntity(new MultipartRequestEntity(parts, httpPost.getParams()));
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception(C.err.network);
			}
			String responseBody = httpPost.getResponseBodyAsString();
			Log.i("HttpHelper.post.httpResult time:"+(System.nanoTime()-time)+"ns",responseBody );
			return responseBody;
		} catch (ConnectTimeoutException e) {
			Log.w("ConnectTimeoutException", "ConnectTimeoutException");
			throw new Exception(C.err.network);
		} catch (SocketTimeoutException e) {
			throw new Exception(C.err.network);
		} catch (HttpException e) {
			throw new Exception(C.err.network);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(C.err.other);
		} finally {
			httpPost.releaseConnection();
		}
	}

	private static PostMethod getHttpPost(String url) {
		PostMethod httpPost = new PostMethod(url);
		// 设置 请求超时时间
		httpPost.getParams().setSoTimeout(SO_TIMEOUT);
		httpPost.setRequestHeader("Connection", "Keep-Alive");
		/*
		 * httpPost.setRequestHeader("Cookie", cookie);
		 * httpPost.setRequestHeader("User-Agent", userAgent);
		 */
		return httpPost;
	}

	private static GetMethod getHttpGet(String url) {
		GetMethod httpGet = new GetMethod(url);
		// 设置 请求超时时间
		httpGet.getParams().setSoTimeout(SO_TIMEOUT);
		httpGet.setRequestHeader("Connection", "Keep-Alive");
		return httpGet;
	}

	public static String packageGetMethod(String url,String... appends){
		StringBuffer buffer = new StringBuffer(url);
		buffer.append("?");
		for(String append:appends){
			buffer.append(append);
			buffer.append("&");
		}
		return buffer.toString();
	}
}