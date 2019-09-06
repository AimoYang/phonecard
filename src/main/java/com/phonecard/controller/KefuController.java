package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.bean.Kefu;
import com.phonecard.service.KefuService;
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
@Api(value = "客服controller", tags = {"客服"})
@RequestMapping(value = "kefu", method = RequestMethod.POST)
public class KefuController {

    @Autowired
    private KefuService kefuService;

    /**
     * 分页查询客服信息
     *
     * @param pageObject
     * @return
     */
    @ApiOperation(value = "客服列表", notes = "分页查询")
    @RequestMapping(value = "getKefuInfo", method = RequestMethod.POST)
    public JsonResult getKefuInfo(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = kefuService.getSelfAddress(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    /**
     * 修改客服信息
     * @param kefuId     传入客服id
     * @param kefuWechat 传入客服微信号
     * @param kefuPhone  传入客服手机号
     * @return
     */
    @ApiOperation(value = "客服修改", notes = "修改信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(Integer kefuId, String kefuWechat, String kefuPhone) {
        JsonResult r = new JsonResult();
        Kefu kefu = kefuService.findById(kefuId);
        if (kefu != null) {
            kefu.setKefuPhone(kefuPhone);
            kefu.setKefuWechat(kefuWechat);
            kefu.setKefuCreateTime(new Date());
            try {
                boolean updateResult = kefuService.updateInfo(kefu);
                if (updateResult) {
                    r.setResult(StatusCode.SUCCESS);
                    r.setMsg("修改成功");
                } else {
                    r.setResult(StatusCode.FAIL);
                    r.setMsg("修改失败");
                }
            } catch (Exception e) {
                r.setResult(StatusCode.FAIL);
                r.setMsg("修改失败");
            }
        } else {
            r.setResult(StatusCode.FAIL);
            r.setMsg("修改失败");
        }
        return r;
    }
}
