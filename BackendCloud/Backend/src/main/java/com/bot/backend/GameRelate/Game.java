package com.bot.backend.GameRelate;

import com.bot.backend.consumer.WebSocketServer;
import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.Combat;
import org.springframework.beans.factory.annotation.Autowired;
import com.bot.backend.mapper.CombatMapper;

import java.util.Date;

public class Game extends Thread{
    public final Player A;
    public final Player B;
    private Date lastTime;

    @Autowired
    CombatMapper combatMapper;

    @Autowired
    WebSocketServer webSocketServer;
    @Autowired
    UserMapper userMapper;

    public Game(Integer userA, Integer userB) {
        this.A = new Player(userA);
        this.B = new Player(userB);
        lastTime = new Date();
    }

    // 查看A和B是否都进行了输入
    private boolean checkInput() {
        if(A.Choose.equals(0) || B.Choose.equals(0))
            return false;
        return true;
    }

    // 检查小局比赛输赢
    private void checkWinLose() {
        Integer aChoose = this.A.Choose, bChoose = this.B.Choose;
        // 1 -> paper 2 -> se   3 -> stone
        if(aChoose.equals(bChoose))
            return;
        Integer winner = -1;
        switch (aChoose) {
            case 1:
                if (bChoose.equals(2))
                    winner = this.B.getUserId();
                else
                    winner = this.A.getUserId();
                break;
            case 2 :
                 if (bChoose.equals(1))
                     winner = this.A.getUserId();
                else
                     winner = this.B.getUserId();
                break;
            case 3 :
                if (bChoose.equals(2))
                    winner = this.A.getUserId();
                else
                    winner = this.B.getUserId();
                break;
            default:
                winner = A.getUserId();
        }
        lastTime = new Date();
        WebSocketServer.sendTempResult(A.getUserId(), B.getUserId(), A.getScore(), B.getScore(), winner);
        if(winner.equals(this.A.getUserId())) {
            A.setScore(A.getScore() + 1);
        }
        else if(!winner.equals(-1)){
            B.setScore(B.getScore() + 1);
        }

        A.setChoose(0);
        B.setChoose(0);
    }

    // 比赛结束 将结果记录数据库
    private void updateDataBase() {
        Integer winner, loser;
        String score;
        if(this.A.getScore() == 3) {
            winner = A.getUserId();
            loser = B.getUserId();
            score = String.format("%d : %d", A.getScore(), B.getScore());
        }
        else {
            winner = B.getUserId();
            loser = A.getUserId();
            score = String.format("%d : %d", B.getScore(), A.getScore());
        }
        Date time = new Date();
        Combat combat = new Combat(winner, loser, time, score);
        combatMapper.insert(combat);
    }

    void checkLastTime() {
        Date now = new Date();
        Long diff = (now.getTime() - lastTime.getTime()) / 1000;
        if(diff >= 10) {
            if(A.Choose.equals(0))
                B.setScore(3);
            else
                A.setScore(3);
        }
    }

    @Override
    public void run() {
        while(!this.A.getScore().equals(3) && !this.B.getScore().equals(3)) {
            if(checkInput()) {
                checkWinLose();
            }
            else {
                checkLastTime();
            }
        }
        updateDataBase();
        Integer winner = A.getScore().equals(3) ? A.getUserId() : B.getUserId();
        WebSocketServer.sendFinishMessage(this.A.getUserId(), this.B.getUserId(), this.A.score, this.B.score, winner);
    }
}
