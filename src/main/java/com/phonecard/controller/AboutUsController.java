package com.phonecard.controller;

import com.phonecard.bean.AboutUs;
import com.phonecard.bean.JsonResult;
import com.phonecard.service.AboutUsService;
import com.phonecard.util.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fish_Xu
 */
@Api(value = "关于我们controller" , tags = {"关于我们"})
@RestController
@RequestMapping(value = "aboutUs" , method = RequestMethod.POST)
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @ApiOperation(value = "获取关于我们" , notes = " ")
    @RequestMapping(value = "/getInfo" , method = RequestMethod.POST)
    public JsonResult info(){
        JsonResult r = new JsonResult();
        try {
            AboutUs aboutUs = aboutUsService.getInfo();
            r.setMsg("OK");
            r.setResult(StatusCode.SUCCESS);
            r.setData(aboutUs);
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
            e.printStackTrace();
        }
        return r;
    }

    @ApiOperation(value = "更新关于我们" , notes = " 传id为1 ")
    @RequestMapping(value = "/updateInfo" , method = RequestMethod.POST)
    public JsonResult updateInfo(AboutUs aboutUs){
        JsonResult r = new JsonResult();
        try {
            boolean result = aboutUsService.updateInfo(aboutUs);
            if (result){
                r.setMsg("OK");
                r.setResult(StatusCode.SUCCESS);
                r.setData(result);
            } else{
                r.setMsg("更新失败");
                r.setResult(StatusCode.FAIL);
                r.setData("更新失败");
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
            e.printStackTrace();
        }
        return r;
    }
}
