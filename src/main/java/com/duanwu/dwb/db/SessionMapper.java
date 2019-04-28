package com.duanwu.dwb.db;

import com.duanwu.dwb.model.LevelRise;
import com.duanwu.dwb.model.Session;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SessionMapper extends Mapper<Session> {
    @Select("select * from t_session order by order_number ASC where year = ${year}")
    List<Session> getSessionAllByLevel(@Param("year") int year);

    @Select("select * from t_session where level = ${level} AND year = ${year}")
    List<Session> getSessionByLevel(@Param("level") int level, @Param("year") int year);

    @Select("select * from t_session where group = #{group} AND year = ${year}")
    List<Session> getSessionByGroup(@Param("group") String group, @Param("year") int year);

    @Select("select * from t_session where order_number = ${order_number} AND year = ${year}")
    List<Session> getSessionByOrder(@Param("order_number") int order_number, @Param("year") int year);

    @Select("select * from t_session where name = #{name} AND session = #{session} AND year = ${year}")
    List<Session> getSessionOne(@Param("name") String name, @Param("session") String session, @Param("year") int year);

    @Select("select * from t_session where name != #{name} AND session = #{session} AND year = ${year}")
    List<Session> getSessionOther(@Param("name") String name, @Param("session") String session, @Param("year") int year);

    @Update("update t_session set one = ${one}, two = ${two}, three = ${three}, score = ${score}, win = ${win}, foul = ${foul}, over = ${over} where name = #{name} AND session = #{session} AND year = ${year}")
    int updateSession(@Param("name") String name,
                      @Param("session") String session,
                      @Param("one") int one,
                      @Param("two") int two,
                      @Param("three") int three,
                      @Param("score") int score,
                      @Param("win") int win,
                      @Param("foul") int foul,
                      @Param("over") int over,
                      @Param("year") int year);

    @Select("select count(*) from t_session where over = ${over} AND level = ${level} AND year = ${year}")
    int getSessionOverCount(@Param("over") int over, @Param("level") int level, @Param("year") int year);

    @Select("select * from t_session where score >= ${score} AND level = ${level} AND year = ${year}")
    List<Session> getSessionRise(@Param("score") int score, @Param("level") int level, @Param("year") int year);

    @Select("select count(*) from t_session where name = #{name} AND group_name = #{group_name} AND win = ${win} AND year = ${year}")
    int getWinCount(@Param("name") String name, @Param("group_name") String group_name, @Param("win") int win, @Param("year") int year);

    @Select("select sum(score) AS totalScore from t_session where name = #{name} AND group_name = #{group_name} AND year = ${year}")
    LevelRise getTotalScore(@Param("name") String name, @Param("group_name") String group_name, @Param("year") int year);

    void truncate();

    @Select("select ifnull(count(1),0) from t_session where year = ${year}")
    int getSessionCount(@Param("year") int year);

}
