package com.phonecard.vo;

import com.phonecard.bean.RecordRefund;
import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/9 0009 19:57
 * @Description:
 */
@Data
public class CancelOrdersDetailVo extends ProductOrderVo{

    private RecordRefund recordRefund;

}
