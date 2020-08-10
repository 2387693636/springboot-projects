package com.xq.system.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.ui.context.Theme;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 13:14
 * @description: 和浏览器网络相关的工具类
 */
public final class WebToolsUtil {

    private static final Logger LOGGER = Logger.getLogger(WebToolsUtil.class);
    private static final WebToolsUtil INSTANCE = new WebToolsUtil();

    public WebToolsUtil get() {
        return INSTANCE;
    }

    private WebToolsUtil() {
        super();
    }

    //

    // --------------------------------------------------------------------------------------------------------------  

    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        if ((ip != null) && (ip.length() > 15)) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
    // --------------------------------------------------------------------------------------------------------------

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    public static HttpSession getSession() {
        return getSession(true);
    }

    public static HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    public static String getSessionId() {
        return getSession().getId();
    }

    public static ServletContext getServletContext() {
        return getSession().getServletContext();    // servlet2.3  
    }

    public Locale getLocale() {
        return RequestContextUtils.getLocale(getRequest());
    }

    public Theme getTheme() {
        return RequestContextUtils.getTheme(getRequest());
    }

    public ApplicationContext getApplicationContext() {
        return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return (ApplicationEventPublisher) getApplicationContext();
    }

    public LocaleResolver getLocaleResolver() {
        return RequestContextUtils.getLocaleResolver(getRequest());
    }

    public ThemeResolver getThemeResolver() {
        return RequestContextUtils.getThemeResolver(getRequest());
    }

    public ResourceLoader getResourceLoader() {
        return (ResourceLoader) getApplicationContext();
    }

    public ResourcePatternResolver getResourcePatternResolver() {
        return (ResourcePatternResolver) getApplicationContext();
    }

    public MessageSource getMessageSource() {
        return (MessageSource) getApplicationContext();
    }

    public ConversionService getConversionService() {
        return getBeanFromApplicationContext(ConversionService.class);
    }

    public DataSource getDataSource() {
        return getBeanFromApplicationContext(DataSource.class);
    }

    public Collection<String> getActiveProfiles() {
        return Arrays.asList(getApplicationContext().getEnvironment().getActiveProfiles());
    }

    public ClassLoader getBeanClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }

    private <T> T getBeanFromApplicationContext(Class<T> requiredType) {
        try {
            return getApplicationContext().getBean(requiredType);
        } catch (NoUniqueBeanDefinitionException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

}  