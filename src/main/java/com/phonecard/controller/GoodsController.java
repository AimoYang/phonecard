package com.phonecard.controller;

import com.phonecard.bean.ResultVO;
import com.phonecard.form.GoodsForm;
import com.phonecard.form.ListForm;
import com.phonecard.service.GoodsService;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/3 0003 16:22
 * @Description:
 */
@Api(tags = {"商品管理"})
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/copyGoods")
    @ApiOperation("复制商品")
    public ResultVO copyGoods(@RequestParam @ApiParam("商品id")Integer id){
        return goodsService.copyGoods(id);
    }

    @PostMapping("/addGoods")
    @ApiOperation("商品添加")
    public ResultVO getAllShops(@RequestBody @ApiParam("商品信息") @Valid GoodsForm goodsForm){
        return goodsService.addGoods(goodsForm);
    }

    @PostMapping("/selectGoodsDetail")
    @ApiOperation("商品详情查询")
    public ResultVO selectGoodsDetail(@RequestParam @ApiParam("商品id")Integer id){
        return goodsService.selectGoodsDetail(id);
    }

    @PostMapping("/editGoods")
    @ApiOperation("商品编辑")
    public ResultVO editGoods(@RequestBody @ApiParam("商品信息") GoodsForm goodsForm){
        return goodsService.editGoods(goodsForm);
    }

    @PostMapping("/selectGoodsList")
    @ApiOperation("商品列表查询")
    public ResultVO selectGoodsList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return goodsService.selectGoodsList(pageObject);
    }

    @PostMapping("/deleteGoodsLists")
    @ApiOperation("商品批量删除")
    public ResultVO deleteGoodsLists(@RequestBody @ApiParam("商品id 集合")ListForm list){
        return goodsService.deleteGoodsLists(list.getList());
    }

    @PostMapping("/deleteGoods")
    @ApiOperation("商品单个删除")
    public ResultVO deleteGoods(@RequestParam @ApiParam("商品id")Integer id){
        return goodsService.deleteGoods(id);
    }


    @PostMapping("/setOrCancelGoodsHotNew")
    @ApiOperation("设置/取消商品活动 1新品推荐2热销商品 ")
    public ResultVO setOrCancelGoodsHotNew(@RequestParam @ApiParam("商品Id")Integer goodsId,@RequestParam @ApiParam("1关联 0取消关联")Short status,@RequestParam @ApiParam("1新品推荐2热销商品 ")Short type){
        if(type != 1 && type != 2){
            return ResultUtil.fail("类型错误");
        }
        return goodsService.setOrCancelGoodsHotNew(goodsId,status,type);
    }

}
