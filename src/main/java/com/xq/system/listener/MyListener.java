package com.xq.system.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @auther: xq2580z
 * @date: 2020/2/25 17:09
 */
@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始
        //取到ServletContext
        ServletContext servletContext = servletContextEvent.getServletContext();

        //给xq2580z复制为上下文
        servletContext.setAttribute("xq2580z", servletContext.getContextPath());

        System.out.println("!!!!!!!!!!!!!!--Servlet容器创建成功 xq2580z被放到ServletContext作用域--!!!!!!!!!!!!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
