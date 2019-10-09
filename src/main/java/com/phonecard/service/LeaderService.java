package com.phonecard.service;

import com.phonecard.bean.*;
import com.phonecard.dao.*;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.LeaderVo;
import com.phonecard.vo.ShareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/11 0011 14:42
 * @Description:
 */
@Service
public class LeaderService {

    @Autowired
    private LeaderMapper leaderMapper;
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private UserBaseMapper userBaseMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyBindMapper companyBindMapper;

    public ResultVO selectLeaderList(PageObject pageObject) {
        int row = leaderMapper.getLeaderRow(pageObject);
        pageObject.setRowCount(row);
        List<LeaderVo> list = leaderMapper.selectLeaderList(pageObject);
        for (LeaderVo l : list) {
            l.setUserNum(leaderMapper.selectUserSum(l.getOpenId()));
            l.setConsumeSum(leaderMapper.selectOrderSum(l.getOpenId()));
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("list", list);
        map.put("pageObject", pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO updateLeaderBind(String agoOpenId, String nowOpenId) {
        int row = shareMapper.updateLeaderBind(agoOpenId, nowOpenId);
        if (row <= 0){
            int count = shareMapper.selectLeaderBind(agoOpenId);
            if (count == 0){
                return ResultUtil.fail("该团长旗下没有用户");
            }
           return ResultUtil.fail("更换失败");
        }
        int row1 = leaderMapper.setLeaderInfo(agoOpenId, (short)1);
        if (row1 <= 0){
            return ResultUtil.fail("更新失败");
        }
        return ResultUtil.success();
    }

    public ResultVO setLeaderInfo(String openId, Short status) {
        if (status == 1){
           int count = shareMapper.selectLeaderBind(openId);
           if (count > 1){
               return ResultUtil.fail("该团长下有用户");
           }
        }
        int row = leaderMapper.setLeaderInfo(openId, status);
        if (row <= 0){
            return ResultUtil.fail("更新失败");
        }
        return ResultUtil.success();
    }

    public ResultVO selectLeaderDetail(Integer id) {
        LeaderVo leaderVo = leaderMapper.selectLeaderDetail(id);
        leaderVo.setUserNum(leaderMapper.selectUserSum(leaderVo.getOpenId()));
        leaderVo.setConsumeSum(leaderMapper.selectOrderSum(leaderVo.getOpenId()));
        return ResultUtil.success(leaderVo);
    }

    public ResultVO selectLeaderUserList(PageObject pageObject) {
        int row = shareMapper.getLeaderUserRow(pageObject);
        pageObject.setRowCount(row);
        List<ShareVo> list = shareMapper.selectLeaderUserList(pageObject);
        int sum = leaderMapper.selectOrderSum(pageObject.getOpenId());
        Map<String, Object> map = new HashMap<>(3);
        map.put("list", list);
        map.put("pageObject", pageObject);
        map.put("OrderSum",sum);
        return ResultUtil.success(map);
    }

    public ResultVO agreeLeader(Leader leader) {
        try {
            Company company = companyMapper.selectByPrimaryKey(leader.getCompanyId());
            if (company == null){
                return ResultUtil.fail("申请公司不存在");
            }
            leader.setInType((short)1);
            leader.setLeaderCompanyName(company.getCompanyName());
            leaderMapper.updateByPrimaryKeySelective(leader);

            Share share = shareMapper.selectLeader(leader.getOpenId());
            if (share == null){
                Share share1 = new Share();
                share1.setOpenId(leader.getOpenId());
                share1.setCreateTime(new Date());
                share1.setOneOpenId(leader.getOpenId());
                share1.setInType((short)1);
                shareMapper.insertSelective(share1);
            }else {
                share.setOneOpenId(leader.getOpenId());
                shareMapper.updateByPrimaryKeySelective(share);
            }

            CompanyBind companyBind = new CompanyBind();
            companyBind.setCompanyId(leader.getCompanyId());
            companyBind.setLeaderId(leader.getId());
            companyBindMapper.insertSelective(companyBind);

            UserBase userBase = userBaseMapper.selectByOpenId(leader.getOpenId());
            userBase.setUserType((short)2);
            userBaseMapper.updateByPrimaryKeySelective(userBase);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail("设置失败");
        }
        return ResultUtil.success();
    }

    public ResultVO refuseLeader(Leader leader) {
        leader.setInType((short)2);
        int row = leaderMapper.updateByPrimaryKeySelective(leader);
        if (row <= 0){
            return ResultUtil.fail("设置失败");
        }
        return ResultUtil.success();
    }

    public ResultVO selectLeaderAllList() {
        List<Leader> list = leaderMapper.selectLeaderAllList();
        return ResultUtil.success(list);
    }

    public ResultVO leaderUpdateCompany(Integer id, Integer companyId) {
        int update  = leaderMapper.leaderUpdateCompany(id,companyId);
        if(update == 0){
            return ResultUtil.fail("设置失败");
        }
        return ResultUtil.success();
    }

    public ResultVO leaderListForEdit(PageObject pageObject) {
        int row = leaderMapper.queryLeaderListRow(pageObject.getCompanyName());
        pageObject.setRowCount(row);
        List<LeaderVo> leaderVoList = leaderMapper.queryLeaderList(pageObject);
        leaderVoList.forEach(leaderVo -> {
            int count = shareMapper.checkOne(pageObject.getOpenId(),leaderVo.getOpenId());
            if(count >0){
                leaderVo.setRelation(1);
            }else{
                leaderVo.setRelation(0);
            }
        });
        Map<String, Object> map = new HashMap<>(2);
        map.put("list", leaderVoList);
        map.put("pageObject", pageObject);
        return ResultUtil.success(map);
    }
}
