package controller;

import model.Consumer;
import model.Producer;
import model.SharedBuffer;
import view.View;

public class Controller {
    private final SharedBuffer buffer = new SharedBuffer();
    private final Producer[] producers = new Producer[3];
    private final Consumer[] consumers = new Consumer[3];
    private final View view;

    public Controller() {
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
            consumers[index] = new Consumer(buffer);
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
}
