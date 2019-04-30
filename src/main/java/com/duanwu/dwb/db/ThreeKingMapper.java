package com.duanwu.dwb.db;

import com.duanwu.dwb.model.ThreeKing;
import tk.mybatis.mapper.common.Mapper;

public interface ThreeKingMapper extends Mapper<ThreeKing> {
    void truncate();
}
