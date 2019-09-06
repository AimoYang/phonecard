package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.service.FeedbackService;
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

import java.util.Date;
import java.util.Map;

@RestController
@Api(value = "意见反馈controller" , tags = {"意见反馈"})
@RequestMapping(value = "feedback" , method = RequestMethod.POST)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(value = "意见反馈" , notes = "意见反馈查看")
    @RequestMapping(value = "/feedbackList" , method = RequestMethod.POST)
    public JsonResult feedbackList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject){
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = feedbackService.getCommentList(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
            e.printStackTrace();
        }
        return r;
    }

}
