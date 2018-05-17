package com.zheng.springboot.mybatis.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zheng.springboot.configuration.yaml.User;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:17
 */
public interface UserDao {
    User findById(Integer id);
    int save(User user);
    int deleteById(Integer id);
    int update(User user);
    PageList<User> findByPageBounds(PageBounds pageBounds);
}
