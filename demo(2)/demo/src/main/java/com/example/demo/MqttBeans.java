package com.example.demo;

import com.example.demo.MqttService.MqttService;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
public class MqttBeans {

    private final MqttService mqttService;

    public MqttBeans(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    // Si definiscono i canali di messaggistica mqttInputChannel() e mqttOutboundChannel()

    MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{"tcp://localhost:1883"});
//        options.setUserName("admin");
//        String password = "12345678";
//        options.setPassword(password.toCharArray());
        options.setCleanSession(true);

        factory.setConnectionOptions(options);

        return factory;
    }

    // mqttInputChannel è un canale diretto (Direct Channel) che viene utilizzato per ricevere messaggi MQTT
    // da un topic specifico (serverIn), tramite l'utilizzo di un MqttPahoMessageDrivenChannelAdapter

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    // inbound() configura questo adapter e lo registra come "MessageProducer" all'interno dell'applicazione.
    // Questo adapter viene impostato per trasformare i messaggi MQTT in un formato di messaggio standard Spring,
    // utilizzando "DefaultPahoMessageConverter".

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",
                mqttPahoClientFactory(), "#");

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    // Questo handler viene attivato quando il messaggio viene ricevuto dal canale mqttInputChannel e viene utilizzato per
    // gestire il messaggio, eseguendo azioni specifiche in base al contenuto del messaggio.

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                if (topic.equals("myTopic")) {
                    System.out.println("Questo è il nostro topic");
                }
                System.out.println(message.getPayload());
                System.out.println("Il messaggio ricevuto è uguale a: " + message);
                System.out.println("Il messaggio stringato è uguale a: " + message.toString());
                Coordinate coordinate = new Gson().fromJson(message.getPayload().toString(), Coordinate.class);
                System.out.println("Il messaggio deserializzato è uguale a: " + coordinate);
                mqttService.sendMessage(coordinate, coordinate.getTopic());

            }
        };
    }

    // Questo canale viene utilizzato per inviare messaggi MQTT ad un topic specifico tramite l'utilizzo di un MqttPahoMessageHandler
    // Questo handler viene registrato come "MessageHandler" nell'applicazione tramite il metodo "mqttOutBound()."

    @Bean
    public MessageChannel mqttOutboundChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(){
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serveOut", mqttPahoClientFactory());

        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        return messageHandler;

    }
}