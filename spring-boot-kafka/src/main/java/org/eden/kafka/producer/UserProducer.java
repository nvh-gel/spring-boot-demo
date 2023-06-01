package org.eden.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eden.kafka.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserProducer {

    @Value("${spring.kafka.topic.user:}")
    private String topic;

    private final KafkaTemplate<String, User> kafkaTemplate;

    public String send(User message) {
        String uuid = UUID.randomUUID().toString();
        this.kafkaTemplate.send(this.topic, uuid, message);
        log.info("Sent message {} to topic {}", message, topic);
        return uuid;
    }
}
