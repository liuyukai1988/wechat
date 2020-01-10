package com.fanenet.interview_service.dao;

import com.fanenet.interview_service.bean.MemberInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @version: V1.0
 * @description: 会员接口Dao
 * @author: Administrator
 **/
public interface MemberInfoRepository extends CrudRepository<MemberInfo, Integer> {

    MemberInfo findByOpenId(String openId);
}
