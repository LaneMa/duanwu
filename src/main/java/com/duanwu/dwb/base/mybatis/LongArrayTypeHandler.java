package com.duanwu.dwb.base.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedTypes(value = {List.class})
public class LongArrayTypeHandler extends BaseTypeHandler<List<Long>> {

    private List<Long> getLongArray(String columnValue) {
        if (columnValue == null) {
            return null;
        }
        String[] values = columnValue.split(",");
        List<Long> list = new ArrayList<>();
        for (String value : values) {
            list.add(Long.parseLong(value));
        }
        return list;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int index,
                                    List<Long> parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder result = new StringBuilder();
        for (Long value : parameter) {
            result.append(value).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        ps.setString(index, result.toString());
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getLongArray(rs.getString(columnName));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getLongArray(rs.getString(columnIndex));
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getLongArray(cs.getString(columnIndex));
    }
}
