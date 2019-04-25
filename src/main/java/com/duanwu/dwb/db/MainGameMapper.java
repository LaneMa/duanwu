package com.duanwu.dwb.db;

import com.duanwu.dwb.model.MainGame;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MainGameMapper extends Mapper<MainGame> {
    @Update("update t_main_game set one = ${one}, two = ${two}, three = ${three}, foul = ${foul}, backboard = ${backboard} where name = #{name} AND quarter = ${quarter}")
    int updateMainGame(@Param("name") String name,
                      @Param("quarter") int quarter,
                      @Param("one") int one,
                      @Param("two") int two,
                      @Param("three") int three,
                      @Param("foul") int foul,
                      @Param("over") int backboard);

    @Select("select * from t_main_game where team = #{team}")
    List<MainGame> getMainGameByTeam(@Param("team") String team);

    @Select("select * from t_main_game where name = #{name}")
    List<MainGame> getMainGameByName(@Param("name") String name);

    @Select("select * from t_main_game where quarter = ${quarter}")
    List<MainGame> getMainGameByQuarter(@Param("quarter") int quarter);
}
