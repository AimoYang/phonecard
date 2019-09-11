package com.phonecard.bean;

import javax.validation.constraints.NotNull;

public class Sku {
    private Integer id;

    private String goodsUuid;
    @NotNull
    private Integer stock;
    @NotNull
    private Double commission;

    private String isCode;
    @NotNull
    private Double deposit;
    @NotNull
    private String properties;
    @NotNull
    private Double oldPrice;
    @NotNull
    private Double newPrice;

    private Short isDelete;
    @NotNull
    private Short pickUp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsUuid() {
        return goodsUuid;
    }

    public void setGoodsUuid(String goodsUuid) {
        this.goodsUuid = goodsUuid == null ? null : goodsUuid.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getIsCode() {
        return isCode;
    }

    public void setIsCode(String isCode) {
        this.isCode = isCode == null ? null : isCode.trim();
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Short getPickUp() {
        return pickUp;
    }

    public void setPickUp(Short pickUp) {
        this.pickUp = pickUp;
    }
}