package fr.TheSakyo.Windows.WindowsParameters.ForAnimation;

import javax.swing.*;
import java.awt.*;

public class WinParamPanel extends JPanel {

    //Les variables définies auparavant ne changent pas
    //On y ajoute nos deux couleurs
    private Color colorForm = Color.RED;
    private Color colorBG = Color.WHITE;

    private int posX = -50;
    private int posY = -50;
    private int drawSize = 50;

    //Un booléen pour le mode morphing
    //Un autre pour savoir si la taille doit être réduite
    private boolean morph = false, reduce = false;
    private String form = "ROND";

    //Le compteur de rafraîchissements
    private int increment = 0;


    public void paintComponent(Graphics g){

        //On choisit une couleur de fond pour le rectangle
        g.setColor(colorBG);

        //On le dessine de sorte qu'il occupe toute la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //On redéfinit une couleur pour le rond
        g.setColor(colorForm);

        //Si le mode morphing est activé, on peint le morphing
        if(this.morph) drawMorph((Graphics2D)g);

        //Sinon, on peint le mode normal
        else draw((Graphics2D)g);
    }

    private void draw(Graphics2D g) {

        if(this.form.equalsIgnoreCase("ROND")) g.fillOval(posX, posY, 50, 50);
        if(this.form.equalsIgnoreCase("CARRÉ")) g.fillRect(posX, posY, 50, 50);

        if(this.form.equalsIgnoreCase("TRIANGLE")) {

            //Calcul des sommets
            //Le sommet 1 se situe à la moitié du côté supérieur du carré
            int s1X = posX + 25;
            int s1Y = posY;

            //Le sommet 2 se situe en bas à droite
            int s2X = posX + 50;
            int s2Y = posY + 50;

            //Le sommet 3 se situe en bas à gauche
            int s3X = posX;
            int s3Y = posY + 50;

            //Nous créons deux tableaux de coordonnées
            int[] ptsX = {s1X, s2X, s3X};
            int[] ptsY = {s1Y, s2Y, s3Y};

            //Nous utilisons la méthode fillPolygon()
            g.fillPolygon(ptsX, ptsY, 3);
        }

        if(this.form.equalsIgnoreCase("ÉTOILE")) {

            //Pour l'étoile, on se contente de tracer des lignes dans le carré
            //correspondant à peu près à une étoile...
            //Mais ce code peut être amélioré !
            int s1X = posX + 25;
            int s1Y = posY;
            int s2X = posX + 50;
            int s2Y = posY + 50;

            g.drawLine(s1X, s1Y, s2X, s2Y);

            int s3X = posX;
            int s3Y = posY + 17;

            g.drawLine(s2X, s2Y, s3X, s3Y);

            int s4X = posX + 50;
            int s4Y = posY + 17;

            g.drawLine(s3X, s3Y, s4X, s4Y);

            int s5X = posX;
            int s5Y = posY + 50;

            g.drawLine(s4X, s4Y, s5X, s5Y);
            g.drawLine(s5X, s5Y, s1X, s1Y);
        }
    }

    //Méthode qui peint le morphing
    private void drawMorph(Graphics2D g) {

        //On incrémente
        this.increment++;

        //On regarde si on doit réduire ou non
        if(this.drawSize >= 50) this.reduce = true;
        if(this.drawSize <= 10) this.reduce = false;

        if(this.reduce) this.drawSize = this.drawSize - getUsedSize();
        else this.drawSize = this.drawSize + getUsedSize();

        if(this.form.equalsIgnoreCase("ROND")) g.fillOval(posX, posY, this.drawSize, this.drawSize);

        if(this.form.equalsIgnoreCase("CARRÉ")) g.fillRect(posX, posY, this.drawSize, this.drawSize);

        if(this.form.equalsIgnoreCase("TRIANGLE")) {

            int s1X = posX + this.drawSize/2;
            int s1Y = posY;

            int s2X = posX + this.drawSize;
            int s2Y = posY + this.drawSize;

            int s3X = posX;
            int s3Y = posY + this.drawSize;

            int[] ptsX = {s1X, s2X, s3X};
            int[] ptsY = {s1Y, s2Y, s3Y};

            g.fillPolygon(ptsX, ptsY, 3);
        }

        if(this.form.equalsIgnoreCase("ÉTOILE")) {

            int s1X = posX + this.drawSize / 2;
            int s1Y = posY;

            int s2X = posX + this.drawSize;
            int s2Y = posY + this.drawSize;

            g.drawLine(s1X, s1Y, s2X, s2Y);

            int s3X = posX;
            int s3Y = posY + this.drawSize / 3;

            g.drawLine(s2X, s2Y, s3X, s3Y);

            int s4X = posX + this.drawSize;
            int s4Y = posY + this.drawSize / 3;

            g.drawLine(s3X, s3Y, s4X, s4Y);

            int s5X = posX;
            int s5Y = posY + this.drawSize;

            g.drawLine(s4X, s4Y, s5X, s5Y);
            g.drawLine(s5X, s5Y, s1X, s1Y);
        }
    }

    //Retourne le nombre à retrancher ou à ajouter pour le morphing
    private int getUsedSize() {

        int res = 0;

        //Si le nombre de tours est de dix, on réinitialise l'incrément et on retourne 1
        if(this.increment / 10 == 1) {

            this.increment = 0;
            res = 1;
        }
        return res;
    }

    public int getDrawSize(){ return this.drawSize; }

    public boolean isMorph(){ return this.morph; }

    public void setMorph(boolean bool) {

        this.morph = bool;

        //On réinitialise la taille
        this.drawSize = 50;
    }

    @SuppressWarnings("unused")
    public void setForm(String form) { this.form = form.toUpperCase(); }

    //Méthode qui redéfinit la couleur du fond
    public void setColorBackground(Color color) { this.colorBG = color; }

    //Méthode qui redéfinit la couleur de la forme
    public void setColorForm(Color color) { this.colorForm = color; }

    public int getPosX() { return posX; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }

    public void setPosY(int posY) { this.posY = posY; }
}




