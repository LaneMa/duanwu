package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Slogan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SloganMapper extends Mapper<Slogan> {
    @Select("select * from t_slogan where year = ${year} AND name = #{name}")
    List<Slogan> getSloganByName(@Param("year") int year, @Param("name") String name);

}
