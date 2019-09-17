package com.phonecard.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/11 0011 16:28
 * @Description:
 */
@Data
public class ShareVo {

    private String nickName;

    private String icon;

    private Date createTime;

    private Double consumeMoney;

    private Integer consumeSum;

    private Double commissionAll;

}
