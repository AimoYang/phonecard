package com.phonecard.dao;

import com.phonecard.bean.TourList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TourListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TourList record);

    int insertSelective(TourList record);

    TourList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TourList record);

    int updateByPrimaryKey(TourList record);
}