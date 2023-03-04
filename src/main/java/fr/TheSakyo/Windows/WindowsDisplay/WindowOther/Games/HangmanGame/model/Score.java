package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Games.HangmanGame.model;

import java.io.Serializable;

public class Score implements Serializable {

  private String name;
  private int point, pointMark;
  private int nbreWord;

  public Score(){
	    name = "........";
	    point = 0;
	    nbreWord = 0;
  }

  public Score(String name, int point, int nbreWord) {

	  this.name = name;
	  this.point = point;
	  this.nbreWord = nbreWord;
  }

  public int getPoint(){
	  return point;
  }
  
  public void initPoint(int nbError) {

	  switch(nbError) {

	      case 0:

	          this.point += 100;
	          this.pointMark = 100;
	          break;
	
	      case 1:

	    	  this.point += 50;
	    	  this.pointMark = 50;
	          break;
	
	      case 2:

	    	  this.point += 35;
	    	  this.pointMark = 35;
	          break;
	
	      case 3:

	    	  this.point += 25;
	    	  this.pointMark = 25;
	          break;
	
	      case 4:

	    	  this.point += 15;
	    	  this.pointMark = 15;
	          break;
	
	      case 5:

	    	  this.point += 10;
	    	  this.pointMark = 10;
	          break;
	
	      case 6:

	    	  this.point += 5;
	    	  this.pointMark = 5;
	          break;
	
	      default: this.point += 0;
      }
  }

  public String getName() { return name; }
  
  public void setName(String name) { this.name = (name != null) ? name : "........"; }

  public void setNumberWord(int nbre) { this.nbreWord = nbre; }
  
  public int getNumberWord() { return this.nbreWord; }
  
  public String toString() { return " "+name+" : "+point+" pts ("+nbreWord+" mot"+((nbreWord > 1) ? "s" : "")+")"; }

  public int getPointMark() { return pointMark; }

}
