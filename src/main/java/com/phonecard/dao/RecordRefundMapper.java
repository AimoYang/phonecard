package com.phonecard.dao;

import com.phonecard.bean.RecordRefund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecordRefundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordRefund record);

    int insertSelective(RecordRefund record);

    RecordRefund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordRefund record);

    int updateByPrimaryKey(RecordRefund record);

    void updateRefuseCancel(@Param("orderUuid") String orderUuid);

    RecordRefund selectByOrdersUuid(@Param("orderUuid") String orderUuid);

    RecordRefund selectByOutRefundNo(@Param("outRefundNo") String outRefundNo);
}