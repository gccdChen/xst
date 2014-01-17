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
@Table("t_sell")
public class Sell {
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
    private List<Sellitem> idleitems;
    /**
     * 可以是视频介绍
     */
    private String introduce;
    /**
     * 图片介绍
     */
    private String imgintro;
    /**
     * 文字介绍
     * @return
     */
    private String strintro;
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
    public List<Sellitem> getIdleitems() {
        return idleitems;
    }
    public void setIdleitems(List<Sellitem> idleitems) {
        this.idleitems = idleitems;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getIntroduce() {
        return introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getImgintro() {
        return imgintro;
    }
    public void setImgintro(String imgintro) {
        this.imgintro = imgintro;
    }
    public String getStrintro() {
        return strintro;
    }
    public void setStrintro(String strintro) {
        this.strintro = strintro;
    }
    
}
