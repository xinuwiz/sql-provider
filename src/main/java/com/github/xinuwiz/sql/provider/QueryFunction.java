package com.github.xinuwiz.sql.provider;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface QueryFunction<T> {

    QueryFunction<Object> EMPTY = function -> Optional.empty();

    Optional<T> accept(Function<ResultSet, T> function);

}
