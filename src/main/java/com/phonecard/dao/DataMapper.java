package com.phonecard.dao;

import com.phonecard.bean.Data;
import com.phonecard.bean.Goods;
import com.phonecard.bean.UserBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DataMapper {
    //获取订单数量
    int getOrderNum(Data data);

    //获取订单金额
    BigDecimal getOrderAmount(Data data);

    //获取商品数量
    int getGoodsOverview(Integer isShelf);

    //获取新增用户数量
    int getUserOverview(Data data);

    //获取商品销量排行TOP10
    List<Goods> getTop10SaleGoods();

    //获取团长返佣排行TOP10
    List<UserBase> getTop10Leader();

    List<Data> getOrderCount(Data data);

    List<Data> getSaleCount(Data data);

    List<UserBase> getTop10CompanyLeader(@Param("companyId") Integer companyId);
}
