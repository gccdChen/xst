package cn.scau.scaubook.entity;

import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 发布信息
 * @author gccd
 *
 */
@Table("t_idlesell")
public class IdleSell {
    @Id
    private long id;
    private long sellerid;
    @One(target=User.class,field="sellerid")
    private User seller;
    private String phone;
    private float price;
    /**
     * 0 正常 1下架/已售
     */
    private int state;
    /**
     * 浏览量
     */
    private int viewtimes;
    /**
     * 上架日期
     * 毫秒级
     */
    @ColDefine(width = 13)
    private long createtime;
    private List<Idleitem> idleitems;
    /**
     * 0不打包 1打包
     */
    private int isPackage = 0;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getSellerid() {
        return sellerid;
    }
    public void setSellerid(long sellerid) {
        this.sellerid = sellerid;
    }
    public User getSeller() {
        return seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getViewtimes() {
        return viewtimes;
    }
    public void setViewtimes(int viewtimes) {
        this.viewtimes = viewtimes;
    }
    public long getCreatetime() {
        return createtime;
    }
    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
    public List<Idleitem> getIdleitems() {
        return idleitems;
    }
    public void setIdleitems(List<Idleitem> idleitems) {
        this.idleitems = idleitems;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
}
