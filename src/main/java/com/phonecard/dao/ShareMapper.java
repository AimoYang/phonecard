package com.phonecard.dao;

import com.phonecard.bean.Share;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
}