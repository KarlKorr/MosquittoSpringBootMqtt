package com.example.demo;

import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;


@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

//    void senToMqtt(@Header(MqttHeaders.TOPIC) String topic, Message<?> messageJolly);

/*
    void senToMqtt(@Header(MqttHeaders.TOPIC) String topic,
                   @Header("latitude") double latitude,
                   @Header("longitude") double longitude,
                   @Header("key") String key,
                   @Header("name") String name);

 */

      void senToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

//    void senToMqtt(Coordinate data, @Header(MqttHeaders.TOPIC) String topic);


}
