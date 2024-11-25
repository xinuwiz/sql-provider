package com.github.xinuwiz.sql.provider.configuration;

import lombok.Data;

@Data public abstract class Configuration {

    private final String driver;
    private final String url;
    private final String username;
    private final String password;

}