package Pieces;

import Chess.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends GamePiece {
     private ArrayList<int[]> possibleMoves;

    public ImageIcon image;

    private boolean hasMoved = false;
    
    private final int moveAmount = 1;

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

            if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0) {
                possibleMoves.add(new int[] {row - 2, column});}
        } else {
            if (row > 0 && row < 6 && Chess.chessBoard[row + 1][column] == 0) {
                possibleMoves.add(new int[] {row + 1, column});
            }

            if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0) {
                possibleMoves.add(new int[] {row + 2, column});
            }
        }

        return possibleMoves;
    }

    public void moveToCoordinate(int row, int column) {
        Chess.chessBoard[this.row][this.column] = 0;
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
