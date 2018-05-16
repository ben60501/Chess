package Checkers;

import Pieces.CheckersPiece;
import Pieces.GamePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;


public class Checkers extends JPanel {

    public static int[][] board = {
            {11, -1, 11, -1, 11, -1, 11, -1},
            {-1, 11, -1, 11, -1, 11, -1, 11},
            {11, -1, 11, -1, 11, -1, 11, -1},
            {0, 11, 0, 11, 0, 11, 0, 11},
            {11, 0, 11, 0, 11, 0, 11, 0},
            {1, 11, 1, 11, 1, 11, 1, 11},
            {11, 1, 11, 1, 11, 1, 11, 1},
            {1, 11, 1, 11, 1, 11, 1, 11},
    };

    private ArrayList<CheckersPiece> pieces = new ArrayList<>();
    private ArrayList<int[]> possibleMoves = new ArrayList<>();

    private boolean initialized = false;
    private boolean hasClicked = false;
    private boolean isSelected = false;

    private int selectedRow = 0;
    private int selectedColumn = 0;

    private GamePiece.Color turn = GamePiece.Color.Black;

    private Winner winner = Winner.NoneYet;

    enum Winner {
        Black, Red, NoneYet
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!initialized) {
            addPieces();
            addMouseListener(new CheckersMouseListener());
            initialized = true;
        }

        checkForQueens();
        checkForGameOver();

