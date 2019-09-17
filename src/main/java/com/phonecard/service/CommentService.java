package com.phonecard.service;

import com.phonecard.bean.AddressSelf;
import com.phonecard.bean.Comment;
import com.phonecard.bean.CommentDto;
import com.phonecard.dao.AddressSelfMapper;
import com.phonecard.dao.CommentMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public Map<String, Object> getCommentList(PageObject pageObject) {
        pageObject.setRowCount(commentMapper.getCountCommentSum(pageObject));
        List<Comment> list = commentMapper.getCommentList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public CommentDto getCommentDetail(Integer id) {
        CommentDto comment = commentMapper.getCommentDetail(id);
        if (comment != null){
            return comment;
        }else {
            return null;
        }
    }

    public boolean deleteById(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (comment != null){
            comment.setIsDelete((short) 1);
            int row = commentMapper.updateByPrimaryKeySelective(comment);
            if (row > 0){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }

    }
}
