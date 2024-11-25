package com.github.xinuwiz.sql.provider;

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
            consumer.accept(statement);
            statement.executeUpdate();
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
            consumer.accept(statement);
            try (ResultSet result = statement.executeQuery()) {
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
            consumer.accept(statement);
            try (ResultSet result = statement.executeQuery()) {
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
