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

}
