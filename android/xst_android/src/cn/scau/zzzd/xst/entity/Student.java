package cn.scau.zzzd.xst.entity;

/**
 * 学生身份
 * @author gccd
 *
 */
public class Student {
    private long id;
    /**
     * 学号
     */
    private String snum;
    /**
     * 年级
     */
    private int grade;
    private long m_id;
    private Major major;

    public Student() {
        // TODO Auto-generated constructor stub
    }
    
    public Student(long id) {
        super();
        this.id = id;
    }

    public Student( String snum, int grade, long m_id) {
        super();
        this.snum = snum;
        this.grade = grade;
        this.m_id = m_id;
    }

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
