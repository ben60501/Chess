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

    public static ArrayList<GamePiece> pieces = new ArrayList<>();

    private int selectedRow;
    private int selectedColumn;
    private boolean isSelected = false;
    private GamePiece.Color turn = GamePiece.Color.Black;

    private ArrayList<int[]> possibleMoves;

    Chess() {
        addMouseListener(new Chess.ChessMouseListener());

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

        //Draw Pieces and check for game over
        boolean hasFoundBlackKing = false;
        boolean hasFoundWhiteKing = false;
        for (GamePiece piece: pieces) {
            String stringPiece = piece.toString();
            stringPiece = stringPiece.substring(7, stringPiece.indexOf("@"));
            String imagePath = "ChessSprites/";

            if (piece.getPieceColor() == GamePiece.Color.Black) {
                imagePath += "Black" + stringPiece + ".png";
                if (stringPiece.equals("King")) {
                    hasFoundBlackKing = true;
                }
            } else {
                imagePath += "White" + stringPiece + ".png";
                if (stringPiece.equals("King")) {
                    hasFoundWhiteKing = true;
                }
            }

            URL pieceURL = getClass().getResource(imagePath);
            ImageIcon image = new ImageIcon(pieceURL);
            image.paintIcon(this, g, piece.getColumn() * 100, piece.getRow() * 100);
        }

        if (!hasFoundBlackKing) {
            System.out.println("Black Looses");
        } else if (!hasFoundWhiteKing) {
            System.out.println("White Looses");
        }

        for (GamePiece piece: pieces) {
            if (piece.isSelected()) {
                g.setColor(Color.RED);
                g.drawRect(selectedColumn * 100, selectedRow * 100, 100, 100);

                g.setColor(Color.GREEN);

                possibleMoves = new ArrayList<>();
                possibleMoves = piece.getPossibleMoves();

                for (int[] possibleMove: possibleMoves) {
                    g.drawRect(possibleMove[1] * 100, possibleMove[0] * 100, 100, 100);
                }
            }
        }

    }

    //To let the player select the piece
    private void selectPiece() {
        for (GamePiece piece: pieces) {
            if (piece.getRow() == selectedRow && piece.getColumn() == selectedColumn && piece.getPieceColor() == turn) {
                piece.changeSelected(true);
            } else {
                piece.changeSelected(false);
            }
        }

        isSelected = true;
    }

    private void moveSelectedPieceToCoordinate(int row, int column) {
        for (GamePiece piece: pieces) {
            if (piece.isSelected()) {
                piece.moveToCoordinate(row, column);
                piece.changeSelected(false);
            }
        }

        if (turn == GamePiece.Color.Black) {
            turn = GamePiece.Color.Red;
        } else {
            turn = GamePiece.Color.Black;
        }
    }

    private boolean checkIfInPossibleMoves(int row, int column) {
        for (int[] possibleMove: possibleMoves) {
            if (possibleMove[0] == row && possibleMove[1] == column) {
                return true;
            }
        }

        return false;
    }

    private class ChessMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            int currentSelectedRow = y / 100;
            int currentSelectedColumn = x / 100;

            if (isSelected) {
                if (turn == GamePiece.Color.Black && chessBoard[currentSelectedRow][currentSelectedColumn] == 1) {
                    selectedRow = currentSelectedRow;
                    selectedColumn = currentSelectedColumn;
                    selectPiece();
                } else if (turn == GamePiece.Color.Red && chessBoard[currentSelectedRow][currentSelectedColumn] == -1) {
                    selectedRow = currentSelectedRow;
                    selectedColumn = currentSelectedColumn;
                    selectPiece();
                }

                if ((chessBoard[currentSelectedRow][currentSelectedColumn] == 0 ||
                        (turn == GamePiece.Color.Black && chessBoard[currentSelectedRow][currentSelectedColumn] == -1) ||
                        (turn == GamePiece.Color.Red && chessBoard[currentSelectedRow][currentSelectedColumn] == 1)) &&
                        checkIfInPossibleMoves(currentSelectedRow, currentSelectedColumn)) {
                    moveSelectedPieceToCoordinate(currentSelectedRow, currentSelectedColumn);
                    isSelected = false;
                }
            } else {
                if (turn == GamePiece.Color.Black && chessBoard[currentSelectedRow][currentSelectedColumn] == 1 ||
                        turn == GamePiece.Color.Red && chessBoard[currentSelectedRow][currentSelectedColumn] == -1) {
                    selectedRow = currentSelectedRow;
                    selectedColumn = currentSelectedColumn;
                    selectPiece();
                }
            }

            repaint();
        }

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }
    }
}
