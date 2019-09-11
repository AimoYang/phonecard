package com.phonecard.bean;

public class Leader {
    private Integer id;

    private String openId;

    private String leaderWechat;

    private String leaderName;

    private String leaderPhone;

    private String leaderId;

    private String leaderCompanyName;

    private String leaderPost;

    private String leaderAddress;

    private Integer companyId;

    private String explains;

    private Double leaderBalance;

    private String introduceName;

    private String introducePhone;

    private String isCode;

    private Short inType;

    private Short isDelete;

    private Short isIntroduce;

    private Short isDisable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getLeaderWechat() {
        return leaderWechat;
    }

    public void setLeaderWechat(String leaderWechat) {
        this.leaderWechat = leaderWechat == null ? null : leaderWechat.trim();
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName == null ? null : leaderName.trim();
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone == null ? null : leaderPhone.trim();
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }

    public String getLeaderCompanyName() {
        return leaderCompanyName;
    }

    public void setLeaderCompanyName(String leaderCompanyName) {
        this.leaderCompanyName = leaderCompanyName == null ? null : leaderCompanyName.trim();
    }

    public String getLeaderPost() {
        return leaderPost;
    }

    public void setLeaderPost(String leaderPost) {
        this.leaderPost = leaderPost == null ? null : leaderPost.trim();
    }

    public String getLeaderAddress() {
        return leaderAddress;
    }

    public void setLeaderAddress(String leaderAddress) {
        this.leaderAddress = leaderAddress == null ? null : leaderAddress.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains == null ? null : explains.trim();
    }

    public Double getLeaderBalance() {
        return leaderBalance;
    }

    public void setLeaderBalance(Double leaderBalance) {
        this.leaderBalance = leaderBalance;
    }

    public String getIntroduceName() {
        return introduceName;
    }

    public void setIntroduceName(String introduceName) {
        this.introduceName = introduceName == null ? null : introduceName.trim();
    }

    public String getIntroducePhone() {
        return introducePhone;
    }

    public void setIntroducePhone(String introducePhone) {
        this.introducePhone = introducePhone == null ? null : introducePhone.trim();
    }

    public String getIsCode() {
        return isCode;
    }

    public void setIsCode(String isCode) {
        this.isCode = isCode == null ? null : isCode.trim();
    }

    public Short getInType() {
        return inType;
    }

    public void setInType(Short inType) {
        this.inType = inType;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Short getIsIntroduce() {
        return isIntroduce;
    }

    public void setIsIntroduce(Short isIntroduce) {
        this.isIntroduce = isIntroduce;
    }

    public Short getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Short isDisable) {
        this.isDisable = isDisable;
    }
}