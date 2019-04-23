package com.duanwu.dwb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Table(name = "t_group_players")
public class Session {
    public String name;
    public String session;
    public int one;
    public int two;
    public int three;
    public int foul;
    public int score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date game_time;
}
