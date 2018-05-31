package Pieces;

import Chess.Chess;
import Pieces.GamePiece.Color;

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
        int[][] spotsToLook = {
                {row + 2 , column - 1},
                {row + 2, column + 1},
                {row -2, column - 1},
                {row -2, column + 1},
                {row + 1, column - 2},
                {row + 1, column + 2},
                {row - 1, column - 2},
                {row - 1, column + 2}};
        	
        int row;
        int column;
        // looks through all the spots and adds spots the piece can move to the arrayList
        for(int[] coordinate: spotsToLook){
            row = coordinate[0];
            column = coordinate[1];
            if (row >= 0 && row <= 7 && column >= 0 && column <= 7)
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
           /* else if (row >= 1 && row <= 6 && column >= 2 && column <= 5)
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
            }*/
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
