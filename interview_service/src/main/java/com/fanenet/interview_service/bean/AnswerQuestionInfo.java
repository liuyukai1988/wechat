package com.fanenet.interview_service.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version: V1.0
 * @description: 试题实体信息
 * @author: Administrator
 **/
@Entity()
@Table(name="answer_question")
public class AnswerQuestionInfo {
    /**
     * 试题ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /**
     * 分类（1-架构师；）
     */
    @Column(name="Type")
    private int type;

    /**
     * 标题
     */
    @Column(name="Title")
    private String title;

    /**
     * 答案1
     */
    @Column(name="Answer1")
    private String answer1;

    /**
     * 答案2
     */
    @Column(name="Answer2")
    private String answer2;

    /**
     * 答案3
     */
    @Column(name="Answer3")
    private String answer3;

    /**
     * 浏览次数
     */
    @Column(name="ViewCount")
    private int viewCount;

    /**
     * 创建时间
     */
    @Column(name="CreateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
