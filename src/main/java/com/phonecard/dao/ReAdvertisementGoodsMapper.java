package com.phonecard.dao;

import com.phonecard.bean.ReAdvertisementGoods;

public interface ReAdvertisementGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReAdvertisementGoods record);

    int insertSelective(ReAdvertisementGoods record);

    ReAdvertisementGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReAdvertisementGoods record);

    int updateByPrimaryKey(ReAdvertisementGoods record);
}