package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.listeners;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.GlassPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseGlassMotionListener extends MouseAdapter {

    private final GlassPane glass;

    public MouseGlassMotionListener(GlassPane glass) { this.glass = glass; }

    /**
     * Méthode fonctionnant sur le même principe que la classe 'MouseGlassListener'
     * mais cette fois sur l'action de déplacement
     */
    public void mouseDragged(MouseEvent e) {

        //Vous connaissez maintenant…
        Component c = e.getComponent();
        Point p = (Point) e.getPoint().clone();

        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, glass);

        glass.setLocation(p);
        glass.repaint();
    }
}
