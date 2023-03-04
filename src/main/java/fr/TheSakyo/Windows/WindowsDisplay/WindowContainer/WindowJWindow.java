package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import javax.swing.*;
import java.awt.*;

public class WindowJWindow extends JWindow {

    private static final String earth_img = "src/main/resources/images/earth.jpg";
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();


    public WindowJWindow() {

        this.setSize(430, 430);
        this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);

        JPanel pan = new JPanel();
        JLabel img = new JLabel(new ImageIcon(earth_img));

        img.setVerticalAlignment(JLabel.CENTER);
        img.setHorizontalAlignment(JLabel.CENTER);
        pan.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        pan.add(img);
        this.getContentPane().add(pan);

        this.setVisible(true);
    }
}
