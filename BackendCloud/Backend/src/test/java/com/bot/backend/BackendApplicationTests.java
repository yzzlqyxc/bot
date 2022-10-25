package com.bot.backend;

import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        Date d = new Date();
        System.out.println(d);

    }
}
