package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupMapper extends Mapper<Group> {
    @Select("select * from t_group")
    List<Group> getGroups();

    @Select("select * from t_group where group_name = #{group_name}")
    List<Group> getGroupsByName(@Param("group_name") String group_name);

    void truncate();
}
