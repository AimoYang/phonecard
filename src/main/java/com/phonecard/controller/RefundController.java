package com.phonecard.controller;

import com.phonecard.bean.RefundOrderDto;
import com.phonecard.enums.OrderEnums;
import com.phonecard.service.RedisService;
import com.phonecard.service.RefundService;
import com.phonecard.util.DateUtil;
import com.phonecard.util.PageObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/4 0004 15:32
 * @Description:
 */
@Api(tags = {"退款管理"})
@RestController
public class RefundController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RefundService refundService;

    @ApiOperation(value = "下载" , notes = "数据")
    @RequestMapping(value = "export/refundExcelDownload", method = RequestMethod.GET)
    public void excelDownload(HttpServletResponse response,String token, String orderNo, Integer fetchType, String leaderNickName, String startTime, String endTime,String companyName,Integer id,Integer state,String goodsName ) throws IOException {
        boolean flag = redisService.exists(token);
        if (flag) {
            try{
                if (StringUtils.isBlank(startTime)){
                    startTime = "1970-01-01 00:00:00";
                }
                if (StringUtils.isBlank(endTime)){
                    endTime = "2090-01-01 00:00:00";
                }
                PageObject pageObject = new PageObject();
                pageObject.setTitle(orderNo);
                pageObject.setName(leaderNickName);
                if(fetchType != null){
                    pageObject.setType(fetchType.shortValue());
                }
                if(startTime != null){
                    pageObject.setStartTime(DateUtil.stringToDateTime(startTime));
                    pageObject.setEndTime(DateUtil.stringToDateTime(endTime));
                }
                pageObject.setCompanyName(companyName);
                pageObject.setState(state);
                pageObject.setId(id);
                pageObject.setGoodsName(goodsName);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("信息表");
                List<RefundOrderDto> addressList = refundService.findOrderByPage(pageObject);
                String filename = "订单详情";//设置要导出的文件的名字
                String fileName = new String(filename.getBytes(), "iso8859-1") + DateUtil.dateTimeToString(new Date()) + ".xls";
                //新增数据行，并且设置单元格数据
                int rowNum = 1;
                String[] headers = { "订单编号", "下单时间", "商品名称", "数量", "单价", "总价" , "套餐类型", "取件方式", "所属团长", "返佣价格", "交易状态", "实收款", "退款金额", "用户昵称"};
                //headers表示excel表中第一行的表头
                HSSFRow row = sheet.createRow(0);
                //在excel表中添加表头
                for(int i=0;i<headers.length;i++){
                    HSSFCell cell = row.createCell(i);
                    HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                    cell.setCellValue(text);
                }
                //在表中存放查询到的数据放入对应的列
                for (RefundOrderDto refundOrderDto : addressList) {
                    HSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(refundOrderDto.getUuid());
                    row1.createCell(1).setCellValue(DateUtil.dateTimeToString(refundOrderDto.getCreateTime()));
                    row1.createCell(2).setCellValue(refundOrderDto.getProductName());
                    row1.createCell(3).setCellValue(refundOrderDto.getQuantity());
                    row1.createCell(4).setCellValue(refundOrderDto.getPrice());
                    row1.createCell(5).setCellValue(refundOrderDto.getAmount());
                    row1.createCell(6).setCellValue(refundOrderDto.getDistributionType());
                    row1.createCell(7).setCellValue(refundOrderDto.getDistributionType() == null ? "" : (refundOrderDto.getDistributionType() == 0 ? "自取" : "邮寄" ));
                    row1.createCell(8).setCellValue(refundOrderDto.getLeaderName());
                    row1.createCell(9).setCellValue(refundOrderDto.getCommission());
                    String orderStatus = "";
                    if( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_DELIVERY.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_DELIVERY.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_COMPLETED.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELING.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                    } else if ( refundOrderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getValue()){
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getDesc();
                    }
                    row1.createCell(10).setCellValue(orderStatus);
                    row1.createCell(11).setCellValue(refundOrderDto.getActualPrice());
                    row1.createCell(12).setCellValue(refundOrderDto.getRefundMoney());
                    row1.createCell(13).setCellValue(refundOrderDto.getNickname());
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
}
