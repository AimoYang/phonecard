package com.phonecard.bean;

import lombok.Data;

import java.util.Date;

@Data
public class RefundOrderDto {

    private Integer id;

    //子订单编号
    private String uuid;
    //总订单编号
    private String productOrderUuid;

    private Integer oneSortId;

    private String goodsUuid;

    private Integer isOne;

    private String properties;

    private Integer skuId;

    private Double price;

    private Integer quantity;

    private Double amount;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Double deposit;

    private Double commission;

    private String thumb;

    private String productName;

    private String nameSelf;

    private String phoneSelf;

    private Short distributionType;

    private Short isComment;

    private String address;

    private Date createTime;

    private String nickname;

    private String leaderName;

    private Double actualPrice;

    private Double refundMoney;
}
