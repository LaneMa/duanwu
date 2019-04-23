package com.duanwu.dwb.controller;

import com.duanwu.dwb.model.Players;
import com.duanwu.dwb.service.PlayersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {
    @Autowired
    PlayersService playersService;

    @GetMapping("/id")
    public Object getPlayersById(@Param("id") int id) {
        Players value = playersService.getPlayersById(id);
        return  value;
    }

    @GetMapping("/name")
    public Object getPlayersByName(@Param("name") String name) {
        Players value = playersService.getPlayersByName(name);
        return  value;
    }

    @GetMapping
    public Object getPlayers() {
        List<Players> value = playersService.getPlayers();
        return  value;
    }
}
