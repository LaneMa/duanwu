package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_main_result")
public class MainResult {
    public String name;
    public int year;
    public int score;
    public int win = 0;
}
