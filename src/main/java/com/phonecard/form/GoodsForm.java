package com.phonecard.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull
    private String goodsName;
    @NotNull
    private Integer oneSortId;
    @NotNull
    private String thumb;
    @NotNull
    private String pictures;

    private Short isShelf;

    private Short pickUp;
    @NotNull
    private Short goodsType;

    private Integer sort;

    private String details;

    private Short isHot;

    private Short isNew;

    private Short isDelete;

    private Double deposit;

    private String introduction;
    @NotNull
    private List<SkuForm> skus;

    private CardInfoForm cardInfoForm;
}
