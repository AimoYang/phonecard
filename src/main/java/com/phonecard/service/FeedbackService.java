package com.phonecard.service;

import com.phonecard.bean.Comment;
import com.phonecard.bean.Feedback;
import com.phonecard.dao.FeedbackMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public Map<String, Object> getCommentList(PageObject pageObject) {
        pageObject.setRowCount(feedbackMapper.getCountFeedbackSum());
        List<Feedback> list = feedbackMapper.getFeedbackList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public boolean handleFeedback(Integer id) {
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        if (feedback != null) {
            feedback.setFeedType((short) 1);
            int row = feedbackMapper.updateByPrimaryKey(feedback);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Map<String, Object> feedbackDetail(Integer id) {
        Map<String, Object> feedback = feedbackMapper.findFeedbackById(id);
        if (feedback != null) {
            return feedback;
        } else {
            return null;
        }
    }

    public boolean feedbackDelete(int id) {
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        if (feedback != null){
            feedback.setIsDelete((short) 1);
            int row = feedbackMapper.updateByPrimaryKey(feedback);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
