package com.duanwu.dwb.service;

import com.duanwu.dwb.db.PlayersMapper;
import com.duanwu.dwb.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService {
    @Autowired
    PlayersMapper playersMapper;

    public Players getPlayersById(int id) {
        Players players = playersMapper.getPlayerById(id);

        return players;
    }

    public Players getPlayersByName(String name) {
        Players players = playersMapper.getPlayerByName(name).get(0);

        return players;
    }

    public List<Players> getPlayersByTeam(String team) {
        List<Players> players = playersMapper.getPlayerByTeam(team);

        return players;
    }

    public List<Players> getPlayers() {
        List<Players> playersList = playersMapper.getPlayers();

        return playersList;
    }

    public List<Players> getPlayersUnSuspend() {
        List<Players> playersList = playersMapper.getPlayersUnSuspend(0);

        return playersList;
    }
}
