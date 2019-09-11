package com.phonecard.dao;

import com.phonecard.bean.Feedback;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    int getCountFeedbackSum();

    List<Feedback> getFeedbackList(PageObject pageObject);
}