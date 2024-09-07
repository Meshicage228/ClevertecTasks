package ru.clevertec.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

@Getter
@Setter
public class Topic {
    private String topicName;
    private ArrayList<Message> messages;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final Condition condition = writeLock.newCondition();
    private Semaphore semaphore;

    public Topic(String topicName, int maxConsumers) {
        this.topicName = topicName;
        semaphore = new Semaphore(maxConsumers);
        messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        writeLock.lock();
        try {
            messages.add(new Message(message));
            condition.signalAll();
        } finally {
            writeLock.unlock();
        }
    }

    public Message getMessage(int index) throws InterruptedException {
        semaphore.acquire();
        try {
            readLock.lock();
            try {
                return messages.get(index);
            } finally {
                readLock.unlock();
            }
        } finally {
            semaphore.release();
        }
    }

    public int getMessageCount() {
        readLock.lock();
        try {
            return messages.size();
        } finally {
            readLock.unlock();
        }
    }
}
