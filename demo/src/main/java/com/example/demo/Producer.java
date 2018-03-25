package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

@Component
public class Producer {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092");
        configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(configProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(producerFactory);
        template.send("ero", "Z@@@@ZZ@Z@Z");
//        template
    }

    @PostConstruct
    public void thisstuff() {
        System.out.println("start");
        sendMessage("heck");
        System.out.println("sent");
    }
}
