package com.phonecard.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.phonecard.interceptors.LoginInterceptor;


@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
    
	@Bean
	LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("20MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
    
    //配置拦截器,对swagger不拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/WeiXinRet/**","/video/**","/login","/Code","/pay/notify","/error","/swagger-resources/**","/v2/**");
        super.addInterceptors(registry);
    }

}
