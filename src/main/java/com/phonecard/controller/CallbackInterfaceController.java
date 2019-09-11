package com.phonecard.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.phonecard.service.OrderService;
import com.phonecard.util.WXPayClient;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "回调接口",tags = "回调接口")
@RestController
@RequestMapping("/WeiXinRet")
public class CallbackInterfaceController {
	private static final Logger logger = LoggerFactory.getLogger(CallbackInterfaceController.class);
	@Autowired
	private WXPayClient wxPayClient;
	@Autowired
	private OrderService orderService;
    /**
     * 退款结果通知
     * <p>
     * 特别说明：退款结果对重要的数据进行了加密，商户需要用商户秘钥进行解密后才能获得结果通知的内容
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("weixinrefund")
    public String refundNotify(HttpServletRequest request) throws Exception {
    	logger.info("===》进入微信回调");
        // 注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。
        // 推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。
        // 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。
        Map<String, String> requstInfoMap = wxPayClient.decodeRefundNotify(request);
       // String returnCode = requstInfoMap.get("return_code");
       // String resultCode = requstInfoMap.get("result_code");
        String outRefundNo = requstInfoMap.get("out_refund_no");
        String refundStatus = requstInfoMap.get("refund_status");
        logger.info(requstInfoMap.toString());
        if ("SUCCESS".equals(refundStatus)) {
        	//退款逻辑处理
        	orderService.dealRefund(outRefundNo);
        }
        logger.info(requstInfoMap.toString());
        // 商户处理退款通知参数后同步返回给微信参数
        Map<String, String> responseMap = new HashMap<>(4);
        responseMap.put("return_code", "SUCCESS");
        responseMap.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap);
        return responseXml;
    }
	
	
    
    @RequestMapping("payNotify")
    public void payNotify(HttpServletRequest request) throws Exception {
    	logger.info("===》进入微信回调");
       
    }
}
