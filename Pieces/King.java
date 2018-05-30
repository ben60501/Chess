package Pieces;

import Chess.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class King extends GamePiece {
    private ArrayList<int[]> possibleMoves;

    public ImageIcon image;

    public King(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();
        int[][] spotsToLook = {
                {row - 1, column - 1}, // bottom three
                {row - 1, column},
                {row - 1, column + 1},
                {row, column - 1},     // middle two
                {row, column + 1},
                {row + 1, column - 1}, // top three
                {row + 1, column},
                {row + 1, column + 1}};

        int row;
        int column;
        // looks through all the spots and adds spots the piece can move to the arrayList
        for(int[] coordinate: spotsToLook){
            row = coordinate[0];
            column = coordinate[1];
            if (row >= 0 && row <= 7 && column > -1 && column <= 7)
            {
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
        }
        return possibleMoves;
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
