package com.phonecard.dao;

import com.phonecard.bean.GoodsStar;

public interface GoodsStarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsStar record);

    int insertSelective(GoodsStar record);

    GoodsStar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsStar record);

    int updateByPrimaryKey(GoodsStar record);
}