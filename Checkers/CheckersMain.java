package Checkers;

import javax.swing.*;

public class CheckersMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Checkers draw = new Checkers();

        frame.add(draw);
        frame.setSize(800, 820);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
