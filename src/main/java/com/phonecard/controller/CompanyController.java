package com.phonecard.controller;

import com.phonecard.bean.Company;
import com.phonecard.bean.JsonResult;
import com.phonecard.bean.OneSort;
import com.phonecard.bean.ResultVO;
import com.phonecard.service.CompanyService;
import com.phonecard.service.OrderService;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/4 0004 15:34
 * @Description:
 */
@Api(tags = {"公司管理"})
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "查询全部公司信息" , notes = "查询全部公司信息")
    @RequestMapping(value = "findCompanyAll" , method = RequestMethod.POST)
    public JsonResult findCompanyAll(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject) {
        JsonResult r = companyService.findCompanyAll(pageObject);
        return r;
    }

    @ApiOperation(value = "查询全部公司列表" , notes = "查询全部公司列表")
    @RequestMapping(value = "findCompanyAllList" , method = RequestMethod.POST)
    public JsonResult findCompanyAllList() {
        JsonResult r = companyService.findCompanyAllList();
        return r;
    }

    @ApiOperation(value = "编辑公司信息" , notes = "编辑公司信息")
    @RequestMapping(value = "updateCompanyInfo" , method = RequestMethod.POST)
    public JsonResult updateCompanyInfo(@ApiParam(value = "公司信息")@RequestBody Company company) {
        JsonResult r = companyService.updateCompanyInfo(company);
        return r;
    }

    @ApiOperation(value = "删除公司信息" , notes = "删除公司信息")
    @RequestMapping(value = "deleteCompany" , method = RequestMethod.POST)
    public JsonResult deleteCompany(@ApiParam(value = "公司id")@RequestParam Integer id) {
        JsonResult r = companyService.deleteCompany(id);
        return r;
    }

    @ApiOperation(value = "新增公司信息" , notes = "新增公司信息")
    @RequestMapping(value = "saveCompanyInfo" , method = RequestMethod.POST)
    public JsonResult saveCompanyInfo(@ApiParam(value = "公司信息")@RequestBody Company company) {
        JsonResult r = companyService.saveCompanyInfo(company);
        return r;
    }

    @ApiOperation(value = "更改公司密码信息" , notes = "编辑公司信息")
    @RequestMapping(value = "updateCompanyPassWord" , method = RequestMethod.POST)
    public JsonResult updateCompanyPassWord(@ApiParam(value = "公司信息")@RequestBody Company company) {
        JsonResult r = companyService.updateCompanyPassWord(company);
        return r;
    }

    @ApiOperation(value = "编辑公司信息" , notes = "编辑公司信息")
    @RequestMapping(value = "loginCompany" , method = RequestMethod.POST)
    public ResultVO loginCompany(@ApiParam(value = "公司信息")@RequestBody Company company) {
        return companyService.loginCompany(company);
    }

    @ApiOperation(value = "查看公司订单信息" , notes = "编辑公司信息")
    @RequestMapping(value = "findCompanyOrderInfo" , method = RequestMethod.POST)
    public ResultVO findCompanyOrderInfo(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject) {
        return orderService.findCompanyOrderInfo(pageObject);
    }

}
