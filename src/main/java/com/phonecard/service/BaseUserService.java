package com.phonecard.service;

import com.phonecard.bean.JsonResult;
import com.phonecard.bean.Share;
import com.phonecard.bean.UserBase;
import com.phonecard.dao.LeaderMapper;
import com.phonecard.dao.ShareMapper;
import com.phonecard.dao.UserBaseMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaseUserService {

    @Autowired
    private UserBaseMapper userBaseMapper;
    @Autowired
    private ShareMapper shareMapper;

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
            int row = userBaseMapper.updateByPrimaryKeySelective(userBase);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public JsonResult userRelationLeader(String openId, String leaderOpenId) {
        int count = shareMapper.checkOneEd(openId);
        if(count>0){
            return new JsonResult(1,"该用户已关联过团长，请先解绑");
        }
        Share share = new Share();
        share.setOpenId(openId);
        share.setOneOpenId(leaderOpenId);
        share.setCreateTime(new Date());
        share.setIsDelete((short)0);
        share.setInType((short)0);
        shareMapper.insertSelective(share);

        return new JsonResult(0,"用户关联团长成功");

    }

    public JsonResult userUNRelationLeader(String openId, String leaderOpenId) {
        int delete = shareMapper.deleteByOpenId(openId, leaderOpenId);
        if(delete == 0){
            return new JsonResult(1,"用户取消关联团长发生异常");
        }
        return new JsonResult(0,"用户取消关联团长成功");
    }
}
