package fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Clock;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Clock.interfaces.Observer;
import fr.TheSakyo.Windows.WindowsDisplay.WindowObserver.Clock.model.Clock;

import javax.swing.*;
import java.awt.*;

public class WindowClock extends Window {

    private final JLabel label = new JLabel();
    private final Clock clock;


    public WindowClock() {

        //On initialise la JFrame
        super(250, 80);

        //On initialise 'clock'
        this.clock = new Clock();

        //On place un écouteur sur 'clock'
        this.clock.addObservateur(new Observer() { public void update(String hour) { label.setText(hour); }});

        //On initialise le JLabel
        Font police = new Font("DS-digital", Font.BOLD, 30);
        this.label.setFont(police);
        this.label.setHorizontalAlignment(JLabel.CENTER);


        //On ajoute le JLabel à la JFrame
        this.getContentPane().add(this.label, BorderLayout.CENTER);
        this.setVisible(true);

        this.clock.run();
    }
}
