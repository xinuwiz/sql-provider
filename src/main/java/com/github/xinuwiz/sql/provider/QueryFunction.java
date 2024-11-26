package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecureResultSet;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface QueryFunction<T> {

    QueryFunction<Object> EMPTY = function -> Optional.empty();

    T apply(SecureResultSet result);

}
