package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HomePanel extends Container {

	private final String GamePath = "src\\main\\resources\\HangmanGame\\";
	
	public HomePanel(Dimension dim) { super(dim); initPanel(); }

	public void initPanel() {

		JLabel titre = new JLabel("Bienvenue dans le jeu du pendu\n");
		titre.setHorizontalAlignment(JLabel.CENTER);
		titre.setFont(comics30);

		this.panel.add(titre, BorderLayout.NORTH);
		this.panel.add(new JLabel(new ImageIcon(GamePath + "images\\home.jpg")), BorderLayout.CENTER);
		
		JTextArea texte = new JTextArea("""
				Vous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !
				Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !""");
		texte.setFont(arial);
		texte.setEditable(false);
		texte.setBackground(Color.WHITE);
		
		this.panel.add(texte, BorderLayout.SOUTH);
	}
	
}
