
package gui;
import javax.swing.*;
import java.awt.*;

public class LinePanel extends JPanel {
  @Override
public void paint(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.RED);
      g.drawLine(50, 50, 200, 200);
    }
}
