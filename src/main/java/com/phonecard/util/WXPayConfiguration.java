/**
 * Copyright (C), 2017-2018, 苏州立昌科技有限公司
 * FileName: WXPayConfiguration
 * Author:   mirror_huang
 * Date:     2018/11/2 0002 14:14
 * Description: 微信支付配置
 * History:
 * <author>          <qq>          <version>
 * mirror_huang     1755496180       版本号
 */
package com.phonecard.util;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信支付配置〉
 *
 * @author mirror_huang
 * @create 2018/11/2 0002 14:14
 * @since 1.0.0
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@EnableConfigurationProperties(WXPayProperties.class)
public class WXPayConfiguration {

    @Autowired
    private WXPayProperties wxPayProperties;

    /**
     * useSandbox 沙盒环境
     *
     * @return
     */
    @Bean
    public WXPay wxPay() {
        return new WXPay(wxPayProperties, WXPayConstants.SignType.MD5, wxPayProperties.getUseSandbox());
    }

    @Bean
    public WXPayClient wxPayClient() {
        return new WXPayClient(wxPayProperties, WXPayConstants.SignType.MD5, wxPayProperties.getUseSandbox());
    }

}
