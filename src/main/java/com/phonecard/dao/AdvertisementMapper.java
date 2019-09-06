package com.phonecard.dao;

import com.phonecard.bean.Advertisement;
import com.phonecard.util.PageObject;
import com.phonecard.vo.AdvertisementVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);

    List<AdvertisementVo> selectList(@Param("pageObject") PageObject pageObject);

    int getAdsListRow(@Param("pageObject")PageObject pageObject);
}