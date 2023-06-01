package org.eden.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eden.kafka.model.User;
import org.eden.kafka.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@AllArgsConstructor
public class UserConsumer {

    private UserService userService;

    @KafkaListener(topics = "${spring.kafka.topic.user}")
    public void consumerMessage(User message,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) List<String> partitions,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("Received message {} from partition {}, topic {}, offset {}", message, partitions, topics, offsets);
        User user = userService.createUser(message);
        log.info("Created user {}", user);
    }
}
