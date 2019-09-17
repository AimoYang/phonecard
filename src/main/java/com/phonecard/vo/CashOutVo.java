package com.phonecard.vo;

import com.phonecard.bean.CashOut;
import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/11 0011 20:08
 * @Description:
 */
@Data
public class CashOutVo extends CashOut {

    private String icon;

    private String nickName;

    private String leaderName;

    private String leaderCompanyName;
}
