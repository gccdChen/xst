package cn.scau.scaubook.dao.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;

import cn.scau.scaubook.entity.Book;
import cn.scau.scaubook.entity.College;
import cn.scau.scaubook.entity.Course;
import cn.scau.scaubook.entity.Sellitem;
import cn.scau.scaubook.entity.Major;
import cn.scau.scaubook.entity.Rating;
import cn.scau.scaubook.entity.School;
import cn.scau.scaubook.entity.Student;
import cn.scau.scaubook.entity.Tags;
import cn.scau.scaubook.entity.User;
import cn.scau.scaubook.util.IOUtil;

public class MyTest {
    Ioc ioc;
    Dao dao;
    @Before
    public void before(){
        ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
        dao = ioc.get(Dao.class);
        dao.create(School.class, true);
        dao.create(College.class, true);
        dao.create(Major.class, true);
        dao.create(Book.class, true);
        dao.create(Student.class, true);
        dao.create(User.class, true);
        dao.create(Course.class, true);
        dao.create(Sellitem.class, true);
    }
    @After
    public void after(){
        if(ioc != null)
            ioc.depose();
    }
    
    @Test
    public void testEmtry(){
    }
    
    @Test
    public void testInit(){
        Json gson = new Json();
        String path = "D:\\1.txt";
        String json;
        try {
            json = IOUtil.read(path );
            Book book = gson.fromJson(Book.class,json);
            dao.insert(book);
//            System.out.println(book);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
