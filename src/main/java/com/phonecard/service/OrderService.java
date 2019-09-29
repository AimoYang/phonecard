package com.phonecard.service;

import com.phonecard.bean.*;
import com.phonecard.dao.*;
import com.phonecard.util.PageObject;
import com.phonecard.util.RandomNum;
import com.phonecard.util.ResultUtil;
import com.phonecard.util.WeiXinRefund;
import com.phonecard.vo.CancelOrdersDetailVo;
import com.phonecard.vo.ProductOrderVo;
import com.phonecard.bean.OrderDto;
import com.phonecard.dao.ProductOrderDetailMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/9 0009 17:39
 * @Description:
 */
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private ProductOrderDetailMapper productOrderDetailMapper;
    @Autowired
    private RecordRefundMapper recordRefundMapper;
    @Autowired
    private TurnoverMapper turnoverMapper;
    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private WeiXinRefund weiXinRefund;

    public ResultVO findCompanyOrderInfo(PageObject pageObject) {
        int row = productOrderDetailMapper.getCompanyOrderRow(pageObject);
        pageObject.setRowCount(row);
        List<ProductOrderVo> list = productOrderDetailMapper.findCompanyOrderInfo(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO ordersListSelect(PageObject pageObject) {
        if (pageObject.getType() == null || pageObject.getType().intValue() == 2){
            pageObject.setType(null);
        }
        int row = productOrderDetailMapper.getOrdersListRow(pageObject);
        pageObject.setRowCount(row);
        List<ProductOrderVo> list = productOrderDetailMapper.selectOrdersList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO ordersDetailSelect(Integer id) {
        ProductOrderVo productOrderVo = productOrderDetailMapper.selectByOrderUuid(id);
        return ResultUtil.success(productOrderVo);
    }

    public ResultVO selectCancelOrders(PageObject pageObject) {
        if (pageObject.getType() == null || pageObject.getType().intValue() == 2){
            pageObject.setType(null);
        }
        List<CancelOrdersDetailVo> list = productOrderDetailMapper.selectCancelOrders(pageObject);
        for (CancelOrdersDetailVo c: list) {
            c.setRecordRefund(recordRefundMapper.selectByOrdersUuidAndDesposit(c.getUuid(),c.getIsDeposit()));
        }
        int row = productOrderDetailMapper.getCancelOrdersRow(pageObject);
        pageObject.setRowCount(row);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO refuseCancelOrders(Integer id) {
        RecordRefund recordRefund = recordRefundMapper.selectByPrimaryKey(id);
        ProductOrderDetail productOrderDetail = productOrderDetailMapper.selectByUuid(recordRefund.getOrderUuid());
        if(productOrderDetail == null ){
            return ResultUtil.fail("订单不存在");
        }
        if(productOrderDetail.getStatus() != 5){
            return ResultUtil.fail("该订单未申请退款");
        }
        //修改状态
        try {
            productOrderDetail.setStatus(productOrderDetail.getStatusPrefix());
            productOrderDetailMapper.updateByPrimaryKeySelective(productOrderDetail);
            RecordRefund r = new RecordRefund();
            r.setId(id);
            r.setIsSuccess(2);
            recordRefundMapper.updateByPrimaryKeySelective(r);
        }catch (Exception e){
            e.printStackTrace();
            return  ResultUtil.fail("操作失败");
        }
        return  ResultUtil.success();
    }

    public ResultVO agreeCancelOrders(Integer id) {
        RecordRefund recordRefund = recordRefundMapper.selectByPrimaryKey(id);
        if (recordRefund == null){
            return ResultUtil.fail("退款订单不存在");
        }
        ProductOrder productOrder = productOrderMapper.selectProductOrderuuid(recordRefund.getOrderUuid());
        if(productOrder == null){
            return ResultUtil.fail("总订单不存在");
        }
        if (productOrder.getActualPrice() > 0){
            try {
                String out_refund_no = "Tu" + RandomNum.getRandomFileName();
                JsonResult r = weiXinRefund.refund(productOrder.getUuid(), out_refund_no, String.valueOf(productOrder.getActualPrice()), recordRefund.getRefundFee());
                if (r.getResult() != 0){
                    return  ResultUtil.fail(r.getMsg());
                }
//                recordRefund.setOutRefundNo(out_refund_no);
                recordRefund.setIsSuccess(1);
                recordRefundMapper.updateByPrimaryKeySelective(recordRefund);
                if (recordRefund.getIsDeposit() != 1){
                    ProductOrderDetail productOrderDetail = productOrderDetailMapper.selectByUuid(recordRefund.getOrderUuid());
                    if (productOrder.getOneOpenId() != null){
                        if (productOrderDetail.getQuantity() > recordRefund.getRefundSum()){
                            double mon = (productOrderDetail.getQuantity()-recordRefund.getRefundSum())/productOrderDetail.getQuantity()*productOrderDetail.getCommission();
                            Turnover turnover = new Turnover();
                            turnover.setTurnoverTime(new Date());
                            turnover.setOpenId(productOrder.getOneOpenId());
                            turnover.setOrderUuid(recordRefund.getOrderUuid());
                            turnover.setTurnoverMon(mon);
                            turnover.setTurnoverType((short)0);
                            turnover.setInType((short)1);
                            turnoverMapper.insertSelective(turnover);

                            Income income = new Income();
                            income.setOpenId(productOrder.getOneOpenId());
                            income.setIncomeAll(turnover.getTurnoverMon());
                            income.setRemain(turnover.getTurnoverMon());
                            incomeMapper.updateByOpenId(income);
                        }
                    }
                    //修改状态
                    productOrderDetail.setStatus(6);
                    productOrderDetailMapper.updateByPrimaryKeySelective(productOrderDetail);
                }
                //退款
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return  ResultUtil.fail("总订单实际付款过少");
        }
        return  ResultUtil.success();
    }

    public ResultVO orderSend(String orderUuid) {
        ProductOrderDetail productOrderDetail = productOrderDetailMapper.selectByUuid(orderUuid);
        if(productOrderDetail == null ){
            return ResultUtil.fail("订单不存在");
        }
        if(productOrderDetail.getStatus() != 1){
            return ResultUtil.fail("该订单无法发货");
        }
        productOrderDetail.setStatus(3);
        productOrderDetailMapper.updateByPrimaryKeySelective(productOrderDetail);
        return ResultUtil.success();
    }

    public ResultVO orderFininsh(String orderUuid) {
        ProductOrderDetail productOrderDetail = productOrderDetailMapper.selectByUuid(orderUuid);
        if(productOrderDetail == null ){
            return ResultUtil.fail("订单不存在");
        }
        if(productOrderDetail.getStatus() != 2){
            return ResultUtil.fail("该订单未发货，无法完成");
        }
        try {
            ProductOrder productOrder = productOrderMapper.selectProductOrderuuid(orderUuid);
            if (productOrder.getOneOpenId() != null){
                Turnover turnover = new Turnover();
                turnover.setTurnoverTime(new Date());
                turnover.setOpenId(productOrder.getOneOpenId());
                turnover.setOrderUuid(orderUuid);
                turnover.setTurnoverMon(productOrderDetail.getCommission());
                turnover.setTurnoverType((short)0);
                turnover.setInType((short)1);
                turnoverMapper.insertSelective(turnover);

                Income income = new Income();
                income.setOpenId(productOrder.getOneOpenId());
                income.setIncomeAll(turnover.getTurnoverMon());
                income.setRemain(turnover.getTurnoverMon());
                incomeMapper.updateByOpenId(income);
            }
            productOrderDetail.setStatus(4);
            productOrderDetailMapper.updateByPrimaryKeySelective(productOrderDetail);
        }catch (Exception e){
            return ResultUtil.fail("操作失败");
        }
        return ResultUtil.success();
    }

    public void dealRefund(String outRefundNo) {
        RecordRefund recordRefund = recordRefundMapper.selectByOutRefundNo(outRefundNo);
        if(recordRefund == null){
            return;
        }
        recordRefund.setType(1);
        recordRefundMapper.updateByPrimaryKeySelective(recordRefund);
    }

    public ResultVO cancelOrdersDetailSelect(String orderUuid) {
        CancelOrdersDetailVo cancelOrdersDetailVo = productOrderDetailMapper.cancelOrdersDetailSelect(orderUuid);
        cancelOrdersDetailVo.setRecordRefund(recordRefundMapper.selectByOrdersUuid(cancelOrdersDetailVo.getUuid()));
        return ResultUtil.success(cancelOrdersDetailVo);
    }


    public List<OrderDto> findOrderByPage(PageObject pageObject, String orderNo, Integer fetchType, String leaderNickName, Date startTime, Date endTime) {
            List<OrderDto> list = productOrderDetailMapper.findOrderByPage(pageObject, orderNo, fetchType, leaderNickName, startTime, endTime);
            return list;
    }
}
