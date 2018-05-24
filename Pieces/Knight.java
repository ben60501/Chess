package Pieces;

import Chess.Chess;

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
        possibleMoves = new ArrayList<>();
        int[][] spotsToLook = new int[8][];

        int i = 0;
        while(i<=8) {
            if (row >= 2 && row <= 5 && column >= 1 && column <= 6) { //row 2, column 1
                spotsToLook[i] = new int[]{row + 2, column + 1};
                i++;
                spotsToLook[i] = new int[]{row + 2, column - 1};
                i++;
                spotsToLook[i] = new int[]{row - 2, column + 1};
                i++;
                spotsToLook[i] = new int[]{row - 2, column - 1};
                i++
            }if (row >= 1 && row <= 6 && column >= 2 && column <= 5)
            {
                spotsToLook[i] = new int[]{row + 1, column + 2};
                i++;
                spotsToLook[i] = new int[]{row - 1, column + 2};
                i++;
                spotsToLook[i] = new int[]{row + 1, column - 2};
                i++;
                spotsToLook[i] = new int[]{row - 1, column - 2};
            }

        }
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
}
