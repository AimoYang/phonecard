package com.phonecard.controller;

import com.phonecard.bean.Comment;
import com.phonecard.bean.JsonResult;
import com.phonecard.service.CommentService;
import com.phonecard.util.PageObject;
import com.phonecard.util.StatusCode;
import com.qiniu.util.Json;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "评论controller" , tags = {"评论"})
@RequestMapping(value = "comment" , method = RequestMethod.POST)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "评论列表" , notes = "商品评价表")
    @RequestMapping(value = "/commentList" , method = RequestMethod.POST)
    public JsonResult commnetList(@ApiParam(value = "分页信息") @RequestBody PageObject pageObject,String nickname){
        JsonResult r = new JsonResult();
        try {
            Map<String, Object> map = commentService.getCommentList(pageObject,nickname);
            r.setData(map);
            r.setResult(StatusCode.SUCCESS);
            r.setMsg("OK");
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "评论详情" , notes = "商品评价详情")
    @RequestMapping(value = "/commentDetail" , method = RequestMethod.POST)
    public JsonResult commentDetail(Integer id){
        JsonResult r = new JsonResult();
        try {
            Comment comment = commentService.getCommentDetail(id);
            if (comment != null){
                r.setData(comment);
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("OK");
            } else{
                r.setData("");
                r.setResult(StatusCode.FAIL);
                r.setMsg("OK");
            }
        } catch (Exception e) {
            r.setMsg(e.getClass().getName() + ":" + e.getMessage());
            r.setResult(StatusCode.FAIL);
        }
        return r;
    }

    @ApiOperation(value = "评论删除" , notes = "删除评论")
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public JsonResult delete(Integer id){
        JsonResult r = new JsonResult();
        boolean result = commentService.deleteById(id);
        try{
            if (result){
                r.setData("删除成功");
                r.setResult(StatusCode.SUCCESS);
                r.setMsg("OK");
            } else{
                r.setData("");
                r.setResult(StatusCode.FAIL);
                r.setMsg("OK");
            }
        }catch (Exception e){

        }
        return r;
    }
}
