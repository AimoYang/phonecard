package com.phonecard.vo;

import com.phonecard.bean.Leader;
import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/11 0011 16:06
 * @Description:
 */
@Data
public class LeaderVo extends Leader {

    private String icon;

    private String nickName;

    private Integer UserNum;

    private Integer consumeSum;

    private Integer relation;
}
