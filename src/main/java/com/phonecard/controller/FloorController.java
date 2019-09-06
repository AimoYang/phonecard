package com.phonecard.controller;

import com.phonecard.bean.Floor;
import com.phonecard.bean.ResultVO;
import com.phonecard.form.FloorForm;
import com.phonecard.service.FloorService;
import com.phonecard.service.GoodsService;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/3 0003 16:24
 * @Description:
 */
@Api(tags = {"楼层广告管理"})
@RestController
public class FloorController {

    @Autowired
    private FloorService floorService;
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/addFloor")
    @ApiOperation("楼层广告添加")
    public ResultVO addFloor(@RequestBody @ApiParam("楼层信息") FloorForm floorForm){
        Floor floor = new Floor();
        BeanUtils.copyProperties(floorForm,floor);
        return floorService.addFloor(floor);
    }

    @PostMapping("/getFloorDetail")
    @ApiOperation("楼层广告详情")
    public ResultVO getFloorDetail(@RequestParam Integer id){
        return floorService.getFloorDetail(id);
    }

    @PostMapping("/updateFloor")
    @ApiOperation("楼层广告修改")
    public ResultVO updateFloor(@RequestBody @ApiParam("楼层信息") FloorForm floorForm){
        Floor floor = new Floor();
        BeanUtils.copyProperties(floorForm,floor);
        return floorService.updateFloor(floor);
    }

    @PostMapping("/selectFloorList")
    @ApiOperation("查询广告列表  -- 分页")
    public ResultVO selectFloorList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return floorService.selectFloorList(pageObject);
    }


    @PostMapping("/selectFloorGoods")
    @ApiOperation("查询广告已关联的商品  -- 分页")
    public ResultVO selectFloorGoods(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectFloorGoods(pageObject);
    }

    @PostMapping("/selectGoodsNoLinkFloor")
    @ApiOperation("查询广告未关联的商品  -- 分页")
    public ResultVO selectGoodsNoLinkFloor(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectGoodsNoLinkFloor(pageObject);
    }

    @PostMapping("/linkOrCancelGoodsFloor")
    @ApiOperation("关联/取消关联商品和广告 ")
    public ResultVO linkOrCancelGoodsFloor(@RequestParam @ApiParam("广告id")Integer floorId,@RequestParam @ApiParam("商品规格uuid")String uuid ,@RequestParam @ApiParam("1关联 0取消关联")Integer status){
        return floorService.linkOrCancelGoodsFloor(floorId,uuid,status);
    }

}
