package com.egen.orders.service;

import com.egen.orders.domain.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    @Autowired
    OrderService orderService;

    private final Logger log = LogManager.getLogger(this.getClass());

    @KafkaListener(topics = "demo", groupId = "group_id",containerFactory="orderKafkaListenerContainerFactory")
    public void consume(Order order)  {
        log.info(String.format("#### -> Consuming message -> %s", order));
        if(orderService.checkIfOrderExists(order.getId())){
            orderService.update(order);
        } else {
            orderService.create(order);
        }
    }
}