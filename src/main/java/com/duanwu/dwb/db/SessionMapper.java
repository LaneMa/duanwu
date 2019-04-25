package com.duanwu.dwb.db;

import com.duanwu.dwb.model.LevelRise;
import com.duanwu.dwb.model.Session;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SessionMapper extends Mapper<Session> {
    @Select("select * from t_session order by order_number ASC")
    List<Session> getSessionAllByLevel();

    @Select("select * from t_session where level = ${level}")
    List<Session> getSessionByLevel(@Param("level") int level);

    @Select("select * from t_session where group = #{group}")
    List<Session> getSessionByGroup(@Param("group") String group);

    @Select("select * from t_session where order_number = ${order_number}")
    List<Session> getSessionByOrder(@Param("order_number") int order_number);

    @Select("select * from t_session where name = #{name} AND session = #{session}")
    List<Session> getSessionOne(@Param("name") String name, @Param("session") String session);

    @Select("select * from t_session where name != #{name} AND session = #{session}")
    List<Session> getSessionOther(@Param("name") String name, @Param("session") String session);

    @Update("update t_session set one = ${one}, two = ${two}, three = ${three}, score = ${score}, foul = ${foul}, over = ${over} where name = #{name} AND session = #{session}")
    int updateSession(@Param("name") String name,
                      @Param("session") String session,
                      @Param("one") int one,
                      @Param("two") int two,
                      @Param("three") int three,
                      @Param("score") int score,
                      @Param("foul") int foul,
                      @Param("over") int over);

    @Select("select count(*) from t_session where over = ${over} AND level = ${level}")
    List<Session> getSessionOverCount(@Param("over") int over, @Param("level") int level);

    @Select("select * from t_session where score >= ${score} AND level = ${level}")
    List<Session> getSessionRise(@Param("score") int score, @Param("level") int level);

    @Select("select count(*) from t_session where name = #{name} AND group = #{group} AND win = ${win}")
    int getWinCount(@Param("name") String name, @Param("group") String group, @Param("win") int win);

    @Select("select sum(score) AS totalScore from t_session where name = #{name} AND group = #{group}")
    LevelRise getTotalScore(@Param("name") String name, @Param("group") String group);

    void truncate();
}
