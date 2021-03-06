package com.duanwu.dwb.service;

import com.duanwu.dwb.db.GroupMapper;
import com.duanwu.dwb.db.SessionMapper;
import com.duanwu.dwb.model.Group;
import com.duanwu.dwb.model.LevelRise;
import com.duanwu.dwb.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SessionService {
    @Value("${groupName}")
    private String groupName[];

    @Autowired
    SessionMapper sessionMapper;

    @Autowired
    GroupMapper groupMapper;

    public List<Session> getSessions() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<Session> sessionList = sessionMapper.getSessionAllByLevel(year);

        return sessionList;
    }

    public List<Session> getSessionsByLevel(int level) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<Session> sessionList = sessionMapper.getSessionByLevel(level, year);

        return sessionList;
    }

    public List<Session> getSessionsByOrder(int order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<Session> sessionList = sessionMapper.getSessionByOrder(order, year);

        return sessionList;
    }

    public int getSessionCount() {
//        if (level == 1) {
//            return sessionMapper.getSessionByLevel().size()/2;
//        } else if (level == 2) {
//            return 2;
//        } else if (level == 3) {
//            return 1;
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        return sessionMapper.getSessionCount(year) / 2;
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

    public Session getRiseLevel1(String group) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<Group> groupList = groupMapper.getGroupsByName(group, year);
        List<LevelRise> levelRises = new ArrayList<>();
        Session session = new Session();
        int count = 0;
        Boolean isSame = false;
        for (int i = 0; i < groupList.size(); i++) {
            LevelRise levelRise = new LevelRise();
            levelRise.name = groupList.get(i).player_name;
            levelRise.winCount = sessionMapper.getWinCount(levelRise.name, groupList.get(i).group_name, 1, year);
            levelRises.add(levelRise);
            if (levelRise.winCount > count) {
                session.name = levelRise.name;
                session.group_name = groupList.get(i).group_name;
                count = levelRise.winCount;
            }
            if (i > 0) {
                if ((levelRises.get(i).winCount == levelRises.get(i-1).winCount) &&
                        (levelRises.get(i).winCount == count)) {
                    isSame = true;
                }
            }
        }
        int score = 0;
        if (isSame == true) {
            for (int i = 0; i < groupList.size(); i++) {
                LevelRise levelRise = new LevelRise();
                levelRise.name = groupList.get(i).player_name;
                levelRise.totalScore = sessionMapper.getTotalScore(levelRise.name, groupList.get(i).group_name, year).totalScore;
                if (levelRise.totalScore > score) {
                    session.name = levelRise.name;
                    session.group_name = groupList.get(i).group_name;
                    score = levelRise.totalScore;
                }
            }
        }

        return session;
    }

    public void setSessionLevel2() {
        List<Session> sessionList = new ArrayList<>();
        for(int j = 0; j < groupName.length; j++) {
            sessionList.add(getRiseLevel1(groupName[j]));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        int order_number = sessionMapper.getSessionByLevel(1, year).size()/2;
        for(int i = 0; i < sessionList.size(); i++) {
            Session session = new Session();
            session.name = sessionList.get(i).name;
            session.game_time = new Date();
            session.level = 2;
            session.year = year;
            if ((sessionList.get(i).group_name.equals("A")) || (sessionList.get(i).group_name.equals("C"))) {
                session.order_number = order_number + 1;
                session.session = "E1";
                session.group_name = "E";
            } else if ((sessionList.get(i).group_name.equals("B")) || (sessionList.get(i).group_name.equals("D"))) {
                session.order_number = order_number + 2;
                session.session = "F1";
                session.group_name = "F";
            }

            sessionMapper.insert(session);
        }
    }

    public void setSessionLevel3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<Session> sessionList = sessionMapper.getSessionRise(11, 2, year);
        int order_number = sessionMapper.getSessionByLevel(1, year).size()/2 + 2;
        for(int i = 0; i < sessionList.size(); i++) {
            Session session = new Session();
            session.name = sessionList.get(i).name;
            session.game_time = new Date();
            session.level = 3;

            session.order_number = order_number + 1;
            session.session = "G1";
            session.group_name = "G";
            session.year = year;

            sessionMapper.insert(session);
        }
    }

    public void setOtherPlayerOver(String name, String session) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Session sessionOther = sessionMapper.getSessionOther(name, session, year).get(0);
        Session sessionNew = cloneSession(sessionOther);
        sessionNew.over = 1;

        sessionMapper.updateSession(sessionNew.name,
                sessionNew.session,
                sessionNew.one,
                sessionNew.two,
                sessionNew.three,
                sessionNew.score,
                sessionNew.win,
                sessionNew.foul,
                sessionNew.over,
                year);
    }

    public void setOtherPlayerOn(String name, String session) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Session sessionOther = sessionMapper.getSessionOther(name, session, year).get(0);
        Session sessionNew = cloneSession(sessionOther);
        sessionNew.over = 0;

        sessionMapper.updateSession(sessionNew.name,
                sessionNew.session,
                sessionNew.one,
                sessionNew.two,
                sessionNew.three,
                sessionNew.score,
                sessionNew.win,
                sessionNew.foul,
                sessionNew.over,
                year);
    }

    public void setSessionState(String name, String session, int type, int level) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Session sessionObject = sessionMapper.getSessionOne(name, session, year).get(0);
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
        if (level == 1) {
            if (sessionNew.score >= 7) {
                sessionNew.over = 1;
                sessionNew.win = 1;
                setOtherPlayerOver(sessionNew.name, sessionNew.session);
            }
        } else if ((level == 2) || (level == 3)) {
            if (sessionNew.score >= 11) {
                sessionNew.over = 1;
                sessionNew.win = 1;
                setOtherPlayerOver(sessionNew.name, sessionNew.session);
            }
        }
        sessionMapper.updateSession(name,
                session,
                sessionNew.one,
                sessionNew.two,
                sessionNew.three,
                sessionNew.score,
                sessionNew.win,
                sessionNew.foul,
                sessionNew.over,
                year);
    }

    public void riseLevel(int level) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        if (sessionMapper.getSessionOverCount(0, level, year) <= 0) {
            if ((level == 1) && (sessionMapper.getSessionByLevel( 2, year).size() <= 0)) {
                setSessionLevel2();
            } else if ((level == 2) && (sessionMapper.getSessionByLevel( 3, year).size() <= 0)) {
                setSessionLevel3();
            }
        }
    }

    public void subtractSessionsState(String name, String session, int type, int level) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Session sessionObject = sessionMapper.getSessionOne(name, session, year).get(0);
        Session sessionNew = cloneSession(sessionObject);
        if (type == 1) {
            sessionNew.one = sessionObject.one - 1;
        } else if (type == 2) {
            sessionNew.two = sessionObject.two - 1;
        } else if (type == 3) {
            sessionNew.three = sessionObject.three - 1;
        } else if (type ==4) {
            sessionNew.foul = sessionObject.foul - 1;
        }
        sessionNew.score = sessionNew.one*1 + sessionNew.two*2 + sessionNew.three*3;
        if (level == 1) {
            if (sessionNew.score < 7 && sessionObject.score >= 7) {
                sessionNew.over = 0;
                sessionNew.win = 0;
                setOtherPlayerOn(sessionNew.name, sessionNew.session);
            }
        } else if((level == 2) || (level == 3)) {
            if (sessionNew.score < 11 && sessionObject.score >= 11) {
                sessionNew.over = 0;
                sessionNew.win = 0;
                setOtherPlayerOn(sessionNew.name, sessionNew.session);
            }
        }

        sessionMapper.updateSession(name,
                session,
                sessionNew.one,
                sessionNew.two,
                sessionNew.three,
                sessionNew.score,
                sessionNew.win,
                sessionNew.foul,
                sessionNew.over,
                year);
    }
}
