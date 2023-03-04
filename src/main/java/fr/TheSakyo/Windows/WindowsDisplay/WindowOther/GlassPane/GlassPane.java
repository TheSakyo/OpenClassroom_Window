package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.GlassPane;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GlassPane extends JPanel {

    //La transparency de notre glace
    private final Composite transparency;

    //L'image qui sera dessinée
    private BufferedImage img;

    //Les coordonnées de l'image
    private Point location;

    public GlassPane() {

        //Afin de ne peindre que ce qui nous intéresse
        setOpaque(false);

        //On définit la transparence
        transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
    }

    public void paintComponent(Graphics g) {

        //Si on n'a pas d'image à dessiner, on ne fait rien…
        if(img == null) return;

        //Dans le cas contraire, on dessine l'image souhaitée
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(transparency);
        g2d.drawImage(img, (int) (location.getX() - (img.getWidth(this)  / 2)), (int) (location.getY() - (img.getHeight(this) / 2)), null);
    }

    public void setLocation(Point location) { this.location = location; }

    public void setImage(BufferedImage image) { img = image; }
}
