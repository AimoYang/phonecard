package com.phonecard.dao;

import com.phonecard.bean.ReAdvertisementGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReAdvertisementGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReAdvertisementGoods record);

    int insertSelective(ReAdvertisementGoods record);

    ReAdvertisementGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReAdvertisementGoods record);

    int updateByPrimaryKey(ReAdvertisementGoods record);

    ReAdvertisementGoods selectByAdsAndGoods(@Param("adsId") Integer adsId, @Param("goodsUuid") String goodsUuid);
}