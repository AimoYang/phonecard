package com.phonecard.controller;

import com.phonecard.bean.Advertisement;
import com.phonecard.bean.ResultVO;
import com.phonecard.form.AdvertisementForm;
import com.phonecard.service.AdvertisementService;
import com.phonecard.service.GoodsService;
import com.phonecard.service.RedisService;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/3 0003 16:29
 * @Description:
 */
@Api(tags = {"广告管理"})
@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/addAds")
    @ApiOperation("广告添加")
    public ResultVO addAds(@RequestBody @ApiParam("广告信息") AdvertisementForm advertisementForm){
        Advertisement advertisement = new Advertisement();
        BeanUtils.copyProperties(advertisementForm,advertisement);
        return advertisementService.addAds(advertisement);
    }

    @PostMapping("/updateAds")
    @ApiOperation("广告修改")
    public ResultVO updateAds(@RequestBody @ApiParam("广告信息") AdvertisementForm advertisementForm){
        Advertisement advertisement = new Advertisement();
        BeanUtils.copyProperties(advertisementForm,advertisement);
        return advertisementService.updateAds(advertisement);
    }

    @PostMapping("/selectAdsList")
    @ApiOperation("查询广告列表  -- 分页")
    public ResultVO selectAdsList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return advertisementService.selectAdsList(pageObject);
    }

    @PostMapping("/selectAdsGoods")
    @ApiOperation("查询广告已关联的商品  -- 分页")
    public ResultVO selectAdsGoods(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectAdsGoods(pageObject);
    }

    @PostMapping("/selectGoodsNoLink")
    @ApiOperation("查询广告未关联的商品  -- 分页")
    public ResultVO selectGoodsNoLink(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectGoodsNoLink(pageObject);
    }

    @PostMapping("/cancelGoodsAds")
    @ApiOperation("关联/取消关联商品和广告 ")
    public ResultVO linkGoodsAds(@RequestParam @ApiParam("adsId 广告id")Integer adsId,@RequestParam @ApiParam("商品uuid")String goodsUuid ,@RequestParam @ApiParam("1关联 0取消关联")Integer status){
        return advertisementService.linkGoodsAds(adsId,goodsUuid,status);
    }

}