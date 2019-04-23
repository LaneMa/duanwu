package com.duanwu.dwb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Table(name = "t_group")
public class Group {
    public String group_name;
    public String player_name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date game_time;
}
