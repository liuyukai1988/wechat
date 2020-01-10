package com.fanenet.interview_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanenet.interview_service.bean.UserInfo;
import com.fanenet.interview_service.dao.UserInfoRepository;
import com.fanenet.interview_service.util.CommonUtil;
import com.fanenet.interview_service.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @version: V1.0
 * @description: 用户控制器
 * @author: Administrator
 **/
@RestController
public class UserController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private HttpSession session;

    @PostMapping(value = "/login")
    public JSONObject findByUsernameAndPassword(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        JSONObject json = new JSONObject();
        String userpass = EncryptUtil.getInstance().MD5(password);
        logger.info(userpass);
        UserInfo userinfo = userInfoRepository.findByUserNameAndPassWord(username,userpass);
        if(null != userinfo){
            //添加sessionKey
            session.setAttribute("name", username);
            CommonUtil.setMsgObject(json, 200, "user login success",userinfo,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "user login failure",null,0);
        }
        return json;
    }
}
