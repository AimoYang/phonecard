package com.phonecard.controller;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/8/21 0021 17:28
 * @Description:
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonecard.bean.Admin;
import com.phonecard.bean.JsonResult;
import com.phonecard.service.AdminService;
import com.phonecard.service.RedisService;
import com.phonecard.util.MD5Password;
import com.phonecard.util.StatusCode;
import com.phonecard.util.UUIDGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "登录controller" , tags = {"登录操作"})
@RestController
public class LoginController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "用户登录" , notes = "根据url登录")
    @RequestMapping(value = "login" , method = RequestMethod.POST)
    public JsonResult login(@ApiParam("用户信息") @RequestBody Admin loginAdmin){
        JsonResult r = new JsonResult();
        try {
            String password = MD5Password.MD5(loginAdmin.getPassword());
            loginAdmin.setPassword(password);
            Admin accountInfo = adminService.checkUserInfo(loginAdmin);
            if(accountInfo != null){
                //获取角色和权限
                if (accountInfo.getIsEnable() != 0) {
                    r.setResult(StatusCode.FAIL);
                    r.setMsg("该账号已被禁用");
                    return r;
                }
                String mytoken = UUIDGenerator.generate() + "_bg";
                Map<String, Object> map = new HashMap<String,Object>();
                map.put("token", mytoken);
                map.put("accountInfo", accountInfo);
                redisService.set(mytoken, accountInfo , (long)36000);
                accountInfo.setPassword(null);
                map.put("accountInfo", accountInfo);
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("登录成功");
                r.setData(map);
            }else {
                r.setMsg("账号或密码错误");
                r.setResult(1);
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(1);
            e.printStackTrace();
        }
        return r;
    }


    @ApiOperation(value = "退出登录" , notes = "根据url退出登录")
    @RequestMapping(value = "quit" , method = RequestMethod.GET)
    public JsonResult quit(HttpServletRequest request){
        String token = request.getHeader("x-access-token");
        System.out.println("---------------" + token);
        JsonResult r = new JsonResult();
        try {
            redisService.remove(token);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("ok");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(1);
            e.printStackTrace();
        }
        return r;
    }

}
