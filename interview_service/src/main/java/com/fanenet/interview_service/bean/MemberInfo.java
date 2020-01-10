package com.fanenet.interview_service.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version: V1.0
 * @description: 会员实体类
 * @author: Administrator
 **/
@Entity()
@Table(name="member")
public class MemberInfo {
    /**
     * 会员ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /**
     * 小程序用户的openid
     */
    @Column(name="OpenId")
    private String openId;

    /**
     * 用户昵称
     */
    @Column(name="NickName")
    private String nickName;

    /**
     * 用户头像
     */
    @Column(name="AvatarUrl")
    private String avatarUrl;

    /**
     * 性别  0-男、1-女
     */
    @Column(name="Gender")
    private int gender;

    /**
     * 所在国家
     */
    @Column(name="Country")
    private String country;

    /**
     * 省份
     */
    @Column(name="Province")
    private String province;

    /**
     * 城市
     */
    @Column(name="City")
    private String city;

    /**
     * 语种
     */
    @Column(name="Language")
    private String language;

    /**
     * 创建/注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="CTime")
    private Date cTime;

    /**
     * 手机号码
     */
    @Column(name="Mobile")
    private String mobile;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="CreateTime")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
