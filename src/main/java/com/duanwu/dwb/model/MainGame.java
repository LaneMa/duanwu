package com.duanwu.dwb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Table(name = "t_main_game")
public class MainGame {
    public String name;
    public String team;
    public int one = 0;
    public int two = 0;
    public int three = 0;
    public int foul = 0;
    public int backboard = 0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date game_time;

    public int quarter = 1;
}
