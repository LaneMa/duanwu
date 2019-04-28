package com.duanwu.dwb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Table(name = "t_session")
public class Session {
    public String name;
    public String group_name;
    public String session;
    public int over = 0;
    public int win = 0;
    public int order_number;
    public int level;
    public int one = 0;
    public int two = 0;
    public int three = 0;
    public int foul = 0;
    public int score = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date game_time;

    public int year;
}
