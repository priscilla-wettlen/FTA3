package view;

import javax.swing.*;
import java.awt.*;
import controller.Controller;

/**
 * The GUI for assignment 3
 */
public class View {

    private JFrame frame;				// The Main window

     private Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }
    private PanelProgress pnlBuffer;
    private PanelProducers pnlProducers;
    private PanelConsumers pnlConsumers;

    public void start()
    {
        frame = new JFrame();
        frame.setBounds(0, 0, 730, 526);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Items Supply System");

        InitializeGUI(); // Fill in components

        // Start a Swing Timer to update progress bar every 500ms
        new javax.swing.Timer(500, e -> {
            int size = controller.getCurrentBufferSize();
            updateProgressbar(size);
        }).start();

        frame.setVisible(true);
        frame.setResizable(false); // Prevent user from changing size
        frame.setLocationRelativeTo(null); // Center window on screen
    }


    /**
     * Sets up the GUI with components
     */
    private void InitializeGUI()
    {
        // First create the three main panels
        pnlBuffer = new PanelProgress(controller);
        frame.add(pnlBuffer);

        pnlProducers = new PanelProducers(controller);
        frame.add(pnlProducers);

        pnlConsumers = new PanelConsumers(controller);
        frame.add(pnlConsumers);

    }
    public void updateProgressbar(int value) {
        pnlBuffer.updateProgressbar(value);
    }


//    public void updateConInfo(int type, int items) {
//        pnlConsumers.updateConInfo( type,  items);
//
//    }
//
//    public void updateFoodList(String foodItem, String name) {
//        pnlConsumers.updateProductList(foodItem, name);
//     }
//
//    public void setStatus(String consumerName, String status)
//    {
//        pnlConsumers.setStatus(consumerName, status);
//      }
//
//    public boolean getContinueLoading(String consumerName) {
//       return pnlConsumers.getContinueLoading(consumerName);
//    }
//

}