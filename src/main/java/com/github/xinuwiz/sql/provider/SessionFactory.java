package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.zaxxer.hikari.HikariDataSource;

public interface SessionFactory {

    Session open();

    HikariDataSource getSource();

    static DefaultSessionFactory newDefault(Configuration configuration) {
        return new DefaultSessionFactory(configuration);
    }
}
