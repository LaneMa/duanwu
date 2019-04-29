package com.duanwu.dwb.db;

import com.duanwu.dwb.model.MainResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MainResultMapper extends Mapper<MainResult> {
    @Select("select * from t_main_result where name = #{name} AND year = ${year}")
    List<MainResult> getMainResultByName(@Param("name") String name, @Param("year") int year);

    @Update("update t_main_result set score = ${score}, win = ${win} where name = #{name} AND year = ${year}")
    int updateMainResult(@Param("name") String name,
                      @Param("score") int score,
                      @Param("win") int win,
                      @Param("year") int year);
}
