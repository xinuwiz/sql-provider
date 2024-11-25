package com.github.xinuwiz.sql.provider;

import com.zaxxer.hikari.HikariDataSource;

public interface SessionFactory {

    Session open();

    HikariDataSource getSource();

}
