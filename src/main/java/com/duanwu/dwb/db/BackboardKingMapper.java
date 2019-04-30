package com.duanwu.dwb.db;

import com.duanwu.dwb.model.BackboardKing;
import tk.mybatis.mapper.common.Mapper;

public interface BackboardKingMapper extends Mapper<BackboardKing> {
    void truncate();
}
