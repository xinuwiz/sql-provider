package com.github.xinuwiz.sql.provider.secure;

import lombok.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data public class SecurePreparedStatement implements AutoCloseable {

    private final PreparedStatement statement;

    public <T> SecurePreparedStatement set(int index, T value) {
        try {
            statement.setObject(index, value);
            return this;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute() {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SecureResultSet query() {
        try {
            final ResultSet result = statement.executeQuery();
            return new SecureResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
