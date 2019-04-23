package com.duanwu.dwb.db;

import com.duanwu.dwb.model.Group;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupMapper extends Mapper<Group> {
    @Select("select * from t_group")
    List<Group> getGroups();

    void truncate();
}
