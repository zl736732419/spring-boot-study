package com.zheng.springboot.mybatis.service;

import com.zheng.springboot.configuration.yaml.User;
import com.zheng.springboot.mybatis.domain.MyPageBounds;
import com.zheng.springboot.mybatis.domain.MyPageList;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:05
 */
public interface UserService {
    User findById(Integer id);
    int save(User user);
    int deleteById(Integer id);
    int update(User user);
    MyPageList<User> findByPageBounds(MyPageBounds pageBounds);
}
