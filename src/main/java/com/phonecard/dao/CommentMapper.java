package com.phonecard.dao;

import com.phonecard.bean.Comment;
import com.phonecard.bean.CommentDto;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findAllComment();

    List<Comment> getCommentList(@Param("pageObject") PageObject pageObject);

    int getCountCommentSum(@Param("pageObject") PageObject pageObject);

    CommentDto getCommentDetail(Integer id);
}