        //Create Board
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column] == 11) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(column * 100, row * 100, 100, 100);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 40));

        if (winner == Winner.Black) {
            g.drawString("Black Wins!", 270, 415);
        } else if (winner == Winner.Red) {
            g.drawString("Red Wins!", 285, 415);
        }

        if (hasClicked && isSelected) {
            //If the selected spot has a piece on it
            if (board[selectedRow][selectedColumn] != 11 && board[selectedRow][selectedColumn] != 0) {
                GamePiece.Color boardColor;
                if (board[selectedRow][selectedColumn] == 1) {
                    boardColor = GamePiece.Color.Black;
                } else {
                    boardColor = GamePiece.Color.Red;
                }

                if (boardColor == turn) {
                    g.setColor(Color.WHITE);
                    g.fillOval(selectedColumn * 100 + 20, selectedRow * 100 + 20, 60, 60);

                    possibleMoves();

                    for (int[] coordinate : possibleMoves) {
                        g.setColor(Color.WHITE);
                        g.drawRect(coordinate[1] * 100, coordinate[0] * 100, 100, 100);
                    }
                }
            } else {
                //Deselect
                isSelected = false;
                possibleMoves = new ArrayList<>();
            }
        }

        //Daw the pieces
        URL crownURL = getClass().getResource("Checkers/Crown.png");

        for (CheckersPiece piece: pieces) {
            if (piece.getPieceColor() == GamePiece.Color.Black) {
                g.setColor(Color.GRAY);
            } else {
                g.setColor(Color.RED);
            }
            g.fillOval(piece.getColumn() * 100 + 25, piece.getRow() * 100 + 25, 50, 50);

            if (piece.isQueen()) {
                ImageIcon crown = new ImageIcon(crownURL);
                crown.paintIcon(this, g, piece.getColumn() * 100 + 25, piece.getRow() * 100 + 25);
            }
        }
    }

    private void selectPiece() {
        for (CheckersPiece piece: pieces) {
            if (piece.getRow() == selectedRow && piece.getColumn() == selectedColumn && piece.getPieceColor() == turn) {
                piece.changeSelected(true);
            } else {
                piece.changeSelected(false);
            }
        }

        isSelected = true;
    }

    private void possibleMoves() {
        possibleMoves = new ArrayList<>();

        for (CheckersPiece piece: pieces) {
            if (piece.isSelected()) {
                possibleMoves = piece.getPossibleMoves();
            }
        }
    }

    private void checkForQueens() {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getPieceColor() == GamePiece.Color.Black) {
                if (pieces.get(i).getRow() == 0) {
                    pieces.get(i).changeToQueen();
                }
            } else {
                if (pieces.get(i).getRow() == 7) {
                    pieces.get(i).changeToQueen();
                }
            }
        }
    }

    private void checkForGameOver() {
        int blackCount = 0;
        int redCount = 0;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column] == 1) {
                    blackCount++;
                } else if (board[row][column] == -1) {
                    redCount++;
                }
            }
        }

        boolean isGameOverForBlack = true;
        boolean isGameOverForRed = true;

        for (CheckersPiece piece: pieces) {
            if (piece.getPieceColor() == GamePiece.Color.Black) {
                if (piece.getPossibleMoves().size() > 0) {
                    isGameOverForBlack = false;
                }
            } else if (piece.getPieceColor() == GamePiece.Color.Red) {
                if (piece.getPossibleMoves().size() > 0) {
                    isGameOverForRed = false;
                }
            }
        }

        if (blackCount == 0 || isGameOverForBlack) {
            winner = Winner.Red;
        } else if (redCount == 0 || isGameOverForRed) {
            winner = Winner.Black;
        }
    }

    private void addPieces() {
        for (int row = 0; row < 3; row++) {
            if (row % 2 == 0) {
                for (int column = 1; column < 8; column += 2) {
                    pieces.add(new CheckersPiece(row, column, GamePiece.Color.Red, false, false));
                }
            } else {
                for (int column = 0; column < 8; column += 2) {
                    pieces.add(new CheckersPiece(row, column, GamePiece.Color.Red, false, false));
                }
            }
        }

        for (int row = 5; row < 8; row++) {
            if (row % 2 == 0) {
                for (int column = 1; column < 8; column += 2) {
                    pieces.add(new CheckersPiece(row, column, GamePiece.Color.Black, false, false));
                }
            } else {
                for (int column = 0; column < 8; column += 2) {
                    pieces.add(new CheckersPiece(row, column, GamePiece.Color.Black, false, false));
                }
            }
        }
    }

    private boolean canMoveToCoordinate() {
        boolean returnValue = false;

        for (int[] coordinates: possibleMoves) {
            if (selectedRow == coordinates[0] && selectedColumn == coordinates[1]) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    private void moveToCoordinate() {
        int indexOfPieceToMove = -1;

        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).isSelected()) {
                indexOfPieceToMove = i;
            }
        }

        if (indexOfPieceToMove != -1) {
            int pieceToRemoveRow = -1;
            int pieceToRemoveColumn = -1;

            if (Math.abs(pieces.get(indexOfPieceToMove).getColumn() - selectedColumn) == 2 &&
                    Math.abs(pieces.get(indexOfPieceToMove).getRow() - selectedRow) == 2) {
                pieceToRemoveRow = (selectedRow + pieces.get(indexOfPieceToMove).getRow()) / 2;
                pieceToRemoveColumn = (selectedColumn + pieces.get(indexOfPieceToMove).getColumn()) / 2;

                System.out.println(pieceToRemoveRow + "     " + pieceToRemoveColumn);

                board[pieceToRemoveRow][pieceToRemoveColumn] = 0;
            }

            board[pieces.get(indexOfPieceToMove).getRow()][pieces.get(indexOfPieceToMove).getColumn()] = 0;

            pieces.get(indexOfPieceToMove).setRow(selectedRow);
            pieces.get(indexOfPieceToMove).setColumn(selectedColumn);

            if (pieces.get(indexOfPieceToMove).getPieceColor() == GamePiece.Color.Black) {
                board[selectedRow][selectedColumn] = 1;
                turn = GamePiece.Color.Red;
            } else {
                board[selectedRow][selectedColumn] = -1;
                turn = GamePiece.Color.Black;
            }

            if (pieceToRemoveColumn != -1) {
                for (int i = 0; i < pieces.size(); i++) {
                    if (pieces.get(i).getRow() == pieceToRemoveRow && pieces.get(i).getColumn() == pieceToRemoveColumn) {
                        pieces.remove(i);
                    }
                }
            }

            isSelected = false;
            possibleMoves = new ArrayList<>();
        }
    }

    private class CheckersMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            selectedRow = y / 100;
            selectedColumn = x / 100;

            hasClicked = true;

            if (canMoveToCoordinate() && isSelected) {
                moveToCoordinate();
            } else {
                selectPiece();
            }

            repaint();
        }

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }
    }
}
