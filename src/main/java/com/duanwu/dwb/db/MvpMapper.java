package com.duanwu.dwb.db;

import com.duanwu.dwb.model.MainResult;
import com.duanwu.dwb.model.Mvp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MvpMapper extends Mapper<Mvp> {
    void truncate();

    @Select("select * from t_main_mvp where year = ${year}")
    List<Mvp> getMvp(@Param("year") int year);
}
