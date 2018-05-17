package com.zheng.springboot.mybatis.service.impl;

import com.zheng.springboot.configuration.yaml.User;
import com.zheng.springboot.mybatis.dao.UserDao;
import com.zheng.springboot.mybatis.domain.MyPageBounds;
import com.zheng.springboot.mybatis.domain.MyPageList;
import com.zheng.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:14
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public int save(User user) {
        return ;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public MyPageList<User> findByPageBounds(MyPageBounds pageBounds) {
        return null;
    }
}
