package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RulesPanel extends Container {

	private final String GamePath = "src\\main\\resources\\HangmanGame\\";

	public RulesPanel(Dimension dim) { super(dim); initPanel(); }

	public void initPanel() {

		JLabel titre = new JLabel();
		titre.setFont(comics30);
		titre.setText("Le jeu du pendu");
		titre.setHorizontalAlignment(JLabel.CENTER);
		
		this.panel.add(titre, BorderLayout.CENTER);
		
		JTextArea home = new JTextArea();
		home.setBackground(Color.WHITE);
		home.setText("""



				Vous avez sept coups pour trouver le mot caché. Si vous réussissez, on recommence !
				Plus vous trouvez de mots, plus votre score augmente. Alors, à vous de jouer !


				COMPTE DES POINTS :

				\tMot trouvé sans erreur\t\t100 pts
				\tMot trouvé avec une erreur\t50 pts
				\tMot trouvé avec deux erreurs\t35 pts
				\tMot trouvé avec trois erreurs\t25 pts
				\tMot trouvé avec quatre erreurs\t15 pts
				\tMot trouvé avec cinq erreurs\t10 pts
				\tMot trouvé avec six erreurs\t5 pts


				Je vous souhaite bien du plaisir !
				Si vous pensez pouvoir trouver un mot en un seul essai, c'est que vous croyez que le dictionnaire est petit.
				Or, pour votre information, il contient plus de 336 000 mots... Bonne chance ! ;)""");

		home.setFont(arial);
		home.setEditable(false);
		this.panel.add(home, BorderLayout.SOUTH);
	}
	
}
