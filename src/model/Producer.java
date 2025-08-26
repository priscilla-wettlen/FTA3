package model;

import java.util.Arrays;

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
                String item = "Item#" + itemId;
                String[] producedItemsArray = new String[30];
                sharedBuffer.produce(item);

                if (itemId < producedItemsArray.length) {
                    producedItemsArray[itemId] = item;
                    itemId++;
                }
                Thread.sleep(1000); // simulate production delay
            }
        } catch (InterruptedException e) {
            System.out.println("Producer stopped.");
        }
    }
}
