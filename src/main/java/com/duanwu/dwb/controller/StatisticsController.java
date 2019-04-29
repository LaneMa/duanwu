package com.duanwu.dwb.controller;

import com.duanwu.dwb.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/solo")
    public Object getSoloKing() {
        return statisticsService.getSoloKing();
    }

    @GetMapping("/three")
    public Object getThreeKing() {
        return statisticsService.getThreeKing();
    }

    @GetMapping("/backboard")
    public Object getBackboardKing() {
        return statisticsService.getBackboardKing();
    }

    @GetMapping("/champion")
    public Object getChampion() {
        return statisticsService.getChampion();
    }
}
