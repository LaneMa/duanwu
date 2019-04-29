package com.duanwu.dwb.service;

import com.duanwu.dwb.db.MainGameMapper;
import com.duanwu.dwb.db.QuarterMapper;
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

    @Autowired
    QuarterMapper quarterMapper;

    public List<MainGameScore> getMainGameByTeam(String team) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<MainGameScore> mainGameScores = new ArrayList<>();
        if ("正赛".equals(team)) {
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

    public int getQuarter() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        return quarterMapper.getQuarter(year).quarter;
    }

    public void setQuarter(int quarter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        quarterMapper.updateQuarter(quarter, year);
    }

    public void increaseData(int type, String name, int quarter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        MainGame mainGame = mainGameMapper.getMainGameByNameByQuarter(name, year, quarter);
        int one = mainGame.one;
        int two = mainGame.two;
        int three = mainGame.three;
        int foul = mainGame.foul;
        int backboard = mainGame.backboard;
        if (type == 1) {
            one += 1;
        } else if (type == 2) {
            two += 1;
        } else if (type == 3) {
            three += 1;
        } else if (type == 4) {
            foul += 1;
        } else if (type == 5) {
            backboard += 1;
        }

        mainGameMapper.updateMainGame(name, quarter, one, two, three, foul, backboard, year);
    }

    public void reduceData(int type, String name, int quarter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        MainGame mainGame = mainGameMapper.getMainGameByNameByQuarter(name, year, quarter);
        int one = mainGame.one;
        int two = mainGame.two;
        int three = mainGame.three;
        int foul = mainGame.foul;
        int backboard = mainGame.backboard;
        if (type == 1) {
            one -= 1;
        } else if (type == 2) {
            two -= 1;
        } else if (type == 3) {
            three -= 1;
        } else if (type == 4) {
            foul -= 1;
        } else if (type == 5) {
            backboard -= 1;
        }

        mainGameMapper.updateMainGame(name, quarter, one, two, three, foul, backboard, year);
    }
}
