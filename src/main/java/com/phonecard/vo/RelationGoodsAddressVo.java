package com.phonecard.vo;

import com.phonecard.bean.RelationGoodsAddress;
import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/17 0017 10:16
 * @Description:
 */
@Data
public class RelationGoodsAddressVo extends RelationGoodsAddress {

    private String city;

    private String addressSelf;
}
