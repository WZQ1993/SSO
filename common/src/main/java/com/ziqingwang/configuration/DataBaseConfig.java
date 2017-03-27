package com.ziqingwang.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by ziqingwang on 2017/3/1.
 */
@Configuration
public class DataBaseConfig {
    @Bean(name = "userDataSource")
    @Qualifier("userDataSource")
    @ConfigurationProperties(prefix="spring.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "logicDataSource")
    @Qualifier("logicDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.logic")
    public DataSource logicDataSource() {
        return DataSourceBuilder.create().build();
    }

}
