package com.phonecard.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: dong~ren
 * @CreateTime: 2019-09-29 19:48
 */
@Data
public class RefundVo {
    private String uuid;
    private Date createTime;
    private String goodsName;
    private Double price;
    private Integer refundNum;
    private Double actualPrice;
    private Double refundPrice;
    private String nickname;
    private String parentNickname;
    private String parentCityName;
    private Double commission;
    private Integer status;

}
