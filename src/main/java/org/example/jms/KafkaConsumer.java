package org.example.jms;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "test", groupId = "myGroup")
    public void receiveUser(Object payload) {
        System.out.println(payload);
    }
}
