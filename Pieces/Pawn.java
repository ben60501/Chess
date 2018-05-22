package Pieces;

import Chess.Chess;

import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends GamePiece {
     private ArrayList<int[]> possibleMoves;

    public ImageIcon image;

    private boolean hasMoved = false;
    
    private final int moveAmount = 1;

    public Pawn(int row, int column, Color pieceColor, boolean isSelected) {
        super(row, column, pieceColor, isSelected);

        possibleMoves = new ArrayList<>();
    }

    public ArrayList<int[]> getPossibleMoves() {
        possibleMoves = new ArrayList<>();

        //checking if the pawn can move straight above
        if (pieceColor == Color.Black) {
            if (row > 0 && Chess.chessBoard[row - 1][column] == 0) {
                possibleMoves.add(new int[] {row - 1, column});
            }
            else if (row > 0 && (Chess.chessBoard[row -1][column -1] == -1 || Chess.chessBoard[row-1][column +1] == -1))
            {
            		if (Chess.chessBoard[row -1][column -1] == -1)
            		{
            			possibleMoves.add(new int[] {row - 1, column-1});
            		}
            		else if (Chess.chessBoard[row-1][column +1] == -1)
            		{
            			possibleMoves.add(new int[] {row - 1, column+1});
            		}
            			
            }
            //if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0) {
                //possibleMoves.add(new int[] {row - 2, column});}
        } 
    		else {
        	 if (row > 0 && row < 6 && Chess.chessBoard[row + 1][column] == 0) {
                 possibleMoves.add(new int[] {row + 1, column});
             }

        	 else if (row > 0 && row < 6 && (Chess.chessBoard[row +1][column +1] == -1 || Chess.chessBoard[row+1][column -1] == -1))
             {
             		if (Chess.chessBoard[row +1][column +1] == -1)
             		{
             			possibleMoves.add(new int[] {row + 1, column + 1});
             		}
             		else if (Chess.chessBoard[row+1][column -1] == -1)
             		{
             			possibleMoves.add(new int[] {row + 1, column - 1});
             		}
             			
             }
        	 
            /* if (row > 1 && !hasMoved && Chess.chessBoard[row - 2][column] == 0) {
                 possibleMoves.add(new int[] {row - 2, column});
             }*/
        }

        return possibleMoves;
    }
    
}
