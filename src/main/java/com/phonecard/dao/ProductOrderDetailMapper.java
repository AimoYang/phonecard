package com.phonecard.dao;

import com.phonecard.bean.ProductOrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrderDetail record);

    int insertSelective(ProductOrderDetail record);

    ProductOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrderDetail record);

    int updateByPrimaryKey(ProductOrderDetail record);
}