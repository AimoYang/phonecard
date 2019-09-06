package com.phonecard.dao;

import com.phonecard.bean.ActivityGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGoods record);

    int insertSelective(ActivityGoods record);

    ActivityGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityGoods record);

    int updateByPrimaryKey(ActivityGoods record);
}