package com.phonecard.dao;

import com.phonecard.bean.Goods;
import com.phonecard.util.PageObject;
import com.phonecard.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    Integer getAdsGoodsRow(PageObject pageObject);

    List<GoodsVo> selectAdsList(PageObject pageObject);

    Integer getGoodsNoLinkRow(PageObject pageObject);

    List<GoodsVo> selectAdsLinKList(PageObject pageObject);

    Integer getFloorRow(PageObject pageObject);

    List<GoodsVo> selectFloorList(PageObject pageObject);

    Integer getFloorLinkRow(PageObject pageObject);

    List<GoodsVo> selectFloorLinkList(PageObject pageObject);
}