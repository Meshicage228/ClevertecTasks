package ru.clevertec.thread;

import lombok.*;
import ru.clevertec.domain.Message;
import ru.clevertec.domain.Topic;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@NoArgsConstructor
public class Consumer extends Thread{
    private Topic topic;
    private CountDownLatch latch;
    private ArrayList<Message> readMessages = new ArrayList<>();
    private int lastIndex = -1;

    public Consumer(Topic topic, CountDownLatch latch) {
        this.topic = topic;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (latch.getCount() > 0) {
                int messageCount = topic.getMessageCount();
                if (lastIndex + 1 < messageCount) {
                    Message message = topic.getMessage(lastIndex + 1);
                    readMessages.add(message);
                    lastIndex++;
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " get " + message);
                } else {
                    topic.getWriteLock().lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " is waiting");
                        topic.getCondition().await(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    } finally {
                        topic.getWriteLock().unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
