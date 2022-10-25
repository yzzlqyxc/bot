package com.bot.backend.service.impl.pk;

import com.bot.backend.service.pk.StartGameService;
import com.bot.backend.consumer.WebSocketServer;
import org.springframework.stereotype.Service;

@Service
public class StartGameServiceImply implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer bId) {
        System.out.println("start game" + aId + "and" + bId);
        WebSocketServer.startGame(aId, bId);
        return "Success";
    }
}
