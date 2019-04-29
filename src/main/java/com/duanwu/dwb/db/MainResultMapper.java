package com.duanwu.dwb.db;

import com.duanwu.dwb.model.MainResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MainResultMapper extends Mapper<MainResult> {
    @Select("select * from t_main_result where name = #{name} AND year = ${year}")
    List<MainResult> getMainResultByName(@Param("name") String name, @Param("year") int year);
}
