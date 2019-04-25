package com.duanwu.dwb.service;

import com.duanwu.dwb.db.SessionMapper;
import com.duanwu.dwb.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    SessionMapper sessionMapper;

    public List<Session> getSessions() {
        List<Session> sessionList = sessionMapper.getSessionAllByLevel();

        return sessionList;
    }

    public List<Session> getSessionsByLevel(int level) {
        List<Session> sessionList = sessionMapper.getSessionByLevel(level);

        return sessionList;
    }

    public List<Session> getSessionsByOrder(int order) {
        List<Session> sessionList = sessionMapper.getSessionByOrder(order);

        return sessionList;
    }

    public int getSessionCount() {
        return sessionMapper.getSessionByLevel(1).size()/2 + 2 +1;
    }
}
