package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observable;
import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer.Observer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Model implements Observable {

	private final String GamePath = "src\\main\\resources\\HangmanGame\\";

	private Score score;
	private final Word word;
	private ImageIcon image;
	private final ScoreSerializer scoreSerializer;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	private int numberWord = 0;
	
	public Model() {

		this.score = new Score();
		this.word = new Word();
		this.scoreSerializer = new ScoreSerializer();
		
	}

	public Score getScore(){ return score; }

	public Word getWord(){ return word; }
	
	public void assign(char c) {
		
		if(this.word.verifyWord(c) != -1) {
			
			if(this.word.isFinished()) {

				this.score.initPoint(this.word.getNumberError());
				this.score.setNumberWord(this.score.getNumberWord() + 1);

				JOptionPane.showMessageDialog(null,
						                        "Vous avez trouvé le mot " + this.word.getWord() +
						                        " en " +
						                        this.word.getNumberShot() + " coup" + ((this.word.getNumberShot() > 1) ? "s" : "") +
												", avec " + this.word.getNumberError() +
						                        " erreur" + ((this.word.getNumberError() > 1) ? "s" : "") + ".\n" +
						                        "\tVous marquez donc " +
						                        this.score.getPointMark() + " pts.\n" +
												"\tVotre avez maintenant un total de " +
												this.score.getPoint() + " pts.",
												"Résultat",
												JOptionPane.INFORMATION_MESSAGE);
				this.word.initWord();
				this.word.setNumberError(0);
				this.numberWord++;
				
				this.restartObserver();
			}

			this.notifyObserver();

		} else {

			this.word.setNumberError(this.word.getNumberError() + 1);
			this.notifyObserver();
			
			if(this.word.getNumberError() == 7) {
				
				JOptionPane.showMessageDialog(null,
						                        "Le mot était :\n\t" +
						                        this.word.getWord(),
												"Vous avez perdu", JOptionPane.INFORMATION_MESSAGE);
				
				if(this.scoreSerializer.isAccepted(this.score)) {

					String name  = JOptionPane.showInputDialog(null,
										"Entrez votre pseudonyme :", "Félicitations",
										JOptionPane.QUESTION_MESSAGE);
					
					this.score.setName(name);
					this.scoreSerializer.serialize();
					this.scoreObserver();

				} else {

					JOptionPane.showMessageDialog(null,
							"""
									Désolé, vous avez perdu


									\tVous n'avez malheureusement pas assez de points pour enregistrer votre score.
									Retentez votre chance à l'occasion !""",
				            "Vous avez perdu", JOptionPane.INFORMATION_MESSAGE);

					this.homeObserver();
				}			
			} else this.notifyObserver();

		}			
	}
	
	public void assign(char[] c) {

		if(this.word.verifyWord(c) != -1) {
			
			if(this.word.isFinished()) {

				this.score.initPoint(this.word.getNumberError());
				this.score.setNumberWord(this.score.getNumberWord() + 1);

				JOptionPane.showMessageDialog(null,
						                        "Vous avez trouvé le mot " + this.word.getWord() +
						                        " en " +
						                        this.word.getNumberShot() + " coup" + ((this.word.getNumberShot() > 1) ? "s" : "") +
												", avec " + this.word.getNumberError() +
						                        " erreur" + ((this.word.getNumberError() > 1) ? "s" : "") + ".\n" +
						                        "\tVous marquez donc " +
						                        this.score.getPointMark() + " pts.\n" +
												"\tVotre avez maintenant un total de " +
												this.score.getPoint() + " pts.",
												"Résultat",
												JOptionPane.INFORMATION_MESSAGE);
				this.word.initWord();
				this.word.setNumberError(0);
				this.numberWord++;
				this.restartObserver();

			} else this.notifyObserver();

		} else {

			this.word.setNumberError(this.word.getNumberError() + 1);
			this.notifyObserver();
			
			if(this.word.getNumberError() == 7) {
				
				JOptionPane.showMessageDialog(null,
						                        "Le mot était :\n\t" +
						                        this.word.getWord(),
												"Vous avez perdu", JOptionPane.INFORMATION_MESSAGE);
				
				if(this.scoreSerializer.isAccepted(this.score)) {

					String name  = JOptionPane.showInputDialog(null,
										"Entrez votre pseudonyme :", "Félicitations",
										JOptionPane.QUESTION_MESSAGE);
					 
					this.score.setName(name);
					this.scoreSerializer.serialize();
					this.scoreObserver();

				} else {

					JOptionPane.showMessageDialog(null,
							"""
									Désolé, vous avez perdu


									\tVous n'avez malheureusement pas assez de points pour enregistrer votre score.
									Retentez votre chance à l'occasion !""",
				            "Vous avez perdu", JOptionPane.INFORMATION_MESSAGE);

					this.homeObserver();
				}

				this.word.initWord();
				this.word.setNumberError(0);

			} else this.notifyObserver();
		}		
	}

	public void reset() {

		this.word.initWord();
		this.word.setNumberError(0);
		this.numberWord = 0;
		this.score = new Score();
	}
	
	public void addObserver(Observer obs) {

		this.listObserver.add(obs);
		this.notifyObserver();
	}

	public void deleteObserver() { this.listObserver = new ArrayList<Observer>(); }

	public void notifyObserver() {

		 this.image = new ImageIcon(GamePath + "images\\hangman"  + this.word.getNumberError() + ".jpg");
		 
		for(Observer obs : this.listObserver) obs.update(this.word.getSecretWord(), this.score.getPoint(), GamePath + "images\\hangman" + this.word.getNumberError() + ".jpg", this.numberWord); }

	public void restartObserver() { for(Observer obs : this.listObserver) obs.restart(this.word.getSecretWord()); }

	public void scoreObserver() { for(Observer obs : this.listObserver) obs.showScore(this.scoreSerializer.getListScore()); }
	
	public void homeObserver() { for(Observer obs : this.listObserver) obs.home(); }
	
	public Score[] getScores(){
		return this.scoreSerializer.getListScore();
	}
}
