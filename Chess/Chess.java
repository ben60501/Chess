package Chess;

import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

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

    private ArrayList<GamePiece> pieces = new ArrayList<>();

    Chess() {
        //Create Pieces
        pieces.add(new Castle(7, 0, GamePiece.Color.Black, false));
        pieces.add(new Knight(7, 1, GamePiece.Color.Black, false));
        pieces.add(new Bishop(7, 2, GamePiece.Color.Black, false));
        pieces.add(new Queen(7, 3, GamePiece.Color.Black, false));
        pieces.add(new King(7, 4, GamePiece.Color.Black, false));
        pieces.add(new Bishop(7, 5, GamePiece.Color.Black, false));
        pieces.add(new Knight(7, 6, GamePiece.Color.Black, false));
        pieces.add(new Castle(7, 7, GamePiece.Color.Black, false));
        for (int col = 0; col < 8; col++) {
            pieces.add(new Pawn(6, col, GamePiece.Color.Black, false));
        }

        pieces.add(new Castle(0, 0, GamePiece.Color.Red, false));
        pieces.add(new Knight(0, 1, GamePiece.Color.Red, false));
        pieces.add(new Bishop(0, 2, GamePiece.Color.Red, false));
        pieces.add(new Queen(0, 3, GamePiece.Color.Red, false));
        pieces.add(new King(0, 4, GamePiece.Color.Red, false));
        pieces.add(new Bishop(0, 5, GamePiece.Color.Red, false));
        pieces.add(new Knight(0, 6, GamePiece.Color.Red, false));
        pieces.add(new Castle(0, 7, GamePiece.Color.Red, false));
        for (int col = 0; col < 8; col++) {
            pieces.add(new Pawn(1, col, GamePiece.Color.Red, false));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Create Board
        setBackground(new Color(210, 180, 140));

        for (int row = 0; row < 8; row++) {
            for (int col = row % 2; col < 8; col += 2) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(col * 100, row * 100, 100, 100);
            }
        }

        //Draw Pieces
        for (GamePiece piece: pieces) {
            String stringPiece = piece.toString();
            stringPiece = stringPiece.substring(7, stringPiece.indexOf("@"));
            String imagePath = "ChessSprites/";

            if (piece.getPieceColor() == GamePiece.Color.Black) {
                imagePath += "Black" + stringPiece + ".png";
            } else {
                imagePath += "White" + stringPiece + ".png";
            }

            URL pieceURL = getClass().getResource(imagePath);
            ImageIcon image = new ImageIcon(pieceURL);
            image.paintIcon(this, g, piece.getColumn() * 100, piece.getRow() * 100);
        }


    }
    private int selectedRow;
    private int selectedColumn;
    private boolean isSelected = false; // go through all pieces first
    // create a turn variable
    private void selectPiece() {
        for (GamePiece piece: pieces) {
            if (piece.getRow() == selectedRow && piece.getColumn() == selectedColumn /*&& piece.getPieceColor() == turn*/) {
                piece.changeSelected(true);
            } else {
                piece.changeSelected(false);
            }
        }

        isSelected = true;
    }
    private class CheckersMouseListener implements MouseListener
    {

        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            selectedRow = x;
            selectedColumn = y;
            repaint();
        }

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }
    }
}
