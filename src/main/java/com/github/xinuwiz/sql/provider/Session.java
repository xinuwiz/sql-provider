package com.github.xinuwiz.sql.provider;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    void execute(String command, StatementConsumer consumer) {
        try (PreparedStatement statement = this.connection.prepareStatement(command)) {
            consumer.accept(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
