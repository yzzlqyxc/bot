package com.bot.backend.service.impl.combat;

import com.bot.backend.mapper.CombatMapper;
import com.bot.backend.pojo.Combat;
import com.bot.backend.service.combat.UploadCombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadCombatServiceImply implements UploadCombatService {
    @Autowired
    CombatMapper combatMapper;

    @Override
    public Map<String, String> upLoadCombat(int winner, int loser, Date time) {
        Combat combat = new Combat(winner, loser, time);
        combatMapper.insert(combat);
        Map<String, String> response = new HashMap<>();
        response.put("response", "success");
        return response;
    }
}
