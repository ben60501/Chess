package Chess;

import javax.swing.*;
import java.awt.*;

public class Chess extends JPanel {

    public static int[][] chessBoard = {
            {-1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
