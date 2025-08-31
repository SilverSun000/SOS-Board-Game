package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener{
    JCheckBox checkBox;
    JLabel label;
    JRadioButton radioButton;

    public MainWindow() {
      LinePanel panel = new LinePanel();

      this.setTitle("SOS Board Game"); // Sets title of frame
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of app
      this.setSize(750,750); // sets height & width of frame
      this.setResizable(false); // Prevent app from being resized

      this.setLayout(new BorderLayout());

      checkBox = new JCheckBox();
      checkBox.setText("I'm not a robot");
      checkBox.setFocusable(false);

      radioButton = new JRadioButton("Click Me...");

      label = new JLabel();
      label.setText("""
        <html>
        3:47PM in the afternoon 08/31/2025<br>
        Cats are napping<br>
        Looks like it may rain
        </html>
        """);

      this.add(label, BorderLayout.NORTH);
      this.add(panel, BorderLayout.CENTER);
      this.add(checkBox, BorderLayout.LINE_START);
      this.add(radioButton, BorderLayout.SOUTH);

      this.setVisible(true); // toggles visibility of frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

