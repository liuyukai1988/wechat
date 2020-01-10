package com.fanenet.interview_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanenet.interview_service.bean.UserInfo;
import com.fanenet.interview_service.dao.UserInfoRepository;
import com.fanenet.interview_service.util.CommonUtil;
import com.fanenet.interview_service.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Optional;

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

    /**
     * 查询所有账号
     *
     * @return JSONObject 返回值
     */
    @GetMapping(path="/getalluser")
    @ResponseBody
    public JSONObject getAllMemberInfo() {
        JSONObject json = new JSONObject();
        Iterable<UserInfo> obj = userInfoRepository.findAll();
        if(null != obj){
            long size = obj.spliterator().getExactSizeIfKnown();
            CommonUtil.setMsgObject(json, 0, "get all user success",obj,size);
        }else{
            CommonUtil.setMsgObject(json, 1, "get all user failure",null,0);
        }
        return json;
    }

    /**
     * 保存账号
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/saveuser")
    @ResponseBody
    public JSONObject AddUser(@RequestParam("id") int id, @RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("state") String state) {
        JSONObject json = new JSONObject();
        Optional userInfoOptional = userInfoRepository.findById(id);
        UserInfo userInfo = null;
        if(null != userInfoOptional){
            userInfo = new UserInfo();
            userInfo.setCreateTime(Calendar.getInstance().getTime());
        }
        userInfo.setUserName(userName);
        userInfo.setPassWord(EncryptUtil.getInstance().MD5(passWord));
        userInfo.setState(state);
        UserInfo obj = userInfoRepository.save(userInfo);
        if(null != obj){
            CommonUtil.setMsgObject(json, 200, "save user success",obj,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "save user failure",null,0);
        }
        return json;
    }

    /**
     * 删除账号
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/deleteuser")
    @ResponseBody
    public JSONObject DeleteUser(@RequestParam("id") int id) {
        JSONObject json = new JSONObject();
        userInfoRepository.deleteById(id);
        CommonUtil.setMsgObject(json, 200, "delete user success",id,0);
        return json;
    }
}
