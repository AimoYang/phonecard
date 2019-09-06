package com.phonecard.dao;

import com.phonecard.bean.State;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);
}