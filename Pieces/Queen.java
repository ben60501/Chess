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
        while(irow <= 6 && icolumn <= 6){
            irow++;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // top left diagonal
        irow = row;
        icolumn = column;
        while(irow >= 1 && icolumn >= 1){
            irow--;
            icolumn--;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 0;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 0;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // bottom left diagonal
        irow = row;
        icolumn = column;
        while(irow <= 6 && icolumn >= 1){
            irow++;
            icolumn--;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 0;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 0;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        // top right diagonal
        irow = row;
        icolumn = column;
        while(irow > 1 && icolumn <= 6){
            irow--;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else
                moves.add(new int[]{irow, icolumn});
        }
        return moves;
    }


    private ArrayList<int[]> findMovesInRow() {
        //Stores all of the moves in the row
        ArrayList<int[]> moves = new ArrayList<>();

        //moves to the left of the piece
        int col = column;
        while (col > 0) {
            if (Chess.chessBoard[row][col] == 0) {
                moves.add(new int[]{row, col});
                col--;
            }else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == 1){
                col = 0;
            }else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == -1){
                col = 0;
            }else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == 1){
                moves.add(new int[]{row, col});
                col = 0;
            }else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == -1){
                moves.add(new int[]{row, col});
                col = 0;
            }
        }
        //moves to the right of the piece
        col = column;
        while (col < 8) {
            if (Chess.chessBoard[row][col] == 0) {
                moves.add(new int[]{row, col});
                col++;
            }else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == 1){
                col = 8;
            }else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == -1){
                col = 8;
            }else if (pieceColor == Color.Black && Chess.chessBoard[row][col] == 1){
                moves.add(new int[]{row, col});
                col = 8;
            }else if (pieceColor == Color.Red && Chess.chessBoard[row][col] == -1){
                moves.add(new int[]{row, col});
                col = 8;
            }
        }
        return moves;
    }


    private ArrayList<int[]> findMovesInCol() {
        //Stores all of the moves in the column
        ArrayList<int[]> moves = new ArrayList<>();

        //finds moves above piece
        int irow = row - 1;
        while (irow > 0) {
            if (Chess.chessBoard[irow][column] == 0) {
                moves.add(new int[]{irow, column});
                irow--;
            } else if (pieceColor == Color.Black && Chess.chessBoard[irow][column] == 1) {
                irow = 0;
            } else if (pieceColor == Color.Red && Chess.chessBoard[irow][column] == -1) {
                irow = 0;
            } else if (pieceColor == Color.Black && Chess.chessBoard[irow][column] == 1) {
                moves.add(new int[]{irow, column});

                irow = 0;
            } else if (pieceColor == Color.Red && Chess.chessBoard[irow][column] == -1) {
                moves.add(new int[]{irow, column});

                irow = 0;
            }
        }

        //finds moves below piece
        irow = row + 1;
        while (irow < 8) {
            if (Chess.chessBoard[irow][column] == 0) {
                moves.add(new int[]{irow, column});
                irow++;
            } else if (pieceColor == Color.Black && Chess.chessBoard[irow][column] == 1) {
                irow = 8;
            } else if (pieceColor == Color.Red && Chess.chessBoard[irow][column] == -1) {
                irow = 8;
            } else if (pieceColor == Color.Black && Chess.chessBoard[irow][column] == 1) {
                moves.add(new int[]{irow, column});
                irow = 8;
            } else if (pieceColor == Color.Red && Chess.chessBoard[irow][column] == -1) {
                moves.add(new int[]{irow, column});
                irow = 8;
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
