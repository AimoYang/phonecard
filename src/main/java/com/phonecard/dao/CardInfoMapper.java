package com.phonecard.dao;

import com.phonecard.bean.CardInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    CardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);
}