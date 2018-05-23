package Pieces;

import java.util.ArrayList;

public class GamePiece {
    public enum Color {
        Black, Red
    }

    protected int row;
    protected int column;
    protected Color pieceColor;
    protected boolean isSelected;
    protected ArrayList<int[]> getPossibleMoves;


    public GamePiece(int row, int column, Color pieceColor, boolean isSelected) {
        this.row = row;
        this.column = column;
        this.pieceColor = pieceColor;
        this.isSelected = isSelected;
        getPossibleMoves = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Color getPieceColor() {
        return pieceColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void changeSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ArrayList<int[]> getPossibleMoves(){
        return getPossibleMoves;
    }
}
