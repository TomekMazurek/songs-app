package com.github.tomekmazurek.songsapp.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2Config {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server createH2Server() {
        try {
            return Server.createTcpServer("-tcp");
        } catch (SQLException exc) {
            exc.getErrorCode();
            return null;
        }
    }
}
