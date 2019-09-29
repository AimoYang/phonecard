package com.phonecard.dao;

import com.phonecard.bean.Leader;
import com.phonecard.util.PageObject;
import com.phonecard.vo.LeaderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leader record);

    int insertSelective(Leader record);

    Leader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leader record);

    int updateByPrimaryKey(Leader record);

    int getLeaderRow(@Param("pageObject") PageObject pageObject);

    List<LeaderVo> selectLeaderList(@Param("pageObject")PageObject pageObject);

    int setLeaderInfo(@Param("openId") String openId, @Param("status") Short status);

    LeaderVo selectLeaderDetail(Integer id);

    Integer selectOrderSum(@Param("openId") String openId);

    Integer selectUserSum(@Param("openId") String openId);

    List<Leader> selectLeaderAllList();

    int leaderUpdateCompany(@Param("id") Integer id, @Param("companyId") Integer companyId);
}