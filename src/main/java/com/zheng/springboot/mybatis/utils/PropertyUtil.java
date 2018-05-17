package com.zheng.springboot.mybatis.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * @Author zhenglian
 * @Date 2018/5/17 21:36
 */
public class PropertyUtil {
    
    private static final String LOCATION = "application.properties";
    private static Properties props = null;
    
    static {
        DefaultResourceLoader loader = new DefaultResourceLoader(PropertyUtil.class.getClassLoader());
        Resource resource = loader.getResource(LOCATION);
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Optional.ofNullable(inputStream).isPresent()) {
           props = new Properties();
            try {
                props.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查配置文件是否正确加载
     * @return
     */
    private static boolean checkValid() {
        return Optional.ofNullable(props).isPresent();
    }
    
    /**
     * 获取属性
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (!checkValid()) {
            return null;
        }
        if (!Optional.ofNullable(key).isPresent()) {
            return null;
        }
        
        String value = props.getProperty(key);
        return value;
    }

    /**
     * 获取属性如果没有返回默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getProperty(String key, Object defaultValue) {
        if (!checkValid()) {
            return defaultValue;
        }
        if (!Optional.ofNullable(key).isPresent()) {
            return defaultValue;
        }

        String value = props.getProperty(key);
        return value;
    }
}
