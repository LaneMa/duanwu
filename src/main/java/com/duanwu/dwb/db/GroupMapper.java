package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupMapper extends Mapper<Group> {
    @Select("select * from t_group where year = ${year}")
    List<Group> getGroups(@Param("year") int year);

    @Select("select * from t_group where group_name = #{group_name} AND year = ${year}")
    List<Group> getGroupsByName(@Param("group_name") String group_name, @Param("year") int year);

    void truncate();
}
