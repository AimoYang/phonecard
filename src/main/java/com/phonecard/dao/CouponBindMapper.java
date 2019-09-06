package com.phonecard.dao;

import com.phonecard.bean.CouponBind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponBind record);

    int insertSelective(CouponBind record);

    CouponBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponBind record);

    int updateByPrimaryKey(CouponBind record);
}