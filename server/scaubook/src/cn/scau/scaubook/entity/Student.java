package cn.scau.scaubook.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
/**
 * 学生身份
 * @author gccd
 *
 */
@Table("t_student")
public class Student {
    @Id
    private long id;
    /**
     * 学号
     */
    private String snum;
    /**
     * 年级
     */
    private int grade;
    public long m_id;
    @One(target=Major.class,field="m_id")
    private Major major;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }
    
}
