package com.phonecard.dao;

import com.phonecard.bean.FloorBind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FloorBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FloorBind record);

    int insertSelective(FloorBind record);

    FloorBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FloorBind record);

    int updateByPrimaryKey(FloorBind record);

    FloorBind selectByFloorAndGoods(Integer floorId, String uuid);
}