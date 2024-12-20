package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecureResultSet;

import java.util.Optional;

@FunctionalInterface
public interface QueryFunction<T> {

    QueryFunction<Object> EMPTY = function -> Optional.empty();

    T apply(SecureResultSet result);

}
