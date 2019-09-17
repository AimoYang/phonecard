package com.phonecard.service;

import com.phonecard.bean.AddressSelf;
import com.phonecard.bean.Coupon;
import com.phonecard.dao.CouponMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponService {

    @Autowired
    private CouponMapper couponMapper;

    public Map<String, Object> getCouponList(PageObject pageObject) {
        pageObject.setRowCount(couponMapper.getCountCouponSum(pageObject));
        List<Coupon> list = couponMapper.getCouponList(pageObject);
        Map<String, Object> map = new HashMap<>(2);
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public boolean addCoupon(Coupon coupon) {
        int row = couponMapper.insertSelective(coupon);
        if (row > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteCoupon(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        if (coupon != null){
            coupon.setIsDelete((short)1);
            int row = couponMapper.updateByPrimaryKey(coupon);
            if (row > 0){
                return true;
            }else{
                return false;
            }
        } else{
            return false;
        }

    }

    public boolean couponUpdate(Coupon coupon) {
        int row = couponMapper.updateByPrimaryKey(coupon);
        if (row > 0){
            return true;
        }else{
            return false;
        }
    }
}
