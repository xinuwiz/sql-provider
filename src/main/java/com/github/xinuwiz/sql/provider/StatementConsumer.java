package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecurePreparedStatement;

@FunctionalInterface
public interface StatementConsumer {

    StatementConsumer EMPTY = statement -> { };

    void accept(SecurePreparedStatement statement);

}
