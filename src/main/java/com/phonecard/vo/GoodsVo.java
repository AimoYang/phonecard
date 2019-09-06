package com.phonecard.vo;

import com.phonecard.bean.Goods;
import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 15:16
 * @Description:
 */
@Data
public class GoodsVo extends Goods{

    private  Double oldPrice;

    private  Double newPrice;

    private String properties;

}
