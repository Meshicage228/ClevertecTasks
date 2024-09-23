package ru.clevertec.thread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.domain.Topic;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Custom broker tests")
class ConsumerTest {
    private Topic topic;

    @BeforeEach
    void setUp() {
        topic = new Topic("Test", 4);
    }

    @Test
    @DisplayName("success consumed messages")
    public void successRead() throws InterruptedException {
        Producer producer = new Producer(topic);
        Consumer consumer = new Consumer(topic, new CountDownLatch(10));

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        consumerThread.join();

        assertEquals(10, consumer.getReadMessages().size());
    }
}