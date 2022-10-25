package com.kop.machingsystem.service.impl;

import com.kop.machingsystem.service.MachingService;
import com.kop.machingsystem.service.utils.MatchingPool;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImply implements MachingService {
    public final static MatchingPool matchingPool = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating) {
        System.out.println("add player" + userId + ' ');
        matchingPool.addPlayer(userId, rating);
        return "add user success";
    }

    @Override
    public String delPlayer(Integer userId) {
        System.out.println("del player" + userId + ' ');
        matchingPool.removePlayer(userId);
        return "del user success";
    }
}
