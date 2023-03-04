package fr.TheSakyo.Windows.WindowsDisplay.WindowOther.Calculator.Observer;

public interface Observable {

    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(String str);
}
