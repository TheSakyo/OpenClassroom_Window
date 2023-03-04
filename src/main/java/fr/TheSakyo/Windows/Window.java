package fr.TheSakyo.Windows;

import javax.swing.*;

public class Window extends JFrame {

    public Window(int width, int heigth) {

        this.setTitle("Title");
        this.setSize(width, heigth);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

