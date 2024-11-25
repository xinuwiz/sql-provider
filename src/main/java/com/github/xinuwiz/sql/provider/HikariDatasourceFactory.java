package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.zaxxer.hikari.HikariDataSource;

public final class HikariDatasourceFactory {

    private static HikariDatasourceFactory instance;

    public HikariDataSource create(Configuration configuration) {
        final HikariDataSource source = new HikariDataSource();

        final String driver = configuration.getDriver();
        final String url = configuration.getUrl();
        final String username = configuration.getUsername();
        final String password = configuration.getPassword();

        source.setDriverClassName(driver);
        source.setJdbcUrl(url);
        source.setUsername(username);
        source.setPassword(password);

        source.addDataSourceProperty("useUnicode", true);
        source.addDataSourceProperty("characterEncoding", "utf8");

        source.addDataSourceProperty("cachePrepStmts", "true");
        source.addDataSourceProperty("prepStmtCacheSize", "250");
        source.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        source.addDataSourceProperty("useServerPrepStmts", "true");
        source.addDataSourceProperty("useLocalSessionState", "true");
        source.addDataSourceProperty("rewriteBatchedStatements", "true");
        source.addDataSourceProperty("cacheResultSetMetadata", "true");
        source.addDataSourceProperty("cacheServerConfiguration", "true");
        source.addDataSourceProperty("elideSetAutoCommits", "true");
        source.addDataSourceProperty("maintainTimeStats", "false");
        source.addDataSourceProperty("alwaysSendSetIsolation", "false");
        source.addDataSourceProperty("cacheCallableStmts", "true");

        return source;
    }

    public static HikariDatasourceFactory getInstance() {
        if (instance == null)
            instance = new HikariDatasourceFactory();
        return instance;
    }
}