package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class WindowJSlider extends Window {

    private final JLabel label = new JLabel("Valeur actuelle : 30");

    public WindowJSlider() {

        super(250, 250);

        JSlider slide = new JSlider();

        slide.setMaximum(100);
        slide.setMinimum(0);
        slide.setValue(30);

        slide.setPaintTicks(true);
        slide.setPaintLabels(true);

        slide.setMinorTickSpacing(10);
        slide.setMajorTickSpacing(20);

        slide.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent event) {

                label.setText("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
            }
        });

        this.getContentPane().add(slide, BorderLayout.CENTER);
        this.getContentPane().add(label, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
