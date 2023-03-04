package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Score;

public class ScorePanel extends Container {

	private final int[] fontSize = {25, 24, 23, 22, 21, 20, 19, 18, 16, 14};
	private Dimension dimension;
	private final Score[] list;
	
	public ScorePanel(Dimension dim, Score[] list) {

		super(dim);
		this.list = list;
		initPanel();
	}

	protected void initPanel() {

		JPanel leftContent = new JPanel();
		JPanel rightContent = new JPanel();
		
		this.dimension = new Dimension(360, 530); 
		rightContent.setPreferredSize(this.dimension);
		rightContent.add(new JLabel(new ImageIcon("src\\main\\resources\\HangmanGame\\images\\hangman7.jpg")), BorderLayout.CENTER);
		rightContent.setBackground(Color.WHITE);
		
		this.dimension = new Dimension(500, 530);
		leftContent.setPreferredSize(this.dimension);
		leftContent.setBackground(Color.WHITE);
		
		for(int i = 0; i < this.list.length; i++) {

			JLabel lab = new JLabel(this.list[i].toString());
			lab.setFont(new Font("Comics Sans MS", Font.BOLD, fontSize[i]));
			lab.setPreferredSize(new Dimension(440, 40));
			leftContent.add(lab);
		}
		
		this.panel.add(leftContent, BorderLayout.CENTER);
		this.panel.add(rightContent, BorderLayout.WEST);
		System.out.println("Page des scores OK !");
	}

}
