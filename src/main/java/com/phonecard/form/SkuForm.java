package com.phonecard.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/6 0006 15:09
 * @Description:
 */
@Data
public class SkuForm {

    private Integer id;

    private Integer stock;

    private Double commission;

    private String isCode;
    @NotNull
    private String properties;
    @NotNull
    private Double oldPrice;
    @NotNull
    private Double newPrice;

    private Short pickUp;

}
