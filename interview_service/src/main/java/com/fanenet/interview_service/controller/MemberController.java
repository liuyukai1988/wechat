package com.fanenet.interview_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanenet.interview_service.bean.MemberInfo;
import com.fanenet.interview_service.dao.MemberInfoRepository;
import com.fanenet.interview_service.service.WXAppletUserInfo;
import com.fanenet.interview_service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @version: V1.0
 * @description: 会员控制器
 * @author: Administrator
 **/
@RestController
public class MemberController {

    protected static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberInfoRepository memberInfoRepository;

    @Autowired
    private WXAppletUserInfo wxAppletUserInfo;

    /**
     * 查询所有会员
     *
     * @return JSONObject 返回值
     */
    @GetMapping(path="/getallmember")
    @ResponseBody
    public JSONObject getAllMemberInfo() {
        JSONObject json = new JSONObject();
        Iterable<MemberInfo> obj = memberInfoRepository.findAll();
        if(null != obj){
            long size = obj.spliterator().getExactSizeIfKnown();
            CommonUtil.setMsgObject(json, 0, "get all member success",obj,size);
        }else{
            CommonUtil.setMsgObject(json, 1, "get all member failure",null,0);
        }
        return json;
    }

    /**
     * 删除会员
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/deletemember")
    @ResponseBody
    public JSONObject DeleteMember(@RequestParam("id") int id) {
        JSONObject json = new JSONObject();
        memberInfoRepository.deleteById(id);
        CommonUtil.setMsgObject(json, 200, "delete member success",id,0);
        return json;
    }

    /**
     * 保存会员
     *
     * @return JSONObject 返回值
     */
    @PostMapping(path="/savemember")
    @ResponseBody
    public JSONObject AddMember(@RequestParam("openId") String openId, @RequestParam("nickName") String nickName, @RequestParam("avatarUrl") String avatarUrl, @RequestParam("gender") int gender, @RequestParam("country") String country, @RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("language") String language, @RequestParam("cTime") String cTime, @RequestParam("mobile") String mobile) {
        JSONObject json = new JSONObject();
        MemberInfo memberInfo = memberInfoRepository.findByOpenId(openId);
        if(null == memberInfo){
            memberInfo = new MemberInfo();
            memberInfo.setCreateTime(Calendar.getInstance().getTime());
        }
        memberInfo.setOpenId(openId);
        memberInfo.setNickName(nickName);
        memberInfo.setAvatarUrl(avatarUrl);
        memberInfo.setGender(gender);
        memberInfo.setProvince(province);
        memberInfo.setCountry(country);
        memberInfo.setCity(city);
        memberInfo.setLanguage(language);
        if(!"undefined".equals(cTime) && !"".equals(cTime)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 指定格式
            try {
                memberInfo.setcTime(simpleDateFormat.parse(cTime));
            } catch (ParseException e) {
                logger.error("simpleDateFormat ParseException",e);
            }
        }
        memberInfo.setMobile(mobile);
        MemberInfo obj = memberInfoRepository.save(memberInfo);
        if(null != obj){
            CommonUtil.setMsgObject(json, 200, "save member success",obj,0);
        }else{
            CommonUtil.setMsgObject(json, 201, "save member failure",null,0);
        }
        return json;
    }

    /**
     * 根据code获取openId,sessionKey,unionId
     *
     * @return JSONObject 返回值
     */
    @GetMapping(path="/getsessionkeyoropenid")
    @ResponseBody
    public JSONObject getSessionKeyOrOpenid(@RequestParam("code") String code) {
        JSONObject json = wxAppletUserInfo.getSessionKeyOropenid(code);
        logger.info("MemberController getSessionKeyOrOpenid="+JSONObject.toJSONString(json));
        return json;
    }


}
