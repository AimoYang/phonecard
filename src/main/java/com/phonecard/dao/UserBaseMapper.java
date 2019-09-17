package com.phonecard.dao;

import com.phonecard.bean.UserBase;
import org.apache.ibatis.annotations.Mapper;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBase record);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKey(UserBase record);

    int getCountCouponSum(@Param("pageObject") PageObject pageObject);

    List<UserBase> getCouponList(@Param("pageObject") PageObject pageObject);

    UserBase selectByOpenId(String openId);
}