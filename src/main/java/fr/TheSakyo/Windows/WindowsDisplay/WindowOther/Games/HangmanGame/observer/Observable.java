package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Score;

public interface Observable {
	public void addObserver(Observer obs);
	public void notifyObserver();
	public void restartObserver();
	public void scoreObserver();
	public void deleteObserver();
	public void homeObserver();
	public Score[] getScores();
	public void reset();
}
