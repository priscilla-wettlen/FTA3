package controller;
//import model.*;
import view.*;


import java.util.ArrayList;

public class Controller {
 /*   private Consumer[] consumers = new Consumer[3];
    private Consumer[] consumerBackup = new Consumer[3];
    private Producer[] producers = new Producer[3];
    private Buffer buffer;*/
    private View view;
    private final int maxBufferSize = 20;

    public Controller() {
       // create and setup producers, consumers, main view and start it
        setup();
    }

    private void setup()
    {
        view = new View(this);
        view.Start();
    }


    public void startConsumer(int index) {
      }
    public void stopConsumer(int index) {
     }
    public void startProducer(int index) {
        }
    public void stopProducer(int index) {
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public synchronized void updateProgressBar() {
        //view.updateProgressbar(buffer.currentBufferSize());
    }


}
