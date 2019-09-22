package com.phonecard.dao;

import com.phonecard.bean.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);

    Sku selectMinSkuByGoods(@Param("uuid") String uuid);

    List<Sku> selectByGoodsUuid(@Param("uuid")String uuid);

    void updateDelete(@Param("uuid") String uuid);
}