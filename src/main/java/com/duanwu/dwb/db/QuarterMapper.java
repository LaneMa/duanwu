package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Quarter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface QuarterMapper extends Mapper<Quarter> {
    @Select("select * from t_main_quarter where year = ${year}")
    Quarter getQuarter(@Param("year") int year);

    @Update("update t_main_quarter set quarter = ${quarter} where year = ${year}")
    int updateQuarter(@Param("quarter") int quarter,
                      @Param("year") int year);
}
