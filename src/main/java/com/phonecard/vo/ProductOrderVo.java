package com.phonecard.vo;

import com.phonecard.bean.ProductOrderDetail;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/9 0009 19:40
 * @Description:
 */
@Data
public class ProductOrderVo extends ProductOrderDetail {

    private Date createTime;

    private String leaderName;

    private String nickName;

    private String icon;

}
