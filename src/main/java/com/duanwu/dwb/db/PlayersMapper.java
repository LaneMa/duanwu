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

    @Select("select * from t_players where team = #{team}")
    List<Players> getPlayerByTeam(@Param("team") String team);

    @Select("select * from t_players")
    List<Players> getPlayers();

    @Select("select * from t_players where suspend = ${suspend} order by ability_value desc")
    List<Players> getPlayersUnSuspendDesc(@Param("suspend") int suspend);

    @Select("select * from t_players where suspend = ${suspend}")
    List<Players> getPlayersUnSuspend(@Param("suspend") int suspend);

    @Select("select * from t_players order by ability_value desc")
    List<Players> getPlayersByAbility();
}
