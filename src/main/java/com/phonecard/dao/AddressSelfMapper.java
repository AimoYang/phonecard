package com.phonecard.dao;

import com.phonecard.bean.AddressSelf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressSelfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressSelf record);

    int insertSelective(AddressSelf record);

    AddressSelf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressSelf record);

    int updateByPrimaryKey(AddressSelf record);
}