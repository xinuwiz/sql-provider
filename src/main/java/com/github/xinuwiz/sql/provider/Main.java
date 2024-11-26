package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.github.xinuwiz.sql.provider.configuration.ConfigurationBuilder;
import com.github.xinuwiz.sql.provider.configuration.MysConfiguration;

public class Main {

    public static void main(String[] args) {

        Configuration configuration = ConfigurationBuilder.of(MysConfiguration.class)
            .url("localhost:3306")
            .username("root")
            .password("123456")
            .build();

        SessionFactory factory = SessionFactory.newDefault(configuration);

        Session session = factory.open();

        session.execute("CREATE TABLE IF NOT EXISTS `a`", statement -> {
            statement.set(1, 10);
            statement.set(2, 10);
            statement.set(3, 10);
        });
    }
}
