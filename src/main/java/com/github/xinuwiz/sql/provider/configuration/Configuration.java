package com.github.xinuwiz.sql.provider.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;

}