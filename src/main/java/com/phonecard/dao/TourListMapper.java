package com.phonecard.dao;

import com.phonecard.bean.TourList;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TourList record);

    int insertSelective(TourList record);

    TourList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TourList record);

    int updateByPrimaryKey(TourList record);

    int getTourRow(@Param("pageObject")PageObject pageObject);

    List<TourList> selectTourList(@Param("pageObject") PageObject pageObject);
}