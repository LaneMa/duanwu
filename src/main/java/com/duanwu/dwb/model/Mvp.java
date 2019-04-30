package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_main_mvp")
public class Mvp {
    public String name;
    public int ticket = 0;
    public int year;
}
