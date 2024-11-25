package com.github.xinuwiz.sql.provider;

import java.sql.Connection;

public final class SyncSession extends Session {

    public SyncSession(Connection connection) {
        super(connection);
    }
}
