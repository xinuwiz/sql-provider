package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecurePreparedStatement;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

@Getter
public abstract class Session implements AutoCloseable {

    private final Connection connection;

    public Session(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void execute(String sql);

    public abstract void execute(String sql, StatementConsumer consumer);

    public abstract <T> T query(String sql, QueryFunction<T> function);

    public abstract <T> T query(String sql, StatementConsumer consumer, QueryFunction<T> function);

    public abstract <T> Set<T> queryMany(String sql, QueryFunction<T> function);

    public abstract <T> Set<T> queryMany(String sql, StatementConsumer consumer, QueryFunction<T> function);

    public abstract <T> T queryWithAdapter(String sql, Class<T> clazz);

    public abstract <T> T queryWithAdapter(String sql, StatementConsumer consumer, Class<T> clazz);

    public abstract <T> Set<T> queryManyWithAdapter(String sql, Class<T> clazz);

    public abstract <T> Set<T> queryManyWithAdapter(String sql, StatementConsumer consumer, Class<T> clazz);

}
