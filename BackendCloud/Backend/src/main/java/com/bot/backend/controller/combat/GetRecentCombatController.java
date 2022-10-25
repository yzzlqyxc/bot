package com.bot.backend.controller.combat;

import com.bot.backend.pojo.Combat;
import com.bot.backend.service.combat.GetRecentCombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetRecentCombatController {

    @Autowired
    private GetRecentCombatService getRecentCombatService;

    @GetMapping("/combat/getcombat/")
    public List<Map<String, String>> getRecentCombat() {
        return getRecentCombatService.getRecentCombat();
    }
}
