package view;

import javax.swing.*;
import java.awt.*;
import controller.Controller;

public class PanelProgress extends  JPanel{

    private JProgressBar bufferStatus;
    private Controller controller;

    public PanelProgress(Controller controller) {
        this.controller = controller;
        CreateProgressPanel();
    }
    private void CreateProgressPanel()
    {
        this.controller = controller;

        //JPanel pnlBuffer = new JPanel();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Storage"));
        setBounds(13, 403, 693, 82);
        setLayout(null);

        // Then create the progressbar, only component in buffer panel
        bufferStatus = new JProgressBar();
        bufferStatus.setBounds(155, 37, 500, 23);
        bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
        bufferStatus.setForeground(Color.GREEN);
        bufferStatus.setMaximum(controller.getMaxBufferSize()); //setting maximum value of the progress bar
        add(bufferStatus);
        JLabel lblmax = new JLabel(String.format("Max capacity: %s", controller.getMaxBufferSize()));
        lblmax.setBounds(10, 42, 126, 13);
        add(lblmax);

    }

    public void updateProgressbar(int value) {
        bufferStatus.setValue(value);
    }
}
