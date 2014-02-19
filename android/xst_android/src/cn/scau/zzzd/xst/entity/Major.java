package cn.scau.zzzd.xst.entity;

import cn.scau.zzzd.xst.base.BaseModel;

/**
 * 
 * @author gccd
 *
 */
public class Major extends BaseModel{
    private long id;
    private String name;
    /**
     * 专业号
     */
    private String mnum;
    public long c_id;
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
