package com.example.repetitioneliminator.MQTTConfig;
/*
发送消息配置MQTT
 */

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
@Configuration
@IntegrationComponentScan
/*@PropertySource("classpath:application.properties")*/
@ConfigurationProperties(prefix="spring.mqtt")
public class MqttSender {
    private String username;
    private String password;
    private String url;
    private String clientId;
    private String defaultTopic;
    private int qos=2;

    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword() {
        return password;
    }

    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }

    public void setClientId(String clientId) {
        this.clientId=clientId;
    }
    public String getClientId(){
        return clientId;
    }

    public void setDefaultTopic(String defaultTopic){
        this.defaultTopic=defaultTopic;
    }
    public String getDefaultTopic(){
        return defaultTopic;
    }

    @Bean
    public MqttConnectOptions getMqttConnectsOptions() {
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{url});
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;

    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory=new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectsOptions());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel= "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler= new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

}
