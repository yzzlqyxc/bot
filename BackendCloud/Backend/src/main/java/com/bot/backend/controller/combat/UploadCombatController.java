package com.bot.backend.controller.combat;

import com.bot.backend.service.combat.UploadCombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class UploadCombatController {

    @Autowired
    private UploadCombatService uploadCombatService;

    @PostMapping("/combat/upload/")
    Map<String, String> upLoadCombatController(@RequestParam Map<String, String>map ) throws ParseException {
        int winner = Integer.parseInt(map.get("winner"));
        int loser = Integer.parseInt(map.get("loser"));
        String score = map.get("score");

        Date d = new Date();
        return uploadCombatService.upLoadCombat(winner, loser, d, score);
    }
}
