package org.example.jms;


import org.example.model.dto.CommentNotificationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<Object, CommentNotificationRecord> kafkaTemplate;

    @Autowired
    public KafkaProducer (KafkaTemplate<Object, CommentNotificationRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCommentNotification (String topic, CommentNotificationRecord comment) {
        kafkaTemplate.send(topic, comment);
    }
}
