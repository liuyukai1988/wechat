package com.fanenet.interview_service.dao;

import com.fanenet.interview_service.bean.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @version: V1.0
 * @description: 账号接口Dao
 * @author: Administrator
 **/
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo findByUserNameAndPassWord(String username, String password);
}
