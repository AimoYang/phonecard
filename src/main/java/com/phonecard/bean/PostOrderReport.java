package com.phonecard.bean;

import com.phonecard.common.ExcelAnnotation;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dong~ren
 * @CreateTime: 2019-09-17 18:09
 */
@Data
public class PostOrderReport implements Serializable{
    private static final long serialVersionUID = 2L;
    @ExcelAnnotation(id = 1,name = {"退款单号"},width = 5000)
    private String refundUUID;
    @ExcelAnnotation(id = 2,name = {"订单编号"},width = 5000)
    private String orderUUID;
    @ExcelAnnotation(id = 3,name = {"商品名"},width = 5000)
    private String goodsName;
    @ExcelAnnotation(id = 4,name = {"退款商品数"},width = 5000)
    private Integer goodsNum;
    @ExcelAnnotation(id = 5,name = {"退款金额"},width = 5000)
    private String refundMoney;
    @ExcelAnnotation(id = 6,name = {"申请时间"},width = 5000)
    private String createTime;
    @ExcelAnnotation(id = 7,name = {"头像"},width = 5000)
    private String icon;
    @ExcelAnnotation(id = 8,name = {"昵称"},width = 5000)
    private String nickname;
    @ExcelAnnotation(id = 9,name = {"收货人"},width = 5000)
    private String receiver;
    @ExcelAnnotation(id = 10,name = {"收货人电话"},width = 5000)
    private String receiverPhone;
    @ExcelAnnotation(id = 11,name = {"订单状态"},width = 5000)
    private String status;
}
