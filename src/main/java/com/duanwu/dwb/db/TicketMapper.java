package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Ticket;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TicketMapper extends Mapper<Ticket> {
    void truncate();

    @Select("select * from t_main_ticket where year = ${year}")
    List<Ticket> getTicket(@Param("year") int year);
}
