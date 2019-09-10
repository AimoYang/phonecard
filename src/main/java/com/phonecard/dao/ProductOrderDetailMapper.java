package com.phonecard.dao;

import com.phonecard.bean.OrderDto;
import com.phonecard.bean.ProductOrderDetail;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrderDetail record);

    int insertSelective(ProductOrderDetail record);

    ProductOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrderDetail record);

    int updateByPrimaryKey(ProductOrderDetail record);

    List<OrderDto> findOrderByPage(@Param("pageObject") PageObject pageObject,
                                   @Param("orderNo") String orderNo,
                                   @Param("fetchType") Integer fetchType,
                                   @Param("leaderNickName") String leaderNickName,
                                   @Param("startTime")Date startTime, @Param("endTime") Date endTime);
}