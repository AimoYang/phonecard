package com.phonecard.dao;

import com.phonecard.bean.Sku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);

    Sku selectMinSkuByGoods(String uuid);
}