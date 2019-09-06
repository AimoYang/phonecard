package com.phonecard.dao;

import com.phonecard.bean.GoodsCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsCart record);

    int insertSelective(GoodsCart record);

    GoodsCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsCart record);

    int updateByPrimaryKey(GoodsCart record);
}