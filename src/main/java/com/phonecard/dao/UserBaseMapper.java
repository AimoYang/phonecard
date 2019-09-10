package com.phonecard.dao;

import com.phonecard.bean.UserBase;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBase record);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKey(UserBase record);

    int getCountCouponSum();

    List<UserBase> getCouponList(@Param("pageObject") PageObject pageObject,@Param("nickName") String nickName,@Param("leaderName") String leaderName);
}