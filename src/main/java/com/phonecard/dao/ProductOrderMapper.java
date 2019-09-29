package com.phonecard.dao;

import com.phonecard.bean.ProductOrder;
import com.phonecard.util.PageObject;
import com.phonecard.vo.OrderExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    ProductOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);

    ProductOrder selectProductOrderuuid(@Param("orderUuid") String orderUuid);

    List<OrderExcelVo> postOrderByCondition(@Param("pageObject") PageObject pageObject);
}