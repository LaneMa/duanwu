package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Session;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SessionMapper extends Mapper<Session> {
    @Select("select * from t_session where level = ${level}")
    List<Session> getSessionByLevel(@Param("level") int level);

    void truncate();
}
