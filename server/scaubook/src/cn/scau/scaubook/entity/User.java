package cn.scau.scaubook.entity;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("tb_user")
public class User{
    @Id
    private long id;
    @Name
    private String username;
    private String password;
    private String nickname;
    /**
     * 0 秘密 1 男 2女
     */
    private int sex = 0;
    private long stu_id;
    @One(target=Student.class,field="stu_id")
    private Student student;
    private long secstu_id;
    /**
     * 第二专业..
     */
    @One(target=Student.class,field="secstu_id")
    private Student secstu;
    private String phone;
    /**
     * 短号
     */
    private String sphone;
    public User() {
        // TODO Auto-generated constructor stub
    }
    
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getStu_id() {
        return stu_id;
    }

    public void setStu_id(long stu_id) {
        this.stu_id = stu_id;
    }

    public long getSecstu_id() {
        return secstu_id;
    }

    public void setSecstu_id(long secstu_id) {
        this.secstu_id = secstu_id;
    }

    public Student getSecstu() {
        return secstu;
    }

    public void setSecstu(Student secstu) {
        this.secstu = secstu;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }
    
    
}
