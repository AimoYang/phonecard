package com.phonecard.dao;

import com.phonecard.bean.AddressSelf;
import com.phonecard.util.PageObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AddressSelfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressSelf record);

    int insertSelective(AddressSelf record);

    AddressSelf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressSelf record);

    int updateByPrimaryKey(AddressSelf record);

    List<AddressSelf> findAllAddress();

    List<AddressSelf> getAddressList(@Param("pageObject") PageObject pageObject);

    int getCountAddressSum();

    int checkAirportIsExist(AddressSelf addressSelf);

    List<AddressSelf> findCityByAdAll(Integer id);
}