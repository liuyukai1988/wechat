package com.fanenet.interview_service.dao;

import com.fanenet.interview_service.bean.AnswerQuestionInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @version: V1.0
 * @description: 面试接口Dao
 * @author: Administrator
 **/
public interface AnswerQuestionInfoRepository extends CrudRepository<AnswerQuestionInfo, Integer>{

    /**
     * 根据Title模糊查询
     *
     * @param title 标题
     * @return {@link List<AnswerQuestionInfo>}
     */
    List<AnswerQuestionInfo> findByTitleContaining(String title);
}
