package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_honor")
public class Honor {
    public String name;
    public int mvp;
    public int solo;
    public int backboard;
    public int three;
    public int champion;
}
