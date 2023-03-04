package fr.TheSakyo.Windows.WindowsDisplay.WindowOther;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class WindowBorder extends Window {

    private final String[] list = {
            "Bevel Border",
            "Etched Border",
            "Line Border",
            "Matted Border",
            "Raised Bevel Border",
            "Title Border",
            "Compound Border"
    };

    private final Border[] listBorder = {
            BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.RED),
            BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY),
            BorderFactory.createLineBorder(Color.GREEN),
            BorderFactory.createMatteBorder(5, 2, 5, 2, Color.MAGENTA),
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createTitledBorder("Titre"),
            BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLUE),
                BorderFactory.createMatteBorder(5, 2, 5, 2, Color.MAGENTA)
            )
    };

    public WindowBorder() {

        super(500, 500);

        JPanel pan = new JPanel();

        for(int i = 0; i < list.length; i++) {

            JLabel lib = new JLabel(list[i]);

            lib.setPreferredSize(new Dimension(150, 50));

            lib.setBorder(listBorder[i]);
            lib.setAlignmentX(JLabel.CENTER);
            lib.setHorizontalAlignment(JLabel.CENTER);

            pan.add(lib);
        }

        this.getContentPane().add(pan);

        this.setVisible(true);
    }
}
