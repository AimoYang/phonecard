package com.phonecard.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/12 0012 17:18
 * @Description:
 */
@Data
public class CommentDto {
    private Integer id;

    private String openId;

    private String orderUuid;

    private String goodsUuid;

    private String content;

    private String pictures;

    private Date createTime;

    private String reply;

    private Short isDelete;

    private Short isReply;

    private String goodsName;

    private String sortName;

    private String icon;

    private String nickName;
}
