package com.duanwu.dwb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duanwu.dwb.model.Ticket;
import com.duanwu.dwb.service.StatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/statistics")
    public Object getStatistics() {
        return statisticsService.getStatistics();
    }

    @PostMapping("/ticket")
    public void setTicket(@RequestBody Map<String, String> str) {
        List<Ticket> ticketList = JSON.parseArray(str.get("ticketList"),Ticket.class);
        statisticsService.setTicket(ticketList);
    }

    @GetMapping("/mvp")
    public Object getMvp() {
        return statisticsService.getMvp();
    }
}
