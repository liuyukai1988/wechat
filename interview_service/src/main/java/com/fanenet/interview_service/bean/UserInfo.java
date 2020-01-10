package com.fanenet.interview_service.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * @version: V1.0
 * @description: 账号实体类
 * @author: Administrator
 **/
@Entity()
@Table(name="user")
public class UserInfo {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /**
     * 用户名
     */
    @Column(name="UserName")
    private String userName;

    /**
     * 密码
     */
    @Column(name="PassWord")
    private String passWord;

    /**
     * 状态 0-可用，1-不可用
     */
    @Column(name="State")
    private String state;

    /**
     * 创建时间
     */
    @Column(name="CreateTime")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
