package com.phonecard.vo;

import com.phonecard.bean.ProductOrderDetail;

import java.util.Date;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/9 0009 19:40
 * @Description:
 */

public class ProductOrderVo extends ProductOrderDetail {

    private Date createTime;

    private String leaderName;

    private String nickName;

    private String icon;

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
