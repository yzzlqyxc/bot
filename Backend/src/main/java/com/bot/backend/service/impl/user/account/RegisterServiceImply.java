package com.bot.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import com.bot.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImply implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if(username == null) {
            map.put("response", "用户名不能为空");
            return map;
        }
        else if(password == null || confirmedPassword == null) {
            map.put("response", "密码不能为空");
            return map;
        }
        username = username.trim();
        if(username.length() == 0) {
            map.put("response", "用户名不能为空");
            return map;
        }
        if(password.length() == 0) {
            map.put("response", "密码不能为空");
            return map;
        }
        if(username.length() > 100) {
            map.put("response", "用户名长度不能超过100");
            return map;
        }
        if(password.length() > 100) {
            map.put("response", "密码长度不能超过100");
            return map;
        }
        if(!password.equals(confirmedPassword)) {
            map.put("response", "两次输入的密码不匹配");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("usr", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()) {
            map.put("response", "用户名被注册");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        userMapper.insert(new User(null, username, encodedPassword, "1231241"));
        map.put("response", "success");
        return map;
    }

}
