package com.zheng.springboot.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.gson.Gson;
import com.zheng.springboot.mybatis.domain.MyPageBounds;
import com.zheng.springboot.mybatis.domain.MyPageList;
import com.zheng.springboot.mybatis.domain.User;
import com.zheng.springboot.mybatis.filter.UserFilter;
import com.zheng.springboot.mybatis.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zhenglian
 * @Date 2018/5/17 22:07
 */
public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;
    
    @Test
    public void save() {
        User user = new User("zhangsan", "123456", 20);
        userService.save(user);
    }
    
    @Test
    public void findById() {
        User user = userService.findById(1);
        System.out.println(user);
    }
    
    @Test
    public void findByPage() {
        UserFilter filter = new UserFilter();
        filter.setUsername("zhang");
        MyPageBounds pageBounds = new MyPageBounds(1, 20);
        MyPageList<User> myPageList = userService.findByPage(filter, pageBounds);
        PageList<User> pageList = MyPageList.convertPageList(myPageList);
        System.out.println(new Gson().toJson(pageList));
    }
    
    @Test
    public void update() {
        User user = userService.findById(1);
        user.setUsername("wangwu");
        userService.update(user);
    }
    
    @Test
    public void delete() {
        userService.deleteById(1);
    }
}
