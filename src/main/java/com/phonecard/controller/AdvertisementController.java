package com.phonecard.controller;

import com.phonecard.bean.Advertisement;
import com.phonecard.bean.ResultVO;
import com.phonecard.bean.TourList;
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

    @PostMapping("/addTourList")
    @ApiOperation("旅游清单添加")
    public ResultVO addTourList(@RequestBody @ApiParam("旅游清单信息") TourList tourList){
        return advertisementService.addTourList(tourList);
    }

    @PostMapping("/deleteTourList")
    @ApiOperation("旅游清单删除")
    public ResultVO deleteTourList(@RequestParam @ApiParam("旅游清单id") Integer id){
        return advertisementService.deleteTourList(id);
    }

    @PostMapping("/selectTourListGoods")
    @ApiOperation("查询旅游清单已关联的商品  -- 分页")
    public ResultVO selectTourListGoods(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectTourListGoods(pageObject);
    }

    @PostMapping("/selectTourListNoLink")
    @ApiOperation("查询旅游清单未关联的商品  -- 分页")
    public ResultVO selectTourListNoLink(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectTourListNoLink(pageObject);
    }

    @PostMapping("/selectTourList")
    @ApiOperation("查询旅游清单  -- 分页")
    public ResultVO selectTourList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectTourList(pageObject);
    }

    @PostMapping("/updateTourList")
    @ApiOperation("旅游清单修改")
    public ResultVO updateTourList(@RequestBody @ApiParam("旅游清单信息") TourList tourList){
        return advertisementService.updateTourList(tourList);
    }

    @PostMapping("/cancelGoodsTourList")
    @ApiOperation("关联/取消关联商品和旅游清单 ")
    public ResultVO cancelGoodsTourList(@RequestParam @ApiParam("旅游清单id")Integer tourId,@RequestParam @ApiParam("商品uuid")String goodsUuid ,@RequestParam @ApiParam("1关联 0取消关联")Integer status){
        return goodsService.cancelGoodsTourList(tourId,goodsUuid,status);
    }

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
