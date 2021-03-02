package com.egen.orders.service;

import com.egen.orders.domain.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final Logger log = LogManager.getLogger(this.getClass());
    private static final String TOPIC = "demo";

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendMessage(Order o) {
        log.info(String.format("#### -> Producing message to topic %s -> %s", TOPIC,o));
        this.kafkaTemplate.send(TOPIC, o);
        log.info(String.format("#### -> Message published -> %s", o));
    }
}
