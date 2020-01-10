package com.fanenet.interview_service.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @version: V1.0
 * @description: 试题类型信息
 * @author: Administrator
 **/
@Entity()
@Table(name="answer_type")
public class AnswerTypeInfo {
    /**
     * 试题ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /**
     * 父级类型ID
     */
    @Column(name="PID")
    private int pid;

    /**
     * 类型名称
     */
    @Column(name="TypeName")
    private String typeName;

    /**
     * 类型排序
     */
    @Column(name="TypeOrder")
    private int typeOrder;

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(int typeOrder) {
        this.typeOrder = typeOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
