package com.phonecard.controller;

import com.phonecard.bean.AddressSelf;
import com.phonecard.bean.JsonResult;
import com.phonecard.service.SelfAddressService;
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

@Api(value = "自提地址controller", tags = {"自提"})
@RestController
@RequestMapping(value = "selfAddress", method = RequestMethod.POST)
public class SelfAddressController {

    @Autowired
    private SelfAddressService selfAddressService;

    @ApiOperation(value = "自提地址", notes = "分页查询")
    @RequestMapping(value = "/getSelfAddress", method = RequestMethod.POST)
    public JsonResult getSelfAddress(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = selfAddressService.getSelfAddress(pageObject);
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


    @ApiOperation(value = "自提地址", notes = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult addAddress(@ApiParam("自提地址") @RequestBody AddressSelf addressSelf) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = selfAddressService.addAddress(addressSelf);
            r.setData(map);
            r.setMsg((String) map.get("msg"));
        } catch (Exception e) {
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "自提地址", notes = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult deleteAddress(@ApiParam("自提地址") @RequestBody AddressSelf addressSelf) {
        JsonResult r = new JsonResult();
        try {
            Boolean addResult = selfAddressService.deleteAddress(addressSelf);
            if (addResult) {
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("OK");
            } else {
                r.setResult(StatusCode.FAIL);
                r.setMsg("删除失败！");
            }
        } catch (Exception e) {
            r.setResult(StatusCode.FAIL);
            r.setMsg("删除失败！");
        }
        return r;
    }

    @ApiOperation(value = "自提地址", notes = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult updateAddress(@ApiParam("自提地址") @RequestBody AddressSelf addressSelf) {
        JsonResult r = new JsonResult();
        try {
            Boolean addResult = selfAddressService.updateAddress(addressSelf);
            if (addResult) {
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("OK");
            } else {
                r.setResult(StatusCode.FAIL);
                r.setMsg("修改失败！");
            }
        } catch (Exception e) {
            r.setResult(StatusCode.FAIL);
            r.setMsg("修改失败！");
        }
        return r;
    }

}
