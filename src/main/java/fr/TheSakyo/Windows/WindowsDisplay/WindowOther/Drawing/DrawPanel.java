package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    //Couleur du pointeur
    private Color pointerColor = Color.RED;

    //Forme du pointeur
    private String pointerType = "CIRCLE";


    //Pour savoir si on doit dessiner ou non
    private boolean erasing = true;

    //Taille du pointeur
    private final int pointerSize = 15;

    //Collection de points !
    private ArrayList<WindowPoint> points = new ArrayList<WindowPoint>();

    public DrawPanel() {

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                points.add(new WindowPoint(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                //On récupère les coordonnées de la souris et on enlève la moitié de la taille du pointeur pour centrer le tracé
                points.add(new WindowPoint(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
                repaint();
            }

            public void mouseMoved(MouseEvent e) {}
        });

    }

    // Vous la connaissez maintenant, celle-là
    public void paintComponent(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Si on doit effacer, on ne passe pas dans le else => pas de dessin
        if(this.erasing) this.erasing = false;

        else {

            //On parcourt notre collection de points
            for(WindowPoint p : this.points) {

                //On récupère la couleur
                g.setColor(p.getColor());

                //Selon le type de point
                if(p.getType().equals("SQUARE")) g.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());

                else g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
            }
        }
    }

    //Efface le contenu
    public void erase() {

        this.erasing = true;
        this.points = new ArrayList<WindowPoint>();
        repaint();
    }

    //Définit la couleur du pointeur
    public void setPointerColor(Color c) { this.pointerColor = c; }

    //Définit la forme du pointeur
    public void setPointerType(String str) { this.pointerType = str; }
}