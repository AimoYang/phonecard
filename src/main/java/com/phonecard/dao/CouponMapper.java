package com.phonecard.dao;

import com.phonecard.bean.Coupon;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    int getCountCouponSum(@Param("pageObject") PageObject pageObject);

    List<Coupon> getCouponList(@Param("pageObject") PageObject pageObject);
}