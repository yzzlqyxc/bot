package com.bot.backend.service.combat;

import java.util.Date;
import java.util.Map;

public interface UploadCombatService {
    public Map<String, String> upLoadCombat(int winner, int loser, Date time);
}
