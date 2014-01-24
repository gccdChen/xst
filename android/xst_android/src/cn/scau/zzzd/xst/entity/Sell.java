package cn.scau.zzzd.xst.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布信息
 * @author gccd
 *
 */
public class Sell {
    private long id;
    private long sellerid;
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
    private long createtime;
    private List<Sellitem> selltems;
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
    public Sell() {
		// TODO Auto-generated constructor stub
	}
    
    public Sell(Sellitem selltems) {
		super();
		this.selltems = new ArrayList<Sellitem>();
		this.selltems.add(selltems);
		this.price = selltems.getPrice();
	}

	public Sell(float price, List<Sellitem> selltems, String strintro) {
		super();
		this.price = price;
		this.selltems = selltems;
		this.strintro = strintro;
	}

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
    public List<Sellitem> getSelltems() {
        return selltems;
    }
    public void setSelltems(List<Sellitem> selltems) {
        this.selltems = selltems;
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
