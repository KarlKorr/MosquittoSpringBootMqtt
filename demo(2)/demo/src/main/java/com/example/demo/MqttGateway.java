package com.example.demo;

import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;


@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

//    void senToMqtt(@Header(MqttHeaders.TOPIC) String topic, Message<?> messageJolly);

      void senToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

//    void senToMqtt(Coordinate data, @Header(MqttHeaders.TOPIC) String topic);


}
