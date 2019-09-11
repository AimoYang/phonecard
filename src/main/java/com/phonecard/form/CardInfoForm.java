package com.phonecard.form;

import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/6 0006 15:21
 * @Description:
 */
@Data
public class CardInfoForm {

    private Integer id;

    private String goodsUuid;

    private Integer destination;

    private String supplier;

    private String selfCity;

    private String startExplain;

    private String costExplain;

    private String productExplain;

    private String reserveNotice;

    private String cancellationPolicy;

    private String network;

    private Short hotspot;

    private String cardSize;

    private Short haveCall;

}
