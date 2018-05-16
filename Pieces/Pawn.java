package Pieces;

import java.util.ArrayList;

public class Pawn extends GamePiece{
    private ArrayList<int[]> possibleMoves;

    public Pawn(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        return possibleMoves;
    }
}
