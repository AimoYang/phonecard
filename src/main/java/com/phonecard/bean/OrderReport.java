package com.phonecard.bean;

import com.phonecard.common.ExcelAnnotation;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dong~ren
 * @CreateTime: 2019-09-17 18:09
 */
@Data
public class OrderReport implements Serializable{

    private static final long serialVersionUID = 2L;
    @ExcelAnnotation(id = 1,name = {"订单编号"},width = 5000)
    private String uuid;
    @ExcelAnnotation(id = 2,name = {"下单时间"},width = 5000)
    private String createTime;
    @ExcelAnnotation(id = 3,name = {"商品名称"},width = 5000)
    private String productName;
    @ExcelAnnotation(id = 4,name = {"数量"},width = 5000)
    private Integer quantity;
    @ExcelAnnotation(id = 5,name = {"单价"},width = 5000)
    private String price;
    @ExcelAnnotation(id = 6,name = {"总价"},width = 5000)
    private String amount;
    @ExcelAnnotation(id = 7,name = {"取件方式"},width = 5000)
    private String distributionType;
    @ExcelAnnotation(id = 8,name = {"用户昵称"},width = 5000)
    private String nickname;
    @ExcelAnnotation(id = 9,name = {"返佣价格"},width = 5000)
    private String commission;
    @ExcelAnnotation(id = 10,name = {"交易状态"},width = 5000)
    private String status;
    @ExcelAnnotation(id = 11,name = {"实收款"},width = 5000)
    private String actualPrice;
    @ExcelAnnotation(id = 12,name = {"所属团长"},width = 5000)
    private String parentNickname;
}
