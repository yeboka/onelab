package org.example.service;

import org.example.jms.KafkaProducer;
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
        String name = "John Doe";

        jmsService.sendUser(topic, name);

        verify(kafkaProducer, times(1)).sendUser(topic, name);
    }
}