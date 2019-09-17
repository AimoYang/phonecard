package com.phonecard.dao;

import com.phonecard.bean.RelationGoodsAddress;
import com.phonecard.vo.RelationGoodsAddressVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RelationGoodsAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationGoodsAddress record);

    int insertSelective(RelationGoodsAddress record);

    RelationGoodsAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationGoodsAddress record);

    int updateByPrimaryKey(RelationGoodsAddress record);

    List<RelationGoodsAddressVo> selectList(@Param("uuid") String uuid);
}