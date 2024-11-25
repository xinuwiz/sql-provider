package com.github.xinuwiz.sql.provider.configuration;

import com.github.xinuwiz.sql.provider.util.ReflectionUtil;

public class ConfigurationBuilder {

    private final Configuration configuration;

    public ConfigurationBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public ConfigurationBuilder(Class<Configuration> clazz) {
        this.configuration = ReflectionUtil.newInstanceWithoutException(clazz, "Failed to copy base configuration.");
    }

    public static ConfigurationBuilder of(Configuration configuration) {
        return new ConfigurationBuilder(configuration);
    }

    public static ConfigurationBuilder of(Class<Configuration> clazz) {
        return new ConfigurationBuilder(clazz);
    }

    public Configuration build() {
        return configuration;
    }
}
