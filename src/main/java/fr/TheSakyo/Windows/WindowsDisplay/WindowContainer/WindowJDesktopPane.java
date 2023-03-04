package fr.TheSakyo.Windows.WindowsDisplay.WindowContainer;

import fr.TheSakyo.Windows.Window;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowJDesktopPane extends Window {

    private final JDesktopPane desktop = new JDesktopPane();
    private static int xy = 10;

    public WindowJDesktopPane() {

        super(400, 300);

        JButton add = new JButton("Ajouter une fenêtre interne");
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                xy += 2;
                desktop.add(new MiniWindowPanel(xy, "Fenêtre n° ", true));
            }
        });

        this.getContentPane().add(desktop, BorderLayout.CENTER);
        this.getContentPane().add(add, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    class MiniWindowPanel extends JInternalFrame {

        public MiniWindowPanel(int xy, String str, boolean number) {

            if(number) this.setTitle(str + getWinNumber());
            else this.setTitle(str);

            this.setClosable(true);
            this.setResizable(true);

            this.setSize(150, 80);
            this.setLocation(xy, xy);

            this.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {

                    // Réactualise le nombre des minifenêtres//
                    for(int i = 0; i < desktop.getAllFrames().length; i++) {

                        desktop.getAllFrames()[i].setTitle("Fenêtre n° " + (i + 1));
                    }
                    // Réactualise le nombre des minifenêtres//
                }
            });

            this.setVisible(true);
        }

        public Integer getWinNumber() { return desktop.getAllFrames().length + 1; }

    }
}
