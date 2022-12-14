package com.bot.backend.consumer;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.bot.backend.GameRelate.Game;
import com.bot.backend.consumer.utils.JwtAuthentication;
import com.bot.backend.mapper.UserMapper;
import com.bot.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketServer {
    private static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    private Session session = null;
    private User user;
    private Game game;
    private static UserMapper userMapper;
    private static RestTemplate restTemplate;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {WebSocketServer.restTemplate = restTemplate;}

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.session = session;
        System.out.println("connected!");

        Integer userId = JwtAuthentication.jwtAuthentication(token);
        this.user = userMapper.selectById(userId);

        if(user == null) {
            this.session.close();
            return;
        }
        System.out.println(user);
        users.put(userId, this);
    }

    private void startMatching() {
        System.out.println("start matching!");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        data.add("rating", this.user.getRankscore().toString());
        restTemplate.postForObject("http://127.0.0.1:3001/player/add/", data, String.class);
    }

    private void closeMatching() {
        System.out.println("close matching!");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        restTemplate.postForObject("http://127.0.0.1:3001/player/del/", data, String.class);
    }

    public static void startGame(Integer aId, Integer bId) {
        User a = userMapper.selectById(aId), b = userMapper.selectById(bId);
        Game game = new Game(a.getId(), b.getId());
        JSONObject respA = new JSONObject();
        JSONObject respB = new JSONObject();

        respA.put("event", "game-start");
        respB.put("event", "game-start");

        respA.put("opponent_username", b.getUsername());
        respB.put("opponent_username", a.getUsername());

        respA.put("opponent_photo", b.getPhoto());
        respB.put("opponent_photo", a.getPhoto());

        respA.put("opponent_rating", b.getRankscore());
        respB.put("opponent_rating", a.getRankscore());

        users.get(a.getId()).sendMessage(respA.toJSONString());
        users.get(b.getId()).sendMessage(respB.toJSONString());
        users.get(a.getId()).game = game;
        users.get(b.getId()).game = game;
    }

    public static void sendTempResult(Integer aId, Integer bId, Integer aScore, Integer bScore, Integer winner) {
        User a = userMapper.selectById(aId), b = userMapper.selectById(bId);
        JSONObject respA = new JSONObject();
        JSONObject respB = new JSONObject();

        respA.put("event", "little_match_over");
        respB.put("event", "little_match_over");

        respA.put("winner", winner.toString());
        respB.put("winner", winner.toString());

        respA.put("your_score", aScore.toString());
        respB.put("your_score", bScore.toString());

        respA.put("your_opponent_score", bScore.toString());
        respB.put("your_opponent_score", aScore.toString());

        users.get(a.getId()).sendMessage(respA.toJSONString());
        users.get(b.getId()).sendMessage(respB.toJSONString());
    }

    public static void sendFinishMessage(Integer aId, Integer bId, Integer aScore, Integer bScore, Integer winner) {
        User a = userMapper.selectById(aId), b = userMapper.selectById(bId);
        JSONObject respA = new JSONObject();
        JSONObject respB = new JSONObject();

        respA.put("event", "match_over");
        respB.put("event", "match_over");

        respA.put("final_winner", winner.toString());
        respB.put("final_winner", winner.toString());

        respA.put("your_score", aScore.toString());
        respB.put("your_score", bScore.toString());

        respA.put("your_opponent_score", bScore.toString());
        respB.put("your_opponent_score", aScore.toString());

        users.get(a.getId()).sendMessage(respA.toJSONString());
        users.get(b.getId()).sendMessage(respB.toJSONString());
    }

    @OnClose
    public void onClose() {
        System.out.println("Disconnected!");
        if(this.user != null) {
            users.remove(user.getId());
            MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
            data.add("user_id", this.user.getId().toString());
            restTemplate.postForObject("http://127.0.0.1:3001/player/del/", data, String.class);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject msg = JSON.parseObject(message);
        String event = msg.getString("event");

        if("start_matching".equals(event)) {
            startMatching();
        }
        else if("end_matching".equals(event)) {
            closeMatching();
        }
        else if("choose".equals(event)) {
            Integer choose = Integer.parseInt(msg.getString("choose"));
            System.out.println("player" + user.getId() + "choose" + choose);
            if(game.A.userId.equals(user.getId())){
                game.A.setChoose(choose);
            }
            else game.B.setChoose(choose);
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
         synchronized (this.session) {
             try {
                 this.session.getBasicRemote().sendText(message);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }
}