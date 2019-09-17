package com.phonecard.dao;

import com.phonecard.bean.CashOut;
import com.phonecard.util.PageObject;
import com.phonecard.vo.CashOutVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CashOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashOut record);

    int insertSelective(CashOut record);

    CashOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashOut record);

    int updateByPrimaryKey(CashOut record);

    int getStateRow(@Param("pageObject") PageObject pageObject);

    List<CashOutVo> selectStateList(@Param("pageObject")PageObject pageObject);
}