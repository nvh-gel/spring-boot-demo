package org.eden.kafka;

import org.eden.kafka.consumer.UserConsumer;
import org.eden.kafka.model.User;
import org.eden.kafka.producer.UserProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@DirtiesContext
class EmbeddedKafkaIntTest {

    @Autowired
    private UserProducer userProducer;
    @Autowired
    private UserConsumer userConsumer;

    @BeforeEach
    void setUp() {
        userConsumer.resetLatch();
    }

    @Test
    void testProducingAndConsumeMessageOK() throws InterruptedException {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");

        String id = userProducer.send(user);
        UUID uuid = UUID.fromString(id);
        Assertions.assertNotNull(uuid);

        boolean messageConsumed = userConsumer.getLatch().await(10, TimeUnit.SECONDS);
        Assertions.assertTrue(messageConsumed);
        Assertions.assertTrue(userConsumer.getReceivedMessage().contains(user.toString()));
    }
}
