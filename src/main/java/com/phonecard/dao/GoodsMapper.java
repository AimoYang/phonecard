package com.phonecard.dao;

import com.phonecard.bean.Goods;
import com.phonecard.util.PageObject;
import com.phonecard.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Integer getAdsGoodsRow(@Param("pageObject") PageObject pageObject);

    List<GoodsVo> selectAdsList(@Param("pageObject")PageObject pageObject);

    Integer getGoodsNoLinkRow(@Param("pageObject")PageObject pageObject);

    List<GoodsVo> selectAdsLinKList(@Param("pageObject")PageObject pageObject);

    Integer getFloorRow(@Param("pageObject")PageObject pageObject);

    List<GoodsVo> selectFloorList(@Param("pageObject")PageObject pageObject);

    Integer getFloorLinkRow(@Param("pageObject")PageObject pageObject);

    List<GoodsVo> selectFloorLinkList(@Param("pageObject")PageObject pageObject);

    Goods selectGoodsDetail(@Param("id") Integer id);

    int getGoodsListRow(@Param("pageObject")PageObject pageObject);

    List<GoodsVo> selectGoodsList(@Param("pageObject")PageObject pageObject);
}