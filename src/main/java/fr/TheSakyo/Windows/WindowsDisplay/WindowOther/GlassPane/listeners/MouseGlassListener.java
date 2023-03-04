package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.listeners;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane.GlassPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MouseGlassListener extends MouseAdapter {

    private final GlassPane glass;
    private BufferedImage image;

    public MouseGlassListener(GlassPane glass){ this.glass = glass; }

    public void mousePressed(MouseEvent e) {

        //On récupère le composant pour en déduire sa position
        Component composant = e.getComponent();
        Point location = (Point)e.getPoint().clone();

        //Les méthodes ci-dessous permettent, dans l'ordre,
        //de convertir un point en coordonnées d'écran
        //et de reconvertir ce point en coordonnées fenêtres
        SwingUtilities.convertPointToScreen(location, composant);
        SwingUtilities.convertPointFromScreen(location, glass);

        //Les instructions ci-dessous permettent de redessiner le composant
        image = new BufferedImage(composant.getWidth(), composant.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        composant.paint(g);

        //On passe les données qui vont bien à notre GlassPane
        glass.setLocation(location);
        glass.setImage(image);

        //On n'oublie pas de dire à notre GlassPane de s'afficher
        glass.setVisible(true);
    }

    public void mouseReleased(MouseEvent e) {

        //---------------------------------------------------------------------//

        //On implémente le transfert lorsqu'on relâche le bouton de souris
        //Ceci afin de ne pas supplanter le fonctionnement du déplacement
        JComponent lab = (JComponent)e.getSource();
        TransferHandler handle = lab.getTransferHandler();
        handle.exportAsDrag(lab, e, TransferHandler.COPY);

        //---------------------------------------------------------------------//

        //On récupère le composant pour en déduire sa position
        Component composant = e.getComponent();
        Point location = (Point)e.getPoint().clone();

        //Les méthodes ci-dessous permettent, dans l'ordre,
        //de convertir un point en coordonnées d'écran
        //et de reconvertir ce point en coordonnées fenêtre
        SwingUtilities.convertPointToScreen(location, composant);
        SwingUtilities.convertPointFromScreen(location, glass);

        //On passe les données qui vont bien à notre GlassPane
        glass.setLocation(location);
        glass.setImage(null);

        //On n'oublie pas de ne plus l'afficher
        glass.setVisible(false);

    }
}
