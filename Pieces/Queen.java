package Pieces;

import java.util.ArrayList;

public class Queen extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public Queen(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        return possibleMoves;
    }
}
