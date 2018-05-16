package Pieces;

public class GamePiece {
    public enum Color {
        Black, Red
    }

    protected int row;
    protected int column;
    protected Color pieceColor;
    protected boolean isSelected;


    public GamePiece(int row, int column, Color pieceColor, boolean isSelected) {
        this.row = row;
        this.column = column;
        this.pieceColor = pieceColor;
        this.isSelected = isSelected;
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
}
