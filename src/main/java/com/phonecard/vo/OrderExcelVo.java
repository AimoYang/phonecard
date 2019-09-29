package com.phonecard.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: dong~ren
 * @CreateTime: 2019-09-29 14:45
 */
@Data
public class OrderExcelVo {
    private String uuid;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double amount;
    private Integer distributionType;
    private Integer status;
    private Double actualPrice;
    private Date createTime;
    private String nickname;
    private String parentNickname;
    private Double commission;

}
