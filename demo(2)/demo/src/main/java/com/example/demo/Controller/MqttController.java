package com.example.demo.Controller;

import com.example.demo.Coordinate;
import com.example.demo.MqttGateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttGateway mqttGateway;


    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("topic") String topic, Coordinate message) {

        System.out.println("In questo momento, le coordinate sono: " + message);

        mqttGateway.senToMqtt(String.valueOf(message), topic);
        return ResponseEntity.ok("Success");

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
