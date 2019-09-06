package com.phonecard.dao;

import com.phonecard.bean.TourListBind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TourListBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TourListBind record);

    int insertSelective(TourListBind record);

    TourListBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TourListBind record);

    int updateByPrimaryKey(TourListBind record);
}