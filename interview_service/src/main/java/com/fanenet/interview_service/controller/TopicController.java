package com.fanenet.interview_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanenet.interview_service.bean.AnswerQuestionInfo;
import com.fanenet.interview_service.dao.AnswerQuestionInfoRepository;
import com.fanenet.interview_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

/**
 * @version: V1.0
 * @description: 试题控制器
 * @author: Administrator
 **/
@RestController
public class TopicController {

    @Autowired
    private AnswerQuestionInfoRepository answerQuestionRepository;

    /**
     * 查询所有面试题
     *
     * @return Iterable<AnswerQuestionInfo> 返回所有面试题
     */
    @GetMapping(path="/all")
    @ResponseBody
    public Iterable<AnswerQuestionInfo> getAllAnswerQuestion() {
        return answerQuestionRepository.findAll();
    }

    /**
     * 根据搜索值查询面试题信息
     *
     * @param title 查询关键字
     * @return Iterable<AnswerQuestionInfo> 返回查询面试题
     */
    @GetMapping(path="/getaqbytitle")
    @ResponseBody
    public Iterable<AnswerQuestionInfo> getAnswerQuestionByTitle(String title) {
        return answerQuestionRepository.findByTitleContaining(title);
    }

    /**
     * 查询所有面试题
     *
     * @return JSONObject 返回值
     */
    @GetMapping(path="/getalltopic")
    @ResponseBody
    public JSONObject getAllAnswerQuestionInfo() {
        JSONObject json = new JSONObject();
        Iterable<AnswerQuestionInfo> obj = answerQuestionRepository.findAll();
        if(null != obj){
            long size = obj.spliterator().getExactSizeIfKnown();
            CommonUtil.setMsgObject(json, 0, "get all answer question success",obj,size);
        }else{
            CommonUtil.setMsgObject(json, 1, "get all answer question failure",null,0);
        }
        return json;
    }

    /**
     * 保存试题
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/savetopic")
    @ResponseBody
    public JSONObject AddTopic(@RequestParam("id") int id,@RequestParam("type") int type, @RequestParam("title") String title, @RequestParam("answer1") String answer1, @RequestParam("answer2") String answer2, @RequestParam("answer3") String answer3) {
        JSONObject json = new JSONObject();
        AnswerQuestionInfo answerQuestionInfo = null;
        Optional<AnswerQuestionInfo> answerQuestionInfoBean = answerQuestionRepository.findById(id);
        if(answerQuestionInfoBean != null && answerQuestionInfoBean.isPresent()){
            answerQuestionInfo = answerQuestionInfoBean.get();
        }
        if(null == answerQuestionInfo){
            answerQuestionInfo = new AnswerQuestionInfo();
        }
        answerQuestionInfo.setType(type);
        answerQuestionInfo.setTitle(title);
        answerQuestionInfo.setAnswer1(answer1);
        answerQuestionInfo.setAnswer2(answer2);
        answerQuestionInfo.setAnswer3(answer3);
        answerQuestionInfo.setCreateTime(Calendar.getInstance().getTime());
        AnswerQuestionInfo obj = answerQuestionRepository.save(answerQuestionInfo);
        if(null != obj){
            CommonUtil.setMsgObject(json, 200, "save topic success",obj,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "save topic failure",null,0);
        }
        return json;
    }

    /**
     * 删除面试题
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/deletetopic")
    @ResponseBody
    public JSONObject DeleteTopic(@RequestParam("id") int id) {
        JSONObject json = new JSONObject();
        answerQuestionRepository.deleteById(id);
        CommonUtil.setMsgObject(json, 200, "delete topic success",id,0);
        return json;
    }

}
