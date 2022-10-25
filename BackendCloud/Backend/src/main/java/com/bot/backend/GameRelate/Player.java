package com.bot.backend.GameRelate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Player {
    public Integer userId;
    public Integer Choose;
    public Integer score;
    public Player(Integer userId) {
        this.userId = userId;
        this.Choose = 0;
        this.score = 0;
    }
}
