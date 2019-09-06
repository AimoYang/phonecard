package com.phonecard.dao;

import com.phonecard.bean.Turnover;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TurnoverMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Turnover record);

    int insertSelective(Turnover record);

    Turnover selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Turnover record);

    int updateByPrimaryKey(Turnover record);
}