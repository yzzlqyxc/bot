package com.bot.backend.controller.ranklist;

import com.bot.backend.pojo.User;
import com.bot.backend.service.combat.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetRankListController {

    @Autowired
    GetRankListService getRankListService;

    @GetMapping("/ranklist/")
    public List<User> getRankList() {
        return getRankListService.getRankList();
    }
}
