package cn.scau.scaubook.module;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;

import cn.scau.scaubook.base.BaseMessage;
import cn.scau.scaubook.entity.User;
/**
 * 用户模块
 * @author gccd
 *
 */
@IocBean
@At("/usr")
public class UserModule implements UserModuleApi{
    
    private static final Log log = Logs.get();
    
    @Inject
    private Dao dao = null;
   
   /* @At
    public boolean login(String username,String password,
                         HttpSession session){
        if(Strings.isBlank(username) || Strings.isBlank(password))
            return false;
        log.debug("username:"+username+",password:"+password);
        username = username.trim().intern();
        password = password.trim().intern();
        User user = dao.fetch(User.class, Cnd.where("username","=",username).and("password","=",password));
        if(user == null)
            return false;
        session.setAttribute("me", user);
        return true;
    }*/
    
    /*@At("/ping")
    public Object ping() {
        // TODO Auto-generated method stub
        log.debug("dao = " + dao);
        return new Date();
    }*/

    @Override
    public BaseMessage sendVerifi(String school_id,
                                  String username,
                                  String phone,
                                  HttpSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage verify(String verifyCode, HttpSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage reg(String m_id, Integer grade, HttpSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage setpw(String m_id, Integer grade, HttpSession session) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage login(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage forgetPw(String phone) {
        // TODO Auto-generated method stub
        return null;
    }
}
interface UserModuleApi{
    /**
     * 短信验证码
     * @return
     */
    BaseMessage sendVerifi(String school_id,String username,String phone,
                           HttpSession session);
    /**
     * 验证码是否正确
     * @param verifyCode
     * @return
     */
    BaseMessage verify(String verifyCode,
                       HttpSession session);
    /**
     * 注册
     * @return
     */
    BaseMessage reg(String m_id,Integer grade
                    ,HttpSession session);
    /**
     * 设置密码
     * @param m_id
     * @param grade
     * @return
     */
    BaseMessage setpw(String m_id,Integer grade
                      ,HttpSession session);
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    BaseMessage login(String username,String password);
    /**
     * 忘记密码
     * @return
     */
    BaseMessage forgetPw(String phone);
}