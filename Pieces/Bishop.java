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
        ArrayList<int[]> spotsToLook = new ArrayList<>();
        int irow = row;
        int icolumn = column;
        while(Chess.chessBoard[irow + 1][icolumn + 1] < 8 && Chess.chessBoard[irow + 1][icolumn + 1] < 8){
            irow++;
            icolumn++;
            spotsToLook.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while(Chess.chessBoard[irow - 1][icolumn - 1] < 8 && Chess.chessBoard[irow - 1][icolumn - 1] < 8){
            irow--;
            icolumn--;
            spotsToLook.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while(Chess.chessBoard[irow + 1][icolumn - 1] < 8 && Chess.chessBoard[irow + 1][icolumn - 1] < 8){
            irow++;
            icolumn--;
            spotsToLook.add(new int[]{irow, icolumn});
        }
        irow = row;
        icolumn = column;
        while(Chess.chessBoard[irow - 1][icolumn + 1] < 8 && Chess.chessBoard[irow - 1][icolumn + 1] < 8){
            irow--;
            icolumn++;
            spotsToLook.add(new int[]{irow, icolumn});
        }

        int row;
        int column;
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
}
