package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.bean.OrderDto;
import com.phonecard.enums.OrderEnums;
import com.phonecard.service.OrderService;
import com.phonecard.service.RedisService;
import com.phonecard.util.DateUtil;
import com.phonecard.util.PageObject;
import com.phonecard.bean.ResultVO;
import com.phonecard.service.OrderService;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/3 0003 16:23
 * @Description:
 */
@Api(tags = {"订单管理"})
@RestController
public class OrdersController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/ordersListSelect")
    @ApiOperation("商品订单列表查询")
    public ResultVO ordersListSelect(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return orderService.ordersListSelect(pageObject);
    }

    @PostMapping("/ordersDetailSelect")
    @ApiOperation("商品订单详情查询")
    public ResultVO ordersDetailSelect(@RequestParam Integer id){
        return orderService.ordersDetailSelect(id);
    }

    @PostMapping("/cancelOrdersDetailSelect")
    @ApiOperation("退款商品订单详情查询")
    public ResultVO cancelOrdersDetailSelect(@RequestParam String orderUuid){
        return orderService.cancelOrdersDetailSelect(orderUuid);
    }

    @ApiOperation(value = "查询申请取消的订单", notes = "查询申请取消的订单")
    @PostMapping(value = "selectCancelOrders")
    public ResultVO selectCancelOrders(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return orderService.selectCancelOrders(pageObject);
    }

    @ApiOperation(value = "拒绝取消订单", notes = "拒绝取消订单")
    @PostMapping(value = "refuseCancelOrders")
    public ResultVO refuseCancelOrders(@RequestParam @ApiParam("订单uuid")String  orderUuid){
        return orderService.refuseCancelOrders(orderUuid);
    }

    @ApiOperation(value = "同意取消订单", notes = "同意取消订单")
    @PostMapping(value = "agreeCancelOrders")
    public ResultVO agreeCancelOrders(@RequestParam @ApiParam("订单uuid")String  orderUuid){
        return orderService.agreeCancelOrders(orderUuid);
    }

    @ApiOperation(value = "发货", notes = "发货")
    @PostMapping(value = "orderSend")
    public ResultVO orderSend(@RequestParam @ApiParam("订单uuid") String orderUuid){
        return orderService.orderSend(orderUuid);
    }

    @ApiOperation(value = "完成商品订单", notes = "完成商品订单")
    @PostMapping(value = "orderFininsh")
    public ResultVO orderFininsh(@RequestParam @ApiParam("订单uuid") String orderUuid) {
        return orderService.orderFininsh(orderUuid);
    }

}
