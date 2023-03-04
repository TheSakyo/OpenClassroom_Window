package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.TheSakyo.Windows.Window;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Model;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Score;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observer;

public class WindowHangmanGame extends Window implements Observer {

	  private final JMenuBar menu = new JMenuBar();

	 private final JMenu file = new JMenu("Fichier"),
			about = new JMenu("À propos");

	  private final JMenuItem further = new JMenuItem("Nouveau"),
			score = new JMenuItem("Score"),
			exit = new JMenuItem("Quitter"),
			rule = new JMenuItem("Règle du jeu"),
	  		aboutItem = new JMenuItem("?");

	  private final JPanel container = new JPanel();
	  private final Dimension size;

	  private Observable model;
	  
	  private final char[] letterTab = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
              'j',
              'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
              't',
              'u', 'v', 'w', 'x', 'y', 'z'};
	  
	public WindowHangmanGame(Observable obs) {

		super(900, 600);
	    
	    this.model = obs;
	    this.model.addObserver(this);
	    this.size = new Dimension(this.getWidth(), this.getHeight());


		further.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				container.removeAll();

				GamePanel gp = new GamePanel(size, model);
				model.addObserver(gp);
				container.add(gp.getPanel(), BorderLayout.CENTER);

				container.revalidate();
				initModel();
			}
		});

		score.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				container.removeAll();
				container.add(new ScorePanel(size, model.getScores()).getPanel(), BorderLayout.CENTER);
				container.revalidate();
				model.reset();
			}
		});

		exit.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
			System.exit(0);
		} });

		rule.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				container.removeAll();
				container.add(new RulesPanel(size).getPanel(), BorderLayout.CENTER);
				container.revalidate();
				model.reset();
			}
		});

		aboutItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						"Créateurs : Cysboy et TheSakyo\nLicence : Freeware",
						"Informations", JOptionPane.INFORMATION_MESSAGE);
				container.removeAll();
				container.add(new HomePanel(size).getPanel());
				container.revalidate();
				model.reset();
			}
		});


		further.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		rule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOLLAR, KeyEvent.SHIFT_DOWN_MASK));


		file.add(further);
		file.add(score);
		file.addSeparator();
		file.add(exit);
		file.setMnemonic('F');

		menu.add(file);

		about.add(rule);
		about.add(aboutItem);
	    about.setMnemonic('A');

	    menu.add(about);

		this.setJMenuBar(menu);
	    
	    this.container.setPreferredSize(this.size);
	    this.container.setBackground(Color.WHITE);
	    this.container.add(new HomePanel(this.size).getPanel());
	    this.setContentPane(this.container);

	    this.setVisible(true);
	}
	
	public void showScore(Score[] list) {

		container.removeAll();
		container.add(new ScorePanel(this.size, list).getPanel(), BorderLayout.CENTER);
		container.revalidate();
		model.reset();
	}
	
	public void home() {

		System.out.println("Mise à jour de l'accueil");
		container.removeAll();
		container.add(new HomePanel(size).getPanel(), BorderLayout.CENTER);
		container.revalidate();
	}

	private void initModel() {

		this.model = new Model();
		this.model.addObserver(this);
	}

	public void update(String mot, int pts, String imgPath, int nbreMot) {}
	public void restart(String word) {}

}
