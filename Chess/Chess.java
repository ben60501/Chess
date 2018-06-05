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

        King blackKing = (King) pieces.get(4);
        King whiteKing = (King) pieces.get(20);

        blackKing.castlingPair = (Castle) pieces.get(7);
        whiteKing.castlingPair = (Castle) pieces.get(23);
        System.out.println(pieces.get(23));

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
            String stringPiece = getPieceType(piece);
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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 40));

        //Checks for Game Over
        if (!hasFoundBlackKing) {
            g.drawString("White Wins!", 270, 415);
        } else if (!hasFoundWhiteKing) {
            g.drawString("Black Wins!", 270, 415);
        }

        //Checks for pawns that can be changed to queens
        checkPawnsToBeReplaced();

        //Draws the box around the selected piece and possible moves
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

                if (getPieceType(piece).equals("Castle")) {
                    Castle castle = (Castle) piece;
                    if (castle.getPieceColor() == GamePiece.Color.Black && castle.getRow() == 7 && castle.getColumn() == 7) {
                        if (!castle.canCastle) {
                            changeKingToNotCastle(GamePiece.Color.Black);
                        }
                    } else if (castle.getPieceColor() == GamePiece.Color.Red && castle.getRow() == 0 && castle.getColumn() == 0) {
                        if (!castle.canCastle) {
                            changeKingToNotCastle(GamePiece.Color.Red);
                        }
                    }
                }

            }
        }

    }

    private void changeKingToNotCastle(GamePiece.Color pieceColor) {
        for (GamePiece piece: pieces) {
            if (piece.getPieceColor() == pieceColor && getPieceType(piece).equals("King")) {
                King king = (King) piece;
                king.canCastle = false;
            }
        }
    }

    private void checkPawnsToBeReplaced() {
        boolean isBlackQueen = false;
        boolean isWhiteQueen = false;

        for (GamePiece piece: pieces) {
            if (getPieceType(piece).equals("Queen")) {
                if (piece.getPieceColor() == GamePiece.Color.Black) {
                    isBlackQueen = true;
                } else {
                    isWhiteQueen = true;
                }
            }
        }

        for (GamePiece piece: pieces) {
            if (getPieceType(piece).equals("Pawn")) {
                if (piece.getPieceColor() == GamePiece.Color.Black && !isBlackQueen && piece.getRow() == 0) {
                    replacePawnWithQueen(piece);
                } else if (piece.getPieceColor() == GamePiece.Color.Red && !isWhiteQueen && piece.getRow() == 7) {
                    replacePawnWithQueen(piece);
                }
            }
        }
    }

    private void replacePawnWithQueen(GamePiece pawn) {
        GamePiece queen = new Queen(pawn.getRow(), pawn.getColumn(), pawn.getPieceColor(), pawn.isSelected());
        pieces.add(queen);
        pieces.remove(pawn);
    }


    private String getPieceType(GamePiece piece) {
        return piece.toString().substring(7, piece.toString().indexOf("@"));
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