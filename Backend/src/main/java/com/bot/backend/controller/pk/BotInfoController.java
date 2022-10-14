package com.bot.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("getbotinfo/")
    public Map<String, String> getBotInfo() {
        Map<String, String> mp = new HashMap<>();
        mp.put("name", "Jackpot");
        mp.put("rating", "1500");
        return mp;
    }
}
