package com.phonecard.service;

import com.phonecard.bean.Floor;
import com.phonecard.bean.FloorBind;
import com.phonecard.bean.ResultVO;
import com.phonecard.bean.Sku;
import com.phonecard.dao.FloorBindMapper;
import com.phonecard.dao.FloorMapper;
import com.phonecard.util.PageObject;
import com.phonecard.util.ResultUtil;
import com.phonecard.vo.FloorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 17:29
 * @Description:
 */
@Service
public class FloorService {

    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private FloorBindMapper floorBindMapper;

    public ResultVO addFloor(Floor floor) {
        floor.setCreateTime(new Date());
        if (floorMapper.insertSelective(floor) < 0){
            return ResultUtil.fail("添加失败");
        }
        return ResultUtil.success();
    }

    public ResultVO getFloorDetail(Integer id) {
        FloorVo floorVo = floorMapper.selectFloorDetail(id);
        return ResultUtil.success(floorVo);
    }

    public ResultVO updateFloor(Floor floor) {
        if (floorMapper.updateByPrimaryKeySelective(floor) < 0){
            return ResultUtil.fail("修改失败");
        }
        return ResultUtil.success();
    }

    public ResultVO selectFloorList(PageObject pageObject) {
        Integer row = floorMapper.getFloorListRow(pageObject);
        pageObject.setRowCount(row);
        List<FloorVo> list = floorMapper.selectList(pageObject);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("pageObject",pageObject);
        return ResultUtil.success(map);
    }

    public ResultVO linkOrCancelGoodsFloor(Integer floorId, String uuid, Integer status) {
        FloorBind floorBind = floorBindMapper.selectByFloorAndGoods(floorId,uuid);
        if(status == 0){
            if(floorBind != null){
                floorBindMapper.deleteByPrimaryKey(floorBind.getId());
            }
        }else {
            if(floorBind == null){
                FloorBind floorBind1 = new FloorBind();
                floorBind1.setFloorId(floorId);
                floorBind1.setGoodsUuid(uuid);
                floorBindMapper.insertSelective(floorBind1);
            }
        }
        return ResultUtil.success();
    }
}
