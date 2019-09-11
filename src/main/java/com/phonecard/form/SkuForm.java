package com.phonecard.form;

import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/6 0006 15:09
 * @Description:
 */
@Data
public class SkuForm {

    private Integer id;

    private String goodsUuid;

    private Integer stock;

    private Double commission;

    private String isCode;

    private String properties;

    private Double oldPrice;

    private Double newPrice;

    private Short pickUp;

}
