package com.phonecard.controller;

import com.phonecard.bean.Coupon;
import com.phonecard.bean.JsonResult;
import com.phonecard.service.CouponService;
import com.phonecard.util.PageObject;
import com.phonecard.util.StatusCode;
import com.phonecard.util.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@Api(value = "优惠券controller", tags = {"优惠券"})
@RequestMapping(value = "coupon", method = RequestMethod.POST)
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation(value = "优惠券列表", notes = "优惠券表")
    @RequestMapping(value = "/couponList", method = RequestMethod.POST)
    public JsonResult commnetList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject) {
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = couponService.getCouponList(pageObject);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "优惠券新增", notes = "新增优惠券")
    @RequestMapping(value = "/couponAdd", method = RequestMethod.POST)
    public JsonResult couponAdd(String couponName, Integer couponNum, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date fromDate,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, Double fullMoney, Double subMoney) {

        JsonResult r = new JsonResult();
        try {
            Coupon coupon = new Coupon();
            coupon.setUuid(UUIDGenerator.generate());
            coupon.setIsDelete((short) 0);
            coupon.setCouponName(couponName);
            coupon.setStartTime(fromDate);
            coupon.setEndTime(endDate);
            coupon.setFullMoney(fullMoney);
            coupon.setSubMoney(subMoney);
            coupon.setCreateTime(new Date());
            coupon.setCouponNum(couponNum);
            boolean addResult = couponService.addCoupon(coupon);
            if (addResult) {
                r.setMsg("新增成功");
                r.setResult(StatusCode.SUCCESS);
            } else {
                r.setMsg("新增失败");
                r.setResult(StatusCode.FAIL);
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "优惠券删除", notes = "删除优惠券")
    @RequestMapping(value = "/couponDelete", method = RequestMethod.POST)
    public JsonResult couponDelete(Integer id) {
        JsonResult r = new JsonResult();
        try {
            boolean addResult = couponService.deleteCoupon(id);
            if (addResult) {
                r.setMsg("删除成功");
                r.setResult(StatusCode.SUCCESS);
            } else {
                r.setMsg("删除失败");
                r.setResult(StatusCode.FAIL);
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "优惠券修改", notes = "修改优惠券")
    @RequestMapping(value = "/couponUpdate", method = RequestMethod.POST)
    public JsonResult couponUpdate(Coupon coupon) {
        JsonResult r = new JsonResult();
        try {
            boolean addResult = couponService.couponUpdate(coupon);
            if (addResult) {
                r.setMsg("修改成功");
                r.setResult(StatusCode.SUCCESS);
            } else {
                r.setMsg("修改失败");
                r.setResult(StatusCode.FAIL);
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }
}
