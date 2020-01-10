package com.fanenet.interview_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanenet.interview_service.bean.AnswerTypeInfo;
import com.fanenet.interview_service.dao.AnswerTypeInfoRepository;
import com.fanenet.interview_service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.CalendarDate;

import java.util.Calendar;

/**
 * @version: V1.0
 * @description: 类型控制器
 * @author: Administrator
 **/
@RestController
public class TypeController {

    protected static final Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private AnswerTypeInfoRepository answerTypeInfoRepository;

    @RequestMapping(value="/type")
    public String type(){
        System.out.println("redirect to type page!");
        return "type";
    }

    /**
     * 查询所有类型
     *
     * @return Iterable<AnswerTypeInfo> 返回所有类型
     */
    @GetMapping(path="/getalltype")
    @ResponseBody
    public JSONObject getAllAnswerType() {
        JSONObject json = new JSONObject();
        Iterable<AnswerTypeInfo> obj = answerTypeInfoRepository.findAll();
        if(null != obj){
            long size = obj.spliterator().getExactSizeIfKnown();
            CommonUtil.setMsgObject(json, 0, "get all type success",obj,size);
        }else{
            CommonUtil.setMsgObject(json, 1, "get all type failure",null,0);
        }
        return json;
    }

    /**
     * 保存类型
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/savetype")
    @ResponseBody
    public JSONObject AddType(@RequestParam("pid") int pid,@RequestParam("typeName") String typeName,@RequestParam("typeOrder") int typeOrder) {
        JSONObject json = new JSONObject();
        AnswerTypeInfo answerTypeInfo = new AnswerTypeInfo();
        answerTypeInfo.setPid(pid);
        answerTypeInfo.setTypeName(typeName);
        answerTypeInfo.setTypeOrder(typeOrder);
        answerTypeInfo.setCreateTime(Calendar.getInstance().getTime());
        AnswerTypeInfo obj = answerTypeInfoRepository.save(answerTypeInfo);
        if(null != obj){
            CommonUtil.setMsgObject(json, 200, "save type success",obj,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "save type failure",null,0);
        }
        return json;
    }

    /**
     * 删除类型
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/deletetype")
    @ResponseBody
    public JSONObject DeleteType(@RequestParam("id") int id) {
        JSONObject json = new JSONObject();
        answerTypeInfoRepository.deleteById(id);
        CommonUtil.setMsgObject(json, 200, "delete type success",id,0);
        return json;
    }

    /**
     * 修改类型
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/edittype")
    @ResponseBody
    public JSONObject EditType(@RequestParam("id") int id,@RequestParam("pid") int pid,@RequestParam("typeName") String typeName,@RequestParam("typeOrder") int typeOrder) {
        JSONObject json = new JSONObject();
        AnswerTypeInfo answerTypeInfo = answerTypeInfoRepository.findById(id).get();
        answerTypeInfo.setPid(pid);
        answerTypeInfo.setTypeName(typeName);
        answerTypeInfo.setTypeOrder(typeOrder);
        AnswerTypeInfo obj = answerTypeInfoRepository.save(answerTypeInfo);
        if(null != obj){
            CommonUtil.setMsgObject(json, 200, "edit type success",obj,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "edit type failure",null,0);
        }
        return json;
    }
}
