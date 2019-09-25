package com.phonecard.util;

import com.github.wxpay.sdk.WXPay;
import com.phonecard.bean.JsonResult;
import com.phonecard.util.WXPayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WeiXinRefund {
	
	@Autowired
	 private WXPay wxPay;
	 @Autowired
     private WXPayProperties wxPayConfig;
	/**
	 * 退款
	 * 注意：调用申请退款、撤销订单接口需要商户证书
	 * 注意：沙箱环境响应结果可能会是"沙箱支付金额不正确,请确认验收case"，但是正式环境不会报这个错误
	 * 微信支付的最小金额是0.1元，所以在测试支付时金额必须大于0.1元，否则会提示微信支付配置错误，可以将microPay的total_fee大于1再退款
	 */
	public JsonResult refund(String out_trade_no, String out_refund_no, String total_fee, String refund_fee) throws Exception {
		System.out.println("--------------进入微信退款--------------------");	
		Map<String, String> reqData = new HashMap<>(16);
		// 商户订单号
		System.out.println("--------------out_trade_no--------------------"  + out_trade_no);	
		reqData.put("out_trade_no", out_trade_no);
		// 授权码
		System.out.println("--------------out_refund_no--------------------"  + out_refund_no);	
		reqData.put("out_refund_no", out_refund_no);
		// 订单总金额，单位为分，只能为整数
		total_fee = String.valueOf((int) (ArithUtil.mul(Double.parseDouble(total_fee), 100d)));
		System.out.println("--------------total_fee--------------------"  + total_fee);
		reqData.put("total_fee", total_fee);
		//退款金额
		refund_fee = String.valueOf((int) (ArithUtil.mul(Double.parseDouble(refund_fee), 100d)));
		System.out.println("--------------refund_fee--------------------"  + refund_fee);
		reqData.put("refund_fee", refund_fee);
		// 退款异步通知地址
		System.out.println("--------------wxPayConfig.getNotifyUrl()--------------------"  + wxPayConfig.getNotifyUrl());	
		reqData.put("notify_url", wxPayConfig.getNotifyUrl());
		
		reqData.put("refund_fee_type", "CNY");
		
		System.out.println("--------------wxPayConfig.getMchID()--------------------"  + wxPayConfig.getMchID());	
		reqData.put("op_user_id", wxPayConfig.getMchID());
		
		Map<String, String> resultMap = wxPay.refund(reqData);
//		System.out.print(resultMap.toString());
		JsonResult r = new JsonResult();
		if (resultMap.containsKey("result_code") && "SUCCESS".equals(resultMap.get("result_code"))) {
			r.setResult(StatusCode.SUCCESS);
			r.setMsg("ok");
			r.setData(resultMap);
		} else {
			r.setResult(StatusCode.FAIL);
			r.setMsg(resultMap.get("err_code_des"));
			r.setData(resultMap);
		}
		return r;
	}
}
