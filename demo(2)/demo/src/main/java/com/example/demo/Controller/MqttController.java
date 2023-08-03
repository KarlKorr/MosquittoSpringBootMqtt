package com.example.demo.Controller;

import com.example.demo.Coordinate;
import com.example.demo.MqttGateway;
import com.example.demo.MqttService.MqttService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class MqttController {

    @Autowired
    MqttGateway mqttGateway;

    @Autowired
    private final MqttService mqttService;

    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    //    @PostMapping("/sendMessage")
//    public ResponseEntity<String> sendMessage(@RequestParam("topic") String topic, Coordinate message) {
//
//        System.out.println("In questo momento, le coordinate sono: " + message);
//
//        mqttGateway.senToMqtt(message, topic);
//        return ResponseEntity.ok("Success");
//
//    }
    //versione corretta

//    @PostMapping("/sendMessage")
//    public ResponseEntity<String> sendMessage(@RequestParam("topic") String topic, Coordinate message) {
//
//        System.out.println("In questo momento, le coordinate sono: " + message);
//
//        mqttGateway.senToMqtt(String.valueOf(message), topic);
//        return ResponseEntity.ok("Success");
//    }


//    @PostMapping("/sendMessage")
//    public ResponseEntity<?> sendMessage(@RequestParam("topic") String topic, String message){
//
//        System.out.println("In questo momento, le coordinate sono: " + message);
//        Message<?> messageJolly = MessageBuilder.withPayload(message.getKey())
//                .setHeader("latitude", message.getLatitude())
//                .setHeader("longitude", message.getLongitude())
//             //   .setHeader("key", message.getKey())
//                .setHeader("name", message.getName())
//                .build();
//        mqttGateway.senToMqtt(topic, messageJolly);
//
//        return ResponseEntity.ok("Success");
//    }





    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessagetoTelegraf(@RequestParam("topic") String topic, Coordinate message) {

        System.out.println("In questo momento, le coordinate sono: " + message);


        mqttGateway.senToMqtt(message.toString(), topic);
        mqttService.sendMessage(message, topic);


        return ResponseEntity.ok("Success");

    }

    @PostMapping("/sendMessage2")
    public void sendMessagetoPostgres(String topic, Coordinate coordinata){
        System.out.println("In questo momento, le coordinate sono: " + coordinata);
        mqttService.sendMessage(coordinata, topic);
    }





//    @PostMapping("/sendMessage")
//    public ResponseEntity<?> publish(@RequestBody String mqttMessage){
//
//        try {
//
//            JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
//            mqttGateway.senToMqtt(convertObject.get("message").toString(), convertObject.get("topic").toString());
//            return ResponseEntity.ok("Success");
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            return ResponseEntity.ok("fail");
//        }
//    }

//    @PostMapping("/sendMessage/{mqttMessage}")
//    public ResponseEntity<?> publish(@RequestBody JsonObject mqttMessage) {
//        try {
//            String message = mqttMessage.get("message").getAsString();
//            String topic = mqttMessage.get("topic").getAsString();
//
//            mqttGateway.senToMqtt(message, topic);
//            return ResponseEntity.ok("Success");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.ok("fail");
//        }
//    }

}
