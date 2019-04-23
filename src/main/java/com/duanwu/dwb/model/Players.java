package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_players")
public class Players {
    public int id;
    public String name;
    public int number;
    public String team;
    public String nick_name;
    public String location;
    public int ability_value;
    public int suspend;
}
