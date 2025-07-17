package model;

public class Consumer extends Thread {
    private final SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String item = sharedBuffer.consume();
                System.out.println("Consumed: " + item);
                Thread.sleep(300); // simulate consumption delay
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer stopped.");
        }
    }
}
