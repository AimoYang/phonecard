package com.phonecard.service;

import com.phonecard.bean.Advertisement;
import com.phonecard.bean.ReAdvertisementGoods;
import com.phonecard.bean.ResultVO;
import com.phonecard.dao.AdvertisementMapper;
import com.phonecard.dao.ReAdvertisementGoodsMapper;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.AdvertisementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 13:41
 * @Description:
 */
@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private ReAdvertisementGoodsMapper reAdvertisementGoodsMapper;

    public ResultVO addAds(Advertisement advertisement) {
        advertisement.setCreateTime(new Date());
        if (advertisementMapper.insertSelective(advertisement) < 0){
            return ResultUtil.fail("添加失败");
        }
        return ResultUtil.success();
    }

    public ResultVO updateAds(Advertisement advertisement) {
        if (advertisementMapper.updateByPrimaryKeySelective(advertisement) < 0){
            return ResultUtil.fail("修改失败");
        }
        return ResultUtil.success();
    }

    public ResultVO selectAdsList(PageObject pageObject) {
        Map<String,Object> map = new HashMap<>(2);
        List<AdvertisementVo> list = advertisementMapper.selectList(pageObject);
        int row = advertisementMapper.getAdsListRow(pageObject);
        pageObject.setRowCount(row);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO linkGoodsAds(Integer adsId, String goodsUuid, Integer status) {
        ReAdvertisementGoods reAdvertisementGoods  = reAdvertisementGoodsMapper.selectByAdsAndGoods(adsId,goodsUuid);
        if(status == 0){
            if(reAdvertisementGoods != null){
                reAdvertisementGoodsMapper.deleteByPrimaryKey(reAdvertisementGoods.getId());
            }
        }else {
            if(reAdvertisementGoods == null){
                ReAdvertisementGoods re = new ReAdvertisementGoods();
                re.setAdvertisementId(adsId);
                re.setGoodsUuid(goodsUuid);
                reAdvertisementGoodsMapper.insertSelective(re);
            }
        }
        return ResultUtil.success();
    }
}
