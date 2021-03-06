package com.phonecard.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取ip地址
 * @author Administrator
 *
 */

public class IPAddressUtils {
	
	//获取客户端的ip地址
	public static String getIPAddress(HttpServletRequest request) {
		String ip = null;
		String ipAddresses = request.getHeader("X-Forwarded-For");
		if (ipAddresses == null || ipAddresses.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ipAddresses == null || ipAddresses.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddresses)) {
			ipAddresses = request.getHeader("X-Real-IP");
		}
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}
		if (ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
