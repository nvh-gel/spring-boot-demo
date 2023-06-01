package org.eden.kafka.consumer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eden.kafka.model.User;
import org.eden.kafka.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
@Log4j2
@RequiredArgsConstructor
@Getter
public class UserConsumer {

    private final UserService userService;

    private String receivedMessage;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${spring.kafka.topic.user}")
    public void consumerMessage(User message,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) List<String> partitions,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                @Header(KafkaHeaders.OFFSET) List<String> offsets) {
        log.info("Received message {} from partition {}, topic {}, offset {}", message, partitions, topics, offsets);
        this.receivedMessage = message.toString();
        User user = userService.createUser(message);
        latch.countDown();
        log.info("Created user {}", user);
    }

    public void resetLatch() {
        this.latch = new CountDownLatch(1);
    }
}
