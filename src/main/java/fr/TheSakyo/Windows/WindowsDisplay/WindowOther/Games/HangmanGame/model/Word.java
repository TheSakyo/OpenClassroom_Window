package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model;

import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

public class Word {

	private final String GamePath = "src\\main\\resources\\HangmanGame\\";

	private String word = "", secretWord = "";
	private char[] tabChar;
	private int error = 0;
	private int nbreCoup = 0;

	public Word() { initWord(); }

	public void initWord(){

		int i = (int)(Math.random() * 100000);

		while(i > 336529) i /= 2;

		try {

			FileInputStream file = new FileInputStream(GamePath + "dictionary\\word.txt");
			InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);

			LineNumberReader fnr = new LineNumberReader(streamReader);

			int carac;
			this.word = "";
			this.secretWord= "";

			while((carac = fnr.read()) != -1) {

				if(fnr.getLineNumber() == (i+1)) break;

				else if(fnr.getLineNumber() == i){ this.word += (char)carac;

				}
			}

			this.word = this.word.trim().toUpperCase();

			for(int j = 0; j < this.word.length(); j++) {

				this.secretWord += (this.word.charAt(j) != '\'' && this.word.charAt(j) != '-') ? "*" : this.word.charAt(j);
			}

			fnr.close();
			this.nbreCoup = 0;
		} catch(FileNotFoundException e) { JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "Erreur", JOptionPane.ERROR_MESSAGE); }
		catch(IOException e) { JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "Erreur", JOptionPane.ERROR_MESSAGE); }


		this.tabChar = this.secretWord.toCharArray();
		this.error = 0;
	}

	public int verifyWord(char c) {

		boolean bok = false;

		for(int i = 0; i < this.word.length(); i++) {

			if(this.word.toUpperCase().charAt(i) == c) {

				this.tabChar[i] = c;
				bok = true;
			}
		}
		++this.nbreCoup;

		this.secretWord = new String(this.tabChar);

		System.out.println("Mot secret = " + this.word);

		return(bok) ? 1 : -1;
	}

	public int verifyWord(char[] tc){

		boolean bok = false;

		for(char c : tc) {

			for(int i = 0; i < this.word.length(); i++) {

				if(this.word.toUpperCase().charAt(i) == c){
					this.tabChar[i] = c;
					System.out.println("OK !");
					bok = true;
				}
			}
		}
		++this.nbreCoup;

		this.secretWord = new String(this.tabChar);

		System.out.println("Mot secret = " + this.word);

		return(bok) ? 1 : -1;
	}

	public boolean isFinished() {

		for(char c : this.tabChar)  if(c == '*')  return false;

		return true;
	}

	public String getWord() { return word; }

	public String getSecretWord() { return secretWord; }

	public int getNumberError(){ return this.error; }

	public void setNumberError(int nbre){ this.error = nbre; }

	public int getNumberShot() { return nbreCoup; }
}
