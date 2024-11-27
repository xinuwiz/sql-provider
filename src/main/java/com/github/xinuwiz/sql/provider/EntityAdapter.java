package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.secure.SecureResultSet;

public interface EntityAdapter<T> {

    T adapt(SecureResultSet result);

}
