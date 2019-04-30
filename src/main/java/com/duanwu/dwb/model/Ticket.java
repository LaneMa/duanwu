package com.duanwu.dwb.model;

import javax.persistence.Table;

@SuppressWarnings("unused")
@Table(name = "t_main_ticket")
public class Ticket {
    public String name;
    public int ticket;
    public int year;
}
