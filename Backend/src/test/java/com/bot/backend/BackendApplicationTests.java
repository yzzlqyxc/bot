package com.bot.backend;

import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> t = userMapper.selectList(null);
        System.out.println(t.size());
        for(User i : t) {
            System.out.println(passwordEncoder.encode(i.getPassword()));
            System.out.println(passwordEncoder.matches("1234", "$2a$10$MsHeeEQrgv5M6I1nTSYJ2.rvBpqkk56F3hfmhYEjvClezFRc7srdW"));
        }

    }

}
