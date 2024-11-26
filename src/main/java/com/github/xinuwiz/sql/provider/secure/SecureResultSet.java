package com.github.xinuwiz.sql.provider.secure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecureResultSet implements AutoCloseable {

    private final ResultSet result;

    public SecureResultSet(ResultSet result) {
        this.result = result;
    }

    public Object get(int index) {
        try {
            return result.getObject(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T get(int index, Class<T> clazz) {
        try {
            return clazz.cast(result.getObject(index));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean next() {
        try {
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
