package com.phonecard.dao;

import com.phonecard.bean.Kefu;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface KefuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Kefu record);

    int insertSelective(Kefu record);

    Kefu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Kefu record);

    int updateByPrimaryKey(Kefu record);

    int getCountKefuSum();

    List<Kefu> getKefuList(PageObject pageObject);
}