package Pieces;

import javax.swing.*;
import java.util.ArrayList;

public class Knight extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public ImageIcon image;

    public Knight(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        return possibleMoves;
    }
}
