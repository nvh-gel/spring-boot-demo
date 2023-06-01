package org.eden.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eden.web.model.User;
import org.eden.web.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserConsumer {

    @Value("${spring.rabbitmq.queue.name}")
    private String topic;

    private final UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void consume(User user) {
        log.info("Received message {} from queue {}", user, topic);
        log.info("Created user: {}", userService.createUser(user));
    }
}
