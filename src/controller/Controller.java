package controller;

import model.Consumer;
import model.Producer;
import model.SharedBuffer;
import view.View;

import javax.swing.*;
import java.util.Arrays;

public class Controller {
    private final SharedBuffer buffer = new SharedBuffer();
    private final Producer[] producers = new Producer[3];
    private final Consumer[] consumers = new Consumer[3];
    private Consumer consumer;
    private final View view;
    private String consumerName;

    public Controller() {
        int index = 0;
        consumer = new Consumer(buffer, consumerName, this);
        view = new View(this);
        view.start();  // initialize GUI
    }

    public void startProducer(int index) {
        if (producers[index] == null || !producers[index].isAlive()) {
            producers[index] = new Producer(buffer);
            producers[index].start();
        }
    }

    public void stopProducer(int index) {
        if (producers[index] != null) {
            producers[index].interrupt();
        }
    }

    public void startConsumer(int index) {
        if (consumers[index] == null || !consumers[index].isAlive()) {
            String consumerName;
            switch (index) {
                case 0 -> consumerName = "ICA";
                case 1 -> consumerName = "Coop";
                case 2 -> consumerName = "City Gross";
                default -> throw new IllegalArgumentException("Invalid consumer index: " + index);
            }

            consumers[index] = new Consumer(buffer, consumerName, this);
            consumers[index].start();
        }
    }

    public void stopConsumer(int index) {
        if (consumers[index] != null) {
            consumers[index].interrupt();
        }
    }

    public int getCurrentBufferSize() {
        return buffer.getCurrentBufferSize();
    }

    public void updateItemListPanel(String itemId, String consumerName) {
        SwingUtilities.invokeLater(() -> view.updateFoodList(itemId, consumerName));
    }

    public void updateStatus(String name, String status){
        view.setStatus(name,status);
    }


}
