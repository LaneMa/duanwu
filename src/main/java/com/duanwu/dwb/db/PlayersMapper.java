package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Players;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PlayersMapper extends Mapper<Players> {
    @Select("select * from t_players where id = ${id}")
    Players getPlayerById(@Param("id") int id);

    @Select("select * from t_players where name = #{name}")
    Players getPlayerByName(@Param("name") String name);

    @Select("select * from t_players")
    List<Players> getPlayers();

    List<Players> getPlayersByAbility();
}
