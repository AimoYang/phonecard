package com.phonecard.dao;

import com.phonecard.bean.Floor;
import com.phonecard.util.PageObject;
import com.phonecard.vo.FloorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FloorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);

    FloorVo selectFloorDetail(@Param("id") Integer id);

    Integer getFloorListRow(@Param("pageObject") PageObject pageObject);

    List<FloorVo> selectList(@Param("pageObject")PageObject pageObject);
}