package com.phonecard.controller;

import com.phonecard.bean.Leader;
import com.phonecard.bean.ResultVO;
import com.phonecard.dao.LeaderMapper;
import com.phonecard.service.LeaderService;
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

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/4 0004 15:35
 * @Description:
 */
@Api(tags = {"团长管理"})
@RestController
public class LeaderController {

    @Autowired
    private LeaderService leaderService;

    @PostMapping("/selectLeaderList")
    @ApiOperation("团长列表查询")
    public ResultVO selectLeaderList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return leaderService.selectLeaderList(pageObject);
    }

    @PostMapping("/selectLeaderAllList")
    @ApiOperation("团长全部查询")
    public ResultVO selectLeaderAllList(){
        return leaderService.selectLeaderAllList();
    }

    @PostMapping("/updateLeaderBind")
    @ApiOperation("更换绑定团长")
    public ResultVO updateLeaderBind(@RequestParam @ApiParam(value = "前团长的openId，现团长的openId") String agoOpenId ,String nowOpenId){
        return leaderService.updateLeaderBind(agoOpenId, nowOpenId);
    }

    @PostMapping("/setLeaderInfo")
    @ApiOperation("启用禁用团长")
    public ResultVO setLeaderInfo(@RequestParam @ApiParam("团长openId")String openId,@RequestParam @ApiParam("1禁用 0启用")Short status){
        if(status != 0 && status != 1){
            return ResultUtil.fail("类型错误");
        }
        return leaderService.setLeaderInfo(openId, status);
    }

    @PostMapping("/selectLeaderDetail")
    @ApiOperation("查看团长详情")
    public ResultVO selectLeaderDetail(@RequestParam @ApiParam("团长id")Integer id){
        return leaderService.selectLeaderDetail(id);
    }

    @PostMapping("/selectLeaderUserList")
    @ApiOperation("旗下用户列表")
    public ResultVO selectLeaderUserList(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return leaderService.selectLeaderUserList(pageObject);
    }

    @PostMapping("/agreeLeader")
    @ApiOperation("同意团长")
    public ResultVO agreeLeader(@ApiParam(value = "同意团长") @RequestBody Leader leader){
        return leaderService.agreeLeader(leader);
    }

    @PostMapping("/refuseLeader")
    @ApiOperation("拒绝团长")
    public ResultVO refuseLeader(@ApiParam(value = "拒绝团长") @RequestBody Leader leader){
        return leaderService.refuseLeader(leader);
    }

}
