package com.phonecard.service;

import com.phonecard.bean.*;
import com.phonecard.dao.*;
import com.phonecard.form.AddressForm;
import com.phonecard.form.CardInfoForm;
import com.phonecard.form.GoodsForm;
import com.phonecard.form.SkuForm;
import com.phonecard.util.PageObject;
import com.phonecard.util.RandomNum;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.GoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private CardInfoMapper cardInfoMapper;
    @Autowired
    private ReAdvertisementGoodsMapper reAdvertisementGoodsMapper;
    @Autowired
    private TourListBindMapper tourListBindMapper;
    @Autowired
    private TourListMapper tourListMapper;
    @Autowired
    private RelationGoodsAddressMapper relationGoodsAddressMapper;

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

    public ResultVO addGoods(GoodsForm goodsForm) {
        Goods goods = new Goods();
        Date now = new Date();
        String uuid = "Gs"+RandomNum.getRandomFileName();
        BeanUtils.copyProperties(goodsForm,goods);
        goods.setGoodsCreateTime(now);
        goods.setGoodsUpdateTime(now);
        goods.setIsDelete((short)0);
        goods.setUuid(uuid);
        List<SkuForm> list = goodsForm.getSkus();
        short type = 3;
        for (SkuForm skuForm: list) {
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuForm,sku);
            if (goods.getGoodsType() == 0){
                if (type == 3){
                    type = sku.getPickUp();
                }
                if (type == 2){
                    goods.setPickUp((short)2);
                }
                if (type != sku.getPickUp()){
                    type = 2;
                }
            }else {
                sku.setPickUp((short)1);
            }
            sku.setIsDelete((short)0);
            sku.setGoodsUuid(uuid);
            skuMapper.insertSelective(sku);
        }
        if (goods.getGoodsType() == 0){
            if (goods.getPickUp() == null){
                goods.setPickUp(type);
            }
        }else {
            goods.setPickUp((short)1);
        }
        if (goods.getGoodsType() == 0){
            if (goodsForm.getCardInfoForm() != null){
                CardInfoForm infoForm = goodsForm.getCardInfoForm();
                CardInfo cardInfo = new CardInfo();
                BeanUtils.copyProperties(infoForm,cardInfo);
                cardInfo.setIsDelete((short)0);
                cardInfo.setGoodsUuid(uuid);
                cardInfoMapper.insertSelective(cardInfo);
                List<RelationGoodsAddress> list1 = infoForm.getRelationGoodsAddress();
                for (RelationGoodsAddress r : list1) {
                    relationGoodsAddressMapper.insertSelective(r);
                }
            }else {
                return ResultUtil.fail("没有电话卡信息");
            }
        }
        goodsMapper.insertSelective(goods);
        return ResultUtil.success();
    }

    public ResultVO selectGoodsDetail(Integer id) {
        Goods goods = goodsMapper.selectGoodsDetail(id);
        if(goods == null){
            return ResultUtil.fail("商品不存在或被删除");
        }
        List<Sku> list = skuMapper.selectByGoodsUuid(goods.getUuid());
        goods.setSkus(list);
        if (goods.getGoodsType() == 0){
            CardInfo cardInfo = cardInfoMapper.selectCardInfo(goods.getUuid());
            cardInfo.setList(relationGoodsAddressMapper.selectList(goods.getUuid()));
            goods.setCardInfo(cardInfo);
        }
        return ResultUtil.success(goods);
    }

    public ResultVO editGoods(GoodsForm goodsForm) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsForm.getId());
        if(goods == null){
            return ResultUtil.fail("商品不存在");
        }
        Date now = new Date();
        BeanUtils.copyProperties(goodsForm,goods);
        goods.setGoodsUpdateTime(now);
        if (goodsForm.getSkus() != null){
            for (SkuForm skuFrom : goodsForm.getSkus()) {
                Sku sku = new Sku();
                BeanUtils.copyProperties(skuFrom,sku);
                if(skuFrom.getId() != null){
                    skuMapper.updateByPrimaryKeySelective(sku);
                }else {
                    sku.setIsDelete((short)0);
                    sku.setGoodsUuid(goods.getUuid());
                    skuMapper.insertSelective(sku);
                }
            }
        }
        if (goods.getGoodsType() == 0 && goodsForm.getCardInfoForm() != null){
            CardInfo cardInfo = new CardInfo();
            BeanUtils.copyProperties(goodsForm.getCardInfoForm(),cardInfo);
            cardInfoMapper.updateByPrimaryKeySelective(cardInfo);
            if (goodsForm.getCardInfoForm().getRelationGoodsAddress() != null){
                List<RelationGoodsAddress> list = goodsForm.getCardInfoForm().getRelationGoodsAddress();
                for (RelationGoodsAddress r:list) {
                    if (r.getId() != null){
                        relationGoodsAddressMapper.updateByPrimaryKeySelective(r);
                    }else {
                        relationGoodsAddressMapper.insertSelective(r);
                    }
                }
            }
        }
        goodsMapper.updateByPrimaryKeySelective(goods);
        return ResultUtil.success();
    }

    public ResultVO deleteGoodsLists(List<Integer> list) {
        for (Integer id: list){
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            List<ReAdvertisementGoods> list1 = reAdvertisementGoodsMapper.selectByGoods(goods.getUuid());
            if(list1 != null && list1.size() > 0){
                return ResultUtil.fail(goods.getGoodsName()+"已与广告关联");
            }
            Goods faGoods = new Goods();
            faGoods.setId(id);
            faGoods.setIsDelete((short)1);
            faGoods.setIsShelf((short)0);
            goodsMapper.updateByPrimaryKeySelective(faGoods);
        }
        return ResultUtil.success();
    }

    public ResultVO deleteGoods(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        List<ReAdvertisementGoods> list1 = reAdvertisementGoodsMapper.selectByGoods(goods.getUuid());
        if(list1 != null && list1.size() > 0){
            return ResultUtil.fail(goods.getGoodsName()+"已与广告关联");
        }
        Goods faGoods = new Goods();
        faGoods.setId(id);
        faGoods.setIsDelete((short)1);
        faGoods.setIsShelf((short)0);
        int row = goodsMapper.updateByPrimaryKeySelective(faGoods);
        if (row > 0){
            return ResultUtil.success();
        }
        return ResultUtil.fail("删除失败");
    }

    public ResultVO setOrCancelGoodsHotNew(Integer goodsId, Short status, Short type) {
        Goods goods = new Goods();
        goods.setId(goodsId);
        if (type == 1){
           goods.setIsNew(status);
        }
        if (type == 2){
           goods.setIsHot(status);
        }
        int row = goodsMapper.updateByPrimaryKeySelective(goods);
        if (row > 0){
            return ResultUtil.success();
        }
        return ResultUtil.fail("设置失败");
    }

    public ResultVO selectGoodsList(PageObject pageObject) {
        int row = goodsMapper.getGoodsListRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectGoodsList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO selectTourListNoLink(PageObject pageObject) {
        int row = goodsMapper.getTourListRow(pageObject);
        pageObject.setRowCount(row);
        List<GoodsVo> list = goodsMapper.selectTourListNoLink(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",getPrice(list));
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO cancelGoodsTourList(Integer tourId, String goodsUuid, Integer status) {
        TourListBind tourListBind  = tourListBindMapper.selectByTourList(tourId,goodsUuid);
        if(status == 0){
            if(tourListBind != null){
                tourListBindMapper.deleteByPrimaryKey(tourListBind.getId());
            }
        }else {
            if(tourListBind == null){
                TourListBind tourListBind1 = new TourListBind();
                tourListBind1.setTourId(tourId);
                tourListBind1.setGoodsUuid(goodsUuid);
                tourListBindMapper.insertSelective(tourListBind1);
            }
        }
        return ResultUtil.success();
    }

    public ResultVO selectTourList(PageObject pageObject) {
        int row = tourListMapper.getTourRow(pageObject);
        pageObject.setRowCount(row);
        List<TourList> list = tourListMapper.selectTourList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }
}
