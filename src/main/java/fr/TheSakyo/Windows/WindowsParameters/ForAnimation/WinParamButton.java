package fr.TheSakyo.Windows.WindowsParameters.ForAnimation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class WinParamButton extends JButton implements MouseListener {

    private final String text;
    private File fileFinal;

    private Image img;

    private Graphics2D G2D;

    private Color colorOne;
    private Color colorTwo;

    private Color colorImg;

    private final Color colorDefaultImg = Color.decode("#e8e8e8");
    private final Color colorSecondImg = Color.decode("#24450e");

    private final File fileHover = new File("src/main/resources/images/buttons/button_hover.jpg");
    private final File fileClic = new File("src/main/resources/images/buttons/button_clic.jpg");
    private final File fileDefault = new File("src/main/resources/images/buttons/button_default.jpg");

    public WinParamButton(String str, File file) {

        super(str);
        this.text = str;

        this.colorOne = Color.BLUE;
        this.colorTwo = Color.CYAN;

        this.colorImg = this.colorDefaultImg;

        if(file != null) {

            this.fileFinal = file;

            try { this.img = ImageIO.read(file); }

            catch(IOException e) { e.printStackTrace(); }
        }

        this.addMouseListener(this);
    }


    private void paintExec(Graphics2D g, Color color1, Color color2, boolean file) {

        if(!file) {

            GradientPaint GP = new GradientPaint(0, 0, color1, 0, 20, color2, true);
            g.setPaint(GP);

            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.WHITE);

        } else {

            this.G2D.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);

            if(color1 == null && color2 == null) g.setColor(Color.BLACK);
            else if(color1 == null) g.setColor(color2);
            else if(color2 == null) g.setColor(color1);
        }

    }


    public void paintComponent(Graphics g) {

        this.G2D = (Graphics2D)g;

        //Définition d'une police d'écriture
        Font font = new Font("Roboto", Font.BOLD + Font.ITALIC, 13);

        if(this.img != null) paintExec((Graphics2D)g, this.colorImg, null, true);
        else paintExec((Graphics2D)g, this.colorOne, this.colorTwo, false);

        g.setFont(font);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        centerString((Graphics2D)g, g.getClipBounds(), this.text, font);
    }



    public void mouseClicked(MouseEvent event) { /* Inutile d'utiliser cette méthode ici */ }

    public void mouseEntered(MouseEvent event) {

        //Nous changeons d'image lors du survol, avec le fichier définit dans les variables
        if(this.fileFinal != null) {

            this.fileFinal = this.fileHover;

            try { this.img = ImageIO.read(this.fileHover); }
            catch(IOException e) { e.printStackTrace(); }

            this.colorImg = this.colorSecondImg;

        //Nous changeons la couleur lors du survol
        } else {

            this.colorOne = Color.RED;
            this.colorTwo = Color.ORANGE;

        }

        paintComponent(this.G2D);
    }



    public void mouseExited(MouseEvent event) {

        //Nous changeons d'image lorsque nous quittons le bouton, avec définit dans les variables
        if(this.fileFinal != null) {

            this.fileFinal = this.fileDefault;

            try { this.img = ImageIO.read(this.fileDefault); }
            catch(IOException e) { e.printStackTrace(); }

            this.colorImg = this.colorDefaultImg;

        //Nous changeons la couleur lorsque nous quittons le bouton
        } else {

            this.colorOne = Color.BLUE;
            this.colorTwo = Color.CYAN;
        }

        paintComponent(this.G2D);
    }



    public void mousePressed(MouseEvent event) {

        //Nous changeons d'image lors du clic gauche, avec définit dans les variables
        if(this.fileFinal != null) {

            this.fileFinal = this.fileClic;

            try { this.img = ImageIO.read(this.fileClic); }
            catch(IOException e) { e.printStackTrace(); }

            this.colorImg = this.colorSecondImg;

        //Nous changeons la couleur lors du clic gauche
        } else {

            this.colorOne = Color.GREEN;
            this.colorTwo = Color.decode("#005706");

        }

        paintComponent(this.G2D);
    }


    public void mouseReleased(MouseEvent event) {

        //Nous changeons d'image lorsque nous relâchons le clic, avec définit dans les variables
        if(this.fileFinal != null) {

            //Si la souris est toujours sur le bouton, on change d'image
            if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth())){

                this.fileFinal = this.fileHover;

                try { this.img = ImageIO.read(this.fileHover); }
                catch(IOException e) { e.printStackTrace(); }

                this.colorImg = this.colorSecondImg;

            //Si on se trouve à l'extérieur, on met l'image par défaut
            } else {

                this.fileFinal = this.fileDefault;

                try { this.img = ImageIO.read(this.fileDefault); }
                catch(IOException e) { e.printStackTrace(); }

                this.colorImg = this.colorDefaultImg;
            }

        //Nous changeons la couleur du bouton lorsque nous relâchons le clic
        } else {

            //Si la souris est toujours sur le bouton, on le change de couleur
            if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0 && event.getX() < this.getWidth())) {

                this.colorOne = Color.RED;
                this.colorTwo = Color.ORANGE;

            //Si on se trouve à l'extérieur, on dessine le fond par défaut
            } else {

                this.colorOne = Color.BLUE;
                this.colorTwo = Color.CYAN;
            }
        }

        paintComponent(this.G2D);
    }



    /**
     * Cette petite méthode centre un <code>String</code> dans un <code>Rectangle</code>
     * @param g - L'instance du <code>Graphics</code>.
     * @param r - Les limites du <code>Rectangle</code>.
     * @param s - Le <code>String</code> à centrer dans le rectangle de délimitation.
     * @param font - La police d'affichage de <code>String</code>
     *
     * @see java.awt.Graphics2D
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public void centerString(Graphics2D g, Rectangle r, String s, Font font) {
        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }
}
