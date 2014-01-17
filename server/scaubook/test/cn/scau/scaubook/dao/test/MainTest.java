package cn.scau.scaubook.dao.test;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import cn.scau.scaubook.entity.Book;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class MainTest {
    public static void main(String[] args) {
//        System.out.println((""+new Date().getTime()).length());
//        Map list = new HashMap();
//        list.put("hello", "world");
//        list.put("data",new Book());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(Json.toJson(list));
//        list.put("data2",new Book());
//        list.put("data3",new Book());
//        System.out.println(Json.toJson(list));
//        list.put("data4",new Book());
//        System.out.println(Json.toJson(list));
//        System.out.println(System.currentTimeMillis());
        Map<String, String> m = new HashMap<String, String>();
        m.put("name", "heell");
        m.put("nameworkd", "he123ell");
        System.out.println(Json.toJson(m));
    }
}
