package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.observer;

import fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model.Score;

public interface Observer {

	public void update(String mot, int pts, String imgPath, int nbreMot);
	public void restart(String word);
	public void showScore(Score[] list);
	public void home();
}
