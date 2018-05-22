package Pieces;

import Chess.Chess;

import java.util.ArrayList;

public class Queen extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public Queen(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }


    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        //Gets all of the row and column moves
        ArrayList<int[]> rowMoves = findMovesInRow();
        ArrayList<int[]> colMoves = findMovesInCol();
        ArrayList<int[]> diagonalMoves = findDiagonalMoves();

        //Adds all of the moves to possible moves
        possibleMoves.addAll(rowMoves);
        possibleMoves.addAll(colMoves);
        possibleMoves.addAll(diagonalMoves);

        return possibleMoves;
    }


    private ArrayList<int[]> findDiagonalMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int irow = row;
        int icolumn = column;
        while (irow < 8 && icolumn < 8) {
            irow++;
            icolumn++;
            moves.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while (irow > 0 && icolumn > 0) {
            irow--;
            icolumn--;
            moves.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while (irow < 8 && icolumn > 0) {
            irow++;
            icolumn--;
            moves.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while (irow > 0 && icolumn < 8) {
            irow--;
            icolumn++;
            moves.add(new int[]{irow, icolumn});
        }
        return moves;
    }


    private ArrayList<int[]> findMovesInRow() {
        //Stores all of the moves in the row
        ArrayList<int[]> moves = new ArrayList<>();

        //Loops over all of the row and finds the moves that the piece can go
        for (int col = 0; col < 8; col++) {
            if (Chess.chessBoard[row][col] == 0) {
                moves.add(new int[] {row, col});
            } else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == -1) {
                moves.add(new int[] {row, col});
            } else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == 1) {
                moves.add(new int[] {row, col});
            }
        }

        return moves;
    }


    private ArrayList<int[]> findMovesInCol() {
        //Stores all of the moves in the column
        ArrayList<int[]> moves = new ArrayList<>();

        //Loops over all of the column and finds the moves that the piece can go
        for (int col = 0; col < 8; col++) {
            if (Chess.chessBoard[row][col] == 0) {
                moves.add(new int[] {row, col});
            } else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == -1) {
                moves.add(new int[] {row, col});
            } else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == 1) {
                moves.add(new int[] {row, col});
            }
        }

        return moves;
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
    }
}
