package com.phonecard.service;

import com.phonecard.bean.OrderDto;
import com.phonecard.bean.RecordRefund;
import com.phonecard.bean.RefundOrderDto;
import com.phonecard.dao.RecordRefundMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RefundService {

    @Autowired
    private RecordRefundMapper recordRefund;

    public List<RefundOrderDto> findOrderByPage(PageObject pageObject) {
        List<RefundOrderDto> list = recordRefund.findRefundOrderByPage(pageObject);
        return list;
    }
}
