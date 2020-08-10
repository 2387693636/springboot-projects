package com.xq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author zxq向前
 * 启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.xq.business.mapper","com.xq.statistics.mapper","com.xq.system.mapper"})
public class MyBikerentSpringbootApplication {

	/** 启动
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MyBikerentSpringbootApplication.class, args);
	}

}
