package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter public final class DefaultSessionFactory implements SessionFactory {

    private final HikariDataSource source;
    private final EntityAdapterManager entityAdapterManager;

    public DefaultSessionFactory(Configuration configuration) {
        this.source = HikariDatasourceFactory.getInstance().create(configuration);
        this.entityAdapterManager = new EntityAdapterManager();
    }

    @Override
    public Session open() {
        try {
            final Connection connection = this.source.getConnection();
            return new DefaultSyncSession(connection, entityAdapterManager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
