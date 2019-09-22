package com.phonecard.service;

import com.phonecard.bean.Kefu;
import com.phonecard.dao.KefuMapper;
import com.phonecard.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KefuService {

    @Autowired
    private KefuMapper kefuMapper;


    public Map<String, Object> getSelfAddress(PageObject pageObject) {
        pageObject.setRowCount(kefuMapper.getCountKefuSum());
        List<Kefu> list = kefuMapper.getKefuList(pageObject);
        Map<String, Object> map = new HashMap<>();
        map.put("page", pageObject);
        map.put("info", list);
        return map;

    }

    public boolean updateInfo(Kefu kefu) {
        try {
            int updateRows = kefuMapper.updateByPrimaryKeySelective(kefu);
            if (updateRows > 0){
                return  true;
            } else {
                return false;
            }
        } catch (Exception e){
            return  false;
        }
    }

    public Kefu findById(Integer kefuId){
        return kefuMapper.selectByPrimaryKey(kefuId);
    }
}
