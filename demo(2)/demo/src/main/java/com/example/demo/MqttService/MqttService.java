package com.example.demo.MqttService;

import com.example.demo.Coordinate;
import com.example.demo.MqttRepository.MqttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MqttService {

    @Autowired
    private final MqttRepository mqttRepository;

    public MqttService(MqttRepository mqttRepository) {
        this.mqttRepository = mqttRepository;
    }


    public void sendMessage(Coordinate coordinata, String topic) {
        coordinata.setTopic(topic);
        coordinata.setDataInvio(LocalDate.now().toString() + LocalTime.now().toString().substring(0, 7));
        coordinata.setOrarioInvio(LocalTime.now().toString());
        mqttRepository.save(coordinata);
    }
}
