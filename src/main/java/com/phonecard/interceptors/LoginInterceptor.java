package com.phonecard.interceptors;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.phonecard.bean.JsonResult;
import com.phonecard.service.RedisService;
import com.phonecard.util.StatusCode;
import com.google.gson.Gson;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private RedisService redisService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*
		System.out.println("------token:" + token);*/
		// 如果不是映射到方法直接通过 */
		String token = request.getHeader("x-access-token");
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		// 登录不做拦截
		if (request.getRequestURI().contains("swagger")) {
			return true;
		}
		
		if (request.getRequestURI().contains("Code")) {
			return true;
		}
		
		if (request.getRequestURI().contains("pay")) {
			return true;
		}

		if (request.getRequestURI().contains("login")) {
			return true;
		}

		if (request.getRequestURI().contains("export")) {
			return true;
		}

		if (token == null || "".equals(token)) {
			printJson(response);
			return false;
		}
		
		boolean flag = redisService.exists(token);
		if (!flag) {
			printJson(response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "false");
		response.setHeader("Access-Control-Allow-Headers",
				"Authorization, Content-Type, X-Requested-With, token, x-access-token");
		response.setHeader("Access-Control-Allow-Methods",
				"GET, HEAD, OPTIONS, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
	}

	private static void printJson(HttpServletResponse response) {
		JsonResult r = new JsonResult();
		r.setResult(StatusCode.INVALID);
		r.setMsg("token过期,请重新登陆");
		Gson gson = new Gson();
		String content = gson.toJson(r);
		printContent(response, content);
	}

	private static void printContent(HttpServletResponse response,
			String content) {
		try {
			response.reset();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(content);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
