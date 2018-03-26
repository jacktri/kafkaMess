package com.example.demo;

import static java.lang.Thread.sleep;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

    boolean running = true;
    public void sendMessage(String msg) throws InterruptedException {
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
        try (KafkaProducer producer = new KafkaProducer<String, String>(configProps)) {
            while(running) {
                System.out.println("sending");
                ProducerRecord<Long, String> record = new ProducerRecord<>("ero", "Hello Mom ");
                producer.send(record);
                System.out.println("sent");
                sleep(5000);
            }
        }
    }

}
