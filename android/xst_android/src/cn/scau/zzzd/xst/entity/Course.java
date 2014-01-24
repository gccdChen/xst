package cn.scau.zzzd.xst.entity;


/**
 * 课程
 * @author gccd
 *
 */
public class Course {
    private long id;
    private String name;
    /**
     *  1 大一 2大二 3大三 4....
     */
    private int grade;
    /**
     * 0公选 1必修 2选修
     */
    private int type;
    private long m_id;
    private Major major;
    private long b_id;
    private Book book;
    
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
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Major getMajor() {
        return major;
    }
    public void setMajor(Major major) {
        this.major = major;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getM_id() {
        return m_id;
    }
    public void setM_id(long m_id) {
        this.m_id = m_id;
    }
    public long getB_id() {
        return b_id;
    }
    public void setB_id(long b_id) {
        this.b_id = b_id;
    }
    
    
}
