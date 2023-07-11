package com.example.demo.MqttRepository;

import com.example.demo.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface MqttRepository extends JpaRepository<Coordinate, Long> {

    @Query("SELECT co FROM Coordinate co WHERE co.topic = ?1 AND co.dispositivo = ?2")
    List<Coordinate> getAllCoordinateByTopicAndDevice(String topic, String disositivo);



}
