package Pieces;

import Chess.Chess;

import java.util.ArrayList;

public class Bishop extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public Bishop(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();
        ArrayList<int[]> spotsToLook = spotsToLook();
        possibleMoves.addAll(spotsToLook);
        int row;
        int column;
        // looks through all the spots and adds spots the piece can move to the arrayList
            for(int[] coordinate: spotsToLook){
                row = coordinate[0];
                column = coordinate[1];
                if(Chess.chessBoard[row][column] == 0){
                    possibleMoves.add(coordinate);
                }
                else if(pieceColor == Color.Black && Chess.chessBoard[row][column] == -1){
                    possibleMoves.add(coordinate);
                }
                else if(pieceColor == Color.Red && Chess.chessBoard[row][column] == 1){
                    possibleMoves.add(coordinate);
                }
        }
        return possibleMoves;
    }


    private ArrayList<int[]> spotsToLook(){
        ArrayList<int[]> spotsToLook = new ArrayList<>(); // arrayList for all the spots to look at
        int irow = row;       // the row that the spot is in
        int icolumn = column; // the column that the spot is in

        // bottom right diagonal
        while(irow <= 6 && icolumn <= 6){
            irow++;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else
            spotsToLook.add(new int[]{irow, icolumn});
        }
        // top left diagonal
        irow = row;
        icolumn = column;
        while(irow >= 1 && icolumn >= 1){
            irow--;
            icolumn--;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 0;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else
            spotsToLook.add(new int[]{irow, icolumn});
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
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 0;
            }else
            spotsToLook.add(new int[]{irow, icolumn});
        }
        // top right diagonal
        irow = row;
        icolumn = column;
        while(irow >= 1 && icolumn <= 6){
            irow--;
            icolumn++;
            if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == -1){
                icolumn = 7;
            }
            else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == 1){
                icolumn = 7;
            }else if(pieceColor == Color.Red && Chess.chessBoard[irow][icolumn] == 1){
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else if(pieceColor == Color.Black && Chess.chessBoard[irow][icolumn] == -1) {
                spotsToLook.add(new int[]{irow, icolumn});
                icolumn = 7;
            }else
            spotsToLook.add(new int[]{irow, icolumn});
        }
        return spotsToLook;
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
    }
}
