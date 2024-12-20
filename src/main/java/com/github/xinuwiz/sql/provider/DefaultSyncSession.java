package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecurePreparedStatement;
import com.github.xinuwiz.sql.provider.secure.SecureResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public final class DefaultSyncSession extends SyncSession {

    private final EntityAdapterManager entityAdapterManager;

    public DefaultSyncSession(Connection connection, EntityAdapterManager entityAdapterManager) {
        super(connection);
        this.entityAdapterManager = entityAdapterManager;
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

    @Override
    public <T> T queryWithAdapter(String sql, Class<? extends EntityAdapter<T>> clazz) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                SecureResultSet secure = new SecureResultSet(resultSet);
                return this.entityAdapterManager.get(clazz).adapt(secure);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T queryWithAdapter(String sql, StatementConsumer consumer, Class<? extends EntityAdapter<T>> clazz) {
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            SecurePreparedStatement secureStatement = new SecurePreparedStatement(statement);
            consumer.accept(secureStatement);
            try (ResultSet resultSet = statement.executeQuery()) {
                SecureResultSet secureResult = new SecureResultSet(resultSet);
                return this.entityAdapterManager.get(clazz).adapt(secureResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Set<T> queryManyWithAdapter(String sql, Class<? extends EntityAdapter<T>> clazz) {
        final Set<T> elements = new HashSet<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    T value = this.entityAdapterManager.get(clazz).adapt(new SecureResultSet(resultSet));
                    elements.add(value);
                }
                return elements;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Set<T> queryManyWithAdapter(String sql, StatementConsumer consumer, Class<? extends EntityAdapter<T>> clazz) {
        final Set<T> elements = new HashSet<>();
        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            SecurePreparedStatement secure = new SecurePreparedStatement(statement);
            consumer.accept(secure);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    T value = this.entityAdapterManager.get(clazz).adapt(new SecureResultSet(resultSet));
                    elements.add(value);
                }
                return elements;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
