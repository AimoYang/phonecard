package com.phonecard.dao;

import com.phonecard.bean.GoodsStar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsStarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsStar record);

    int insertSelective(GoodsStar record);

    GoodsStar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsStar record);

    int updateByPrimaryKey(GoodsStar record);
}