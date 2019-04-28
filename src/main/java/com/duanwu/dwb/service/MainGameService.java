package com.duanwu.dwb.service;

import com.duanwu.dwb.db.MainGameMapper;
import com.duanwu.dwb.model.MainGame;
import com.duanwu.dwb.model.MainGameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MainGameService {
    @Autowired
    MainGameMapper mainGameMapper;

    public List<MainGameScore> getMainGameByTeam(String team) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<MainGameScore> mainGameScores = new ArrayList<>();
        if ("ÕýÈü".equals(team)) {
            List<MainGame> mainGames = mainGameMapper.getMainGameByMain(year);

            for (int i = 0; i < mainGames.size(); i++) {
                Boolean isContain = false;
                for (int j = 0; j < mainGameScores.size(); j++) {
                    if (mainGameScores.get(j).name.equals(mainGames.get(i).team)) {
                        mainGameScores.get(j).score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                        mainGameScores.get(j).backboard += mainGames.get(i).backboard;
                        mainGameScores.get(j).foul += mainGames.get(i).foul;
                        mainGameScores.get(j).one += mainGames.get(i).one;
                        mainGameScores.get(j).two += mainGames.get(i).two;
                        mainGameScores.get(j).three += mainGames.get(i).three;
                        isContain = true;
                    }
                }
                if (isContain == false) {
                    MainGameScore mainGameScore1 = new MainGameScore();
                    mainGameScore1.name = mainGames.get(i).team;
                    mainGameScore1.score = mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                    mainGameScore1.backboard = mainGames.get(i).backboard;
                    mainGameScore1.foul = mainGames.get(i).foul;
                    mainGameScore1.one = mainGames.get(i).one;
                    mainGameScore1.two = mainGames.get(i).two;
                    mainGameScore1.three = mainGames.get(i).three;
                    mainGameScores.add(mainGameScore1);
                }
            }

        } else {
            List<MainGame> mainGames = mainGameMapper.getMainGameByTeam(team, year);
            int score = 0;
            int one = 0;
            int two = 0;
            int three = 0;
            int backboard = 0;
            int foul = 0;
            for (int i = 0; i < mainGames.size(); i++) {
                Boolean isContain = false;
                for (int j = 0; j < mainGameScores.size(); j++) {
                    if (mainGameScores.get(j).name.equals(mainGames.get(i).name)) {
                        mainGameScores.get(j).score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                        mainGameScores.get(j).backboard += mainGames.get(i).backboard;
                        mainGameScores.get(j).foul += mainGames.get(i).foul;
                        mainGameScores.get(j).one += mainGames.get(i).one;
                        mainGameScores.get(j).two += mainGames.get(i).two;
                        mainGameScores.get(j).three += mainGames.get(i).three;
                        isContain = true;
                    }
                }
                if (isContain == false) {
                    MainGameScore mainGameScore1 = new MainGameScore();
                    mainGameScore1.name = mainGames.get(i).name;
                    mainGameScore1.score = mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                    mainGameScore1.backboard = mainGames.get(i).backboard;
                    mainGameScore1.foul = mainGames.get(i).foul;
                    mainGameScore1.one = mainGames.get(i).one;
                    mainGameScore1.two = mainGames.get(i).two;
                    mainGameScore1.three = mainGames.get(i).three;
                    mainGameScores.add(mainGameScore1);
                }
                score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                backboard += mainGames.get(i).backboard;
                foul += mainGames.get(i).foul;
                one += mainGames.get(i).one;
                two += mainGames.get(i).two;
                three += mainGames.get(i).three;
            }
            MainGameScore mainGameScore = new MainGameScore();
            mainGameScore.name = team;
            mainGameScore.backboard = backboard;
            mainGameScore.score = score;
            mainGameScore.foul = foul;
            mainGameScore.one = one;
            mainGameScore.two = two;
            mainGameScore.three = three;
            mainGameScores.add(mainGameScore);
        }

        return mainGameScores;
    }

    public MainGameScore getMainGameByName(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<MainGame> mainGames = mainGameMapper.getMainGameByName(name, year);
        MainGameScore mainGameScore = new MainGameScore();
        mainGameScore.name = name;
        for (int i = 0; i < mainGames.size(); i++) {
            mainGameScore.score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
            mainGameScore.foul += mainGames.get(i).foul;
            mainGameScore.backboard += mainGames.get(i).backboard;
            mainGameScore.one += mainGames.get(i).one;
            mainGameScore.two += mainGames.get(i).two;
            mainGameScore.three += mainGames.get(i).three;
        }

        return mainGameScore;
    }
}
