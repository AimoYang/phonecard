package com.phonecard.bean;

import com.phonecard.util.DateUtil;

import java.util.Date;

public class Data {
    //0、全部，1、当天，2、本周，3、本月
    private Integer type;
    private String startDate;
    private String endDate;

    private String groupDate;
    private Integer total;

    public Data() {
    }

    public Data(Integer type, String startDate, String endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static Data getLastMonth() {//上月
        return new Data(-3, DateUtil.getFirstDayOfLastMonth(), DateUtil.getLastDayOfLastMonth());
    }

    public static Data getLastWeek() {//上周
        return new Data(-2, DateUtil.getDayOfLastWeek(DateUtil.getFirstDayOfWeek()), DateUtil.getDayOfLastWeek(DateUtil.getLastDayOfWeek()));
    }

    public static Data getYesterday() {//昨天
        return new Data(-1, DateUtil.getYesterday(), "");
    }

    public static Data getAll() {//所有
        return new Data(0, "", "");
    }

    public static Data getToday() {//当天
        return new Data(1, DateUtil.dateToString(new Date()), "");
    }

    public static Data getThisWeek() {//本周
        return new Data(2, DateUtil.getFirstDayOfWeek(), DateUtil.getLastDayOfWeek());
    }

    public static Data getThisMonth() {//本月
        return new Data(3, DateUtil.getFirstDayOfMonth(), DateUtil.getLastDayOfMonth());
    }
}
