package com.bot.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combat {
    public int winner;
    public int loser;
    public Date time;
    public String score;
}
