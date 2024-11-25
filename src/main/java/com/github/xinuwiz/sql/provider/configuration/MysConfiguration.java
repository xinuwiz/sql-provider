package com.github.xinuwiz.sql.provider.configuration;

public class MysConfiguration extends Configuration {

    public MysConfiguration(String url, String username, String password) {
        super("com.mysql.cj.jdbc.Driver", url, username, password);
    }

    public MysConfiguration() {
        super();
        setDriver("com.mysql.cj.jdbc.Driver");
    }
}
