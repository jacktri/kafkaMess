package com.aggregator;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EroListener {

    public static Logger logger = LoggerFactory.getLogger(EroListener.class);

    @KafkaListener(topics = "ero")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("heckkk");
        logger.info(consumerRecord.toString());
    }
}
