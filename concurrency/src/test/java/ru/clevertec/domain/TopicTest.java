package ru.clevertec.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("topic tests")
class TopicTest {
    private Topic topic;

    @BeforeEach
    void setUp() {
        topic = new Topic("testTopic", 5);
        topic.addMessage("Test");
    }

    @Test
    @DisplayName("add message success")
    public void testAddMessage() {
        assertEquals(1, topic.getMessageCount());
    }

    @Test
    @DisplayName("check message content")
    public void testGetMessage() throws InterruptedException {
        Message message = topic.getMessage(0);

        assertNotNull(message);
        assertEquals("Test", message.getMessageContent());
    }

    @Test
    @DisplayName("semaphore limit test")
    public void testSemaphoreLimit() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    topic.getMessage(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        Thread.sleep(1000);
        assertEquals(5, topic.getSemaphore().availablePermits());
    }
}