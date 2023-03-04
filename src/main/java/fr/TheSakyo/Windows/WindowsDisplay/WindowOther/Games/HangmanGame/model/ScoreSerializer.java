package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class ScoreSerializer {

	private final String GamePath = "src\\main\\resources\\HangmanGame\\";

	private Score[] listeScore = new Score[10];
	
	public ScoreSerializer() {

		try {

			File file = new File(GamePath + "dictionary\\score.scr");

			if(file.length() > 0) {

				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
				
				listeScore = (Score[])ois.readObject();
				ois.close();

			} else {

				for(int i = 0; i < 10; i++) { listeScore[i] = new Score(); }
			}
			
		} catch(FileNotFoundException e) { JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier des scores !\n"+e.getCause()+"\n", "Erreur", JOptionPane.ERROR_MESSAGE); }
		catch(IOException e) { JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier des scores !\n"+e.getCause()+"\n", "Erreur", JOptionPane.ERROR_MESSAGE); }
		catch(ClassNotFoundException e) { JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier des scores !\n"+e.getCause()+"\n", "Erreur", JOptionPane.ERROR_MESSAGE); }
	}
	
	public boolean isAccepted(Score score) {

		boolean bok = false;
				
		for(int i = 0; i < listeScore.length; i++) {

			Score scr = listeScore[i];

			System.out.println("Score : " + scr.toString());
			
			if(score.getPoint() >= scr.getPoint()) {

				bok = true;
				listeScore[i] = score;
				score = scr;
			}
		}		
		return bok;
	}
	
	public void serialize() {

		File file = new File(GamePath + "dictionary\\score.scr");

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			oos.writeObject(listeScore);
			oos.close();
			
		} catch(FileNotFoundException e) { JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier des scores !", "Erreur", JOptionPane.ERROR_MESSAGE); }
		catch(IOException e) { JOptionPane.showMessageDialog(null, "Erreur d'enregistrement dans le fichier des scores !", "Erreur", JOptionPane.ERROR_MESSAGE); }
	}
	
	public Score[] getListScore() { return this.listeScore; }
	
}
