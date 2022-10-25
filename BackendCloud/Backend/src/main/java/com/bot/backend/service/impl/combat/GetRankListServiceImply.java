package com.bot.backend.service.impl.combat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import com.bot.backend.service.combat.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRankListServiceImply implements GetRankListService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getRankList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rankscore");
        return userMapper.selectList(queryWrapper);
    }

}
