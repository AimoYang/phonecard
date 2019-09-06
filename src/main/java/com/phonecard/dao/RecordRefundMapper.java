package com.phonecard.dao;

import com.phonecard.bean.RecordRefund;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRefundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordRefund record);

    int insertSelective(RecordRefund record);

    RecordRefund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordRefund record);

    int updateByPrimaryKey(RecordRefund record);
}