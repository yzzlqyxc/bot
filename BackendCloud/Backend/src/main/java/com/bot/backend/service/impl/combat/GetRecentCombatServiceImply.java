package com.bot.backend.service.impl.combat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bot.backend.mapper.CombatMapper;
import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.Combat;
import com.bot.backend.service.combat.GetRecentCombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GetRecentCombatServiceImply implements GetRecentCombatService {

    @Autowired
    private CombatMapper combatMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, String>> getRecentCombat() {
        QueryWrapper<Combat> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        queryWrapper.last("limit 0,50");

        List<Combat> q = combatMapper.selectList(queryWrapper);
        List<Map<String, String>> response = new ArrayList<>();

        for(Combat i : q) {
            Map<String, String> map = new HashMap<>();
            String winner = userMapper.selectById(i.winner).getUsername();
            String loser = userMapper.selectById(i.loser).getUsername();
            map.put("winner", winner);
            map.put("loser", loser);

            Date date = i.time;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String  time = format.format(date);
            map.put("time", time);
            map.put("score", i.getScore());
            response.add(map);
        }

        return response;
    }
}
