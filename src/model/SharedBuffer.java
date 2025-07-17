package model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {
    private final int maxBufferSize = 30;
    private final String[] storageArray = new String[maxBufferSize];
    private int head = 0; // index for consumer
    private int tail = 0; // index for producer
    private int count = 0;

    private final Semaphore emptySlots = new Semaphore(maxBufferSize); // initially all slots are empty
    private final Semaphore filledSlots = new Semaphore(0);            // no filled slots initially
    private final Semaphore mutex = new Semaphore(1);

    public void produce(String item) throws InterruptedException {
        emptySlots.acquire();
        mutex.acquire();
        try {
            storageArray[tail] = item;
            tail = (tail + 1) % maxBufferSize;
            count++;
        } finally {
            mutex.release();
            filledSlots.release();
        }
    }

    public String consume() throws InterruptedException {
        filledSlots.acquire();  // Wait for item to be available
        mutex.acquire();
        try {
            String item = storageArray[head];
            head = (head + 1) % maxBufferSize;
            count--;
            return item;
        } finally {
            mutex.release();
            emptySlots.release(); // signal one empty slot
        }
    }

    public int getCurrentBufferSize() {
        return count;
    }
}
