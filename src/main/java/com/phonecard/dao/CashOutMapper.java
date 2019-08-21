package com.phonecard.dao;

import com.phonecard.bean.CashOut;

public interface CashOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashOut record);

    int insertSelective(CashOut record);

    CashOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashOut record);

    int updateByPrimaryKey(CashOut record);
}