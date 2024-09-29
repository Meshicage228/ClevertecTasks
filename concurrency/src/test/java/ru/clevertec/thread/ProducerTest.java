package ru.clevertec.thread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.domain.Topic;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("producer tests")
class ProducerTest {
    private Topic topic;

    @BeforeEach
    void setUp() {
        topic = new Topic("Test", 4);
    }

    @Test
    @DisplayName("success messages send by producer")
    public void messagesByProducer() throws InterruptedException {
        Producer producer = new Producer(topic);
        Thread producerThread = new Thread(producer);

        producerThread.start();
        producerThread.join();

        assertEquals(20, topic.getMessages().size());
    }
}