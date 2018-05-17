package com.zheng.springboot.mybatis.constants;

import com.zheng.springboot.mybatis.utils.PropertyUtil;

/**
 * @Author zhenglian
 * @Date 2018/5/17 21:31
 */
public class AppConstants {
    public static final Integer DEFAULT_PAGE;
    public static final Integer DEFAULT_LIMIT;

    static {
        Object page = PropertyUtil.getProperty("mybatis.default.page", 1);
        DEFAULT_PAGE = Integer.parseInt(page + "");
        Object limit = PropertyUtil.getProperty("mybatis.default.limit", 10);
        DEFAULT_LIMIT = Integer.parseInt(limit + "");
    }

}
