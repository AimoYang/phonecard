package com.phonecard.service;

import com.phonecard.bean.Advertisement;
import com.phonecard.bean.ResultVO;
import com.phonecard.bean.Sku;
import com.phonecard.dao.AdvertisementMapper;
import com.phonecard.dao.GoodsMapper;
import com.phonecard.dao.SkuMapper;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 13:41
 * @Description:
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SkuMapper skuMapper;

    public ResultVO selectAdsGoods(PageObject pageObject) {
        Integer row = goodsMapper.getAdsGoodsRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectAdsList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO selectGoodsNoLink(PageObject pageObject) {
        Integer row = goodsMapper.getGoodsNoLinkRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectAdsLinKList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    private  List<GoodsVo> getPrice(List<GoodsVo> list){
        for (GoodsVo g: list) {
            Sku sku = skuMapper.selectMinSkuByGoods(g.getUuid());
            if(sku != null){
                g.setNewPrice(sku.getNewPrice());
                g.setOldPrice(sku.getOldPrice());
                g.setProperties(sku.getProperties());
            }else {
                g.setNewPrice((double)0);
                g.setOldPrice((double)0);
            }
        }
        return list;
    }

    public ResultVO selectFloorGoods(PageObject pageObject) {
        Integer row = goodsMapper.getFloorRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectFloorList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO selectGoodsNoLinkFloor(PageObject pageObject) {
        Integer row = goodsMapper.getFloorLinkRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectFloorLinkList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }
}
