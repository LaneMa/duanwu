package com.duanwu.dwb.service;

import com.duanwu.dwb.db.MainGameMapper;
import com.duanwu.dwb.db.MainResultMapper;
import com.duanwu.dwb.db.QuarterMapper;
import com.duanwu.dwb.model.MainGame;
import com.duanwu.dwb.model.MainGameScore;
import com.duanwu.dwb.model.MainResult;
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

    @Autowired
    MainResultMapper mainResultMapper;

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

    public void gameOver() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<MainGameScore> mainGameScores = new ArrayList<>();
        String team = "湘北";
        List<MainGame> mainGames = mainGameMapper.getMainGameByTeam(team, year);
        int score = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int backboard = 0;
        int foul = 0;
        for (int i = 0; i < mainGames.size(); i++) {
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

        String team2 = "狼群";
        List<MainGame> mainGames2 = mainGameMapper.getMainGameByTeam(team2, year);
        int score2 = 0;
        int one2 = 0;
        int two2 = 0;
        int three2 = 0;
        int backboard2 = 0;
        int foul2 = 0;
        for (int i = 0; i < mainGames2.size(); i++) {
            score2 += mainGames2.get(i).one*1 + mainGames2.get(i).two*2 + mainGames2.get(i).three*3;
            backboard2 += mainGames2.get(i).backboard;
            foul2 += mainGames2.get(i).foul;
            one2 += mainGames2.get(i).one;
            two2 += mainGames2.get(i).two;
            three2 += mainGames2.get(i).three;
        }
        MainGameScore mainGameScore2 = new MainGameScore();
        mainGameScore2.name = team;
        mainGameScore2.backboard = backboard;
        mainGameScore2.score = score;
        mainGameScore2.foul = foul;
        mainGameScore2.one = one;
        mainGameScore2.two = two;
        mainGameScore2.three = three;

        MainResult mainResult = new MainResult();
        mainResult.name = "湘北";
        mainResult.score = mainGameScore.score;
        mainResult.year = year;
        MainResult mainResult2 = new MainResult();
        mainResult2.name = "狼群";
        mainResult2.score = mainGameScore.score;
        mainResult2.year = year;
        if (mainGameScore.score > mainGameScore2.score) {
            mainResult.win = 1;
            mainResult2.win = 0;
        } else {
            mainResult.win = 0;
            mainResult2.win = 1;
        }
        mainResultMapper.updateMainResult(mainResult.name, mainResult.score, mainResult.win, mainResult.year);
        mainResultMapper.updateMainResult(mainResult2.name, mainResult2.score, mainResult2.win, mainResult2.year);
    }
}
