package model;

import controller.Controller;

import java.util.Arrays;

public class Consumer extends Thread {
    private final SharedBuffer sharedBuffer;
    private Controller controller;
    private String item;
    private int consumeIndex;
    private String consumerName;

    public Consumer(SharedBuffer sharedBuffer, String consumerName, Controller controller) {
        this.sharedBuffer = sharedBuffer;
        this.controller = controller;
        this.consumerName = consumerName;
    }

    @Override
    public void run() {
        try {
            while (sharedBuffer.getCurrentBufferSize() > 0) {
                item = sharedBuffer.consume();
                controller.updateItemListPanel(item, consumerName);
                if(sharedBuffer.getCurrentBufferSize() <= 0){
                    controller.updateStatus(consumerName, "Idle");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer stopped.");
        }
    }

}
