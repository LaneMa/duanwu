package com.duanwu.dwb.service;

import com.duanwu.dwb.db.MainGameMapper;
import com.duanwu.dwb.model.MainGame;
import com.duanwu.dwb.model.MainGameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainGameService {
    @Autowired
    MainGameMapper mainGameMapper;

    public List<MainGameScore> getMainGameByTeam(String team) {
        List<MainGame> mainGames = mainGameMapper.getMainGameByTeam(team);
        List<MainGameScore> mainGameScores = new ArrayList<>();
        int score = 0;
        int backboard = 0;
        int foul = 0;
        for (int i = 0; i < mainGames.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < mainGameScores.size(); j++) {
                if (mainGameScores.get(j).name.equals(mainGames.get(i).name)) {
                    mainGameScores.get(j).score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                    mainGameScores.get(j).backboard += mainGames.get(i).backboard;
                    mainGameScores.get(j).foul += mainGames.get(i).foul;
                    isContain = true;
                }
            }
            if (isContain == false) {
                MainGameScore mainGameScore1 = new MainGameScore();
                mainGameScore1.name = mainGames.get(i).name;
                mainGameScore1.score = mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                mainGameScore1.backboard = mainGames.get(i).backboard;
                mainGameScore1.foul = mainGames.get(i).foul;
                mainGameScores.add(mainGameScore1);
            }
            score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
            backboard += mainGames.get(i).backboard;
            foul += mainGames.get(i).foul;
        }
        MainGameScore mainGameScore = new MainGameScore();
        mainGameScore.name = team;
        mainGameScore.backboard = backboard;
        mainGameScore.score = score;
        mainGameScore.foul = foul;
        mainGameScores.add(mainGameScore);

        return mainGameScores;
    }

    public MainGameScore getMainGameByName(String name) {
        List<MainGame> mainGames = mainGameMapper.getMainGameByName(name);
        MainGameScore mainGameScore = new MainGameScore();
        mainGameScore.name = name;
        for (int i = 0; i < mainGames.size(); i++) {
            mainGameScore.score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
            mainGameScore.foul += mainGames.get(i).foul;
            mainGameScore.backboard += mainGames.get(i).backboard;
        }

        return mainGameScore;
    }
}
