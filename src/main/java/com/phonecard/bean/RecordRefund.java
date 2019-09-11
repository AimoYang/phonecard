package com.phonecard.bean;

import java.util.Date;

public class RecordRefund {
    private Integer id;

    private Integer type;

    private String orderUuid;

    private String outRefundNo;

    private String refundFee;

    private Date createTime;

    private Integer isSuccess;

    private Short isDeposit;

    private Integer refundSum;

    private String refundCause;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid == null ? null : orderUuid.trim();
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee == null ? null : refundFee.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Short getIsDeposit() {
        return isDeposit;
    }

    public void setIsDeposit(Short isDeposit) {
        this.isDeposit = isDeposit;
    }

    public Integer getRefundSum() {
        return refundSum;
    }

    public void setRefundSum(Integer refundSum) {
        this.refundSum = refundSum;
    }

    public String getRefundCause() {
        return refundCause;
    }

    public void setRefundCause(String refundCause) {
        this.refundCause = refundCause == null ? null : refundCause.trim();
    }

}