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

    public Solo getSoloKing() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int year = Integer.parseInt(sdf.format(date));

        Session session = sessionMapper.getSessionKing(year, "G", 1).get(0);

        Solo solo = new Solo();
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
        for (int m = 1; m < threeList.size(); m++) {
            int n = m - 1;
            if (threeList.get(m).three > threeList.get(n).three) {
                for (int r = 0; r < threeList1.size(); r++) {
                    threeList1.remove(r);
                }
                threeList1.add(threeList.get(m));
            } else if (threeList.get(m).three == threeList.get(n).three) {
                for (int r = 0; r < threeList1.size(); r++) {
                    threeList1.remove(r);
                }
                threeList1.add(threeList.get(m));
                threeList1.add(threeList.get(n));
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

        return three;
    }
}
