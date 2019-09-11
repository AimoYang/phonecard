package com.phonecard.dao;

import com.phonecard.bean.State;
import org.apache.ibatis.annotations.Mapper;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);

    int getCountStateSum();

    List<State> getStateRecommedList(@Param("pageObject") PageObject pageObject);

    List<State> getStateHotList(@Param("pageObject") PageObject pageObject);
}