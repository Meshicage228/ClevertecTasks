package ru.clevertec.thread;

import lombok.RequiredArgsConstructor;
import ru.clevertec.domain.Topic;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Producer extends Thread {
    private final Topic topic;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            topic.addMessage("Message " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
