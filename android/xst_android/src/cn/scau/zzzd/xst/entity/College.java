package cn.scau.zzzd.xst.entity;


/**
 * 学院
 * @author gccd
 *
 */
public class College {
    private long id;
    private String name;
    /**
     * 学院号
     */
    private String cnum;
    public long school_id;
    private School school;
    
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
    public String getCnum() {
        return cnum;
    }
    public void setCnum(String cnum) {
        this.cnum = cnum;
    }
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public long getSchool_id() {
        return school_id;
    }
    public void setSchool_id(long school_id) {
        this.school_id = school_id;
    }

    
    
}
