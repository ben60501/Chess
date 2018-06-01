package Pieces;

import Chess.Chess;

import java.util.ArrayList;

public class Pawn extends GamePiece {
     private ArrayList<int[]> possibleMoves;

    private boolean hasMoved = false;

    public Pawn(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        if (pieceColor == GamePiece.Color.Black) {
            if (row > 0 && Chess.chessBoard[row - 1][column] == 0) {
                possibleMoves.add(new int[] {row - 1, column});
            }

            if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0 && Chess.chessBoard[row - 1][column] == 0) {
                possibleMoves.add(new int[] {row - 2, column});
            }

            if (row > 0 && column < 7 && Chess.chessBoard[row - 1][column + 1] == -1) {
                possibleMoves.add(new int[] {row - 1, column + 1});
            }

            if (row > 0 && column > 0 && Chess.chessBoard[row - 1][column - 1] == -1) {
                possibleMoves.add(new int[] {row - 1, column - 1});
            }

        } else {
            if (row < 7 && Chess.chessBoard[row + 1][column] == 0) {
                possibleMoves.add(new int[] {row + 1, column});
            }

            if (row < 6 && !hasMoved && Chess.chessBoard[row + 2][column] == 0 && Chess.chessBoard[row + 1][column] == 0) {
                possibleMoves.add(new int[] {row + 2, column});
            }

            if (row < 7 && column < 7 && Chess.chessBoard[row + 1][column + 1] == 1) {
                possibleMoves.add(new int[] {row + 1, column + 1});
            }

            if (row < 7 && column > 0 && Chess.chessBoard[row + 1][column - 1] == 1) {
                possibleMoves.add(new int[] {row + 1, column - 1});
            }
        }

        return possibleMoves;
    }

    public void moveToCoordinate(int row, int column) {
        Chess.chessBoard[this.row][this.column] = 0;
        ArrayList<GamePiece> temp = new ArrayList<>();

        for (GamePiece piece: Chess.pieces) {
            if (!(piece.getRow() == row && piece.getColumn() == column)) {
                temp.add(piece);
            }
        }

        Chess.pieces = temp;

        if (this.pieceColor == GamePiece.Color.Black) {
            Chess.chessBoard[row][column] = 1;
        } else {
            Chess.chessBoard[row][column] = -1;
        }

        this.row = row;
        this.column = column;

        hasMoved = true;
    }
    
}
