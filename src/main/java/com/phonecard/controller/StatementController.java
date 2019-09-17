package com.phonecard.controller;

import com.phonecard.bean.CashOut;
import com.phonecard.bean.ResultVO;
import com.phonecard.service.StatementService;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/4 0004 15:37
 * @Description:
 */
@Api(tags = {"结算管理"})
@RestController
public class StatementController {


    @Autowired
    private StatementService statementService;

    @PostMapping("/selectStateList")
    @ApiOperation("结算列表查询")
    public ResultVO selectStateList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return statementService.selectStateList(pageObject);
    }

    @PostMapping("/agreeState")
    @ApiOperation("同意结算")
    public ResultVO agreeState(@ApiParam(value = "结算信息")@RequestBody CashOut cashOut, HttpServletRequest request){
        return statementService.agreeState(cashOut, request);
    }

    @PostMapping("/refuseState")
    @ApiOperation("拒绝结算")
    public ResultVO refuseState(@ApiParam(value = "结算信息")@RequestBody CashOut cashOut){
        return statementService.refuseState(cashOut);
    }
}
