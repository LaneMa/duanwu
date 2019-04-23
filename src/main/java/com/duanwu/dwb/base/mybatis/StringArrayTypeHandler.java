package com.duanwu.dwb.base.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedTypes(value = {List.class})
public class StringArrayTypeHandler extends BaseTypeHandler<List<String>> {

    private List<String> getStringArray(String columnValue) {
        if (columnValue == null) {
            return null;
        }
        String[] values = columnValue.split(",");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(values));
        return list;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int index,
                                    List<String> parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder result = new StringBuilder();
        for (String value : parameter) {
            result.append(value).append(",");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
            ps.setString(index, result.toString());
        }else {
            ps.setString(index, null);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getStringArray(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getStringArray(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getStringArray(cs.getString(columnIndex));
    }
}
