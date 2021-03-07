package com.example.DependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.example.DependencyInjection.MyDbConfig;

public class MyApplicationConfig {
    @Autowired
    Environment env;

    @Bean(name="dbConfig")
    public MyDbConfig getDbConfig(){

        MyDbConfig dbConf = new MyDbConfig();
        dbConf.setMsg(env.getProperty("db.msg"));
        dbConf.setEmail(env.getProperty("db.email"));
        return dbConf;
    }
}
