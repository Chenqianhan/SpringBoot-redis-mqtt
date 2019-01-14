package com.example.repetitioneliminator.RedisConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="redis")
public class RedisConfigBean {
    private int database;
    private String host;
    private String password;
    private int port;
    private int timeout;

/*    @Value("${redis.sentinel.nodes}")
    private String redisNodes;

    @Value("${redis.sentinel.master}")
    private String master;
*/
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

    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout=timeout;
    }
    /*
    public String getRedisNodes() {
        return redisNodes;
    }
    public void setRedisNodes(String redisNodes) {
        this.redisNodes=redisNodes;
    }

    public String getMaster() {
        return master;
    }
    public void setMaster(String master) {
        this.master=master;
    }
    */
}
