package com.github.xinuwiz.sql.provider;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface StatementConsumer {

    StatementConsumer EMPTY = statement -> { };

    void accept(PreparedStatement statement);

}