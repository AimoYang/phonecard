package com.phonecard.dao;

import com.phonecard.bean.ProductOrderDetail;
import com.phonecard.util.PageObject;
import com.phonecard.vo.CancelOrdersDetailVo;
import com.phonecard.vo.ProductOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrderDetail record);

    int insertSelective(ProductOrderDetail record);

    ProductOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrderDetail record);

    int updateByPrimaryKey(ProductOrderDetail record);

    int getOrdersListRow(@Param("pageObject")PageObject pageObject);

    List<ProductOrderVo> selectOrdersList(@Param("pageObject")PageObject pageObject);

    ProductOrderVo selectByOrderUuid(Integer id);

    List<CancelOrdersDetailVo> selectCancelOrders(@Param("pageObject")PageObject pageObject);

    int getCancelOrdersRow(@Param("pageObject") PageObject pageObject);

    ProductOrderDetail selectByUuid(@Param("orderUuid") String orderUuid);

    CancelOrdersDetailVo cancelOrdersDetailSelect(@Param("orderUuid")String orderUuid);
}