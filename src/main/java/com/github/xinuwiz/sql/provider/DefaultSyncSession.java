package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecurePreparedStatement;
import com.github.xinuwiz.sql.provider.secure.SecureResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public final class DefaultSyncSession extends SyncSession {

    public DefaultSyncSession(Connection connection) {
        super(connection);
    }

    @Override
    public void execute(String sql) {
        execute(sql, StatementConsumer.EMPTY);
    }

    @Override
    public void execute(String sql, StatementConsumer consumer) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            SecurePreparedStatement secure = new SecurePreparedStatement(statement);
            consumer.accept(secure);
            secure.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T query(String sql, QueryFunction<T> function) {
        return query(sql, StatementConsumer.EMPTY, function);
    }

    @Override
    public <T> T query(String sql, StatementConsumer consumer, QueryFunction<T> function) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            SecurePreparedStatement secure = new SecurePreparedStatement(statement);
            try (SecureResultSet result = secure.query()) {
                return function.apply(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Set<T> queryMany(String sql, QueryFunction<T> function) {
        return queryMany(sql, StatementConsumer.EMPTY, function);
    }

    @Override
    public <T> Set<T> queryMany(String sql, StatementConsumer consumer, QueryFunction<T> function) {
        final Set<T> elements = new HashSet<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            SecurePreparedStatement secure = new SecurePreparedStatement(statement);
            consumer.accept(secure);
            try (SecureResultSet result = secure.query()) {
                while (!result.next()) {
                    T value = function.apply(result);
                    elements.add(value);
                }
                return elements;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
