package com.duanwu.dwb.service;

import com.duanwu.dwb.db.GroupMapper;
import com.duanwu.dwb.db.PlayersMapper;
import com.duanwu.dwb.model.Group;
import com.duanwu.dwb.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    @Autowired
    PlayersMapper playersMapper;

    @Autowired
    GroupMapper groupMapper;

    public List<Group> getGroups() {
        return groupMapper.getGroups();
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public List<Group> setGroups() {
        groupMapper.truncate();

        String groupName[] = {"A", "B", "C", "D"};
        int j = getRandom(0, 3);
        List<Players> players = playersMapper.getPlayersUnSuspend(0);
        for (int n = 0; n < 4; n++) {
            Group group = new Group();
            group.player_name = players.get(n).name;
            group.group_name = groupName[j%4];
            group.game_time = new Date();

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

            groupMapper.insert(group);
            players.remove(random);
            i--;
            j++;
        }
        return getGroups();
    }
}
