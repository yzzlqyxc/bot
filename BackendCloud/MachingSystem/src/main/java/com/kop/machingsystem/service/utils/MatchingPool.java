package com.kop.machingsystem.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MatchingPool extends Thread{
    private static List<Player> players = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    private static RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        MatchingPool.restTemplate = restTemplate;
    }

    public void addPlayer(Integer userId, Integer rating) {
        lock.lock();
        try {
            players.add(new Player(userId, rating, 0));
        }finally {
            lock.unlock();
        }
    }

    public void removePlayer(Integer userId) {
        lock.lock();
        try {
            List<Player> afterDel = new ArrayList<>();
            for(Player player : players) {
                if(!player.getUserId().equals(userId)) {
                    afterDel.add(player);
                }
            }
            players = afterDel;
        }finally {
            lock.unlock();
        }

    }

    private void increaseRatingTime() {
        for(Player player : players) {
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }

    private boolean checkMatched(Player a, Player b) {
        int rate_diff = Math.abs(a.getRating() - b.getRating());
        int waitingTime = Math.min(a.getWaitingTime(), b.getWaitingTime());
        return rate_diff <= waitingTime * 10;
    }

    private void sendResult(Player a, Player b) {
        System.out.println("Match Player" + a.toString() + "and" + b.toString());
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("b_id", b.getUserId().toString());
        restTemplate.postForObject("http://127.0.0.1:3000/pk/startgame/", data, String.class);
    }

    private void matchPlayers() {
        System.out.println("players Matching" + players);
        boolean[] used = new boolean[players.size()];
        for(int i = 0; i < used.length; i ++ ) {
            if(used[i]) continue;
            for(int j = i + 1; j < used.length; j ++ ) {
                if(used[j]) continue;
                Player a = players.get(i), b = players.get(j);

                if(checkMatched(a, b)) {
                    sendResult(a, b);
                    used[j] = true;
                    used[i] = true;
                }
            }
        }
        List<Player> afterDel = new ArrayList<>();
        for(int i = 0; i < players.size(); i ++ ) {
            if(!used[i]) {
                afterDel.add(players.get(i));
            }
        }
        players = afterDel;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                lock.lock();
                try {
                    increaseRatingTime();
                    matchPlayers();
                } finally {
                    lock.unlock();
                }

                increaseRatingTime();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
