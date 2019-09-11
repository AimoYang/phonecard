package com.phonecard.util;

import com.github.wxpay.sdk.WXPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信支付配置文件〉
 *
 * @author mirror_huang
 * @create 2018/11/2 0002 11:14
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "pay.wxpay")
public class WXPayProperties implements WXPayConfig {
	private static final Logger log = LoggerFactory.getLogger(WXPayProperties.class);
    /**
     * 公众账号ID
     */
    private String appID = "wx9462bdbda12444d9";

    /**
     * 商户号
     */
    private String mchID = "1500910161";

    /**
     * API 密钥
     */
    private String key = "YOUxing123qwefuwei321FUWEI123321";

    /**
     * API 沙箱环境密钥
     */
    private String sandboxKey = "3639bc1370e105aa65f10cd4fef2a3ef";

    /**
     * API证书绝对路径
     */
    private String certPath = "/usr/local/certificate/apiclient_cert.p12";
    /**
     * 退款异步通知地址
     */
    private String notifyUrl = "http://47.103.135.181:82/WeiXinRet/weixinrefund";

    private Boolean useSandbox = false;

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     */
    private int httpConnectTimeoutMs = 8000;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     */
    private int httpReadTimeoutMs = 10000;


    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream() {
        File certFile = new File(certPath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(certFile);
        } catch (FileNotFoundException e) {
            log.error("cert file not found, path={}, exception is:{}", certPath, e);
        }
        return inputStream;
    }

    @Override
    public String getKey() {
        if (useSandbox) {
            return sandboxKey;
        }
        return key;
    }

	public String getAppID() {
		return appID;
	}


	public String getMchID() {
		return mchID;
	}


	public String getSandboxKey() {
		return sandboxKey;
	}


	public String getCertPath() {
		return certPath;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}


	public Boolean getUseSandbox() {
		return useSandbox;
	}


	public int getHttpConnectTimeoutMs() {
		return httpConnectTimeoutMs;
	}


	public int getHttpReadTimeoutMs() {
		return httpReadTimeoutMs;
	}


	public WXPayProperties() {
		super();
	}

}
