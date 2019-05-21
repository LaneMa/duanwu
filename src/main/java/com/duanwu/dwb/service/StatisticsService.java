package com.duanwu.dwb.service;

import com.duanwu.dwb.db.*;
import com.duanwu.dwb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    SessionMapper sessionMapper;

    @Autowired
    SloganMapper sloganMapper;

    @Autowired
    MainGameMapper mainGameMapper;

    @Autowired
    MainResultMapper mainResultMapper;

    @Autowired
    PlayersMapper playersMapper;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    MvpMapper mvpMapper;

    @Autowired
    ThreeKingMapper threeKingMapper;

    @Autowired
    BackboardKingMapper backboardKingMapper;

    public Solo getSoloKing() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Solo solo = new Solo();
        List<Session> sessionList1 = sessionMapper.getSessionKing(year, "G", 1);
        if (sessionList1.size() == 0){
            return solo;
        }
        Session session = sessionList1.get(0);

        solo.year = year;
        solo.name = session.name;

        List<Session> sessionList = sessionMapper.getSessionByName(year, solo.name);
        for (int i = 0; i < sessionList.size(); i++) {
            if (sessionList.get(i).win == 1) {
                solo.win++;
            } else {
                solo.lose++;
            }
            solo.one += sessionList.get(i).one;
            solo.two += sessionList.get(i).two;
            solo.three += sessionList.get(i).three;
        }

        solo.slogan = sloganMapper.getSloganByName(year, solo.name).get(0).solo;

        return solo;
    }

    public Three getThreeKing() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Three three = new Three();
        three.year = year;
        List<MainGame> mainGames = mainGameMapper.getMainGame(year);
        List<Three> threeList = new ArrayList<>();
        for (int i = 0; i < mainGames.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < threeList.size(); j++) {
                if (threeList.get(j).name.equals(mainGames.get(i).name)) {
                    threeList.get(j).three += mainGames.get(i).three;
                    isContain = true;
                }
            }
            if (isContain == false) {
                Three three1 = new Three();
                three1.name = mainGames.get(i).name;
                three1.year = year;
                three1.three = mainGames.get(i).three;
                threeList.add(three1);
            }
        }

        List<Three> threeList1 = new ArrayList<>();
        threeList1.add(threeList.get(0));
        for (int m = 1; m < threeList.size(); m++) {
            if (threeList.get(m).three > threeList1.get(0).three) {
                int t = threeList1.size();
                for (int r = 0; r < t; r++) {
                    threeList1.remove(0);
                }
                threeList1.add(threeList.get(m));
            } else if (threeList.get(m).three == threeList1.get(0).three) {
                threeList1.add(threeList.get(m));
            }
        }

        if (threeList1.size() == 1) {
            three.name = threeList1.get(0).name;
            three.three = threeList1.get(0).three;
        } else if (threeList1.size() > 1) {
            Boolean isWin = false;
            for (int i = 0; i < threeList1.size(); i++) {
                String team = playersMapper.getPlayerByName(threeList1.get(i).name).get(0).team;
                List<MainResult> mainResultList = mainResultMapper.getMainResultByName(team, year);
                if (mainResultList.size() != 0) {
                    if(mainResultList.get(0).win == 1) {
                        three.name = threeList1.get(i).name;
                        three.three = threeList1.get(i).three;
                        isWin = true;
                    }

                }
            }
            if (isWin == false) {
                three.name = threeList1.get(0).name;
                three.three = threeList1.get(0).three;
            }
        }

        three.slogan = sloganMapper.getSloganByName(year, three.name).get(0).three;

        threeKingMapper.truncate();//
        ThreeKing threeKing = new ThreeKing();
        threeKing.year = year;
        threeKing.name = three.name;
        threeKing.count = three.three;
        threeKingMapper.insert(threeKing);

        return three;
    }

    public Backboard getBackboardKing() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Backboard backboard = new Backboard();
        backboard.year = year;
        List<MainGame> mainGames = mainGameMapper.getMainGame(year);
        List<Backboard> backboardList = new ArrayList<>();
        for (int i = 0; i < mainGames.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < backboardList.size(); j++) {
                if (backboardList.get(j).name.equals(mainGames.get(i).name)) {
                    backboardList.get(j).backboard += mainGames.get(i).backboard;
                    isContain = true;
                }
            }
            if (isContain == false) {
                Backboard backboard1 = new Backboard();
                backboard1.name = mainGames.get(i).name;
                backboard1.year = year;
                backboard1.backboard = mainGames.get(i).backboard;
                backboardList.add(backboard1);
            }
        }

        List<Backboard> Backboard1 = new ArrayList<>();
        Backboard1.add(backboardList.get(0));
        for (int m = 1; m < backboardList.size(); m++) {
            if (backboardList.get(m).backboard > Backboard1.get(0).backboard) {
                int t = Backboard1.size();
                for (int r = 0; r < t; r++) {
                    Backboard1.remove(0);
                }
                Backboard1.add(backboardList.get(m));
            } else if (backboardList.get(m).backboard == Backboard1.get(0).backboard) {
                Backboard1.add(backboardList.get(m));
            }
        }

        if (Backboard1.size() == 1) {
            backboard.name = Backboard1.get(0).name;
            backboard.backboard = Backboard1.get(0).backboard;
        } else if (Backboard1.size() > 1) {
            Boolean isWin = false;
            for (int i = 0; i < Backboard1.size(); i++) {
                String team = playersMapper.getPlayerByName(Backboard1.get(i).name).get(0).team;
                List<MainResult> mainResultList = mainResultMapper.getMainResultByName(team, year);
                if (mainResultList.size() != 0) {
                    if(mainResultList.get(0).win == 1) {
                        backboard.name = Backboard1.get(i).name;
                        backboard.backboard = Backboard1.get(i).backboard;
                        isWin = true;
                    }

                }
            }
            if (isWin == false) {
                backboard.name = Backboard1.get(0).name;
                backboard.backboard = Backboard1.get(0).backboard;
            }
        }

        backboard.slogan = sloganMapper.getSloganByName(year, backboard.name).get(0).backboard;

        backboardKingMapper.truncate();//
        BackboardKing backboardKing = new BackboardKing();
        backboardKing.name = backboard.name;
        backboardKing.year = year;
        backboardKing.count = backboard.backboard;
        backboardKingMapper.insert(backboardKing);

        return backboard;
    }

    public Champion getChampion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        MainResult mainResult = mainResultMapper.getMainResultByWin(1, year).get(0);
        Champion champion = new Champion();

        champion.team = mainResult.name;
        champion.year = year;
        champion.slogan = sloganMapper.getSloganByName(year, champion.team).get(0).champion;

        List<MainGame> mainGames = mainGameMapper.getMainGameByTeam(champion.team, year);
        List<ChampionPlayer> championPlayers = new ArrayList<>();
        for (int i = 0; i < mainGames.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < championPlayers.size(); j++) {
                if (championPlayers.get(j).name.equals(mainGames.get(i).name)) {
                    championPlayers.get(j).score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                    championPlayers.get(j).backboard += mainGames.get(i).backboard;
                    isContain = true;
                }
            }
            if (isContain == false) {
                ChampionPlayer championPlayer = new ChampionPlayer();
                championPlayer.name = mainGames.get(i).name;
                championPlayer.team = champion.team;
                championPlayer.score = mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                championPlayer.backboard = mainGames.get(i).backboard;
                championPlayers.add(championPlayer);
            }
        }

        champion.championPlayers = championPlayers;

        return champion;
    }

    public List<Champion> getStatistics() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        List<MainGame> mainGames = mainGameMapper.getMainGameByTeam("湘北", year);
        List<ChampionPlayer> championPlayers = new ArrayList<>();
        Champion champion1 = new Champion();
        champion1.team = "湘北";
        champion1.year = year;
        for (int i = 0; i < mainGames.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < championPlayers.size(); j++) {
                if (championPlayers.get(j).name.equals(mainGames.get(i).name)) {
                    championPlayers.get(j).score += mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                    championPlayers.get(j).backboard += mainGames.get(i).backboard;
                    isContain = true;
                }
            }
            if (isContain == false) {
                ChampionPlayer championPlayer = new ChampionPlayer();
                championPlayer.name = mainGames.get(i).name;
                championPlayer.team = mainGames.get(i).team;
                championPlayer.score = mainGames.get(i).one*1 + mainGames.get(i).two*2 + mainGames.get(i).three*3;
                championPlayer.backboard = mainGames.get(i).backboard;
                championPlayers.add(championPlayer);
            }
        }

        List<MainGame> mainGames2 = mainGameMapper.getMainGameByTeam("狼群", year);
        List<ChampionPlayer> championPlayers2 = new ArrayList<>();
        Champion champion2 = new Champion();
        champion2.team = "狼群";
        champion2.year = year;
        for (int i = 0; i < mainGames2.size(); i++) {
            Boolean isContain = false;
            for (int j = 0; j < championPlayers2.size(); j++) {
                if (championPlayers2.get(j).name.equals(mainGames2.get(i).name)) {
                    championPlayers2.get(j).score += mainGames2.get(i).one*1 + mainGames2.get(i).two*2 + mainGames2.get(i).three*3;
                    championPlayers2.get(j).backboard += mainGames2.get(i).backboard;
                    isContain = true;
                }
            }
            if (isContain == false) {
                ChampionPlayer championPlayer = new ChampionPlayer();
                championPlayer.name = mainGames2.get(i).name;
                championPlayer.team = mainGames2.get(i).team;
                championPlayer.score = mainGames2.get(i).one*1 + mainGames2.get(i).two*2 + mainGames2.get(i).three*3;
                championPlayer.backboard = mainGames2.get(i).backboard;
                championPlayers2.add(championPlayer);
            }
        }

        champion1.championPlayers = championPlayers;
        champion2.championPlayers = championPlayers2;

        champion1.slogan = sloganMapper.getSloganByName(year, champion1.team).get(0).champion;
        champion2.slogan = sloganMapper.getSloganByName(year, champion2.team).get(0).champion;

        List<Champion> champions = new ArrayList<>();
        champions.add(champion1);
        champions.add(champion2);

        return champions;
    }

    public void setTicket(List<Ticket> ticketList) {
        ticketMapper.truncate();//

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Mvp mvp = new Mvp();
        mvp.year = year;
        for (int i = 0; i < ticketList.size(); i++) {
            ticketList.get(i).year = year;
            ticketMapper.insert(ticketList.get(i));
            if (mvp.ticket < ticketList.get(i).ticket) {
                mvp.name = ticketList.get(i).name;
                mvp.ticket = ticketList.get(i).ticket;
            }
        }
        mvpMapper.truncate();//
        mvpMapper.insert(mvp);
    }

    public MvpSlogan getMvp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        MvpSlogan mvpSlogan = new MvpSlogan();
        mvpSlogan.year = year;
        List<Mvp> mvpList = mvpMapper.getMvp(year);
        if (mvpList.size() == 0) {
            return mvpSlogan;
        } else {
            Mvp mvp = mvpList.get(0);
            mvpSlogan.name = mvp.name;
            mvpSlogan.slogan = sloganMapper.getSloganByName(year, mvpSlogan.name).get(0).mvp;
        }

        return mvpSlogan;
    }

    public SoloMap getSoloMap () {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        SoloMap soloMap = new SoloMap();

        //A组
        List<Session> sessionListA1 = sessionMapper.getSessionBySession(year, "A1");
        List<Session> sessionListA2 = sessionMapper.getSessionBySession(year, "A2");
        List<Session> sessionListA3 = sessionMapper.getSessionBySession(year, "A3");
        SoloGroup soloGroupA1 = new SoloGroup();
        soloGroupA1.groupName = "A";
        soloGroupA1.playerLeft = sessionListA1.get(0).name;
        soloGroupA1.score = sessionListA1.get(0).score + " " + ":" + " ";
        soloGroupA1.score += sessionListA1.get(1).score;
        soloGroupA1.playerRight = sessionListA1.get(1).name;

        SoloGroup soloGroupA2 = new SoloGroup();
        soloGroupA2.groupName = "A";
        soloGroupA2.playerLeft = sessionListA2.get(0).name;
        soloGroupA2.score = sessionListA2.get(0).score + " " + ":" + " ";
        soloGroupA2.score += sessionListA2.get(1).score;
        soloGroupA2.playerRight = sessionListA2.get(1).name;

        SoloGroup soloGroupA3 = new SoloGroup();
        soloGroupA3.groupName = "A";
        soloGroupA3.playerLeft = sessionListA3.get(0).name;
        soloGroupA3.score = sessionListA3.get(0).score + " " + ":" + " ";
        soloGroupA3.score += sessionListA3.get(1).score;
        soloGroupA3.playerRight = sessionListA3.get(1).name;

        List<SoloGroup> soloGroupListA = new ArrayList<>();
        soloGroupListA.add(soloGroupA1);
        soloGroupListA.add(soloGroupA2);
        soloGroupListA.add(soloGroupA3);

        soloMap.soloGroupListA = soloGroupListA;

        //B组
        List<Session> sessionListB1 = sessionMapper.getSessionBySession(year, "B1");
        List<Session> sessionListB2 = sessionMapper.getSessionBySession(year, "B2");
        List<Session> sessionListB3 = sessionMapper.getSessionBySession(year, "B3");
        SoloGroup soloGroupB1 = new SoloGroup();
        soloGroupB1.groupName = "B";
        soloGroupB1.playerLeft = sessionListB1.get(0).name;
        soloGroupB1.score = sessionListB1.get(0).score + " " + ":" + " ";
        soloGroupB1.score += sessionListB1.get(1).score;
        soloGroupB1.playerRight = sessionListB1.get(1).name;

        SoloGroup soloGroupB2 = new SoloGroup();
        soloGroupB2.groupName = "B";
        soloGroupB2.playerLeft = sessionListB2.get(0).name;
        soloGroupB2.score = sessionListB2.get(0).score + " " + ":" + " ";
        soloGroupB2.score += sessionListB2.get(1).score;
        soloGroupB2.playerRight = sessionListB2.get(1).name;

        SoloGroup soloGroupB3 = new SoloGroup();
        soloGroupB3.groupName = "B";
        soloGroupB3.playerLeft = sessionListB3.get(0).name;
        soloGroupB3.score = sessionListB3.get(0).score + " " + ":" + " ";
        soloGroupB3.score += sessionListB3.get(1).score;
        soloGroupB3.playerRight = sessionListB3.get(1).name;

        List<SoloGroup> soloGroupListB = new ArrayList<>();
        soloGroupListB.add(soloGroupB1);
        soloGroupListB.add(soloGroupB2);
        soloGroupListB.add(soloGroupB3);

        soloMap.soloGroupListB = soloGroupListB;

        //C组
        List<Session> sessionListC1 = sessionMapper.getSessionBySession(year, "C1");
        List<Session> sessionListC2 = sessionMapper.getSessionBySession(year, "C2");
        List<Session> sessionListC3 = sessionMapper.getSessionBySession(year, "C3");
        SoloGroup soloGroupC1 = new SoloGroup();
        soloGroupC1.groupName = "C";
        soloGroupC1.playerLeft = sessionListC1.get(0).name;
        soloGroupC1.score = sessionListC1.get(0).score + " " + ":" + " ";
        soloGroupC1.score += sessionListC1.get(1).score;
        soloGroupC1.playerRight = sessionListC1.get(1).name;

        SoloGroup soloGroupC2 = new SoloGroup();
        soloGroupC2.groupName = "C";
        soloGroupC2.playerLeft = sessionListC2.get(0).name;
        soloGroupC2.score = sessionListC2.get(0).score + " " + ":" + " ";
        soloGroupC2.score += sessionListC2.get(1).score;
        soloGroupC2.playerRight = sessionListC2.get(1).name;

        SoloGroup soloGroupC3 = new SoloGroup();
        soloGroupC3.groupName = "C";
        soloGroupC3.playerLeft = sessionListC3.get(0).name;
        soloGroupC3.score = sessionListC3.get(0).score + " " + ":" + " ";
        soloGroupC3.score += sessionListC3.get(1).score;
        soloGroupC3.playerRight = sessionListC3.get(1).name;

        List<SoloGroup> soloGroupListC = new ArrayList<>();
        soloGroupListC.add(soloGroupC1);
        soloGroupListC.add(soloGroupC2);
        soloGroupListC.add(soloGroupC3);

        soloMap.soloGroupListC = soloGroupListC;

        //D组
        List<Session> sessionListD1 = sessionMapper.getSessionBySession(year, "D1");
        List<Session> sessionListD2 = sessionMapper.getSessionBySession(year, "D2");
        List<Session> sessionListD3 = sessionMapper.getSessionBySession(year, "D3");
        SoloGroup soloGroupD1 = new SoloGroup();
        soloGroupD1.groupName = "D";
        soloGroupD1.playerLeft = sessionListD1.get(0).name;
        soloGroupD1.score = sessionListD1.get(0).score + " " + ":" + " ";
        soloGroupD1.score += sessionListD1.get(1).score;
        soloGroupD1.playerRight = sessionListD1.get(1).name;

        SoloGroup soloGroupD2 = new SoloGroup();
        soloGroupD2.groupName = "D";
        soloGroupD2.playerLeft = sessionListD2.get(0).name;
        soloGroupD2.score = sessionListD2.get(0).score + " " + ":" + " ";
        soloGroupD2.score += sessionListD2.get(1).score;
        soloGroupD2.playerRight = sessionListD2.get(1).name;

        SoloGroup soloGroupD3 = new SoloGroup();
        soloGroupD3.groupName = "D";
        soloGroupD3.playerLeft = sessionListD3.get(0).name;
        soloGroupD3.score = sessionListD3.get(0).score + " " + ":" + " ";
        soloGroupD3.score += sessionListD3.get(1).score;
        soloGroupD3.playerRight = sessionListD3.get(1).name;

        List<SoloGroup> soloGroupListD = new ArrayList<>();
        soloGroupListD.add(soloGroupD1);
        soloGroupListD.add(soloGroupD2);
        soloGroupListD.add(soloGroupD3);

        soloMap.soloGroupListD = soloGroupListD;

        //E组
        List<Session> sessionListE1 = sessionMapper.getSessionBySession(year, "E1");
        if (sessionListE1 != null && sessionListE1.size() != 0) {
            SoloGroup soloGroupE1 = new SoloGroup();
            soloGroupE1.groupName = "E";
            soloGroupE1.playerLeft = sessionListE1.get(0).name;
            soloGroupE1.score = sessionListE1.get(0).score + " " + ":" + " ";
            soloGroupE1.score += sessionListE1.get(1).score;
            soloGroupE1.playerRight = sessionListE1.get(1).name;

            List<SoloGroup> soloGroupListE = new ArrayList<>();
            soloGroupListE.add(soloGroupE1);

            soloMap.soloGroupListE = soloGroupListE;
        }

        //F组
        List<Session> sessionListF1 = sessionMapper.getSessionBySession(year, "F1");
        if (sessionListF1 != null && sessionListF1.size() != 0) {
            SoloGroup soloGroupF1 = new SoloGroup();
            soloGroupF1.groupName = "F";
            soloGroupF1.playerLeft = sessionListF1.get(0).name;
            soloGroupF1.score = sessionListF1.get(0).score + " " + ":" + " ";
            soloGroupF1.score += sessionListF1.get(1).score;
            soloGroupF1.playerRight = sessionListF1.get(1).name;

            List<SoloGroup> soloGroupListF = new ArrayList<>();
            soloGroupListF.add(soloGroupF1);

            soloMap.soloGroupListF = soloGroupListF;
        }

        //G组
        List<Session> sessionListG1 = sessionMapper.getSessionBySession(year, "G1");
        if (sessionListG1 != null && sessionListG1.size() != 0) {
            SoloGroup soloGroupG1 = new SoloGroup();
            soloGroupG1.groupName = "G";
            soloGroupG1.playerLeft = sessionListG1.get(0).name;
            soloGroupG1.score = sessionListG1.get(0).score + " " + ":" + " ";
            soloGroupG1.score += sessionListG1.get(1).score;
            soloGroupG1.playerRight = sessionListG1.get(1).name;

            List<SoloGroup> soloGroupListG = new ArrayList<>();
            soloGroupListG.add(soloGroupG1);

            soloMap.soloGroupListG = soloGroupListG;
        }

        soloMap.year = year;
        return soloMap;
    }
}
