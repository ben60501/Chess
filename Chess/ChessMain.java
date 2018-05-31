package Chess;

import javax.swing.*;

public class ChessMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Chess draw = new Chess();

        frame.add(draw);
        frame.setSize(800, 820);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
