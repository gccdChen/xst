package cn.scau.scaubook.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
/**
 * 
 * @author gccd
 *
 */
@Table("t_major")
public class Major {
    @Id
    private long id;
    private String name;
    /**
     * 专业号
     */
    private String mnum;
    public long c_id;
    @One(target = College.class, field = "c_id")
    private College college;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMnum() {
        return mnum;
    }
    public void setMnum(String mnum) {
        this.mnum = mnum;
    }
    public College getCollege() {
        return college;
    }
    public void setCollege(College college) {
        this.college = college;
    }
    public long getC_id() {
        return c_id;
    }
    public void setC_id(long c_id) {
        this.c_id = c_id;
    }
    
    
}
