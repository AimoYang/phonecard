package com.phonecard.dao;

import com.phonecard.bean.Income;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    void updateByOpenId(Income income);
}