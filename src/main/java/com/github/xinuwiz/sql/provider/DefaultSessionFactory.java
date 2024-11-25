package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class DefaultSessionFactory implements SessionFactory {

    private final HikariDataSource source;

    public DefaultSessionFactory(Configuration configuration) {
        this.source = HikariDatasourceFactory.getInstance().create(configuration);
    }

    @Override
    public Session open() {
        try {
            final Connection connection = this.source.getConnection();
            return new DefaultSyncSession(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HikariDataSource getSource() {
        return source;
    }
}