package cn.scau.scaubook.util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.nutz.http.sender.GetSender;
import org.nutz.json.Json;

import cn.scau.scaubook.entity.Book;

public class DouBanApiUtil {
    
    /**
     * https://api.douban.com/v2/book/isbn/:name
     * @param isbn
     * @return
     */
    public static Book getBookByISBN(String isbn){
        String json_result = HttpClientUtil.sendGetRequest(API.ISBN);
        return Json.fromJson(Book.class,json_result);
    }
    
    
    /**
     * https://api.douban.com/v2/book/search 
     * @param q 查询关键字   q和tag必传其一
     * @param start 取结果的offset  默认为0
     * @param count 取结果的条数  默认为20，最大为100
     * @return
     */
    public static List<Book> search(String q,int start,int count){
        String json_result = HttpClientUtil.sendGetRequest(API.SEARCH+"?q="+q+"&start="+start+"&count="+count);
        try {
            JSONObject jsonObject =new JSONObject(json_result);
            String data = jsonObject.get("books").toString();
            return Json.fromJsonAsList(Book.class, data);
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * https://api.douban.com/v2/book/search 
     * @param q 查询关键字   q和tag必传其一
     * @param start 取结果的offset  默认为0
     * @param count 取结果的条数  默认为20，最大为100
     * @return
     */
    public static List<Book> searchByTag(String tag,int start,int count){
        String json_result = HttpClientUtil.sendGetRequest(API.ISBN+"?tag="+tag+"&start="+start+"&count="+count);
        try {
            JSONObject jsonObject =new JSONObject(json_result);
            String data = jsonObject.get("books").toString();
            return Json.fromJsonAsList(Book.class, data);
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private class API{
       public final static String ISBN = "https://api.douban.com/v2/book/isbn/";
       public final static String SEARCH = "https://api.douban.com/v2/book/search";
    }
}
