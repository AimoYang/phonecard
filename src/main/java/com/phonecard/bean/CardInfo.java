package com.phonecard.bean;

import com.phonecard.form.AddressForm;
import com.phonecard.vo.RelationGoodsAddressVo;

import java.util.List;

public class CardInfo {
    private Integer id;

    private String goodsUuid;

    private Integer destination;

    private String supplier;

    private String selfCity;

    private String startExplain;

    private String costExplain;

    private String productExplain;

    private String reserveNotice;

    private String cancellationPolicy;

    private String network;

    private Short hotspot;

    private String cardSize;

    private Short isDelete;

    private Short haveCall;

    private Integer deviceStock;

    private String stateName;

    private List<RelationGoodsAddressVo> list;

    public List<RelationGoodsAddressVo> getList() {
        return list;
    }

    public void setList(List<RelationGoodsAddressVo> list) {
        this.list = list;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

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

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getSelfCity() {
        return selfCity;
    }

    public void setSelfCity(String selfCity) {
        this.selfCity = selfCity == null ? null : selfCity.trim();
    }

    public String getStartExplain() {
        return startExplain;
    }

    public void setStartExplain(String startExplain) {
        this.startExplain = startExplain == null ? null : startExplain.trim();
    }

    public String getCostExplain() {
        return costExplain;
    }

    public void setCostExplain(String costExplain) {
        this.costExplain = costExplain == null ? null : costExplain.trim();
    }

    public String getProductExplain() {
        return productExplain;
    }

    public void setProductExplain(String productExplain) {
        this.productExplain = productExplain == null ? null : productExplain.trim();
    }

    public String getReserveNotice() {
        return reserveNotice;
    }

    public void setReserveNotice(String reserveNotice) {
        this.reserveNotice = reserveNotice == null ? null : reserveNotice.trim();
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy == null ? null : cancellationPolicy.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public Short getHotspot() {
        return hotspot;
    }

    public void setHotspot(Short hotspot) {
        this.hotspot = hotspot;
    }

    public String getCardSize() {
        return cardSize;
    }

    public void setCardSize(String cardSize) {
        this.cardSize = cardSize == null ? null : cardSize.trim();
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Short getHaveCall() {
        return haveCall;
    }

    public void setHaveCall(Short haveCall) {
        this.haveCall = haveCall;
    }

    public Integer getDeviceStock() {
        return deviceStock;
    }

    public void setDeviceStock(Integer deviceStock) {
        this.deviceStock = deviceStock;
    }
}