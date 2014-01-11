package cn.scau.scaubook;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;

import cn.scau.scaubook.base.C;
import cn.scau.scaubook.entity.User;

public class GccdSetup implements Setup{
    private static final Log log = Logs.get();
    @Override
    public void init(NutConfig config) {
        // TODO Auto-generated method stub
        log.debug("config ioc = "+config.getIoc());
        
        Dao dao = config.getIoc().get(Dao.class);
        for(Class<?> klass : Scans.me().scanPackage(C.BASE_PACKAGE_PATH)){
            if(null != klass.getAnnotation(Table.class)){
                dao.create(klass,false);
            }
        }
        
        if(dao.count(User.class) == 0){
            User admin = new User("admin","123456");
            dao.insert(admin);
        }
    }

    @Override
    public void destroy(NutConfig config) {
        // TODO Auto-generated method stub
        
    }

}
