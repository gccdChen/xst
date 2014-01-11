package cn.scau.scaubook.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 学院
 * @author gccd
 *
 */
@Table("t_college")
public class College {
    @Id
    private long id;
    private String name;
    /**
     * 学院号
     */
    private String cnum;
    public long school_id;
    @One(target = School.class, field = "school_id")
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
