package cn.scau.scaubook.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import cn.scau.scaubook.base.BaseMessage;
import cn.scau.scaubook.clientmodel.Sell;

@IocBean
@At("/sell")
public class SellModule implements SellModuleApi{
    private static final Log log = Logs.get();
    
    @Inject
    private Dao dao = null;

    @Override
    public BaseMessage scan(String isbn13) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage sell(Sell[] sells) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage getSellsByUserid(long userid, int pageNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage getSellsByBooks(long[] bids, int pageNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage getMySells(int pageNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseMessage getSellsByMid(long mid, int grade, int pageNo) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
interface SellModuleApi{
    /**
     * 扫描条形码
     * @param isbn13
     * @return
     */
    BaseMessage scan(String isbn13);
    /**
     * 卖
     * @param sells
     * @returns
     */
    BaseMessage sell(@Param("sells")Sell[] sells);
    /**
     * 获取userid的出售列表
     * @param pageNo
     * @return
     */
    BaseMessage getSellsByUserid(long userid,int pageNo);
    /**
     * 获取自己的出售列表
     * @param pageNo
     * @return
     */
    BaseMessage getMySells(int pageNo);
    /**
     * 获取相关bookid的出售列表
     * @param bids
     * @param pageNo
     * @return
     */
    BaseMessage getSellsByBooks(long[] bids,int pageNo);
    /**
     * 对应一键搜教材
     *  获取相关mid的出售列表
     * @param mid
     * @param grade
     * @return
     */
    BaseMessage getSellsByMid(long mid,int grade,int pageNo);
}
