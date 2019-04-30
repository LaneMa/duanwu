package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Ticket;
import tk.mybatis.mapper.common.Mapper;

public interface TicketMapper extends Mapper<Ticket> {
    void truncate();
}
