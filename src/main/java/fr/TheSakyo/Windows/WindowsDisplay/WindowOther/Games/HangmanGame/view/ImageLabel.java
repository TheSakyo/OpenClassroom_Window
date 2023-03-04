package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	private String imagePath = "src\\main\\resources\\HangmanGame\\images\\hangman0.jpg";

	public ImageLabel(){}
	
	public void paint(Graphics g) {

			g.setColor(Color.RED);
			g.drawRect(0, 0, this.getWidth(), this.getHeight());

			try {

				BufferedImage img = ImageIO.read(new File(this.imagePath));
				g.drawImage(img, 0, 0, this);

			} catch(IOException e) { e.printStackTrace(); }
	}
	
	public void setImagePath(String path) {

		this.imagePath = path;
		repaint();
	}
}
