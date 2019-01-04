package com.example.demomqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
    把普通pojo实例化到sprirng容器中，其中pojo(Plain Ordinary Java Object)就是普通的Java对象
    Component和CP一起，把配置文件的信息读取并自动封装成实体类
*/
@Component
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfigBean {
    private int database;
    private String host;
    private String password;
    private int port;

    public int getDatabase() {
        return database;
    }
    public void setDatabase(int database) {
        this.database=database;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host=host;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port=port;
    }
}
