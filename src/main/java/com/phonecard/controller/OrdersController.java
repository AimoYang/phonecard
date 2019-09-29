package com.phonecard.controller;

import com.phonecard.bean.JsonResult;
import com.phonecard.bean.OrderDto;
import com.phonecard.bean.OrderReport;
import com.phonecard.enums.OrderEnums;
import com.phonecard.service.OrderService;
import com.phonecard.service.RedisService;
import com.phonecard.util.DateUtil;
import com.phonecard.util.PageObject;
import com.phonecard.bean.ResultVO;
import com.phonecard.service.OrderService;
import com.phonecard.util.PageObject;
import com.phonecard.util.ReportUtil;
import com.phonecard.vo.OrderExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jsoup.helper.DataUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
    private RedisService redisService;

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
    public ResultVO refuseCancelOrders(@RequestParam @ApiParam("订单id")Integer id){
        return orderService.refuseCancelOrders(id);
    }

    @ApiOperation(value = "同意取消订单", notes = "同意取消订单")
    @PostMapping(value = "agreeCancelOrders")
    public ResultVO agreeCancelOrders(@RequestParam @ApiParam("订单id")Integer  id){
        return orderService.agreeCancelOrders(id);
    }

    @ApiOperation(value = "发货", notes = "发货")
    @PostMapping(value = "orderSend")
    public ResultVO orderSend(@RequestParam @ApiParam("订单uuid") String orderUuid){
        return orderService.orderSend(orderUuid);
    }

    @ApiOperation(value = "完成商品订单", notes = "完成商品订单")
    @PostMapping(value = "orderFininsh")
    public ResultVO orderFininsh(@RequestParam @ApiParam("订单uuid") String orderUuid) {
        return orderService.orderFininsh(orderUuid);
    }

    @ApiOperation(value = "下载" , notes = "数据")
    @RequestMapping(value = "export/orderExcelDownload", method = RequestMethod.GET)
    public void excelDownload(HttpServletResponse response,
                              @ApiParam(name = "token", value = "token")@RequestParam(value = "token") String token,
                              @ApiParam(name = "orderNo", value = "订单id")@RequestParam(value = "orderNo",required = false) String orderNo,
                              @ApiParam(name = "leaderNickName", value = "团长名")@RequestParam(value = "leaderNickName",required = false) String leaderNickName,
                              @ApiParam(name = "fetchType", value = "取件方式")@RequestParam(value = "fetchType",required = false) Integer fetchType,
                              @ApiParam(name = "startTime", value = "开始时间")@RequestParam(value = "startTime",required = false) String startTime,
                              @ApiParam(name = "endTime", value = "结束时间")@RequestParam(value = "endTime",required = false) String endTime,
                              @ApiParam(name = "companyName", value = "公司名")@RequestParam(value = "companyName",required = false) String companyName,
                              @ApiParam(name = "state", value = "订单状态")@RequestParam(value = "state",required = false) Integer state,
                              @ApiParam(name = "id", value = "商品类型")@RequestParam(value = "id",required = false) Integer id,
                              @ApiParam(name = "goodsName", value = "商品名")@RequestParam(value = "goodsName",required = false) String goodsName){
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

//        if (flag){
            List<OrderReport> list = new ArrayList<>();
            List<OrderExcelVo> orderExcelVoList = orderService.postOrderByCondition(pageObject);
            orderExcelVoList.forEach(orderExcelVo -> {
                OrderReport orderReport = new OrderReport();
                orderReport.setUuid(orderExcelVo.getUuid());
                orderReport.setCreateTime(DateUtil.dateTimeToString(orderExcelVo.getCreateTime()));
                orderReport.setProductName(orderExcelVo.getProductName());
                orderReport.setAmount("￥"+orderExcelVo.getAmount());
                orderReport.setPrice("￥"+orderExcelVo.getPrice());
                orderReport.setActualPrice("￥"+orderExcelVo.getActualPrice());
                orderReport.setCommission("￥"+orderExcelVo.getCommission());
                orderReport.setDistributionType(orderExcelVo.getDistributionType()== 0 ? "自取" : "邮寄");
                orderReport.setNickname(orderExcelVo.getNickname());
                orderReport.setParentNickname(orderExcelVo.getParentNickname());
                orderReport.setQuantity(orderExcelVo.getQuantity());
                String orderStatus = "";
                if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_DELIVERY.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_DELIVERY.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_COMPLETED.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_CANCELING.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                } else if (orderExcelVo.getStatus() == OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getValue()) {
                    orderStatus = OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getDesc();
                }
                orderReport.setStatus(orderStatus);
                list.add(orderReport);
            });
        //sheet名
        String sheetName = "订单信息表";
        try {
            excelExport(list, sheetName, OrderReport.class, 1, response, null);
        } catch (IOException e) {
            e.printStackTrace();
        }




            /*try {
                PageObject pageObject = new PageObject();
                if (StringUtils.isBlank(startTime)) {
                    startTime = "1970-01-01 00:00:00";
                }
                if (StringUtils.isBlank(endTime)) {
                    endTime = "2090-01-01 00:00:00";
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("信息表");

                List<OrderDto> addressList = orderService.findOrderByPage(pageObject, orderNo, fetchType, leaderNickName, DateUtil.stringToDateTime(startTime), DateUtil.stringToDateTime(endTime));
                String filename = "订单详情";//设置要导出的文件的名字
                String fileName = new String(filename.getBytes(), "iso8859-1") + DateUtil.dateTimeToString(new Date()) + ".xls";
                //新增数据行，并且设置单元格数据
                int rowNum = 1;
                String[] headers = {"订单编号", "下单时间", "商品名称", "数量", "单价", "总价", "套餐类型", "取件方式", "所属团长", "返佣价格", "交易状态", "实收款", "用户昵称"};
                //headers表示excel表中第一行的表头
                HSSFRow row = sheet.createRow(0);
                //在excel表中添加表头
                for (int i = 0; i < headers.length; i++) {
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
                    row1.createCell(7).setCellValue(orderDto.getDistributionType() == null ? "" : (orderDto.getDistributionType() == 0 ? "自取" : "邮寄"));
                    row1.createCell(8).setCellValue(orderDto.getLeaderName());
                    row1.createCell(9).setCellValue(orderDto.getCommission());
                    String orderStatus = "";
                    if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_CREATE_NOT_PAY.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_PAID_NOT_DELIVERY.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCEL_NOT_PAID.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_DELIVERY.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_DELIVERY.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_COMPLETED.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELING.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELING.getDesc();
                    } else if (orderDto.getStatus() == OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getValue()) {
                        orderStatus = OrderEnums.orderStatus.ORDER_CANCELE_AGGREED.getDesc();
                    }
                    row1.createCell(10).setCellValue(orderStatus);
                    row1.createCell(11).setCellValue(orderDto.getActualPrice());
                    row1.createCell(12).setCellValue(orderDto.getNickname());
                    rowNum++;
                }
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.flushBuffer();
                workbook.write(response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
//        }
    }

    /**
     * 功能: Excel导出公共方法
     * 记录条数大于50000时 导出.xlsx文件(excel07+)  小于等于50000时导出 .xls文件(excel97-03)
     * 开发：wangkecheng
     * @param list            需要导出的列表数据
     * @param title            导出文件的标题
     * @param className        导出对象的类名
     * @param exportType    针对同一个pojo可能有多个不同的导出模板时,可以通过此属性来决定导出哪一套模板，默认第一套
     * @param response         用来获取输出流
     * @param request       针对火狐浏览器导出时文件名乱码的问题,也可以不传入此值
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void excelExport(List list, String title, Class className, Integer exportType, HttpServletResponse response, HttpServletRequest request) throws IOException {
        OutputStream out = response.getOutputStream();
        try {
            ReportUtil excel = new ReportUtil();
            if(list!=null && list.size() > ReportUtil.EXPORT_07_LEAST_SIZE){
                dealBigNumber(list, title, className, exportType, response, request, out, excel);
            }else{
                HSSFWorkbook hss = new HSSFWorkbook();
                if(exportType==null){
                    hss = excel.exportExcel(list,title,className,0);
                }else{
                    hss = excel.exportExcel(list, title, className, exportType);
                }
                String disposition = "attachment;filename=";
                if(request!=null&&request.getHeader("USER-AGENT")!=null&& org.apache.commons.lang.StringUtils.contains(request.getHeader("USER-AGENT"), "Firefox")){
                    disposition += new String((title/*+System.currentTimeMillis()*/+".xls").getBytes(),"ISO8859-1");
                }else{
                    disposition += URLEncoder.encode(title+System.currentTimeMillis()+".xls", "UTF-8");
                }

                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Content-disposition", disposition);
                hss.write(out);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            out.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void dealBigNumber(List list, String title, Class className, Integer exportType,
                               HttpServletResponse response, HttpServletRequest request, OutputStream out, ReportUtil excel)
            throws Exception{
        SXSSFWorkbook hss;
        if(exportType==null){
            hss = excel.exportExcel07S(list,title,className,0);
        }else{
            hss = excel.exportExcel07S(list, title, className, exportType);
        }

        String disposition = "attachment;filename=";
        if(request!=null && request.getHeader("USER-AGENT") != null && org.apache.commons.lang.StringUtils.contains(request.getHeader("USER-AGENT"), "Firefox")){
            disposition += new String((title+".xlsx").getBytes(),"ISO8859-1");
        }else{
            disposition += URLEncoder.encode(title+".xlsx", "UTF-8");
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        response.setHeader("Content-disposition", disposition);
        hss.write(out);
    }


}
