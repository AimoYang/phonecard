package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *@Auther: FishXu
 * @Date:
 * @Description:
 */
@Api(value = "统计controller" , tags = {"数据统计"})
@RestController
@RequestMapping(value = "data" , method = RequestMethod.POST)
public class DataController {

    @ApiOperation(value = "订单统计" , notes = "根据时间筛选销量")
    @RequestMapping(value = "/orderCount" , method = RequestMethod.POST)
    public JsonResult orderCount(Date startDate, Date endDate){
        JsonResult r = new JsonResult();
        return r;
    }

    @ApiOperation(value = "销售统计" , notes = "根据时间筛选销量")
    @RequestMapping(value = "/saleCount" , method = RequestMethod.POST)
    public JsonResult saleCount(Date startDate, Date endDate){
        JsonResult r = new JsonResult();
        return r;
    }
}
