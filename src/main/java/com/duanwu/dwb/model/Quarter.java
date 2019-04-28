package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_main_quarter")
public class Quarter {
    public int year;
    public int quarter;
}
