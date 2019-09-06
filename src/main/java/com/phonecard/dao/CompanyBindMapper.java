package com.phonecard.dao;

import com.phonecard.bean.CompanyBind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyBind record);

    int insertSelective(CompanyBind record);

    CompanyBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyBind record);

    int updateByPrimaryKey(CompanyBind record);
}