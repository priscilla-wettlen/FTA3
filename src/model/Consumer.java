package model;

import controller.Controller;

import java.util.Arrays;

public class Consumer extends Thread {
    private final SharedBuffer sharedBuffer;
    private Controller controller;
    private String[] consumedItemsArray = new String[30];
    private String item;
    private String consumedItem;
    private int consumeIndex;
    private String consumerName;

    public Consumer(SharedBuffer sharedBuffer, int consumeIndex, String consumerName, Controller controller) {
        this.sharedBuffer = sharedBuffer;
        this.controller = controller;
        this.consumeIndex = consumeIndex;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                item = sharedBuffer.consume();
                controller.updateItemListPanel(item, consumerName);
                Thread.sleep(3000); // consumption delay
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer stopped.");
        }
    }

}
