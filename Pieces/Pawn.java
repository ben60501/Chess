package Pieces;

import Chess.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public ImageIcon image;

    private boolean hasMoved = false;

    public Pawn(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        if (pieceColor == Color.Black) {
            if (row > 0 && Chess.chessBoard[row - 1][column] == 0) {
                possibleMoves.add(new int[] {row - 1, column});
            }

            if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0) {
                possibleMoves.add(new int[] {row - 2, column});
            }
        } else {

        }

        return possibleMoves;
    }
}
