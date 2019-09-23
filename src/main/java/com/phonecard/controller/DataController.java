package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.service.DataService;
import com.phonecard.util.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: Peng
 * @Date:  2019-09-10
 * @Description: 主页统计
 */
@Api(value = "统计controller", tags = {"数据统计"})
@RestController
@RequestMapping(value = "data", method = RequestMethod.POST)
public class DataController {
    @Autowired
    private DataService dataService;

    @ApiOperation(value = "主页统计")
    @RequestMapping(value = "/homeCount", method = RequestMethod.POST)
    public JsonResult homepageCount(Integer companyId) {
        if (companyId == null){
            return new JsonResult(StatusCode.SUCCESS,"请求成功！", dataService.getHomepageCount());
        } else{
            return new JsonResult(StatusCode.SUCCESS,"请求成功！", dataService.getHomepageCompanyCount(companyId));
        }
    }
}
