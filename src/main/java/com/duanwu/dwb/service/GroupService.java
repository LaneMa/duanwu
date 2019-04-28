package com.duanwu.dwb.service;

import com.duanwu.dwb.db.GroupMapper;
import com.duanwu.dwb.db.PlayersMapper;
import com.duanwu.dwb.db.SessionMapper;
import com.duanwu.dwb.model.Group;
import com.duanwu.dwb.model.Players;
import com.duanwu.dwb.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GroupService {
    @Value("${groupName}")
    private String groupName[];

    @Autowired
    PlayersMapper playersMapper;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    SessionMapper sessionMapper;

    public List<Group> getGroups() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        return groupMapper.getGroups(year);
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public List<Group> setGroups() {
        groupMapper.truncate();//

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        int j = getRandom(0, 3);
        List<Players> players = playersMapper.getPlayersUnSuspendDesc(0);
        for (int n = 0; n < 4; n++) {
            Group group = new Group();
            group.player_name = players.get(n).name;
            group.group_name = groupName[j%4];
            group.game_time = new Date();
            group.year = year;

            groupMapper.insert(group);
            j++;
        }

        int i = players.size();
        while(i > 4) {
            Group group = new Group();
            int random = getRandom(4, i - 1);
            group.player_name = players.get(random).name;
            group.group_name = groupName[j%4];
            group.game_time = new Date();
            group.year = year;

            groupMapper.insert(group);
            players.remove(random);
            i--;
            j++;
        }

        setSessionLevel1();

        return getGroups();
    }

    public List<Group> getGroupsByName(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        return groupMapper.getGroupsByName(name, year);
    }

    public void setSessionLevel1() {
        sessionMapper.truncate();//

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        for (int i = 0; i < groupName.length; i++) {
            List<Group> groupList = getGroupsByName(groupName[i]);
            List<Session> sessionList = new ArrayList<>();
            int n = groupList.size();
            while (n > 0){
                Session session = new Session();
                int random = 0;
                if (n > 1) {
                    random = getRandom(0, n - 1);
                }
                session.name = groupList.get(random).player_name;
                session.group_name = groupList.get(random).group_name;
                sessionList.add(session);

                groupList.remove(random);
                n--;
            }
            int m = sessionList.size();
            int sessionNumber = 1;
            int number = i + 1;
            for (int r = 0; r < m; r++) {
                for (int t = r+1; t < m; t++) {
                    Session session1 = new Session();
                    session1.group_name = sessionList.get(r).group_name;
                    session1.name = sessionList.get(r).name;
                    session1.session = groupName[i] + sessionNumber;
                    session1.order_number = number;
                    session1.level = 1;
                    session1.game_time = new Date();
                    session1.year = year;

                    Session session2 = new Session();
                    session2.group_name = sessionList.get(t).group_name;
                    session2.name = sessionList.get(t).name;
                    session2.session = groupName[i] + sessionNumber;
                    session2.order_number = number;
                    session2.level = 1;
                    session2.game_time = new Date();
                    session2.year = year;

                    sessionMapper.insert(session1);
                    sessionMapper.insert(session2);

                    number = number + 4;//无论如何都是4组
                    sessionNumber++;
                }
            }
        }
    }
}
