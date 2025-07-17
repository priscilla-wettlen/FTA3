package model;

public class Producer extends Thread {
    private final SharedBuffer sharedBuffer;
    private int itemId = 0;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String item = "Item" + (++itemId);
                sharedBuffer.produce(item);
                System.out.println("Produced: " + item);
                Thread.sleep(200); // simulate production delay
            }
        } catch (InterruptedException e) {
            System.out.println("Producer stopped.");
        }
    }
}
