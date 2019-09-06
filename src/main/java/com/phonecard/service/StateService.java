package com.phonecard.service;

import com.phonecard.bean.Coupon;
import com.phonecard.bean.State;
import com.phonecard.dao.StateMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StateService {

    @Autowired
    private StateMapper stateMapper;

    public Map<String, Object> getStateRecomendList(PageObject pageObject) {
        pageObject.setRowCount(stateMapper.getCountStateSum());
        List<State> list = stateMapper.getStateRecommedList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public Map<String, Object> getStateHotList(PageObject pageObject) {
        pageObject.setRowCount(stateMapper.getCountStateSum());
        List<State> list = stateMapper.getStateHotList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public boolean stateRecomendCancel(Integer id, int isRecommend) {
        State state = stateMapper.selectByPrimaryKey(id);
        if (state != null){
            state.setIsRecommend((short) isRecommend);
            int row = stateMapper.updateByPrimaryKey(state);
            if (row >0 ){
                return true;
            } else{
                return  false;
            }
        } else{
            return  false;
        }
    }

    public boolean stateHotCancel(Integer id, int isHot) {
        State state = stateMapper.selectByPrimaryKey(id);
        if (state != null){
            state.setIsHot((short) isHot);
            int row = stateMapper.updateByPrimaryKey(state);
            if (row >0 ){
                return true;
            } else{
                return  false;
            }
        } else{
            return  false;
        }
    }
}
