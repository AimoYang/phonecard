package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.service.BaseUserService;
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
@Api(value = "用户controller", tags = {"用户"})
@RequestMapping(value = "user", method = RequestMethod.POST)
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @ApiOperation(value = "用户列表", notes = "用户表")
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public JsonResult commnetList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = baseUserService.getUserList(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "禁用启用用户", notes = "用户状态禁用启用 是否禁用（0否1是）")
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    public JsonResult updateUserStatus(Integer userId, Integer status) {
        JsonResult r = new JsonResult();
        try {
            boolean updateStatus = baseUserService.updateUserStatus(userId, status);
            if (updateStatus) {
                r.setData("修改成功");
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("OK");
            } else {
                r.setMsg("修改失败");
                r.setResult(StatusCode.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

}
