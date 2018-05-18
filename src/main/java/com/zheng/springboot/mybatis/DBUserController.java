package com.zheng.springboot.mybatis;

import com.zheng.springboot.mybatis.domain.User;
import com.zheng.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/18 17:10
 */
@RestController
@RequestMapping("/db/user")
public class DBUserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/find/{userId}")
    public User find(@PathVariable Integer userId) {
        if (!Optional.ofNullable(userId).isPresent()) {
            return null;
        }
        User user = userService.findById(userId);
        return user;
    }

    @GetMapping("/create/{username}/{password}/{age}")
    public User create(@PathVariable String username, String password, Integer age) {
        User user = new User(username, password, age);
        userService.save(user);
        return user;
    }
}
