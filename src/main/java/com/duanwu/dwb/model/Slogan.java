package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_slogan")
public class Slogan {
    public String name;
    public int year;
    public String solo;
    public String three;
    public String backboard;
    public String champion;
    public String mvp;
}
