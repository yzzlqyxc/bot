package com.bot.backend.controller.usr;

import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UsrController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("all/")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @RequestMapping("{userId}/")
    public User getUser(@PathVariable int userId) {
        return userMapper.selectById(userId);
    }

    @RequestMapping("add/{userId}/{username}/{password}/")
    public String addUser(@PathVariable int userId, @PathVariable String username,
                          @PathVariable String password) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(userId, username, passwordEncoder.encode(password), "123", 0);
        userMapper.insert(user);
        return "Add user Successfully!";
    }

    @GetMapping("delete/{userId}")
    public String deleteUser(@PathVariable int userId)  {
        userMapper.deleteById(userId);
        return "Delete user successfully!";
    }
}
