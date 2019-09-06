package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.service.StateService;
import com.phonecard.util.PageObject;
import com.phonecard.util.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "国家controller", tags = {"国家"})
@RequestMapping(value = "state", method = RequestMethod.POST)
public class StateController {

    @Autowired
    private StateService stateService;

    @ApiOperation(value = "推荐国家列表", notes = "推荐国家表")
    @RequestMapping(value = "/stateRecommendList", method = RequestMethod.POST)
    public JsonResult stateRecommendList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = stateService.getStateRecomendList(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "设置/取消推荐国家", notes = "是否推荐0否 1是")
    @RequestMapping(value = "/stateRecommendCancel", method = RequestMethod.POST)
    public JsonResult stateRecommendCancel(Integer countryId,Integer isRecommend) {
        JsonResult r = new JsonResult();
        try {
            boolean cancelResult = stateService.stateRecomendCancel(countryId, isRecommend);
            r.setData(cancelResult);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }


    @ApiOperation(value = "热门国家列表", notes = "热门国家表")
    @RequestMapping(value = "/stateHotList", method = RequestMethod.POST)
    public JsonResult stateHotList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = stateService.getStateHotList(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "设置/取消热门国家", notes = "是否推荐0否 1是")
    @RequestMapping(value = "/stateHotCancel", method = RequestMethod.POST)
    public JsonResult stateHotCancel(Integer countryId, Integer isHot) {
        JsonResult r = new JsonResult();
        try {
            boolean cancelResult = stateService.stateHotCancel(countryId, isHot);
            r.setData(cancelResult);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }
}
