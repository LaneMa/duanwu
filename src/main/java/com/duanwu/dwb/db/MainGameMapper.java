package com.duanwu.dwb.db;

import com.duanwu.dwb.model.MainGame;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MainGameMapper extends Mapper<MainGame> {
    @Update("update t_main_game set one = ${one}, two = ${two}, three = ${three}, foul = ${foul}, backboard = ${backboard} where name = #{name} AND quarter = ${quarter} AND year = ${year}")
    int updateMainGame(@Param("name") String name,
                      @Param("quarter") int quarter,
                      @Param("one") int one,
                      @Param("two") int two,
                      @Param("three") int three,
                      @Param("foul") int foul,
                      @Param("backboard") int backboard,
                      @Param("year") int year);

    @Select("select * from t_main_game where team = #{team} AND year = ${year}")
    List<MainGame> getMainGameByTeam(@Param("team") String team, @Param("year") int year);

    @Select("select * from t_main_game where year = ${year}")
    List<MainGame> getMainGameByMain(@Param("year") int year);

    @Select("select * from t_main_game where name = #{name} AND year = ${year}")
    List<MainGame> getMainGameByName(@Param("name") String name, @Param("year") int year);

    @Select("select * from t_main_game where name = #{name} AND year = ${year} AND quarter = ${quarter}")
    MainGame getMainGameByNameByQuarter(@Param("name") String name, @Param("year") int year, @Param("quarter") int quarter );

    @Select("select * from t_main_game where quarter = ${quarter} AND year = ${year}")
    List<MainGame> getMainGameByQuarter(@Param("quarter") int quarter, @Param("year") int year);
}
