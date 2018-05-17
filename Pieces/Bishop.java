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
        //make an ArrayList for spotsToLook
        //for loops, loop over each diagonal, have 4 loops
        //add each spot into the spotsToLook
        possibleMoves = new ArrayList<>();
        int[][] spotsToLook = {{row - 1, column - 1}, {row - 1, column + 1}, {row + 1, column - 1},
                {row + 1, column + 1}};
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
