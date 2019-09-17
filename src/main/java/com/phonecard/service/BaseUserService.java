package com.phonecard.service;

import com.phonecard.bean.Coupon;
import com.phonecard.bean.UserBase;
import com.phonecard.dao.UserBaseMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseUserService {

    @Autowired
    private UserBaseMapper userBaseMapper;

    public Map<String, Object> getUserList(PageObject pageObject) {
        pageObject.setRowCount(userBaseMapper.getCountCouponSum(pageObject));
        List<UserBase> list = userBaseMapper.getCouponList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public boolean updateUserStatus(Integer userId, int status) {
        UserBase userBase = userBaseMapper.selectByPrimaryKey(userId);
        if (userBase != null) {
            userBase.setIsEnable((short) status);
            int row = userBaseMapper.updateByPrimaryKey(userBase);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
