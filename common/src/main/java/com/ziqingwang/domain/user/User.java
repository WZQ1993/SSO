package com.ziqingwang.domain.user;

import javax.persistence.*;

/**
 * Created by ziqingwang on 2016/10/8.
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    //数据表字段命名：user_name
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column
    private int age;
    @Column
    private String info;
    @Column
    private String pic;
    @Column
    private String school;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
