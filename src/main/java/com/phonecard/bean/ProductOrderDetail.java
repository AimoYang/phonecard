package com.phonecard.bean;

import java.util.Date;

public class ProductOrderDetail {
    private Integer id;

    private String uuid;

    private String productOrderUuid;

    private Integer oneSortId;

    private String goodsUuid;

    private Integer isOne;

    private String properties;

    private Integer skuId;

    private Double price;

    private Integer quantity;

    private Double amount;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Double deposit;

    private Double commission;

    private String thumb;

    private String productName;

    private String nameSelf;

    private String phoneSelf;

    private Short distributionType;

    private Short isComment;

    private String address;

    private String remark;

    private Integer statusPrefix;

    private Integer selfAddressId;

    private Double actualPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getProductOrderUuid() {
        return productOrderUuid;
    }

    public void setProductOrderUuid(String productOrderUuid) {
        this.productOrderUuid = productOrderUuid == null ? null : productOrderUuid.trim();
    }

    public Integer getOneSortId() {
        return oneSortId;
    }

    public void setOneSortId(Integer oneSortId) {
        this.oneSortId = oneSortId;
    }

    public String getGoodsUuid() {
        return goodsUuid;
    }

    public void setGoodsUuid(String goodsUuid) {
        this.goodsUuid = goodsUuid == null ? null : goodsUuid.trim();
    }

    public Integer getIsOne() {
        return isOne;
    }

    public void setIsOne(Integer isOne) {
        this.isOne = isOne;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getNameSelf() {
        return nameSelf;
    }

    public void setNameSelf(String nameSelf) {
        this.nameSelf = nameSelf == null ? null : nameSelf.trim();
    }

    public String getPhoneSelf() {
        return phoneSelf;
    }

    public void setPhoneSelf(String phoneSelf) {
        this.phoneSelf = phoneSelf == null ? null : phoneSelf.trim();
    }

    public Short getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(Short distributionType) {
        this.distributionType = distributionType;
    }

    public Short getIsComment() {
        return isComment;
    }

    public void setIsComment(Short isComment) {
        this.isComment = isComment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatusPrefix() {
        return statusPrefix;
    }

    public void setStatusPrefix(Integer statusPrefix) {
        this.statusPrefix = statusPrefix;
    }

    public Integer getSelfAddressId() {
        return selfAddressId;
    }

    public void setSelfAddressId(Integer selfAddressId) {
        this.selfAddressId = selfAddressId;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }
}