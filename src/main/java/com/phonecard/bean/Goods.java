package com.phonecard.bean;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Goods {
    private Integer id;

    private String uuid;
    @NotNull
    private String goodsName;
    @NotNull
    private Integer oneSortId;

    private Integer showCount;
    @NotNull
    private String thumb;
    @NotNull
    private String pictures;

    private Date goodsCreateTime;

    private Date goodsUpdateTime;

    private Short isShelf;

    private Short pickUp;

    private String goodsVideo;

    private String videoName;

    private String videoImage;

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @NotNull
    private Short goodsType;

    private Integer sort;

    private String details;

    private Short isHot;

    private Short isNew;

    private Short isDelete;
    @NotNull
    private Double deposit;

    private String introduction;
    @NotNull
    private List<Sku> skus;

    private CardInfo cardInfo;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getOneSortId() {
        return oneSortId;
    }

    public void setOneSortId(Integer oneSortId) {
        this.oneSortId = oneSortId;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public Date getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Date goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public Date getGoodsUpdateTime() {
        return goodsUpdateTime;
    }

    public void setGoodsUpdateTime(Date goodsUpdateTime) {
        this.goodsUpdateTime = goodsUpdateTime;
    }

    public Short getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Short isShelf) {
        this.isShelf = isShelf;
    }

    public Short getPickUp() {
        return pickUp;
    }

    public void setPickUp(Short pickUp) {
        this.pickUp = pickUp;
    }

    public String getGoodsVideo() {
        return goodsVideo;
    }

    public void setGoodsVideo(String goodsVideo) {
        this.goodsVideo = goodsVideo;
    }

    public Short getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Short goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public Short getIsHot() {
        return isHot;
    }

    public void setIsHot(Short isHot) {
        this.isHot = isHot;
    }

    public Short getIsNew() {
        return isNew;
    }

    public void setIsNew(Short isNew) {
        this.isNew = isNew;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", oneSortId=" + oneSortId +
                ", showCount=" + showCount +
                ", thumb='" + thumb + '\'' +
                ", pictures='" + pictures + '\'' +
                ", goodsCreateTime=" + goodsCreateTime +
                ", goodsUpdateTime=" + goodsUpdateTime +
                ", isShelf=" + isShelf +
                ", pickUp=" + pickUp +
                ", goodsType=" + goodsType +
                ", sort=" + sort +
                ", details='" + details + '\'' +
                ", isHot=" + isHot +
                ", isNew=" + isNew +
                ", isDelete=" + isDelete +
                ", deposit=" + deposit +
                ", introduction='" + introduction + '\'' +
                ", skus=" + skus +
                ", cardInfo=" + cardInfo +
                ", state=" + state +
                '}';
    }
}