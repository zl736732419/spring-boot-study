package com.zheng.springboot.mybatis.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zheng.springboot.mybatis.constants.AppConstants;
import com.zheng.springboot.mybatis.domain.MyPageBounds;
import com.zheng.springboot.mybatis.domain.MyPageList;
import com.zheng.springboot.mybatis.domain.User;
import com.zheng.springboot.mybatis.filter.UserFilter;
import com.zheng.springboot.mybatis.mapper.UserMapper;
import com.zheng.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:14
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Cacheable(value = "user", key = "#id")
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 添加用户时，会同时添加到缓存中
     * @param user
     * @return
     */
    @CachePut(value = "user", key = "#user.id")
    @Override
    public int save(User user) {
        if (!Optional.ofNullable(user).isPresent()) {
            return 0;
        }
        return userMapper.save(user);
    }

    /**
     * 删除用户时会删除缓存中的记录
     * @param id
     * @return
     */
    @CacheEvict(value = "user", key = "#id")
    @Override
    public int deleteById(Integer id) {
        if (!Optional.ofNullable(id).isPresent()) {
            return 0;   
        }
        return userMapper.deleteById(id);
    }

    /**
     * 更新时会同时更新缓存中的结果
     * @param user
     * @return
     */
    @CachePut(value = "user", key="#user.id")
    @Override
    public int update(User user) {
        if (!Optional.ofNullable(user).isPresent()) {
            return 0;
        }
        return userMapper.update(user);
    }

    @Override
    public MyPageList<User> findByPage(UserFilter userFilter, MyPageBounds pageBounds) {
        if (!Optional.ofNullable(userFilter).isPresent()) {
            userFilter = new UserFilter();
        }
        if (!Optional.ofNullable(pageBounds).isPresent()) {
            pageBounds = new MyPageBounds(AppConstants.DEFAULT_PAGE, AppConstants.DEFAULT_LIMIT);
        }

        PageList<User> pageList = userMapper.findByPage(userFilter, pageBounds.getPageBounds());
        if (CollectionUtils.isEmpty(pageList)) {
            return null;
        }
        
        return new MyPageList<>(pageList);
    }
}
