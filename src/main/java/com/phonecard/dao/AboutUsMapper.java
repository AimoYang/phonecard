package com.phonecard.dao;

import com.phonecard.bean.AboutUs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AboutUsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AboutUs record);

    int insertSelective(AboutUs record);

    AboutUs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AboutUs record);

    int updateByPrimaryKeyWithBLOBs(AboutUs record);
}