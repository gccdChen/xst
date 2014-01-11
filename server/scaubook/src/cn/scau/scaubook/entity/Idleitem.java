package cn.scau.scaubook.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
/**
 * 发布信息项
 * @author gccd
 *
 */
@Table("t_idleitem")
public class Idleitem {
    @Id
    private long id;
    private long bookid;
    @One(target=Book.class,field="bookid")
    private Book goods;
    /**
     * 成色 保留
     */
    private int fresh = 0;
    /**
     * 卖家留言
     */
    private String note;
    /**
     * 照片数
     */
    private int imagenum;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getBookid() {
        return bookid;
    }
    public void setBookid(long bookid) {
        this.bookid = bookid;
    }
    public Book getGoods() {
        return goods;
    }
    public void setGoods(Book goods) {
        this.goods = goods;
    }
    public int getFresh() {
        return fresh;
    }
    public void setFresh(int fresh) {
        this.fresh = fresh;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getImagenum() {
        return imagenum;
    }
    public void setImagenum(int imagenum) {
        this.imagenum = imagenum;
    }
    
}