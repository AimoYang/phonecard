package com.phonecard.dao;

import com.phonecard.bean.Kefu;

public interface KefuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Kefu record);

    int insertSelective(Kefu record);

    Kefu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Kefu record);

    int updateByPrimaryKey(Kefu record);
}