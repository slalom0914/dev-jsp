package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.basic.ServletLifeCycle;

@Configuration
public class ServletConfig {
    
    @Bean
    public ServletRegistrationBean<ServletLifeCycle> servletRegistrationBean() {
        ServletRegistrationBean<ServletLifeCycle> registrationBean = 
            new ServletRegistrationBean<>(new ServletLifeCycle(), "/hello");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
} 