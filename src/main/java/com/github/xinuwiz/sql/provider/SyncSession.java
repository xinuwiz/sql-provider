package com.github.xinuwiz.sql.provider;

import java.sql.Connection;
import java.util.Set;

public abstract class SyncSession extends Session {

    public SyncSession(Connection connection) {
        super(connection);
    }

    public abstract void execute(String sql);

    public abstract void execute(String sql, StatementConsumer consumer);

    public abstract <T> T query(String sql, QueryFunction<T> function);

    public abstract <T> T query(String sql, StatementConsumer consumer, QueryFunction<T> function);

    public abstract <T> Set<T> queryMany(String sql, QueryFunction<T> function);

    public abstract <T> Set<T> queryMany(String sql, StatementConsumer consumer, QueryFunction<T> function);

}