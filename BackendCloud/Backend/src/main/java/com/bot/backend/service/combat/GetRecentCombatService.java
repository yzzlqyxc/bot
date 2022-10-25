package com.bot.backend.service.combat;

import com.bot.backend.pojo.Combat;

import java.util.List;
import java.util.Map;

public interface GetRecentCombatService {
    public List<Map<String, String>> getRecentCombat();
}
