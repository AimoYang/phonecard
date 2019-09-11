package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.bean.OrderDto;
import com.phonecard.enums.OrderEnums;
import com.phonecard.service.OrderService;
import com.phonecard.util.DateUtil;
import com.phonecard.util.PageObject;
import com.phonecard.bean.ResultVO;
import com.phonecard.service.OrderService;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/3 0003 16:23
 * @Description:
 */
@Api(tags = {"订单管理"})
@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/ordersListSelect")
    @ApiOperation("商品订单列表查询")
    public ResultVO ordersListSelect(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return orderService.ordersListSelect(pageObject);
    }

    @PostMapping("/ordersDetailSelect")
    @ApiOperation("商品订单详情查询")
    public ResultVO ordersDetailSelect(@RequestParam Integer id){
        return orderService.ordersDetailSelect(id);
    }

    @PostMapping("/cancelOrdersDetailSelect")
    @ApiOperation("退款商品订单详情查询")
    public ResultVO cancelOrdersDetailSelect(@RequestParam String orderUuid){
        return orderService.cancelOrdersDetailSelect(orderUuid);
    }

    @ApiOperation(value = "查询申请取消的订单", notes = "查询申请取消的订单")
    @PostMapping(value = "selectCancelOrders")
    public ResultVO selectCancelOrders(@ApiParam(value = "分页信息")@RequestBody PageObject pageObject){
        return orderService.selectCancelOrders(pageObject);
    }

    @ApiOperation(value = "拒绝取消订单", notes = "拒绝取消订单")
    @PostMapping(value = "refuseCancelOrders")
    public ResultVO refuseCancelOrders(@RequestParam @ApiParam("订单uuid")String  orderUuid){
        return orderService.refuseCancelOrders(orderUuid);
    }

    @ApiOperation(value = "同意取消订单", notes = "同意取消订单")
    @PostMapping(value = "agreeCancelOrders")
    public ResultVO agreeCancelOrders(@RequestParam @ApiParam("订单uuid")String  orderUuid){
        return orderService.agreeCancelOrders(orderUuid);
    }

    @ApiOperation(value = "发货", notes = "发货")
    @PostMapping(value = "orderSend")
    public ResultVO orderSend(@RequestParam @ApiParam("订单uuid") String orderUuid){
        return orderService.orderSend(orderUuid);
    }

    @ApiOperation(value = "完成商品订单", notes = "完成商品订单")
    @PostMapping(value = "orderFininsh")
    public ResultVO orderFininsh(@RequestParam @ApiParam("订单uuid") String orderUuid){
        return orderService.orderFininsh(orderUuid);
    }

    @ApiOperation(value = "下载" , notes = "数据")
    @RequestMapping(value = "/order/excelDownload", method = RequestMethod.GET)
    public void excelDownload(HttpServletResponse response, String orderNo, Integer fetchType,String leaderNickName, String startTime, String endTime) throws IOException {
        try{
            PageObject pageObject = new PageObject();
            if (StringUtils.isBlank(startTime)){
                startTime = "1970-01-01 00:00:00";
            }
            if (StringUtils.isBlank(endTime)){
                endTime = "2090-01-01 00:00:00";
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("信息表");
            List<OrderDto> addressList = orderService.findOrderByPage(pageObject, orderNo, fetchType, leaderNickName, DateUtil.stringToDateTime(startTime),  DateUtil.stringToDateTime(endTime));
            String fileName = "订单详情.xls";//设置要导出的文件的名字
            //新增数据行，并且设置单元格数据
            int rowNum = 1;
            String[] headers = { "订单编号", "下单时间", "商品名称", "数量", "单价", "总价" , "套餐类型", "取件方式", "所属团长", "返佣价格", "交易状态", "实收款", "用户昵称"};
            //headers表示excel表中第一行的表头
            HSSFRow row = sheet.createRow(0);
            //在excel表中添加表头
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            //在表中存放查询到的数据放入对应的列
            for (OrderDto orderDto : addressList) {
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(orderDto.getUuid());
                row1.createCell(1).setCellValue(DateUtil.dateTimeToString(orderDto.getCreateTime()));
                row1.createCell(2).setCellValue(orderDto.getProductName());
                row1.createCell(3).setCellValue(orderDto.getQuantity());
                row1.createCell(4).setCellValue(orderDto.getPrice());
                row1.createCell(5).setCellValue(orderDto.getAmount());
                row1.createCell(6).setCellValue(orderDto.getDistributionType());
                row1.createCell(7).setCellValue(orderDto.getDistributionType() == null ? "" : (orderDto.getDistributionType() == 0 ? "自取" : "邮寄" ));
                row1.createCell(8).setCellValue(orderDto.getLeaderName());
                row1.createCell(9).setCellValue(orderDto.getCommission());
                String orderStatus = "";
                if( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_DELIVERY.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_DELIVERY.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_COMPLETED.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELING.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                } else if ( orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getValue()){
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getDesc();
                }
                row1.createCell(10).setCellValue(orderStatus);
                row1.createCell(11).setCellValue(orderDto.getActualPrice());
                row1.createCell(12).setCellValue(orderDto.getNickname());
                rowNum++;
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename="+fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
