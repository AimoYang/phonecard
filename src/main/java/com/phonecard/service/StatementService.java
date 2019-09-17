package com.phonecard.service;

import com.phonecard.bean.*;
import com.phonecard.dao.CashOutMapper;
import com.phonecard.dao.IncomeMapper;
import com.phonecard.util.ArithUtil;
import com.phonecard.util.BusinessPayUtil;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.CashOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/11 0011 11:05
 * @Description:
 */
@Service
public class StatementService {

    @Autowired
    private CashOutMapper cashOutMapper;
    @Autowired
    private IncomeMapper incomeMapper;

    public ResultVO selectStateList(PageObject pageObject) {
        int row = cashOutMapper.getStateRow(pageObject);
        pageObject.setRowCount(row);
        List<CashOutVo> list = cashOutMapper.selectStateList(pageObject);
        Map<String, Object> map = new HashMap<>(2);
        map.put("list", list);
        map.put("pageObject", pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO agreeState(CashOut cashOut ,HttpServletRequest request) {
        try {
            Income income = new Income();
            income.setOpenId(cashOut.getOpenId());
            income.setRemain(cashOut.getCashNumber());
            income.setAlreadyCash(cashOut.getCashNumber());
            incomeMapper.agreeState(income);

            Map<String,String> map = new HashMap<>(3);
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmSS");
            String randNo = format.format(new Date());
            map.put("openId", cashOut.getOpenId());
            map.put("money", Integer.toString((int)(ArithUtil.mul(cashOut.getCashNumber(), 100.0))));
            map.put("randNo", randNo);
            JsonResult r = BusinessPayUtil.businessPay(map, request);
            if (r.getResult() != 0){
                return ResultUtil.fail("提现失败");
            }

            cashOut.setStatus((short)1);
            cashOut.setCountTime(new Date());
            cashOut.setPayUuid(randNo);
            cashOutMapper.updateByPrimaryKeySelective(cashOut);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail("操作失败");
        }
        return ResultUtil.success();
    }

    public ResultVO refuseState(CashOut cashOut) {
        cashOut.setStatus((short)2);
        int row = cashOutMapper.updateByPrimaryKeySelective(cashOut);
        if (row <= 0){
            return ResultUtil.fail("操作失败");
        }
        return ResultUtil.success();
    }
}
