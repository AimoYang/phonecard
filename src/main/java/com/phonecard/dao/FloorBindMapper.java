package com.phonecard.dao;

import com.phonecard.bean.FloorBind;
import com.phonecard.bean.ReAdvertisementGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FloorBindMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(FloorBind record);

    int insertSelective(FloorBind record);

    FloorBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FloorBind record);

    int updateByPrimaryKey(FloorBind record);

    FloorBind selectByFloorAndGoods(@Param("floorId") Integer floorId, @Param("uuid")String uuid);

    List<FloorBind> selectByGoods(@Param("uuid") String uuid);
}