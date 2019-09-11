package com.phonecard.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/6 0006 15:06
 * @Description:
 */
@Data
public class GoodsForm {

    private Integer id;

    private String uuid;

    private String goodsName;

    private Integer oneSortId;

    private String thumb;

    private String pictures;

    private Short isShelf;

    private Short pickUp;

    private Short goodsType;

    private Integer sort;

    private String details;

    private Short isHot;

    private Short isNew;

    private Short isDelete;

    private Double deposit;

    private String introduction;

    private List<SkuForm> skus;

    private CardInfoForm cardInfoForm;
}
