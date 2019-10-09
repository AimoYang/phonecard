package com.phonecard.dao;

import com.phonecard.bean.Share;
import com.phonecard.util.PageObject;
import com.phonecard.vo.ShareVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);

    int updateLeaderBind(@Param("agoOpenId") String agoOpenId, @Param("nowOpenId") String nowOpenId);

    int selectLeaderBind(@Param("openId") String openId);

    int getLeaderUserRow(@Param("pageObject") PageObject pageObject);

    List<ShareVo> selectLeaderUserList(@Param("pageObject")PageObject pageObject);

    Share selectLeader(@Param("openId")String openId);

    int checkOne(@Param("openId")String openId, @Param("leaderOpenId")String leaderOpenId);

    int deleteByOpenId(@Param("openId")String openId, @Param("leaderOpenId")String leaderOpenId);

    int checkOneEd(String openId);
}