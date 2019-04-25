package com.duanwu.dwb.controller;

import com.duanwu.dwb.service.SessionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @GetMapping
    public Object getSessions() {
        return sessionService.getSessions();
    }

    @GetMapping("/level")
    public Object getSessionsByLevel(@Param("level") int level) {
        return sessionService.getSessionsByLevel(level);
    }

    @GetMapping("/order")
    public Object getSessionsByOrder(@Param("order") int order) {
        return sessionService.getSessionsByOrder(order);
    }

    @GetMapping("/count")
    public Object getSessionsByOrder() {
        return sessionService.getSessionCount();
    }
}
