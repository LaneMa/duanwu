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

    public Session cloneSession(Session sessionObject) {
        Session sessionNew = new Session();
        sessionNew.name = sessionObject.name;
        sessionNew.order_number = sessionObject.order_number;
        sessionNew.level = sessionObject.level;
        sessionNew.session = sessionObject.session;
        sessionNew.over = sessionObject.over;
        sessionNew.group_name = sessionObject.group_name;
        sessionNew.one = sessionObject.one;
        sessionNew.two = sessionObject.two;
        sessionNew.three = sessionObject.three;
        sessionNew.foul = sessionObject.foul;
        sessionNew.game_time = sessionObject.game_time;
        sessionNew.score = sessionObject.score;

        return sessionNew;
    }

    public void setSessionState(String name, String session, int type) {
        Session sessionObject = sessionMapper.getSessionOne(name, session).get(0);
        Session sessionNew = cloneSession(sessionObject);
        if (type == 1) {
            sessionNew.one = sessionObject.one + 1;
        } else if (type == 2) {
            sessionNew.two = sessionObject.two + 1;
        } else if (type == 3) {
            sessionNew.three = sessionObject.three + 1;
        } else if (type ==4) {
            sessionNew.foul = sessionObject.foul + 1;
        }
        sessionNew.score = sessionNew.one*1 + sessionNew.two*2 + sessionNew.three*3;
        if (sessionNew.level == 1) {
            if (sessionNew.score >= 7) {
                sessionNew.over = 1;
            }
        } else if ((sessionNew.level == 2) || (sessionNew.level == 3)) {
            if (sessionNew.score >= 11) {
                sessionNew.over = 1;
            }
        }
        sessionMapper.updateSession(name,
                session,
                sessionNew.one,
                sessionNew.two,
                sessionNew.three,
                sessionNew.score,
                sessionNew.foul,
                sessionNew.over);
    }
}
