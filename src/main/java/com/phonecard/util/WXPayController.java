package com.phonecard.util;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付 - 通用API.
 *
 * <p>
 * 类似支付宝中的条码支付.
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2018/6/15
 */
@CrossOrigin
@RestController
@RequestMapping("/wxpay")
public class WXPayController {
	private static final Logger log = LoggerFactory.getLogger(WXPayController.class);
    @Autowired
    private WXPay wxPay;

    @Autowired
    private WXPayClient wxPayClient;

    @Autowired
    private WXPayProperties wxPayConfig;


    /**
     * 订单查询
     *
     * @param orderNo
     * @return
     * @throws Exception
     */
    @GetMapping("/orderQuery")
    public Object orderQuery(String orderNo) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("out_trade_no", orderNo);
        Map<String, String> result = wxPay.orderQuery(data);
        return result;
    }

    /**
     * 退款
     * 注意：调用申请退款、撤销订单接口需要商户证书
     * 注意：沙箱环境响应结果可能会是"沙箱支付金额不正确,请确认验收case"，但是正式环境不会报这个错误
     * 微信支付的最小金额是0.1元，所以在测试支付时金额必须大于0.1元，否则会提示微信支付配置错误，可以将microPay的total_fee大于1再退款
     */
    public  Object refund(String orderLeadNo,String orderNo,String actualMoney,String refundMoney) throws Exception {
        Map<String, String> reqData = new HashMap<>(16);
        // 商户订单号
        reqData.put("out_trade_no", orderLeadNo);
        // 退款号
        reqData.put("out_refund_no", orderNo);
        // 订单总金额，单位为分，只能为整数
        reqData.put("total_fee", actualMoney);
        //退款金额
        reqData.put("refund_fee", refundMoney);
        // 退款异步通知地址
        reqData.put("notify_url", wxPayConfig.getNotifyUrl());
        reqData.put("refund_fee_type", "CNY");
        reqData.put("op_user_id", wxPayConfig.getMchID());
        Map<String, String> resultMap = wxPay.refund(reqData);
        log.info(resultMap.toString());
        return resultMap;
    }

    
    
    /**
     * 退款结果通知
     * <p>
     * 特别说明：退款结果对重要的数据进行了加密，商户需要用商户秘钥进行解密后才能获得结果通知的内容
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/refund/notify")
    public String refundNotify(HttpServletRequest request) throws Exception {
        // 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
        // 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
        // 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
        Map<String, String> requstInfoMap = wxPayClient.decodeRefundNotify(request);
        String returnCode = requstInfoMap.get("return_code");
        String resultCode = requstInfoMap.get("result_code");
        String outRefundNo = requstInfoMap.get("out_refund_no");
        if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
           
        }
           
        log.info(requstInfoMap.toString());
        // 商户处理退款通知参数后同步返回给微信参数
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", "SUCCESS");
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
    }

    /**
     * 退款查询
     *
     * @param orderNo
     * @return
     * @throws Exception
     */
    @GetMapping("/refundQuery")
    public Object refundQuery(String orderNo) throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("out_trade_no", orderNo);
        Map<String, String> result = wxPay.refundQuery(reqData);
        return result;
    }


    /**
     * 下载对账单
     * 注意：
     * 微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
     * 对账单接口只能下载三个月以内的账单。
     *
     * @throws Exception
     */
    @PostMapping("/downloadBill")
    public Object downloadBill(String billDate) throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("bill_date", billDate);
        reqData.put("bill_type", "ALL");
        Map<String, String> resultMap = wxPay.downloadBill(reqData);
        return resultMap;
    }


    /**
     * 获取沙箱环境API秘钥，
     * <p>
     * 这里只是为了获取，可以放在main方法下运行，这里作为api来运行的，实际情况下不应该暴露出来
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/sandbox/getSignKey")
    public Object getSignKey() throws Exception {
        Map<String, String> signKey = wxPayClient.getSignKey();
        log.info(signKey.toString());
        return signKey;
    }
}
