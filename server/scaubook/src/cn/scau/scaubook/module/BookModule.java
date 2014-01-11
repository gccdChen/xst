package cn.scau.scaubook.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;

import cn.scau.scaubook.base.BaseMessage;

@IocBean
@At("/book")
public class BookModule {
    private static final Log log = Logs.get();
    
    @Inject
    private Dao dao = null;
    
}
interface BookModuleApi{
    /**
     * 书本详情
     * @return
     */
    BaseMessage getDetail();
    /**
     * 搜索
     * @param keyword
     * @param pageNo
     * @return
     */
    BaseMessage search(String keyword,int pageNo);
    /**
     * 根据专业和年级获取课本
     * @param mid
     * @param grade
     * @return
     */
    BaseMessage getByMid(long mid,int grade);
    /**
     * 根据专业和年级获取课本
     * @param mid
     * @param grade
     * @return
     */
    BaseMessage getByMids(long[] mid,int[] grade);
}