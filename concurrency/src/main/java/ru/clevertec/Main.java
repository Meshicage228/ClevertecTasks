package ru.clevertec;

import ru.clevertec.thread.Consumer;
import ru.clevertec.thread.Producer;
import ru.clevertec.domain.Topic;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Topic firstTopic = new Topic("firstTopic", 3);

        Producer producer = new Producer(firstTopic);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        ThreadGroup group = new ThreadGroup("Consumers");

        List<Thread> list = IntStream.rangeClosed(0, 10)
                .mapToObj(CountDownLatch::new)
                .map(latch -> new Thread(group, new Consumer(firstTopic, latch), "Thread № " + latch.getCount()))
                .peek(Thread::start)
                .toList();

        Consumer consumer = new Consumer(firstTopic, new CountDownLatch(100));
        Thread thread1 = new Thread(group, consumer);
        thread1.start();

        list.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    };
}