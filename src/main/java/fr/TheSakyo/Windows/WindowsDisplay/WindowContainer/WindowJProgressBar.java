package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.utils.MethodCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJProgressBar extends Window {

    private Thread t;
    private final JProgressBar bar;
    private final JButton launch ;


    public WindowJProgressBar() {

        super(300, 80);

        t = new Thread(new Processing());
        bar  = new JProgressBar();

        bar.setMaximum(500);
        bar.setMinimum(0);
        bar.setStringPainted(true);

        this.getContentPane().add(bar, BorderLayout.CENTER);

        launch = new JButton("Lancer");
        launch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                t = new Thread(new Processing());
                t.start();
            }
        });

        this.getContentPane().add(launch, BorderLayout.SOUTH);

        t.start();

        this.setVisible(true);
    }

    class Processing implements Runnable {

        public void run() {

            launch.setEnabled(false);

            for(int val = 0; val <= 500; val++) {

                bar.setValue(val);
                try { MethodCustom.Waiting(10); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }

            launch.setEnabled(true);
        }
    }
}
