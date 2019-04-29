package com.duanwu.dwb.controller;

import com.duanwu.dwb.service.MainGameService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainGameController {
    @Autowired
    MainGameService mainGameService;

    @GetMapping("/team")
    public Object getMainByTeam(@Param("team") String team) {
        return mainGameService.getMainGameByTeam(team);
    }

    @GetMapping("/name")
    public Object getMainByName(@Param("name") String name) {
        return mainGameService.getMainGameByName(name);
    }

    @GetMapping("/quarter")
    public Object getQuarter() {
        return mainGameService.getQuarter();
    }

    @GetMapping("/quarter/set")
    public void setQuarter(@Param("quarter") int quarter) {
        mainGameService.setQuarter(quarter);
    }

    @GetMapping("/increase")
    public void increase(@Param("type") int type, @Param("name") String name, @Param("quarter") int quarter) {
        //type--1:1分，2:2分，3:3分，4:犯规，5:篮板
        mainGameService.increaseData(type, name, quarter);
    }

    @GetMapping("/reduce")
    public void reduce(@Param("type") int type, @Param("name") String name, @Param("quarter") int quarter) {
        //type--1:1分，2:2分，3:3分，4:犯规，5:篮板
        mainGameService.reduceData(type, name, quarter);
    }

    @GetMapping("/over")
    public void gameOver() {
        mainGameService.gameOver();
    }

}
