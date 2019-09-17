package com.phonecard.service;

import com.phonecard.bean.Data;
import com.phonecard.dao.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataService {
    @Autowired
    private DataMapper dataMapper;

    /**
     * 主页统计
     */
    public Map<String, Object> getHomepageCount() {
        Map<String, Object> map = new HashMap<>();
        //获取订单数量
        map.put("orderNumCount", getOrderNum());
        //获取订单金额
        map.put("orderAmountCount", getOrderAmount());
        //获取商品总览
        map.put("goodsOverviewCount", getGoodsOverview());
        //获取用户总览
        map.put("userOverviewCount", getUserOverview());
        //获取商品销量排行TOP10
        map.put("top10SaleGoodsCount", dataMapper.getTop10SaleGoods());
        //获取团长返佣排行TOP10
        map.put("top10UserHeadCount", dataMapper.getTop10Leader());
        //获取订单统计
        map.put("orderCount", getCountOrder());
        //获取销售统计
        map.put("saleCount", getCountSale());
        return map;
    }

    /**
     * 获取订单数量
     *
     * @return Map<String, Integer>
     */
    private Map<String, Integer> getOrderNum() {
        Map<String, Integer> map = new HashMap<>();
        //今日订单数量
        map.put("today", dataMapper.getOrderNum(Data.getToday()));
        //本周订单数量
        map.put("thisWeek", dataMapper.getOrderNum(Data.getThisWeek()));
        //本月订单数量
        map.put("thisMonth", dataMapper.getOrderNum(Data.getThisMonth()));
        //全部订单数量
        map.put("all", dataMapper.getOrderNum(Data.getAll()));
        return map;
    }

    /**
     * 获取订单金额
     *
     * @return Map<String, BigDecimal>
     */
    private Map<String, BigDecimal> getOrderAmount() {
        Map<String, BigDecimal> map = new HashMap<>();
        //今日订单金额
        BigDecimal today = dataMapper.getOrderAmount(Data.getToday());
        map.put("today", today == null ? BigDecimal.ZERO : today);
        //本周订单金额
        BigDecimal thisWeek = dataMapper.getOrderAmount(Data.getThisWeek());
        map.put("thisWeek", thisWeek == null ? BigDecimal.ZERO : thisWeek);
        //本月订单金额
        BigDecimal thisMonth = dataMapper.getOrderAmount(Data.getThisMonth());
        map.put("thisMonth", thisMonth == null ? BigDecimal.ZERO : thisMonth);
        //全部订单金额
        BigDecimal all = dataMapper.getOrderAmount(Data.getAll());
        map.put("all", all == null ? BigDecimal.ZERO : all);
        return map;
    }

    /**
     * 获取商品总览
     *
     * @return Map<String, Integer>
     */
    private Map<String, Integer> getGoodsOverview() {
        Map<String, Integer> map = new HashMap<>();
        //已下架
        map.put("notShelf", dataMapper.getGoodsOverview(0));
        //已上架
        map.put("hasShelf", dataMapper.getGoodsOverview(1));
        //全部商品
        map.put("all", dataMapper.getGoodsOverview(null));
        return map;
    }

    /**
     * 获取用户总览
     *
     * @return Map<String, Integer>
     */
    private Map<String, Integer> getUserOverview() {
        Map<String, Integer> map = new HashMap<>();
        //今日新增
        map.put("today", dataMapper.getUserOverview(Data.getToday()));
        //昨日新增
        map.put("yesterday", dataMapper.getUserOverview(Data.getYesterday()));
        //用户总数
        map.put("all", dataMapper.getUserOverview(Data.getAll()));
        return map;
    }

    /**
     * 获取订单统计
     *
     * @return Map<String, Object>
     */
    private Map<String, Object> getCountOrder() {
        Map<String, Object> map = new HashMap<>();
        //本月订单数量
        int thisMonthNum = dataMapper.getOrderNum(Data.getThisMonth());
        map.put("thisMonth", thisMonthNum);
        //上月订单数量
        int lastMonthNum = dataMapper.getOrderNum(Data.getLastMonth());
        //同比上月
        String monthPercent = ((thisMonthNum - lastMonthNum) / (lastMonthNum == 0 ? 1 : lastMonthNum)) * 100 + "%";
        map.put("monthPercent", monthPercent);
        //本周订单数量
        int thisWeekNum = dataMapper.getOrderNum(Data.getThisWeek());
        map.put("thisWeek", thisWeekNum);
        //上周订单数量
        int lastWeekNum = dataMapper.getOrderNum(Data.getLastWeek());
        //同比上周
        String weekPercent = ((thisWeekNum - lastWeekNum) / (lastWeekNum == 0 ? 1 : lastWeekNum)) * 100 + "%";
        map.put("weekPercent", weekPercent);
        //今日订单统计
        map.put("todayCount", dataMapper.getOrderCount(Data.getToday()));
        //本周订单统计
        map.put("thisWeekCount", dataMapper.getOrderCount(Data.getThisWeek()));
        //本月订单统计
        map.put("thisMonthCount", dataMapper.getOrderCount(Data.getThisMonth()));
        return map;
    }

    /**
     * 获取销售统计
     *
     * @return Map<String, Object>
     */
    private Map<String, Object> getCountSale() {
        Map<String, Object> map = new HashMap<>();
        //本月销售总额
        BigDecimal thisMonthAmount = dataMapper.getOrderAmount(Data.getThisMonth());
        map.put("thisMonth", thisMonthAmount);
        //上月销售总额
        System.out.print("1-------------------");
        BigDecimal lastMonthAmount = dataMapper.getOrderAmount(Data.getLastMonth());
        lastMonthAmount = lastMonthAmount == null ? BigDecimal.ZERO : lastMonthAmount;
        //同比上月
        System.out.print("3--------------------");
        thisMonthAmount = thisMonthAmount == null ? BigDecimal.ZERO : thisMonthAmount;
        String monthPercent = (thisMonthAmount.subtract(lastMonthAmount)).divide((lastMonthAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ONE : lastMonthAmount),2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%";
        map.put("monthPercent", monthPercent);
        System.out.print("4--------------------");
        //本周销售总额
        BigDecimal thisWeekAmount = dataMapper.getOrderAmount(Data.getThisWeek());
        map.put("thisWeek", thisWeekAmount);
        //上周销售总额
        BigDecimal lastWeekAmount = dataMapper.getOrderAmount(Data.getLastWeek());
        lastWeekAmount = lastWeekAmount == null ? BigDecimal.ZERO : lastWeekAmount;
        //同比上周
        thisWeekAmount = thisWeekAmount == null ? BigDecimal.ZERO : thisWeekAmount;
        String weekPercent = (thisWeekAmount.subtract(lastWeekAmount)).divide((lastWeekAmount.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ONE : lastWeekAmount),2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%";
        map.put("weekPercent", weekPercent);
        //今日销售额统计
        map.put("todayCount", dataMapper.getSaleCount(Data.getToday()));
        //本周销售额统计
        map.put("thisWeekCount", dataMapper.getSaleCount(Data.getThisWeek()));
        //本月销售额统计
        map.put("thisMonthCount", dataMapper.getSaleCount(Data.getThisMonth()));
        return map;
    }
}
