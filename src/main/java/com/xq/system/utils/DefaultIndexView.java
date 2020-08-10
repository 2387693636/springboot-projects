package com.xq.system.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author zxq
 * @DATE : 2020/3/20
 * @USER : xq2580z
 * @DESCRIPTION : 设置默认主页 index.jsp
 **/
@Configuration
public class DefaultIndexView implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
