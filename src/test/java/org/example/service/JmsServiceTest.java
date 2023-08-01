package org.example.service;

import org.example.jms.KafkaProducer;
import org.example.model.dto.CommentNotificationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class JmsServiceTest {
    @Mock
    KafkaProducer kafkaProducer;
    @InjectMocks
    JmsService jmsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendUser() {
        String topic = "user-topic";
        CommentNotificationRecord notificationRecord =
                new CommentNotificationRecord(1L,
                        2,
                        "commentText");
        jmsService.sendCommentNotification(topic, notificationRecord);

        verify(kafkaProducer, times(1)).sendCommentNotification(topic, notificationRecord);
    }
}