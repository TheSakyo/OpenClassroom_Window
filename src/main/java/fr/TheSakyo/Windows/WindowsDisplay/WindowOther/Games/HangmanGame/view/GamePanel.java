package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.Controler;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Score;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observer;

public class GamePanel extends Container implements Observer {

	private Dimension dimension = new Dimension();
	private final int pts = 0;
	private ImageIcon img;
	private String word;
	private JLabel 	numberWord,
					score,
					wordSecret;
	private ImageLabel imageLabel;
	private JButton[] button;
	
	private final Controler controler;
	
	public GamePanel(Dimension dim, Observable mod) {

		super(dim);
		this.controler = new Controler(mod);
		initPanel();
	}

	protected void initPanel() {

		JPanel leftContent = new JPanel();
		JPanel rightContent = new JPanel();

		JPanel head = new JPanel();
		this.imageLabel = new ImageLabel(); 
		this.imageLabel.setPreferredSize(new Dimension(300, 300));
		this.imageLabel.setVerticalAlignment(JLabel.CENTER);

		this.numberWord = new JLabel();
		this.score = new JLabel(); 
		
		this.dimension = new Dimension(400, 530); 
		rightContent.setPreferredSize(this.dimension);
		rightContent.add(this.imageLabel, BorderLayout.CENTER);
		rightContent.setBackground(Color.white);
		
		leftContent.setPreferredSize(this.dimension);
		
		Dimension dim = new Dimension(410, 200);
		head.setPreferredSize(new Dimension(410, 100));
		
		this.numberWord.setText("Nombre de mots trouvés : 0");
		this.numberWord.setPreferredSize(new Dimension(300, 20));
		this.numberWord.setHorizontalAlignment(JLabel.CENTER);
		this.numberWord.setFont(arial);
		
		head.add(this.numberWord, BorderLayout.NORTH);
		
		this.score.setText("Votre score actuel est de 0 point.");
		this.score.setPreferredSize(new Dimension(300, 20));
		this.score.setHorizontalAlignment(JLabel.CENTER);
		this.score.setFont(arial);

		head.add(this.score, BorderLayout.SOUTH);
		head.setBackground(Color.white);
		
		this.wordSecret = new JLabel("fffffffffffffffffff");
		this.wordSecret.setPreferredSize(new Dimension(400, 60));
		this.wordSecret.setForeground(Color.blue);
		this.wordSecret.setFont(comics30);
		this.wordSecret.setHorizontalAlignment(JLabel.CENTER);

		head.add(this.wordSecret);
		
		JPanel body = new JPanel();
		body.setPreferredSize(dim);
		body.setBackground(Color.white);
		char[] carac = {'a', 'b','c', 'd', 'e', 'f',
						'g', 'h', 'i', 'j', 'k', 'l', 
						'm', 'n', 'o', 'p', 'q', 'r', 
						's', 't' ,'u', 'v', 'w', 'x', 
						'y', 'z'};
		
		BoutonListener bl = new BoutonListener();

		Dimension buttonDimension = new Dimension(50,30);

		this.button = new JButton[26];

		int i = 0;
		for(char c : carac) {

			this.button[i] = new JButton(String.valueOf(c).toUpperCase());
			button[i].addActionListener(bl);
			button[i].setPreferredSize(buttonDimension);
			body.add(button[i]);
			i++;
		}
		
		leftContent.add(head, BorderLayout.NORTH);
		leftContent.add(body, BorderLayout.CENTER);
		leftContent.setBackground(Color.white);
		
		this.panel.add(leftContent);
		this.panel.add(rightContent);
		
	}

	
	public void restart(String word) {

		for(JButton bout : this.button)
			bout.setEnabled(true);
		
		this.imageLabel.setImagePath("src\\main\\resources\\HangmanGame\\images\\hangman0.jpg");
		this.wordSecret.setText(word);
	}

	public void update(String word, int pts, String path, int nbreMot) {

		this.wordSecret.setText(word);

		this.score.setText("Votre score actuel est de " + pts + " point"+((pts > 1) ? "s" : "")+".");

		this.imageLabel.setImagePath(path);

		this.numberWord.setText("Nombre de mots trouvés : " + nbreMot);
		
	}
	
	public void home() {}
	public void showScore(Score[] list) {}

	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			((JButton)e.getSource()).setEnabled(false);
			controler.control(((JButton)e.getSource()).getText().charAt(0));
		}		
	}
	
}
