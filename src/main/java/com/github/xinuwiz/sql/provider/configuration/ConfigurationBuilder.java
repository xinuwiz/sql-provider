package com.github.xinuwiz.sql.provider.configuration;

import com.github.xinuwiz.sql.provider.util.ReflectionUtil;

public class ConfigurationBuilder {

    private final Configuration configuration;

    public ConfigurationBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public ConfigurationBuilder(Class<? extends Configuration> clazz) {
        this.configuration = ReflectionUtil.newInstanceWithoutException(clazz, "Failed to copy base configuration.");
    }

    public static ConfigurationBuilder of(Configuration configuration) {
        return new ConfigurationBuilder(configuration);
    }

    public static ConfigurationBuilder of(Class<? extends Configuration> clazz) {
        return new ConfigurationBuilder(clazz);
    }

    public ConfigurationBuilder driver(String driver) {
        this.configuration.setDriver(driver);
        return this;
    }

    public ConfigurationBuilder url(String url) {
        this.configuration.setUrl(url);
        return this;
    }

    public ConfigurationBuilder username(String username) {
        this.configuration.setUsername(username);
        return this;
    }

    public ConfigurationBuilder password(String password) {
        this.configuration.setPassword(password);
        return this;
    }

    public Configuration build() {
        return configuration;
    }
}
