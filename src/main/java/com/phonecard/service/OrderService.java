package com.phonecard.service;

import com.phonecard.bean.OrderDto;
import com.phonecard.dao.ProductOrderDetailMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductOrderDetailMapper orderMapper;


    public List<OrderDto> findOrderByPage(PageObject pageObject, String orderNo, Integer fetchType, String leaderNickName, Date startTime, Date endTime) {
        List<OrderDto> list = orderMapper.findOrderByPage(pageObject, orderNo, fetchType, leaderNickName, startTime, endTime);
        return list;
    }
}
