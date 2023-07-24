package org.example.service;


import org.example.jms.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmsService {

    private final KafkaProducer kafkaProducer;

    @Autowired
    public JmsService (KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendUser(String topic, String name) {
        kafkaProducer.sendUser(topic, name);
    }
}
