package com.github.xinuwiz.sql.provider;

import java.sql.Connection;

public abstract class SyncSession extends Session {

    public SyncSession(Connection connection) {
        super(connection);
    }
}