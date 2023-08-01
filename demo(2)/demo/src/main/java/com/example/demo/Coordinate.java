package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table
public class Coordinate {

    @Id
    @SequenceGenerator(
            name = "coordinate_sequence",
            sequenceName = "coordinate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coordinate_sequence"
    )
    private Long idCoordinata;

    private double latitude;
    private double longitude;

    private String key;
    private String name;

    private String topic;

    private String orarioInvio;
    private String dataInvio;

    private String dispositivo;

    private String address;

    public Coordinate(Long idCoordinata, double latitude, double longitude, String name, String key, String topic, String dispositivo) {
        this.dispositivo = dispositivo;
        this.idCoordinata = idCoordinata;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.key = key;
        this.topic = topic;
    }

    public Coordinate(Long idCoordinata, double latitude, double longitude, String name, String key, String orarioInvio, String dataInvio, String topic) {
        this.idCoordinata = idCoordinata;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.key = key;
        this.orarioInvio = orarioInvio;
        this.dataInvio = dataInvio;
        this.topic = topic;
    }


    public Coordinate(){}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrarioInvio() {
        return orarioInvio;
    }

    public void setOrarioInvio(String orarioInvio) {
        this.orarioInvio = orarioInvio;
    }

    public String getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(String dataInvio) {
        this.dataInvio = dataInvio;
    }

    public Long getIdCoordinata() {
        return idCoordinata;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //    @Override
//    public String toString() {
//        return "latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", name =" + name +
//                ", key=" + key;
//    }


    @Override
    public String toString() {
        return "Coordinate{" +
                "idCoordinata=" + idCoordinata +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", orarioInvio='" + orarioInvio + '\'' +
                ", topic='" + topic + '\'' +
                ", dataInvio='" + dataInvio + '\'' +
                '}';
    }
}
