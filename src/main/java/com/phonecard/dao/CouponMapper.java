package com.phonecard.dao;

import com.phonecard.bean.Coupon;
import com.phonecard.util.PageObject;

import java.util.List;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    int getCountCouponSum();

    List<Coupon> getCouponList(PageObject pageObject, String couponName);
}