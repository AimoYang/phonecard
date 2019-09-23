package com.phonecard.bean;

import com.phonecard.util.DateUtil;

import java.util.Date;

@lombok.Data
public class Data {
    //0、全部，1、当天，2、本周，3、本月
    private Integer type;
    private String startDate;
    private String endDate;

    private String groupDate;
    private Integer total;
    private Integer company;

    public Data() {
    }

    public Data(Integer type, String startDate, String endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Data(Integer type, String startDate, String endDate, Integer company) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
    }


    public static Data getLastMonth(Integer companyId) {//上月
        return new Data(-3, DateUtil.getFirstDayOfLastMonth(), DateUtil.getLastDayOfLastMonth(), companyId);
    }

    public static Data getLastWeek(Integer companyId) {//上周
        return new Data(-2, DateUtil.getDayOfLastWeek(DateUtil.getFirstDayOfWeek()), DateUtil.getDayOfLastWeek(DateUtil.getLastDayOfWeek()), companyId);
    }

    public static Data getYesterday(Integer companyId) {//昨天
        return new Data(-1, DateUtil.getYesterday(), "", companyId);
    }

    public static Data getAll(Integer companyId) {//所有
        return new Data(0, "", "", companyId);
    }

    public static Data getToday(Integer companyId) {//当天
        return new Data(1, DateUtil.dateToString(new Date()), "", companyId);
    }

    public static Data getThisWeek(Integer companyId) {//本周
        return new Data(2, DateUtil.getFirstDayOfWeek(), DateUtil.getLastDayOfWeek(), companyId);
    }

    public static Data getThisMonth(Integer companyId) {//本月
        return new Data(3, DateUtil.getFirstDayOfMonth(), DateUtil.getLastDayOfMonth(), companyId);
    }
}
