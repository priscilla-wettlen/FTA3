package model;

import java.util.concurrent.Semaphore;

public class SharedBuffer {
    private final int maxBufferSize = 30;
    private final String[] storageArray = new String[maxBufferSize];
    private int conIndex = 0;
    private int prodIndex = 0;
    private int count = 0;
    private final Semaphore emptySlots = new Semaphore(maxBufferSize); // initially all slots are empty
    private final Semaphore filledSlots = new Semaphore(0);   // no filled slots initially
    private final Semaphore mutex = new Semaphore(1);

    public void produce(String item) throws InterruptedException {
        emptySlots.acquire();
        mutex.acquire();
        try {
            storageArray[prodIndex] = item;
            prodIndex = (prodIndex + 1) % maxBufferSize;
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
            String item = storageArray[conIndex];
            conIndex = (conIndex + 1) % maxBufferSize;
            count--;
            return item;
        } finally {
            mutex.release();
            emptySlots.release(); // signal that there's one empty slot
        }
    }

    public int getCurrentBufferSize() {
        return count;
    }

    public String[] getStorageArray() {
        return storageArray;
    }
}
