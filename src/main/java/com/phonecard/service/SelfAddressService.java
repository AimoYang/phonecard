package com.phonecard.service;

import com.phonecard.bean.AddressSelf;
import com.phonecard.bean.JsonResult;
import com.phonecard.dao.AddressSelfMapper;
import com.phonecard.dao.CityMapper;
import com.phonecard.util.PageObject;
import com.phonecard.util.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelfAddressService {

    @Autowired
    private AddressSelfMapper addressSelfMapper;
    @Autowired
    private CityMapper cityMapper;

    public Map<String, Object> getSelfAddress(PageObject pageObject) {
        pageObject.setRowCount(addressSelfMapper.getCountAddressSum());
        List<AddressSelf> list = addressSelfMapper.getAddressList(pageObject);
        Map<String, Object> map = new HashMap<>(2);
        map.put("page", pageObject);
        map.put("info", list);
        return map;
    }

    public Map<String, Object> addAddress(AddressSelf addressSelf) {
        Map<String, Object> map = new HashMap<>();
        String airport = addressSelf.getAirport();
        if (StringUtils.isNotBlank(airport)) {
            //查询地点是否已添加
            if (addressSelfMapper.checkAirportIsExist(addressSelf) == 0) {
                boolean addResult = addressSelfMapper.insertSelective(addressSelf) > 0;
                map.put("addResult", addResult);
                map.put("msg", "新增成功");
            } else {
                map.put("addResult", false);
                map.put("msg", "机场名已存在！");
            }
        } else {
            map.put("addResult", false);
            map.put("msg", "机场名为空");
        }
        return map;
    }

    public boolean deleteAddress(AddressSelf addressSelf) {
        return addressSelfMapper.deleteByPrimaryKey(addressSelf.getId()) > 0;
    }

    public boolean updateAddress(AddressSelf addressSelf) {
        return addressSelfMapper.updateByPrimaryKeySelective(addressSelf) > 0;
    }

    public List<AddressSelf> findAllAddress() {
        return addressSelfMapper.findAllAddress();
    }

    public JsonResult findCityAll() {
        return new JsonResult(StatusCode.SUCCESS,"OK",cityMapper.findCityAll());
    }

    public JsonResult findCityByAdAll(List<Integer> list) {
        List<AddressSelf> Add = new ArrayList<>();
        for (Integer e:list) {
            List<AddressSelf>  AddressList = addressSelfMapper.findCityByAdAll(e);
            for (AddressSelf a: AddressList) {
                Add.add(a);
            }
        }
        return new JsonResult(StatusCode.SUCCESS,"OK",Add);
    }
}
