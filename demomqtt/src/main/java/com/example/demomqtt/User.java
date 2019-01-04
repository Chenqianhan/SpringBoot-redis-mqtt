package com.example.demomqtt;

import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/* 配置，可理解为spring里xml的<beans>标签
 * @Bean 可理解为spring里xml的<bean>标签 */
@Configuration
/* Serializable序列化提供两种主要特性的支持：
    1. 远程方法调用(RMI)使本来存在与其他机器的对象可以表现出好像就在本地机器上的行为。
    将消息发给远程对象时，需要通过对象序列化来传输参数和返回值。
    2. 使用一个Java Bean时，它的状态信息通常在设计期间配置好。程序启动后，这种状态信息
    必须保存下来，以便程序启动以后恢复；具体工作由对象序列化完成。
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String image_url;

    public User() {};

    public User(String name, String image_url) {
        this.name=name;
        this.image_url=image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url=image_url;
    }
}
